package Design;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMode extends JFrame {

	private JPanel contentPane;
	ImageIcon icon1,icon2,icon3,icon4,icon5,icon6,b1,b2,b3,b4;
	TextField text1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMode frame = new GameMode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame
	 */
//	Mock_menu mock = new Mock_menu();
//	Flash_menu flash = new Flash_b4menu();
	
	GameMode() {
		Login client = new Login();
		icon1 = new ImageIcon("image/Back2.png");
		icon2 = new ImageIcon("image/Gamemode_title.png");
		icon4 = new ImageIcon("image/exam_info.png");
		icon5 = new ImageIcon("image/flashing_room_info.png");
		icon6 = new ImageIcon("image/discussion_info.png");
		b1 = new ImageIcon("image/exam.png");
		b2 = new ImageIcon("image/flashing_room.png");
		b3 = new ImageIcon("image/back_button.png");
		b4 = new ImageIcon("image/discussion_button.png");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1200, 800);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon1.getImage(),0,0,1200,800,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		contentPane.setBounds(10, 10, 100, 200);
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon2.getImage(),0,0,985,109,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(74, 28, 985, 109);
		contentPane.add(panel);
		
		JPanel p1 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon4.getImage(),0,0,511,515,null);
				setOpaque(false);
			}
		};
		p1.setLayout(null);
		p1.setBounds(74, 219, 511, 515);
		contentPane.add(p1);
		
		JPanel p2 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon5.getImage(),0,0,511,515,null);
				setOpaque(false);
			}
		};
		p2.setLayout(null);
		p2.setBounds(74, 219, 511, 515);
		contentPane.add(p2);
		
		JPanel p3 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon6.getImage(),0,0,511,515,null);
				setOpaque(false);
			}
		};
		p3.setLayout(null);
		p3.setBounds(74, 219, 511, 515);
		contentPane.add(p3);
		
		
		p1.setVisible(true);
		p2.setVisible(false);
		p3.setVisible(false);
		//single button
		Image originImg = b1.getImage();
		Image changedImg= originImg.getScaledInstance(400, 100, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JButton button1 = new JButton(Icon);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.setBounds(659, 338, 400, 100);
		contentPane.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button1)) {
					new exam().setVisible(true);
					setVisible(false);
				}
			}
		});
		
		button1.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				p1.setVisible(true);
				p2.setVisible(false);
				p3.setVisible(false);
			}
		});
		
		Image originImg1 = b2.getImage();
	    Image changedImg1= originImg1.getScaledInstance(400, 100, Image.SCALE_SMOOTH );
	    ImageIcon Ic1 = new ImageIcon(changedImg1);
		JButton button2 = new JButton(Ic1);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setFocusPainted(false);
		button2.setBounds(659, 219, 400, 100);
		contentPane.add(button2);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button2)) {
					new Flash().setVisible(true);
					setVisible(false);
				}
			}
		});
		
		button2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				p1.setVisible(false);
				p2.setVisible(true);
				p3.setVisible(false);
			}
		});
		Image originImg2 = b3.getImage();
	    Image changedImg2= originImg2.getScaledInstance(400, 100, Image.SCALE_SMOOTH );
	    ImageIcon Ic2 = new ImageIcon(changedImg2);
		JButton button3 = new JButton(Ic2);
		button3.setFocusPainted(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setBounds(659, 606, 400, 100);
		contentPane.add(button3);
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button3)) {
					new MainMenu().setVisible(true);
					setVisible(false);
				}
			}
		});
		
		
		Image originImg3 = b4.getImage();
	    Image changedImg3= originImg3.getScaledInstance(400, 100, Image.SCALE_SMOOTH );
	    ImageIcon Ic3 = new ImageIcon(changedImg3);
		JButton button4 = new JButton(Ic3);
		button4.setFocusPainted(false);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button4.setBounds(659, 467, 400, 100);
		contentPane.add(button4);
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button4)) {
					discussion k = new discussion();	
					new Thread(k).start();
					setVisible(false);
				}
			}
		});
		button4.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				p1.setVisible(false);
				p2.setVisible(false);
				p3.setVisible(true);
			}
		});
	
	}
}
