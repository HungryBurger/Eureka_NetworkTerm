package Design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Result extends JFrame {

	private JPanel contentPane;
	ImageIcon icon1;
	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result();
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
	public Result()
	{
		try {
			Login client = new Login();
			icon1 = new ImageIcon("image/result.png");
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
			contentPane.setLayout(null);
			setContentPane(contentPane);
			Font f2 = new Font("ÈÞ¸Õ¸ðÀ½T",Font.BOLD,50);
			String score = client.in.readLine();
			System.out.println(score);
			JLabel label = new JLabel();
			label.setBounds(532, 522, 269, 85);
			label.setFont(f2);
			contentPane.add(label);
			label.setText(score);
			label.setForeground(Color.WHITE);	
			
			String after = client.in.readLine();
			String before = client.in.readLine();
			JLabel label_1 = new JLabel();
			label_1.setText("MY Point :BEFORE = "+before+" \n After = "+score+" +"+after+"");
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 30));
			label_1.setBounds(321, 632, 733, 85);
			contentPane.add(label_1);
			String remain=null;
			remain=client.in.readLine();
			while (!remain.equals("[END]")) {
				try {
					remain = client.in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
