package Design;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Duration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JLabel;
public class exam extends JFrame {
	private long lastTickTime;
	private Timer timer;
	private JPanel contentPane;
	ImageIcon p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12;
	JLabel label,label_1,label_2,label_3;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exam frame = new exam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public exam() {
		try{
			Login client = new Login();
			Login.clients.sound("bgm/Quiz_bgm.wav", true);
			p1 = new ImageIcon("image/battle_back.jpg");
			p2 = new ImageIcon("image/problem_pane.png");
			p3 = new ImageIcon("image/time.png");
			p4 = new ImageIcon("image/button_pane.png");
			p5 = new ImageIcon("image/status.png");
			p6 = new ImageIcon("image/home_button.png");
			p7 = new ImageIcon("image/back_check.png");
			Font f1 = new Font("휴먼모음T",Font.PLAIN,20);
			Font f2 = new Font("휴먼모음T",Font.PLAIN,20);
			client.out.println("[MOCK]");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400, 100, 1200, 800);
			contentPane = new JPanel(){
				public void paintComponent(Graphics g){
					Dimension d = getSize();
					g.drawImage(p1.getImage(),0,0,1200,800,null);
					setOpaque(false);
				}
			};
			contentPane.setLayout(null);
			setContentPane(contentPane);


			JPanel panel_3 = new JPanel(){
				public void paintComponent(Graphics g){
					Dimension d = getSize();
					g.drawImage(p2.getImage(),0,0,1030,318,null);
					setOpaque(false);
				}
			};
			panel_3.setBounds(65, 96, 1030, 318);
			panel_3.setLayout(null);
			contentPane.add(panel_3);

			JLabel problem = new JLabel();
			problem.setHorizontalAlignment(JLabel.CENTER);
			panel_3.add(problem);
			problem.setBounds(14,12,1002,294);

			JLabel label = new JLabel();
			label.setBounds(100, 599, 481, 85);
			label.setFont(f1);
			label.setHorizontalAlignment(JLabel.CENTER);
			JLabel label_1 = new JLabel();
			label_1.setBounds(606, 599, 481, 85);
			label_1.setFont(f1);
			label_1.setHorizontalAlignment(JLabel.CENTER);
			JLabel label_2 = new JLabel();
			label_2.setBounds(606, 474, 481, 85);
			label_2.setFont(f1);
			label_2.setHorizontalAlignment(JLabel.CENTER);
			JLabel label_3 = new JLabel();
			label_3.setBounds(100, 474, 481, 85);
			label_3.setHorizontalAlignment(JLabel.CENTER);
			label_3.setFont(f1);

			Image originImg = p4.getImage();
			Image changedImg= originImg.getScaledInstance(447, 85, Image.SCALE_SMOOTH );
			ImageIcon Icon = new ImageIcon(changedImg);
			JButton button1 = new JButton(Icon);
			button1.setBorderPainted(false);
			button1.setContentAreaFilled(false);
			button1.setBorderPainted(false);
			button1.setFocusPainted(false);
			button1.setBounds(76, 474, 447, 85);
			contentPane.add(button1);
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button1)) {
						System.out.println("1번 ");
						try {
							client.out.println("1");
							String temp = client.in.readLine();
							if(temp.equals("correct"))
								new correct().setVisible(true);
							else
								new error().setVisible(true);
							String a = client.in.readLine();
							if(a.equals("[END]"))
							{
								String Score=client.in.readLine();
								client.out.println("[Result]");
								client.out.println(Score);
								setVisible(false);
								new Result().setVisible(true);
							}
							else{
								String a1 = client.in.readLine();
								String a2=client.in.readLine();
								String a3=client.in.readLine();
								String a4=client.in.readLine();
								System.out.println(a);
								System.out.println(a1);
								System.out.println(a2);
								System.out.println(a3);
								System.out.println(a4);
								problem.setFont(f2);
								if(a!=null)
								{
									if(a.length()>20)
									{
										String a11 = a.substring(0, a.length()/2);
										String a21 = a.substring(a.length()/2+1,a.length());
										problem.setText("<html>"+a11+"<br>"+a21+"</html>");
									}
									else
										problem.setText(a);
								}
								label.setText("1.  "+a1);
								label_1.setText("2.  "+a2);
								label_2.setText("3.   "+a3);
								label_3.setText("4.   "+a4);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});

			Image originImg1 = p4.getImage();
			Image changedImg1= originImg1.getScaledInstance(447, 85, Image.SCALE_SMOOTH );
			ImageIcon Icon1 = new ImageIcon(changedImg1);

			JButton button2 = new JButton(Icon1);
			button2.setBorderPainted(false);
			button2.setContentAreaFilled(false);
			button2.setBorderPainted(false);
			button2.setFocusPainted(false);
			button2.setBounds(606, 474, 447, 85);
			contentPane.add(button2);
			button2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button2)) {
						System.out.println("2번 ");
						try {
							client.out.println("2");
							String temp = client.in.readLine();
							if(temp.equals("correct"))
								new correct().setVisible(true);
							else
								new error().setVisible(true);
							String a = client.in.readLine();
							if(a.equals("[END]"))
							{
								String Score=client.in.readLine();
								client.out.println("[Result]");
								client.out.println(Score);
								new Result().setVisible(true);
								setVisible(false);
							}
							else{
								String a1 = client.in.readLine();
								String a2=client.in.readLine();
								String a3=client.in.readLine();
								String a4=client.in.readLine();
								System.out.println(a);
								System.out.println(a1);
								System.out.println(a2);
								System.out.println(a3);
								System.out.println(a4);
								problem.setFont(f2);
								problem.setText(a);
								label.setText("1.  "+a1);
								label_1.setText("2.  "+a2);
								label_2.setText("3.   "+a3);
								label_3.setText("4.   "+a4);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			});
			Image originImg2 = p4.getImage();
			Image changedImg2= originImg2.getScaledInstance(447, 85, Image.SCALE_SMOOTH );
			ImageIcon Icon2 = new ImageIcon(changedImg2);
			JButton button3 = new JButton(Icon2);
			button3.setBorderPainted(false);
			button3.setContentAreaFilled(false);
			button3.setBorderPainted(false);
			button3.setFocusPainted(false);
			button3.setBounds(606, 599, 447, 85);
			contentPane.add(button3);
			button3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button3)) {
						System.out.println("4번 ");
						try {
							client.out.println("4");
							String temp = client.in.readLine();
							if(temp.equals("correct"))
								new correct().setVisible(true);
							else
								new error().setVisible(true);
							String a = client.in.readLine();
							if(a.equals("[END]"))
							{
								String Score=client.in.readLine();
								client.out.println("[Result]");
								client.out.println(Score);
								
								new Result().setVisible(true);
								setVisible(false);
							}
							else{
								String a1 = client.in.readLine();
								String a2=client.in.readLine();
								String a3=client.in.readLine();
								String a4=client.in.readLine();
								System.out.println(a);
								System.out.println(a1);
								System.out.println(a2);
								System.out.println(a3);
								System.out.println(a4);
								problem.setFont(f2);
								problem.setText(a);
								label.setText("1.  "+a1);
								label_1.setText("2.  "+a2);
								label_2.setText("3.   "+a3);
								label_3.setText("4.   "+a4);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			Image originImg6 = p4.getImage();
			Image changedImg6= originImg6.getScaledInstance(447, 85, Image.SCALE_SMOOTH );
			ImageIcon Icon6 = new ImageIcon(changedImg6);
			JButton button4 = new JButton(Icon6);
			button4.setBorderPainted(false);
			button4.setContentAreaFilled(false);
			button4.setBorderPainted(false);
			button4.setFocusPainted(false);
			button4.setBounds(76, 599, 447, 85);
			contentPane.add(button4);
			button4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button4)) {
						System.out.println("3번 ");
						try {
							client.out.println("3");
							String temp = client.in.readLine();
							if(temp.equals("correct"))
								new correct().setVisible(true);
							else
								new error().setVisible(true);
							String a = client.in.readLine();
							if(a.equals("[END]"))
							{
								String Score=client.in.readLine();
								client.out.println("[Result]");
								System.out.println("result");
								client.out.println(Score);
								new Result().setVisible(true);
								setVisible(false);
							}
							else{
								String a1 = client.in.readLine();
								String a2=client.in.readLine();
								String a3=client.in.readLine();
								String a4=client.in.readLine();
								System.out.println(a);
								System.out.println(a1);
								System.out.println(a2);
								System.out.println(a3);
								System.out.println(a4);
								problem.setFont(f2);
								problem.setText(a);
								label.setText("1.  "+a1);
								label_1.setText("2.  "+a2);
								label_2.setText("3.   "+a3);
								label_3.setText("4.   "+a4);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			});

			Image originImg3 = p7.getImage();
			Image changedImg3= originImg3.getScaledInstance(90, 90, Image.SCALE_SMOOTH );
			ImageIcon Icon3 = new ImageIcon(changedImg3);
			JButton button6 = new JButton(Icon3);
			button6.setBorderPainted(false);
			button6.setContentAreaFilled(false);
			button6.setBorderPainted(false);
			button6.setFocusPainted(false);
			button6.setBounds(1080, 474, 88, 85);
			contentPane.add(button6);
			button6.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button6)) {
						new MainMenu().setVisible(true);
						setVisible(false);
					}
				}
			});

			Image originImg5 = p6.getImage();
			Image changedImg5= originImg5.getScaledInstance(88, 85, Image.SCALE_SMOOTH );
			ImageIcon Icon5 = new ImageIcon(changedImg5);
			JButton button5 = new JButton(Icon5);
			button5.setBorderPainted(false);
			button5.setContentAreaFilled(false);
			button5.setBorderPainted(false);
			button5.setFocusPainted(false);
			button5.setBounds(1080, 599, 88, 85);
			contentPane.add(button5);
			button5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button5)) {
						new GameMode().setVisible(true);
						setVisible(false);
					}
				}
			});
			button1.add(label);
			button2.add(label_1);
			button4.add(label_2);
			button3.add(label_3);

			String a = client.in.readLine();
			String a1 = client.in.readLine();
			String a2=client.in.readLine();
			String a3=client.in.readLine();
			String a4=client.in.readLine();
			System.out.println(a);
			System.out.println(a1);
			System.out.println(a2);
			System.out.println(a3);
			System.out.println(a4);
			problem.setFont(f2);
			problem.setText(a);
			label.setText("1.  "+a1);
			label_1.setText("2.  "+a2);
			label_2.setText("3.   "+a3);
			label_3.setText("4.   "+a4);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

}
