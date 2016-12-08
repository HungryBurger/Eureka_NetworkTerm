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
	// jdbc 설정

	private String url = "jdbc:mysql://127.0.0.1/network";// user테이블을 수정하면
	private String strUser = "root"; // 계정 id
	private String strPassword = "12345"; // 계정 패스워드
	private String strMySQLDriver = "com.mysql.jdbc.Driver"; // 드라이버 이름 따로 만들어줌
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	String id;
	String pass;
	String name;
	int age;
	int CHECK_FORCE=0;
	// 로그인과 맴버 클래스를 가져온다.
	member sumit = new member(); // false
	Login log = new Login(); // true
	Data d = new Data();
	// 다이얼로그 , 버튼, 라벨,
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
						//time설정해주는 방법 생각해봐
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
									System.out.println("맞았어");
									out.println("correct");
									count++;
								}
								else
								{
									System.out.println("틀렸어");
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
			// 로그인 버튼이 클릭 되었을시에 jdbc드라이버를 등록한다.
			Class.forName(strMySQLDriver);
			// DriverManager로부터 커넥션을 얻어오는데 mysql서버 . id, pw 등을 언어온다.
			con = (Connection) DriverManager.getConnection(url, strUser,strPassword);
			// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
			stmt = (Statement) con.createStatement();

		} catch (Exception b) {
			System.out.println("db연결실패");
		}

		// 회원가입 등록버튼 리스너
		sumit.btn2.addActionListener(this);// 버튼에 리스너를 단다.
		// 회원가입 취소 버튼
		sumit.btn3.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 해당하는 버튼값 가져오기
		Object obj = e.getSource();

		if (obj.equals(sumit.btn2)) {
			// 회원가입 창의 버튼 라벨이 등록 일경우 , 수정일 경우 각 해당하는 메소드를 실행 해준다.
			if (sumit.btn2.getLabel().equals("등록")) {
				// 삽입
				sumit.setVisible(false);
			} else if (sumit.btn2.getLabel().equals("수정")) {
				// 수정
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
	// 수정
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
			System.out.println(rss + "업데이트");
			sumit.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	// 삽입
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
				System.out.println(rss + "삽입");
				CHECK_FORCE=1;
			}
			else
			{
				System.out.println("존재하는 아이디입니다");
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
	// 검색
	void showUpdate() {
		// TODO Auto-generated method stub
		// String sql = "select * from users where name='"+name+"'";
		sumit.btn3.setLabel("수정취소");
		sumit.btn2.setLabel("수정");
		// 수정할 이름을 가지고 온다.
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
				System.out.println(rs + "삭제");
			}
		} catch (Exception e) {
			System.out.println("수정 중 예외 발생 : " + e);
		}
		// 수정시 아이디는 고치지 못하도록
		sumit.tf_id.setEnabled(false);
		sumit.setVisible(true);
	}
	// 삭제
	void selectDelete() {
		// TODO Auto-generated method stub

		String sql = "delete from users where name='" + name + "'";
		System.out.println(sql);
		try {

			int rss = stmt.executeUpdate(sql);

			System.out.println(rss + "삭제");
			// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.
		} catch (Exception e) {
			System.out.println("삭제중 예외 발생 : " + e);
		}
	}// deleteDB

	// 로그인 체 크
	int loginCheck(String id, String pass) {
		// SELECT 쿼리를 작성한다. 해당하는 아이디값의 패스워드를 검색한다.
		String query = "SELECT pw,name FROM users where id='" + id + "'";
		System.out.println(query);
		try {
			// executeQuery() 메서드로 SELECT문의 실행시키고 결과로 ResultSet 객체를 받는다.
			ResultSet rs = stmt.executeQuery(query);
			// 레코드가 있는지 검사
			if (rs.next()) {
				CHECK_FORCE = 0;
				name = rs.getString("name");
				// 텍스트필드에 쓴값과 데이터베이스에 있는 패스워드 값을 비교한다.
				if (pass.equals(rs.getString("pw")))
				{
					System.out.println("일치한다");
					CHECK_FORCE = 1;
					// 맞으면 로그인서브를 띄워준다.
				}
				else
				{
					System.out.println("일치하지않는다");
					CHECK_FORCE = 0 ;
				}
			}
		} catch (Exception b) {
			b.printStackTrace();
		}
		return CHECK_FORCE;
	}
	// 실행
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