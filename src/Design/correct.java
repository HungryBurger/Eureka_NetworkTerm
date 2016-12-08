package Design;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class correct extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	ImageIcon p1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					correct frame = new correct();
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
	public correct() {
		p1 = new ImageIcon("image/correct.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Image originImg = p1.getImage();
		Image changedImg= originImg.getScaledInstance(1200, 800, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JButton button1 = new JButton(Icon);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.setBounds(0,0,1200, 800);
		contentPane.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				setVisible(false);
			}
		});
		contentPane.add(button1);
	}

}
