package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class ManualFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4759204113572653397L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ManualFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AdminFrame();
			}
		});
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1594, 865);
		contentPane.add(scrollPane);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
}
