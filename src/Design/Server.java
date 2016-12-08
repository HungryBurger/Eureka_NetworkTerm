package Design;
import java.awt.*;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;
import java.util.Random;

public class Server extends JFrame implements ActionListener {
	// jdbc ����

	private String url = "jdbc:mysql://127.0.0.1/network";// user���̺��� �����ϸ�
	private String strUser = "root"; // ���� id
	private String strPassword = "12345"; // ���� �н�����
	private String strMySQLDriver = "com.mysql.jdbc.Driver"; // ����̹� �̸� ���� �������
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	String id;
	String pass;
	String name;
	int age;
	int CHECK_FORCE=0;
	// �α��ΰ� �ɹ� Ŭ������ �����´�.
	member sumit = new member(); // false
	Login log = new Login(); // true
	Data d = new Data();
	// ���̾�α� , ��ư, ��,
	Button ok;
	Label msg;
	Dialog info;
	private static final int PORT = 9000;
	private static HashSet<String> ides = new HashSet<String>();
	private static HashSet<PrintWriter> outs = new HashSet<PrintWriter>();
	private static HashMap<String,PrintWriter> map = new HashMap<String,PrintWriter>();
	private static class Handler extends Thread {
		private String Check_Class;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private String s, tmp;
		private boolean check = false;


		public Handler(Socket socket){
			this.socket = socket;
		}
		public void run() {
			try{
				String id="hello";
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				while(true){
					Check_Class=in.readLine();
					if(Check_Class.startsWith("[login]")==true){
						String id1 = in.readLine();
						String pw1 = in.readLine();
						System.out.println(id1);
						System.out.println(pw1);
						int temp=new Server().loginCheck(id1,pw1);
						if(temp==1)
							id=id1;
						out.println(temp);
					}
					else if(Check_Class.startsWith("[member]")==true){
						System.out.println("member");
						String id1 = in.readLine();
						String pw1 = in.readLine();
						String name1 = in.readLine();
						String age1 = in.readLine();
						System.out.println(id1);
						System.out.println(name1);
						System.out.println(age1);
						System.out.println(pw1);
						int temp = new Server().selectInsert(id1, pw1, name1, age1);
						out.println(temp);
						out.println("[END]");
					}
					else if(Check_Class.startsWith("[MainMenu]"))
					{
						System.out.println("Mainmenu");
						out.println("[END]");
					}
					else if(Check_Class.startsWith("[FLASH]")==true)
					{
						ArrayList<Integer> already = new ArrayList<Integer>();
						System.out.println("FLASH");

						int idx, count = 0;
						while (count < 20) {
							idx = (int)(Math.random() * 42) + 1;
							if (already.contains(idx)) {
								continue;
							}
							else {
								String quest = new Server().FindQeustion(idx);
								out.println(quest);
								String answer = new Server().FindAnswer(idx);
								out.println(answer);
								quest = new Server().FindQeustion(idx);
								out.println(quest);
								answer = new Server().FindAnswer(idx);
								out.println(answer);

								already.add(idx);
								count ++;
							} 
						}
						out.println("END");
					}

					else if(Check_Class.startsWith("[MOCK]")==true)
					{
						ArrayList<Integer> already = new ArrayList<Integer>();
						System.out.println("MOCK");
						//time�������ִ� ��� �����غ�
						int idx, cnt = 0;
						int count = 0;

						while (cnt < 20) {
							idx = (int)(Math.random() * 42) + 1;
							if (already.contains(idx)) {
								continue;
							}
							else {
								System.out.println("count ="+count);
								String quest=new Server().FindQeustion(idx);
								String quest1=new Server().select_1(idx);
								String quest2=new Server().select_2(idx);
								String quest3=new Server().select_3(idx);
								String quest4=new Server().select_4(idx);
								System.out.println(quest1);
								System.out.println(quest2);
								System.out.println(quest3);
								System.out.println(quest4);
								out.println(quest);
								out.println(quest1);
								out.println(quest2);
								out.println(quest3);
								out.println(quest4);
								String abc=in.readLine();
								String ta = new Server().Check_answer(idx);
								System.out.println("abc = "+abc);
								System.out.println("ta = "+ta);
								if(abc.equals(ta))
								{
									System.out.println("�¾Ҿ�");
									out.println("correct");
									count++;
								}
								else
								{
									System.out.println("Ʋ�Ⱦ�");
									out.println("error");
								}

								already.add(idx);
							}
							cnt ++;
						}
						out.println("[END]");
						System.out.println("END");
						out.println(count);
						System.out.println(count);
						count=0;
					}
					else if(Check_Class.startsWith("[Result]")==true)	
					{
						int total=0;
						System.out.println("Result");
						String Score=in.readLine();
						System.out.println(Score);
						out.println(Score);
						int take=Integer.parseInt(Score);
						//before
						String bf=new Server().UPDATE(id,take);
						int br=Integer.parseInt(bf);
						int sc = Integer.parseInt(Score);
						total=br+sc;
						System.out.println();
						String change= String.valueOf(total);
						out.println(total);
						out.println(bf);
						out.println(Score);
						System.out.println("h1");
						out.println("[END]");
						
						
					}
					else if(Check_Class.startsWith("[Chat]")==true)	
					{
						System.out.println("Chat");
						out.println(id);
						System.out.println(id);
					}
					else if(Check_Class.startsWith("[RANK]")==true)	
					{
						int i=0;
						System.out.println("RANK");
						for(i=0;i<5;i++){							
							String ch =new Server().RANKOUT(i);
							String[] value = ch.split(" ");
							out.println(value[0]);
							out.println(value[1]);
							System.out.println(value[0]);
							System.out.println(value[1]);
						}
						String point = new Server().FindIDPOINT(id);
						out.println(id);
						out.println(point);
						out.println("[END]");
					}
					else
					{
						System.out.println("ANotehw");
					}
				}
			}catch(IOException e){
				System.out.println(e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
	public Server() {
		try {
			// �α��� ��ư�� Ŭ�� �Ǿ����ÿ� jdbc����̹��� ����Ѵ�.
			Class.forName(strMySQLDriver);
			// DriverManager�κ��� Ŀ�ؼ��� �����µ� mysql���� . id, pw ���� ���´�.
			con = (Connection) DriverManager.getConnection(url, strUser,strPassword);
			// Ŀ�ؼ����κ��� ������ sql���� �����Ű�� ���� statement ��ü�� ���´�.
			stmt = (Statement) con.createStatement();

		} catch (Exception b) {
			System.out.println("db�������");
		}

		// ȸ������ ��Ϲ�ư ������
		sumit.btn2.addActionListener(this);// ��ư�� �����ʸ� �ܴ�.
		// ȸ������ ��� ��ư
		sumit.btn3.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// �ش��ϴ� ��ư�� ��������
		Object obj = e.getSource();

		if (obj.equals(sumit.btn2)) {
			// ȸ������ â�� ��ư ���� ��� �ϰ�� , ������ ��� �� �ش��ϴ� �޼ҵ带 ���� ���ش�.
			if (sumit.btn2.getLabel().equals("���")) {
				// ����
				sumit.setVisible(false);
			} else if (sumit.btn2.getLabel().equals("����")) {
				// ����
				selectUpdate();
			}
		} else if (obj.equals(sumit.btn3)) {
			sumit.setVisible(false);
		}
	}
	private void clearText() {
		// TODO Auto-generated method stub
		log.textField.setText("");
		log.textField_1.setText("");
		sumit.tf_id.setText("");
		sumit.tf_pass.setText("");
		sumit.tf_name.setText("");
		sumit.tf_age.setText("");
	}
	// ����
	void selectUpdate() {
		// TODO Auto-generated method stub
		Data d = new Data();
		d.id = sumit.tf_id.getText().trim();
		d.pw = sumit.tf_pass.getText().trim();
		d.name = sumit.tf_name.getText().trim();
		d.age = sumit.tf_age.getText().trim();

		String sql = "update users set id='" + d.id + "',pw='" + d.pw
				+ "' where name='" + d.name + "'";
		try {
			int rss = stmt.executeUpdate(sql);
			System.out.println(rss + "������Ʈ");
			sumit.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	// ����
	int selectInsert(String id,String pw,String name,String age) {
		// TODO Auto-generated method stub
		Data d = new Data();
		d.id = id;
		d.pw = pw;
		d.name = name;
		d.age = age;
		System.out.println(id+pw+name+age);
		String sql = "insert into users value('" + d.id + "','" + d.pw + "','"+ d.name + "',"+Integer.parseInt(d.age)+",NULL,NULL,1)";
		String sql2 = "SELECT pw,name FROM users where id='" + id + "'";
		try {
			ResultSet rs =stmt.executeQuery(sql2);
			System.out.println(sql2);
			CHECK_FORCE=0;
			if(!rs.next())
			{
				int rss = stmt.executeUpdate(sql);
				System.out.println(rss + "����");
				CHECK_FORCE=1;
			}
			else
			{
				System.out.println("�����ϴ� ���̵��Դϴ�");
				CHECK_FORCE=0;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return CHECK_FORCE;
	}
	String RANKOUT(int num)
	{
		String str="";
		String qwe="";
		String qwe1="";
		String sql1 = "select id, point from users order by point desc limit "+num+",1";
		try {
			ResultSet rs =stmt.executeQuery(sql1);
			System.out.println(sql1);
			System.out.println(rs.next());
			qwe = new String(rs.getString("id").getBytes("8859_1"),"KSC5601");
			qwe1 = new String(rs.getString("point").getBytes("8859_1"),"KSC5601");
			System.out.println(qwe);
			System.out.println(qwe1);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return qwe+" "+qwe1;
	}
	String UPDATE(String id, int Score)
	{
		int a = 0;
		String before = "";
		String sql1 = "select point from users where id='"+id+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql1);
			System.out.println(sql1);
			System.out.println(rs.next());
			String qwe = new String(rs.getString("point").getBytes("8859_1"),"KSC5601");
			if(qwe.equals("NULL"))	{
				a = 0;
			}
			else {
				System.out.println(qwe);
				a =Integer.parseInt(qwe);
				a=a+Score;
			}
			System.out.println("score"+a);
			String sql = "UPDATE users set point = '"+a+"'where id = '"+id+"'";
			int rt =stmt.executeUpdate(sql);
			System.out.println(sql);
			before= String.valueOf(qwe);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return before;
	}
	String FindIDPOINT(String id){
		String yyy=null;
		
		String sql = "select point from users where id='"+id+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			yyy = new String(rs.getString("point").getBytes("8859_1"),"KSC5601");
			System.out.println(yyy);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return yyy;
	}
	String FindQeustion(int num) {
		String qqq = "";
		String sql = "select question from history where number='"+num+"'";
		String sql1 = "select answer from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			qqq = new String(rs.getString("question").getBytes("8859_1"),"KSC5601");
			System.out.println(qqq);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return qqq;
	}
	String FindAnswer(int num) {
		String aaa = "";
		String sql = "select answer from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			String number_p = new String(rs.getString("answer").getBytes("8859_1"),"KSC5601");

			System.out.println(number_p);
			String sql1 = "select select_"+number_p+" from history where number='"+num+"'";
			rs =stmt.executeQuery(sql1);
			System.out.println(rs.next());
			aaa = new String(rs.getString("select_"+number_p+"").getBytes("8859_1"),"KSC5601");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return aaa;
	}
	String select_1(int num) {
		String ttt = "";
		String sql = "select select_1 from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			ttt = new String(rs.getString("select_1").getBytes("8859_1"),"KSC5601");
			System.out.println(ttt);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return ttt;
	}
	String select_2(int num) {
		String bbb = "";
		String sql = "select select_2 from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			bbb = new String(rs.getString("select_2").getBytes("8859_1"),"KSC5601");
			System.out.println(bbb);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return bbb;
	}
	String select_3(int num) {
		String ccc = "";
		String sql = "select select_3 from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			ccc = new String(rs.getString("select_3").getBytes("8859_1"),"KSC5601");
			System.out.println(ccc);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return ccc;
	}
	String select_4(int num) {
		String ddd = "";
		String sql = "select select_4 from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			ddd = new String(rs.getString("select_4").getBytes("8859_1"),"KSC5601");
			System.out.println(ddd);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return ddd;
	}
	String Check_answer(int num) {
		String answer = "";
		String sql = "select answer from history where number='"+num+"'";
		try {
			ResultSet rs =stmt.executeQuery(sql);
			System.out.println(sql);
			System.out.println(rs.next());
			answer = new String(rs.getString("answer").getBytes("8859_1"),"KSC5601");
			System.out.println(answer);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return answer;
	}
	// �˻�
	void showUpdate() {
		// TODO Auto-generated method stub
		// String sql = "select * from users where name='"+name+"'";
		sumit.btn3.setLabel("�������");
		sumit.btn2.setLabel("����");
		// ������ �̸��� ������ �´�.
		String id = sumit.tf_id.getText();
		String pw = sumit.tf_pass.getText();
		String sql = "select * from users where name='" + name + "'";
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				sumit.tf_id.setText(rs.getString("id"));
				sumit.tf_pass.setText(rs.getString("pw"));
				sumit.tf_name.setText(rs.getString("name"));
				sumit.tf_age.setText(rs.getString("age"));
				System.out.println(rs + "����");
			}
		} catch (Exception e) {
			System.out.println("���� �� ���� �߻� : " + e);
		}
		// ������ ���̵�� ��ġ�� ���ϵ���
		sumit.tf_id.setEnabled(false);
		sumit.setVisible(true);
	}
	// ����
	void selectDelete() {
		// TODO Auto-generated method stub

		String sql = "delete from users where name='" + name + "'";
		System.out.println(sql);
		try {

			int rss = stmt.executeUpdate(sql);

			System.out.println(rss + "����");
			// Ŀ�ؼ����κ��� ������ sql���� �����Ű�� ���� statement ��ü�� ���´�.
		} catch (Exception e) {
			System.out.println("������ ���� �߻� : " + e);
		}
	}// deleteDB

	// �α��� ü ũ
	int loginCheck(String id, String pass) {
		// SELECT ������ �ۼ��Ѵ�. �ش��ϴ� ���̵��� �н����带 �˻��Ѵ�.
		String query = "SELECT pw,name FROM users where id='" + id + "'";
		System.out.println(query);
		try {
			// executeQuery() �޼���� SELECT���� �����Ű�� ����� ResultSet ��ü�� �޴´�.
			ResultSet rs = stmt.executeQuery(query);
			// ���ڵ尡 �ִ��� �˻�
			if (rs.next()) {
				CHECK_FORCE = 0;
				name = rs.getString("name");
				// �ؽ�Ʈ�ʵ忡 ������ �����ͺ��̽��� �ִ� �н����� ���� ���Ѵ�.
				if (pass.equals(rs.getString("pw")))
				{
					System.out.println("��ġ�Ѵ�");
					CHECK_FORCE = 1;
					// ������ �α��μ��긦 ����ش�.
				}
				else
				{
					System.out.println("��ġ�����ʴ´�");
					CHECK_FORCE = 0 ;
				}
			}
		} catch (Exception b) {
			b.printStackTrace();
		}
		return CHECK_FORCE;
	}
	// ����
	public static void main(String[] args) throws Exception {
		System.out.println("The Server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while(true){
				new Server();
				new Handler(listener.accept()).start();
			}
		}finally{
			listener.close();
		}
	}
}