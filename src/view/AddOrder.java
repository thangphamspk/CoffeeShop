package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.DBConnection;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddOrder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5780732598636617968L;
	private JPanel contentPane;
	public static int tableID;
	public static int MaNV;
	private JComboBox<String> cbxCustomer;
	private JComboBox<String> cbxCustomerID;
	private Connection conn;
	private Statement statement;
	private ResultSet rs;

	/**
	 * Create the frame.
	 */
	public AddOrder() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AdminFrame();
			}
		});
		setBounds(100, 100, 528, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("THÊM HOÁ ĐƠN MỚI");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBackground(new Color(121, 85, 72));
		label.setBounds(0, 0, 510, 57);
		contentPane.add(label);

		JLabel lblCustomer = new JLabel("Khách hàng:");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomer.setBounds(11, 69, 165, 30);
		contentPane.add(lblCustomer);

		cbxCustomer = new JComboBox<String>();
		cbxCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxCustomer.setEditable(false);
		cbxCustomer.setBounds(187, 69, 311, 30);
		contentPane.add(cbxCustomer);

		JButton btnAddOrder = new JButton("Thêm");
		btnAddOrder.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = cbxCustomer.getSelectedIndex();
				int MaKH = -1;
				try {
					MaKH = Integer.parseInt(cbxCustomerID.getItemAt(index));
					try {
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar calobj = Calendar.getInstance();
						String thoidiem = df.format(calobj.getTime()).toString();

						// Generate SoHD
						int SoHD = -1;
						String sql = "SELECT SoHD FROM hoadon ORDER BY `SoHD` DESC LIMIT 1";
						statement = conn.createStatement();
						rs = statement.executeQuery(sql);
						if (rs.next()) {
							SoHD = rs.getInt(1) + 1;
						} else {
							SoHD = 1;
						}
						if (SoHD != -1) {
							try {
								// Update bảng chọn bàn
								sql = "INSERT INTO chonban VALUES(" + MaKH + ",'" + thoidiem + "'," + tableID
										+ ",NULL)";
								conn = DBConnection.getConnection();
								statement = conn.createStatement();
								statement.executeUpdate(sql);

								// Update bảng hoá đơn
								sql = "INSERT INTO hoadon VALUES(" + SoHD + ", 0.0," + MaNV + "," + MaKH + ",'"
										+ thoidiem + "')";
								statement = conn.createStatement();
								statement.executeUpdate(sql);
								AdminFrame.soHD = SoHD;

							} catch (SQLException ex2) {
								// TODO: handle exception
								Message.messageBox(ex2.getMessage(), "ERROR");
								System.out.println(ex2.getMessage());
							}
						}
						rs.next();
						statement.close();
						conn.close();
						dispose();
						new AdminFrame();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}

				} catch (NumberFormatException e2) {
					// TODO: handle exception
					Message.messageBox("Bạn chưa chọn khách hàng", "THÔNG BÁO");
				}

			}
		});
		btnAddOrder.setBounds(344, 119, 154, 40);
		contentPane.add(btnAddOrder);

		JButton btnCancel = new JButton("Huỷ");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AdminFrame();
			}
		});
		btnCancel.setActionCommand("AddNew");
		btnCancel.setBounds(187, 119, 154, 40);
		contentPane.add(btnCancel);

		cbxCustomerID = new JComboBox<String>();
		cbxCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxCustomerID.setEditable(false);
		cbxCustomerID.setEnabled(false);
		cbxCustomerID.setVisible(false);
		cbxCustomerID.setBounds(54, 125, 54, 30);
		contentPane.add(cbxCustomerID);
		setLocationRelativeTo(null);
		setVisible(true);
		loadCustomer();
		cbxCustomer.setSelectedIndex(1);
	}

	private void loadCustomer() {

		try {
			String sql = "SELECT MaKH, HotenKH FROM khachhang where MaKH not in (SELECT MaKH FROM coffeeshop.chonban where NgayGioTra is null)";
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxCustomerID.addItem(rs.getString(1));
				cbxCustomer.addItem(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
