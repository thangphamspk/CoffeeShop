package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8315797842649502948L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(121,85,72));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
	}
}
