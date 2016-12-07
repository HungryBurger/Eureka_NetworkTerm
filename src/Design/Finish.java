package Design;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Finish extends JFrame {

	private JPanel contentPane;
	ImageIcon icon1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finish frame = new Finish();
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
	public Finish() {
		icon1 = new ImageIcon("image/Finish.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Image originImg2 = icon1.getImage();
		Image changedImg2= originImg2.getScaledInstance(1182,753, Image.SCALE_SMOOTH );
		ImageIcon Ic2 = new ImageIcon(changedImg2);
		JButton button3 = new JButton(Ic2);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setFocusPainted(false);
		button3.setBounds(0, 0, 1182, 753);
		contentPane.add(button3);
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button3)) {
					new GameMode().setVisible(true);
					setVisible(false);
				}
			}
		});
	}
}
