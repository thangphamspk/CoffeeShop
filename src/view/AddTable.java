package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DBConnection;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AddTable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -900442557381751000L;
	private JPanel contentPane;
	private Connection conn;
	private Statement statement;
	private ResultSet rs;
	private JButton btnAddTable;
	private JComboBox<Integer> cbxArea;

	/**
	 * Create the frame.
	 */
	public AddTable() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AdminFrame();
			}
		});
		setBounds(100, 100, 405, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAreaID = new JLabel("Vị trí:");
		lblAreaID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAreaID.setBounds(12, 88, 71, 30);
		contentPane.add(lblAreaID);

		cbxArea = new JComboBox<Integer>();
		cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxArea.setEditable(false);
		cbxArea.setBounds(118, 88, 50, 30);
		contentPane.add(cbxArea);

		btnAddTable = new JButton("Thêm bàn");
		btnAddTable.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// Tạo số bàn
				int soBan = 0;
				try {
					conn = DBConnection.getConnection();
					String sql = "SELECT * FROM coffeeshop.ban order by MaBan desc limit 1";
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					while (rs.next()) {
						soBan = rs.getInt(2) + 1;
					}
					int vt = Integer.parseInt(cbxArea.getSelectedItem().toString());
					// Thêm bàn mới
					sql = "insert into ban values(" + soBan + "," + soBan + "," + vt + ", 1)";
					statement = conn.createStatement();
					statement.executeUpdate(sql);
					Message.messageBox("Thêm bàn thành công", "THÔNG BÁO");
					statement.close();
					rs.close();
					conn.close();
					dispose();
					new AdminFrame();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddTable.setBounds(206, 78, 156, 40);
		contentPane.add(btnAddTable);

		JLabel lblTitle = new JLabel("THÊM BÀN MỚI");
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBackground(new Color(33, 150, 243));
		lblTitle.setBounds(0, 0, 387, 45);
		contentPane.add(lblTitle);
		setVisible(true);
		setLocationRelativeTo(null);
		loadArea();
	}

	public void loadArea() {
		try {
			String sql = "SELECT * FROM coffeeshop.vitri";
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxArea.addItem(rs.getInt(1));
			}
			cbxArea.setSelectedIndex(0);
			statement.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
