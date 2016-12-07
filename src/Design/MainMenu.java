package Design;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainMenu extends JFrame {

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
					MainMenu frame = new MainMenu();
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
	MainMenu() {
		try{
			Login client = new Login();
			Login.clients.sound("bgm/other_bgm.wav", true);
			icon1 = new ImageIcon("image/Back1.png");
			icon2 = new ImageIcon("image/title_menu.png");
			icon3 = new ImageIcon("image/data1.png");
			icon4 = new ImageIcon("image/data2.png");
			icon5 = new ImageIcon("image/data3.png");
			icon6 = new ImageIcon("image/data4.png");
			b1 = new ImageIcon("image/menu1.png");
			b2 = new ImageIcon("image/menu2.png");
			b3 = new ImageIcon("image/menu3.png");
			b4 = new ImageIcon("image/menu4.png");
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
			panel.setBounds(74, 44, 985, 109);
			contentPane.add(panel);

			JPanel p1 = new JPanel(){
				public void paintComponent(Graphics g){
					Dimension d = getSize();
					g.drawImage(icon3.getImage(),0,0,511,515,null);
					setOpaque(false);
				}
			};
			p1.setLayout(null);
			p1.setBounds(74, 219, 511, 515);
			contentPane.add(p1);
			String id="";
			client.out.println("[MainMenu]");
			String remain = null;
			remain = client.in.readLine();
			while (!remain.equals("[END]")) {
					remain = client.in.readLine();
			}
			JPanel p2 = new JPanel(){
				public void paintComponent(Graphics g){
					Dimension d = getSize();
					g.drawImage(icon4.getImage(),0,0,511,515,null);
					setOpaque(false);
				}
			};
			p2.setLayout(null);
			p2.setBounds(74, 219, 511, 515);
			contentPane.add(p2);

			JPanel p3 = new JPanel(){
				public void paintComponent(Graphics g){
					Dimension d = getSize();
					g.drawImage(icon5.getImage(),0,0,511,515,null);
					setOpaque(false);
				}
			};
			p3.setLayout(null);
			p3.setBounds(74, 219, 511, 515);
			contentPane.add(p3);


			JPanel p4 = new JPanel(){
				public void paintComponent(Graphics g){
					Dimension d = getSize();
					g.drawImage(icon6.getImage(),0,0,511,515,null);
					setOpaque(false);

				}
			};
			p4.setLayout(null);
			p4.setBounds(74, 219, 511, 515);
			contentPane.add(p4);
			
			p1.setVisible(true);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			
			//single button
			Image originImg = b1.getImage();
			Image changedImg= originImg.getScaledInstance(400, 100, Image.SCALE_SMOOTH );
			ImageIcon Icon = new ImageIcon(changedImg);
			JButton button1 = new JButton(Icon);
			button1.setBorderPainted(false);
			button1.setContentAreaFilled(false);
			button1.setBorderPainted(false);
			button1.setFocusPainted(false);
			button1.setBounds(659, 219, 400, 100);
			contentPane.add(button1);
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button1)) {
						new GameMode();
						setVisible(false);
					}
				}
			});
			button1.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					button1.setVisible(true);
					p1.setVisible(true);
					p2.setVisible(false);
					p3.setVisible(false);
					p4.setVisible(false);
				}
			});
			
			Image originImg2 = b3.getImage();
			Image changedImg2= originImg2.getScaledInstance(400, 100, Image.SCALE_SMOOTH );
			ImageIcon Ic2 = new ImageIcon(changedImg2);
			JButton button3 = new JButton(Ic2);
			button3.setBorderPainted(false);
			button3.setContentAreaFilled(false);
			button3.setBorderPainted(false);
			button3.setFocusPainted(false);
			button3.setBounds(659, 484, 400, 100);
			contentPane.add(button3);
			button3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object obj = e.getSource();
					if (obj.equals(button3)) {
						new Ranking().setVisible(true);
						setVisible(false);
					}
				}
			});

			button3.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					button3.setVisible(true);
					p1.setVisible(false);
					p2.setVisible(false);
					p3.setVisible(false);
					p4.setVisible(true);
				}
			});
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

