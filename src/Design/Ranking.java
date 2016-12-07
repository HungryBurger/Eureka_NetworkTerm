package Design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Ranking extends JFrame {

	Login client = new Login();
	private JPanel contentPane;
	ImageIcon p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking frame = new Ranking();
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
	public Ranking() {
		p1 = new ImageIcon("image/rank_back.png");
		p2 = new ImageIcon("image/rank_title.png");
		p3 = new ImageIcon("image/first.png");
		p4 = new ImageIcon("image/second.png");
		p5 = new ImageIcon("image/third.png");
		p6 = new ImageIcon("image/fourth.png");
		p7 = new ImageIcon("image/fifth.png");
		p8 = new ImageIcon("image/my.png");
		p9 = new ImageIcon("image/my_part.png");
		p10 = new ImageIcon("image/home_button.png");
		p11= new ImageIcon("image/my.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1300, 900);
		client.out.println("[RANK]");
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p1.getImage(),0,0,1300,900,null);
				setOpaque(false);
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p2.getImage(),0,0,951,135,null);
				setOpaque(false);
			}
		};
		panel.setBounds(149, 46, 951, 135);
		contentPane.add(panel);

		JPanel panel_3 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p3.getImage(),0,0,102,84,null);
				setOpaque(false);
			}
		};
		panel_3.setBounds(197, 209, 102, 84);
		contentPane.add(panel_3);

		JPanel panel_4 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p4.getImage(),0,0,102,84,null);
				setOpaque(false);
			}
		};
		panel_4.setBounds(197, 305, 102, 84);
		contentPane.add(panel_4);

		JPanel panel_5 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p5.getImage(),0,0,102,84,null);
				setOpaque(false);
			}
		};
		panel_5.setBounds(197, 401, 102, 84);
		contentPane.add(panel_5);

		JPanel panel_6 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p6.getImage(),0,0,102,84,null);
				setOpaque(false);
			}
		};
		panel_6.setBounds(197, 497, 102, 84);
		contentPane.add(panel_6);

		JPanel panel_7 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p7.getImage(),0,0,102,84,null);
				setOpaque(false);
			}
		};
		panel_7.setBounds(197, 593, 102, 84);
		contentPane.add(panel_7);

		JPanel panel_8 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p8.getImage(),0,0,102,84,null);
				setOpaque(false);
			}
		};
		panel_8.setBounds(197, 742, 102, 84);
		contentPane.add(panel_8);

		JPanel panel_9 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p9.getImage(),0,0,712,84,null);
				setOpaque(false);
			}
		};
		panel_9.setLayout(null);
		panel_9.setBounds(333, 209, 712, 84);
		contentPane.add(panel_9);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(14, 12, 698, 72);
		panel_9.add(lblNewLabel);

		String id=null;
		String point = null;
		try {
			id = client.in.readLine();
			point = client.in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNewLabel.setText("ID = "+id+",Point = "+point+"");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));
		JPanel panel_10 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p9.getImage(),0,0,712,84,null);
				setOpaque(false);
			}
		};
		panel_10.setBounds(333, 305, 712, 84);
		contentPane.add(panel_10);
		panel_10.setLayout(null);

		JLabel label = new JLabel("New label");
		label.setBounds(14, 0, 698, 84);
		panel_10.add(label);
		try {
			id = client.in.readLine();
			point = client.in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label.setText("ID = "+id+",Point = "+point+"");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));


		JPanel panel_11 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p9.getImage(),0,0,712,84,null);
				setOpaque(false);
			}
		};
		panel_11.setLayout(null);
		panel_11.setBounds(333, 401, 712, 84);
		contentPane.add(panel_11);

		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(14, 0, 698, 84);
		panel_11.add(label_1);
		try {
			id = client.in.readLine();
			point = client.in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_1.setText("ID = "+id+",Point = "+point+"");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));

		JPanel panel_12 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p9.getImage(),0,0,712,84,null);
				setOpaque(false);
			}
		};
		panel_12.setLayout(null);
		panel_12.setBounds(333, 497, 712, 84);
		contentPane.add(panel_12);

		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(14, 0, 698, 84);
		panel_12.add(label_2);
		try {
			id = client.in.readLine();
			point = client.in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_2.setText("ID = "+id+",Point = "+point+"");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));

		JPanel panel_13 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p9.getImage(),0,0,712,84,null);
				setOpaque(false);
			}
		};
		panel_13.setLayout(null);
		panel_13.setBounds(333, 593, 712, 84);
		contentPane.add(panel_13);

		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(14, 0, 698, 84);
		panel_13.add(label_3);
		try {
			id = client.in.readLine();
			point = client.in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_3.setText("ID = "+id+",Point = "+point+"");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));

		JPanel panel_14 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(p9.getImage(),0,0,712,84,null);
				setOpaque(false);
			}
		};
		panel_14.setLayout(null);
		panel_14.setBounds(333, 742, 712, 84);
		contentPane.add(panel_14);

		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(14, 0, 698, 84);
		panel_14.add(label_4);
		try {
			id = client.in.readLine();
			point = client.in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_4.setText("ID = "+id+",POINT = "+point+"");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));


		Image originImg = p10.getImage();
		Image changedImg= originImg.getScaledInstance(95, 84, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JButton button1 = new JButton(Icon);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.setBounds(35, 742, 95, 84);
		contentPane.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button1)) {
					new MainMenu().setVisible(true);
					setVisible(false);
				}
			}
		});
		String remain = null;
		try {
			remain = client.in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (!remain.equals("[END]")) {
			try {
				remain = client.in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
