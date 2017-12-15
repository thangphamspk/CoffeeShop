package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.Login;
import model.User;
import javax.swing.JPasswordField;
import javax.swing.JPanel;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2691377709695770639L;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnReset, btnLogin, btnExit;

	ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("Reset")) {
				// Reset form
				txtUser.setText("");
				txtPassword.setText("");
				txtUser.requestFocus();

			} else if (e.getActionCommand().equals("Exit")) {
				System.exit(1);
			} else {
				// Đăng nhập hệ thống
				String username = txtUser.getText().toString().trim();
				String password = new String(txtPassword.getPassword());
				if (username.length() > 0 && password.length() > 0) {
					User user = Login.doLogin(username, password);
					if (user != null) {
						// Đóng login form
						dispose();
						// Mở trang quản trị
						AdminFrame admin = new AdminFrame();
						admin.setVisible(true);

					} else {
						Message.messageBox("Username or password does not match !!!!", "Error");
					}
				}
			}
		}
	};

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setSize(594, 289);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JLabel lblTitle = new JLabel("COFFEE HOUSE MANAGERMENT");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(0, 0, 576, 52);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(121, 85, 72));
		getContentPane().add(lblTitle);

		JLabel lblUser = new JLabel("Username:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(12, 65, 110, 30);
		getContentPane().add(lblUser);

		txtUser = new JTextField();
		txtUser.setText("admin");
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUser.setBounds(150, 65, 410, 30);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(12, 115, 110, 30);
		getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPassword.setColumns(10);
		txtPassword.setText("123456");
		txtPassword.setBounds(150, 115, 410, 30);
		getContentPane().add(txtPassword);

		JPanel panel = new JPanel();
		panel.setBounds(150, 178, 410, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnReset = new JButton("Reset");
		btnReset.setBounds(154, 5, 100, 35);
		panel.add(btnReset);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReset.setActionCommand("Reset");
		btnReset.addActionListener(listener);

		btnExit = new JButton("Exit");
		btnExit.setBounds(310, 5, 100, 35);
		panel.add(btnExit);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setActionCommand("Exit");
		btnExit.addActionListener(listener);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(0, 5, 100, 35);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setActionCommand("Login");
		btnLogin.addActionListener(listener);
		setResizable(false);
		setVisible(true);
	}
}
