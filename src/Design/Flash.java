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
public class Flash extends JFrame implements Runnable {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	ImageIcon p1,p2,p3,p4,p5,p6,p7;
	private JLabel label;
	private long lastTickTime;
	private Timer timer;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flash frame = new Flash();
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
	public Flash(){
		Login client = new Login();
		client.out.println("[FLASH]");
		p1 = new ImageIcon("image/flash_back.jpg");
		p2 = new ImageIcon("image/white_back.png");
		p3 = new ImageIcon("image/left.png");
		p4 = new ImageIcon("image/back_check.png");
		p5 = new ImageIcon("image/home_button.png");
		p6 = new ImageIcon("image/right.png");
		p7 = new ImageIcon("image/start.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1200, 800);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p1.getImage(),0,0,1200,800,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p2.getImage(),0,0,859,414,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_1.setBounds(171, 173, 859, 414);
		panel_1.setLayout(null);
		contentPane.add(panel_1);


		Image originImg = p3.getImage();
		Image changedImg= originImg.getScaledInstance(110, 101, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		Image originImg1 = p4.getImage();
		Image changedImg1= originImg1.getScaledInstance(120, 111, Image.SCALE_SMOOTH );
		ImageIcon Icon1 = new ImageIcon(changedImg1);
		JButton button2 = new JButton(Icon1);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setFocusPainted(false);
		button2.setBounds(422, 624, 110, 101);
		contentPane.add(button2);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				String remain = null;
				if (obj.equals(button2)) {
					try {
						remain = client.in.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					while (!remain.equals("END")) {
						try {
							remain = client.in.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					new GameMode().setVisible(true);
					setVisible(false);
				}
			}
		});
		Image originImg2 = p5.getImage();
		Image changedImg2= originImg2.getScaledInstance(110, 101, Image.SCALE_SMOOTH );
		ImageIcon Icon2 = new ImageIcon(changedImg2);
		JButton button3 = new JButton(Icon2);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setFocusPainted(false);
		button3.setBounds(667, 624, 110, 101);
		contentPane.add(button3);
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				String remain = null;
				if (obj.equals(button3)) {
					try {
						remain = client.in.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					while (!remain.equals("END")) {
						try {
							remain = client.in.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					new MainMenu().setVisible(true);
					setVisible(false);
				}
			}
		});
		Image originImg3 = p6.getImage();
		Image changedImg3= originImg3.getScaledInstance(110, 101, Image.SCALE_SMOOTH );
		ImageIcon Icon3 = new ImageIcon(changedImg3);



		Image originImg4 = p7.getImage();
		Image changedImg4= originImg4.getScaledInstance(355, 71, Image.SCALE_SMOOTH );
		ImageIcon Icon4 = new ImageIcon(changedImg4);
		JButton button5 = new JButton(Icon4);
		button5.setBorderPainted(false);
		button5.setContentAreaFilled(false);
		button5.setBorderPainted(false);
		button5.setFocusPainted(false);
		button5.setBounds(422, 51, 355, 71);
		contentPane.add(button5);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!timer.isRunning()) {
					lastTickTime = System.currentTimeMillis();
					timer.start();
				}
			}
		});
		long check=0;
		long take = 0;
		JLabel problem = new JLabel();
		problem.setHorizontalAlignment(JLabel.CENTER);
		panel_1.add(problem);
		problem.setBounds(150,50, 600, 300);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long runningTime = System.currentTimeMillis() - lastTickTime;
				Duration duration = Duration.ofMillis(runningTime);
				long hours = duration.toHours();
				duration = duration.minusHours(hours);
				long minutes = duration.toMinutes();
				duration = duration.minusMinutes(minutes);
				long millis = duration.toMillis();
				long seconds = millis / 1000;
				millis-=(seconds * 1000);
				label.setText(String.format("%04d:%02d:%d.%03d", hours, minutes, seconds, check));
				Font f1 = new Font("ÈÞ¸Õ¸ðÀ½T",Font.PLAIN,20);
				try {
					if(seconds%3!=0)
					{
						System.out.println(millis);
						String a="Chance";
						a=client.in.readLine();
						problem.setFont(f1);
						if(a!=null)
						{
							if(a.length()>20)
							{
								String a1 = a.substring(0, a.length()/2);
								String a2 = a.substring(a.length()/2+1,a.length());
								problem.setText("<html>"+a1+"<br>"+a2+"</html>");
							}
							else
								problem.setText(a);
						}
					}
					else if(seconds == 30)
					{
						timer.stop();
						String remain=null;
						try {
							remain = client.in.readLine();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						while (!remain.equals("END")) {
							try {
								remain = client.in.readLine();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						setVisible(false);
						new Finish().setVisible(true);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
