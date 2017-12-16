package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.DBConnection;
import model.Order;
import model.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8315797842649502948L;

	public static User user;
	private JPanel contentPane;
	public static JLabel lblTableName;
	public static JLabel lblCustomerName;
	public static int MaKH;
	private static JLabel lblTotalPrice;
	private JLabel lblDrinkName;
	private JTable tableDrink;
	private DefaultTableModel tableModel;
	public static DefaultTableModel tableModelOrdered;
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	public static JTable tableOrdered;
	private JTextField txtQuantity;
	private double drinkPrice;
	public static String tableID;
	public static int soHD;
	private static JTabbedPane tabbedPane;

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setTitle("PHẦN MỀM QUẢN LÝ QUÁN CAFE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1269, 43);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		contentPane.add(menuBar);

		JMenu mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setForeground(Color.BLUE);
		mnHeThong.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(mnHeThong);

		JMenuItem mntmLogout = new JMenuItem("Đăng xuất");
		mntmLogout.setForeground(Color.BLUE);
		mntmLogout.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Đóng Admin Frame
				dispose();
				new LoginFrame();
			}
		});
		mnHeThong.add(mntmLogout);

		JMenuItem mntmExit = new JMenuItem("Thoát");
		mntmExit.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
		});
		mntmExit.setForeground(Color.BLUE);
		mnHeThong.add(mntmExit);

		JMenu mnHoatDong = new JMenu("Hoạt động");
		mnHoatDong.setForeground(Color.BLUE);
		mnHoatDong.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(mnHoatDong);
		JMenuItem mntmStaffManager = new JMenuItem("Quản lý nhân viên");
		mntmStaffManager.setForeground(Color.BLUE);
		mntmStaffManager.setFont(new Font("Dialog", Font.BOLD, 18));

		mntmStaffManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Đóng Admin Frame
				dispose();
				new StaffManager();
			}
		});

		mnHoatDong.add(mntmStaffManager);

		JMenuItem mntmCustomerManager = new JMenuItem("Quản lý khách hàng");
		mntmCustomerManager.setForeground(Color.BLUE);
		mntmCustomerManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Đóng Admin Frame
				dispose();
				new CustomerManager();
			}
		});
		mntmCustomerManager.setFont(new Font("Dialog", Font.BOLD, 18));
		mnHoatDong.add(mntmCustomerManager);

		JMenuItem mntmDrinkManager = new JMenuItem("Danh mục đồ uống");
		mntmDrinkManager.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmDrinkManager.setForeground(Color.BLUE);
		mntmDrinkManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Đóng Admin Frame
				dispose();
				new DrinkManager();
			}
		});

		JMenuItem mntmSupplierManager = new JMenuItem("Quản lý nhà cung cấp");
		mntmSupplierManager.setForeground(Color.BLUE);
		mntmSupplierManager.setFont(new Font("Dialog", Font.BOLD, 18));
		mnHoatDong.add(mntmSupplierManager);
		mntmSupplierManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Đóng Admin Frame
				dispose();
				new SupplierManager();
			}
		});
		mnHoatDong.add(mntmDrinkManager);

		JMenu mnTroGiup = new JMenu("Trợ giúp");
		mnTroGiup.setFont(new Font("Dialog", Font.BOLD, 18));
		mnTroGiup.setForeground(Color.BLUE);
		mnTroGiup.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ManualFrame();
			}
		});
		menuBar.add(mnTroGiup);
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		JLabel lblTableArea = new JLabel("KHU VỰC QUẢN LÝ BÀN");
		lblTableArea.setBounds(0, 44, 989, 54);
		lblTableArea.setForeground(Color.WHITE);
		lblTableArea.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTableArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableArea.setOpaque(true);
		lblTableArea.setBackground(new Color(121, 85, 72));
		contentPane.add(lblTableArea);

		JLabel lblBillDetail = new JLabel("CHI TIẾT HOÁ ĐƠN");
		lblBillDetail.setBounds(989, 44, 608, 54);
		lblBillDetail.setOpaque(true);
		lblBillDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillDetail.setForeground(Color.WHITE);
		lblBillDetail.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBillDetail.setBackground(new Color(255, 112, 67));
		contentPane.add(lblBillDetail);

		JPanel panelBillDetail = new JPanel();
		panelBillDetail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBillDetail.setBounds(989, 99, 605, 765);
		contentPane.add(panelBillDetail);
		panelBillDetail.setLayout(null);

		JLabel lblTable = new JLabel("Bàn:");
		lblTable.setOpaque(true);
		lblTable.setBackground(new Color(38, 198, 218));
		lblTable.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTable.setBounds(0, 0, 48, 40);
		panelBillDetail.add(lblTable);

		lblTableName = new JLabel();
		lblTableName.setOpaque(true);
		lblTableName.setBackground(new Color(38, 198, 218));
		lblTableName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTableName.setBounds(48, 0, 155, 40);
		panelBillDetail.add(lblTableName);

		JLabel lblCustomer = new JLabel("Khách hàng:");
		lblCustomer.setOpaque(true);
		lblCustomer.setBackground(new Color(38, 198, 218));
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomer.setBounds(202, 0, 123, 40);
		panelBillDetail.add(lblCustomer);

		lblCustomerName = new JLabel();
		lblCustomerName.setOpaque(true);
		lblCustomerName.setBackground(new Color(38, 198, 218));
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerName.setBounds(325, 0, 280, 40);
		panelBillDetail.add(lblCustomerName);

		JScrollPane scrollPaneDrinks = new JScrollPane();
		scrollPaneDrinks.setBounds(340, 128, 265, 566);
		panelBillDetail.add(scrollPaneDrinks);

		tableDrink = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -936429936172931659L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		tableDrink.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = tableDrink.getSelectedRow();
				if (index != -1) {
					lblDrinkName.setText(tableDrink.getValueAt(index, 0).toString());
					txtQuantity.setText(1 + "");
				}
			}
		});
		tableDrink.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableDrink.setRowSelectionAllowed(true);
		tableDrink.getTableHeader().setReorderingAllowed(false);
		// Không cho phép thay đổi độ dài cột
		tableDrink.getTableHeader().setResizingAllowed(false);
		tableDrink.setFillsViewportHeight(true);
		scrollPaneDrinks.setColumnHeaderView(tableDrink);
		tableDrink.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPaneDrinks.setViewportView(tableDrink);

		JScrollPane scrollPaneOrdered = new JScrollPane();
		scrollPaneOrdered.setBounds(0, 128, 341, 566);
		panelBillDetail.add(scrollPaneOrdered);

		tableOrdered = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8269189349479147153L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				// Cho phép chỉnh sửa số lư
				switch (column) {
				case 0:
					return false;
				case 1:
					return true;
				default:
					return false;
				}
			}

		};

		tableOrdered.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableOrdered.setRowSelectionAllowed(true);
		tableOrdered.getTableHeader().setReorderingAllowed(false);
		// Không cho phép thay đổi độ dài cột
		tableOrdered.getTableHeader().setResizingAllowed(false);
		tableOrdered.setFillsViewportHeight(true);
		scrollPaneOrdered.setColumnHeaderView(tableOrdered);
		tableOrdered.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPaneOrdered.setViewportView(tableOrdered);

		JPanel panel = new JPanel();
		panel.setBounds(0, 707, 605, 45);
		panelBillDetail.add(panel);
		panel.setLayout(null);

		JButton btnAddTable = new JButton("THÊM BÀN");
		btnAddTable.setBounds(175, 0, 135, 40);
		btnAddTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AddTable();
			}
		});
		panel.add(btnAddTable);
		btnAddTable.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnDisableTable = new JButton("BÁO BÀN HỎNG");
		btnDisableTable.setBounds(312, 0, 163, 40);
		btnDisableTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					// Kiểm tra xem bàn đang có khách không
					String sql = "select NgayGioTra from chonban where MaBan = " + tableID
							+ " order by NgayGioDen desc limit 1 ";
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					String date = "";
					if (rs.next()) {
						date = rs.getString(1);
					}
					if (date != null) {
						System.out.println(date);
						sql = "update ban set TrangThai = 0 where MaBan =" + tableID;
						conn = DBConnection.getConnection();
						statement = conn.createStatement();
						statement.executeUpdate(sql);
						Message.messageBox("Báo bàn hỏng thành công", "THÔNG BÁO");
						statement.close();
						conn.close();
						dispose();
						new AdminFrame();
					} else {
						Message.messageBox("Bàn đang có khách", "THÔNG BÁO");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
		});
		panel.add(btnDisableTable);
		btnDisableTable.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnEnableTable = new JButton("THAY BÀN");
		btnEnableTable.setBounds(477, 0, 126, 40);
		btnEnableTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					// Kiểm tra xem bàn đang hỏng thì thay thế
					boolean kt = false;
					String sql = "SELECT * FROM coffeeshop.ban where TrangThai = 0 and MaBan =" + tableID;
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					if (rs.next()) {
						kt = true;
					}

					if (kt == true) {
						sql = "update ban set TrangThai = 1 where MaBan =" + tableID;
						statement = conn.createStatement();
						statement.executeUpdate(sql);
						Message.messageBox("Thay bàn thành công", "THÔNG BÁO");
						dispose();
						new AdminFrame();
					} else {
						Message.messageBox("Bàn vẫn còn tốt", "THÔNG BÁO");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
		});
		panel.add(btnEnableTable);
		btnEnableTable.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnAddOrder = new JButton("THÊM HOÁ ĐƠN");
		btnAddOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddOrder.setBounds(2, 0, 171, 40);
		btnAddOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int id = Integer.parseInt(tableID);
					if (id > 0) {
						// Kiểm tra xem bàn có khả dụng không
						String sql = "SELECT TrangThai FROM coffeeshop.ban where MaBan =" + id;
						conn = DBConnection.getConnection();
						statement = conn.createStatement();
						rs = statement.executeQuery(sql);
						boolean TrangThai = true;
						if (rs.next()) {
							TrangThai = rs.getBoolean(1);
						}
						if (TrangThai == true) {
							// Kiểm tra bàn có khách không
							sql = "SELECT * FROM coffeeshop.chonban where NgayGioTra is null and MaBan =" + tableID
									+ " ORDER BY NgayGioDen DESC LIMIT 1";
							conn = DBConnection.getConnection();
							statement = conn.createStatement();
							rs = statement.executeQuery(sql);
							boolean kt = false;
							if (rs.next()) {
								kt = true;
							}
							if (kt == true) {
								Message.messageBox("Bàn đang có khách không thể tạo hoá đơn mới", "THÔN BÁO");
							} else {
								// Tạo hoá đơn cho khách hàng
								AddOrder.tableID = Integer.parseInt(tableID);
								AddOrder.MaNV = user.getId();
								dispose();
								new AddOrder();
							}
						} else {
							Message.messageBox("Bàn đang hư vui lòng chọn bàn khác.", "THÔNG BÁO");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				} catch (NumberFormatException e2) {
					// TODO: handle exception
					Message.messageBox("Vui lòng chọn bàn trống", "THÔNG BÁO");
				}
			}
		});
		panel.add(btnAddOrder);

		JPanel panelControl = new JPanel();
		panelControl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelControl.setBounds(0, 38, 605, 45);
		panelBillDetail.add(panelControl);
		panelControl.setLayout(null);

		// Xuất hoá đơn
		JButton btnReport = new JButton("THANH TOÁN");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int count = tableOrdered.getRowCount();
				if (count <= 0) {
					Message.messageBox("Vui lòng chọn bàn cần thanh toán", "THÔNG BÁO");
				} else {
					PaidOrder.customer = lblCustomerName.getText();
					PaidOrder.total = Double.parseDouble(lblTotalPrice.getText());
					List<Order> orders = new ArrayList<>();
					for (int i = 0; i < count; i++) {
						Order order = new Order();
						order.setName(tableOrdered.getValueAt(i, 0).toString());
						order.setNumber(Long.parseLong(tableOrdered.getValueAt(i, 1).toString()));
						order.setPrice(Double.parseDouble(tableOrdered.getValueAt(i, 2).toString()));
						orders.add(order);
					}
					PaidOrder.orders = orders;
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calobj = Calendar.getInstance();
					String date = df.format(calobj.getTime()).toString();
					PaidOrder.date = date;
					// Cập nhật vào csdl bàn đã thanh toán
					df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					calobj = Calendar.getInstance();
					String curentDate = df.format(calobj.getTime()).toString();
					try {
						conn = DBConnection.getConnection();
						String sql = "update chonban set NgayGioTra='" + curentDate + "' where MaBan=" + tableID
								+ " and MaKH = " + MaKH;
						statement = conn.createStatement();
						statement.executeUpdate(sql);
						statement.close();
						conn.close();

						// Cập nhật lại tổng tiền trong bảng hoá đơn
						double trigia = Double.parseDouble(lblTotalPrice.getText());

						conn = DBConnection.getConnection();
						sql = "UPDATE hoadon SET TriGia =" + trigia + "WHERE SoHD = " + soHD;
						statement = conn.createStatement();
						statement.executeUpdate(sql);
						statement.close();
						conn.close();

						// Lấy NgayGioDen của bàn
						conn = DBConnection.getConnection();
						sql = "SELECT NgayGioDen FROM chonban WHERE MaKH = " + MaKH + " AND NgayGioTra IS NULL";
						statement = conn.createStatement();
						rs = statement.executeQuery(sql);

						String NgayGioDen = null;
						if (rs.next()) {
							NgayGioDen = rs.getString(1);
						}
						statement.close();
						conn.close();
						// Cập nhật bảng chonban báo bàn đã thanh toán
						conn = DBConnection.getConnection();
						sql = "UPDATE chonban SET NgayGioTra = '" + curentDate + "'WHERE MaKH = " + MaKH
								+ " AND NgayGioDen = " + NgayGioDen;
						statement = conn.createStatement();
						statement.executeUpdate(sql);

						statement.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					} catch (Exception e2) {
						// TODO: handle exception
					}
					dispose();
					new PaidOrder();
				}
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReport.setBounds(455, 3, 150, 42);
		panelControl.add(btnReport);

		JLabel lblTotal = new JLabel("TỔNG:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(0, 2, 83, 40);
		panelControl.add(lblTotal);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setOpaque(true);
		lblTotal.setBackground(new Color(255, 112, 67));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblTotalPrice = new JLabel();
		lblTotalPrice.setBounds(83, 2, 183, 40);
		panelControl.add(lblTotalPrice);
		lblTotalPrice.setForeground(Color.WHITE);

		lblTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalPrice.setOpaque(true);
		lblTotalPrice.setBackground(new Color(255, 112, 67));

		JLabel lblCurency = new JLabel("VNĐ");
		lblCurency.setBounds(266, 2, 50, 40);
		panelControl.add(lblCurency);
		lblCurency.setForeground(Color.WHITE);
		lblCurency.setOpaque(true);
		lblCurency.setBackground(new Color(255, 112, 67));
		lblCurency.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurency.setFont(new Font("Tahoma", Font.BOLD, 20));

		// Cập nhật order
		JButton btnUpdateOrder = new JButton("CẬP NHẬT");
		btnUpdateOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdateOrder.setBounds(321, 3, 132, 42);
		btnUpdateOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = tableOrdered.getSelectedRow();
				if (index != -1) {
					try {
						int soluong = Integer.parseInt(tableOrdered.getValueAt(index, 1).toString());
						double tt = Double.parseDouble(tableOrdered.getValueAt(index, 2).toString());
						double gia = Double.parseDouble(tableOrdered.getValueAt(index, 4).toString());
						int current = (int) (tt / gia);
						if (soluong < current) {
							Message.messageBox("Số lượng không hợp lệ", "THÔNG BÁO");
							soluong = current;
							tableOrdered.setValueAt(soluong, index, 1);
							tableModelOrdered.fireTableDataChanged();

						} else {
							gia = Double.parseDouble(tableOrdered.getValueAt(index, 4).toString());
							tableOrdered.setValueAt((soluong * gia), index, 2);
							tableModelOrdered.fireTableDataChanged();
							// Lưu thông tin thay đổi chitiethoadon xuống CSDL
							int MaTU = Integer.parseInt(tableOrdered.getValueAt(index, 3).toString());
							String sql2 = "update coffeeshop.chitiethoadon set SoLuong = " + soluong + " where SoHD = "
									+ soHD + " and MaTU = " + MaTU;
							Connection conn2 = DBConnection.getConnection();
							Statement stm2 = conn2.createStatement();
							stm2.executeUpdate(sql2);
							stm2.close();
							conn2.close();
							// Cập nhật lại tổng tiền trong bảng hoá đơn
							if (soHD > 0) {
								loadOrdered(Integer.parseInt(tableID));
								double trigia = Double.parseDouble(lblTotalPrice.getText());
								conn = DBConnection.getConnection();
								String sql = "UPDATE hoadon SET TriGia =" + trigia + "WHERE SoHD = " + soHD;
								statement = conn.createStatement();
								statement.executeUpdate(sql);
								statement.close();
								conn.close();
								Message.messageBox("Cập nhật thành công", "THÔNG BÁO");
							} else {
								System.out.println("Error");
							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}

				} else {
					if (soHD > 0) {
						// Cập nhật lại tổng tiền
						try {
							double trigia = Double.parseDouble(lblTotalPrice.getText());
							conn = DBConnection.getConnection();
							String sql = "UPDATE hoadon SET TriGia =" + trigia + "WHERE SoHD = " + soHD;
							statement = conn.createStatement();
							statement.executeUpdate(sql);
							statement.close();
							conn.close();
							Message.messageBox("Cập nhật lại tổng tiền thành công", "THÔNG BÁO");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						Message.messageBox("Vui lòng chọn bàn cần cập nhật", "THÔNG BÁO");
					}
				}
				// Cập nhật lại tổng hoá đơn
				int count = tableOrdered.getRowCount();
				double total = 0.0;
				for (int i = 0; i < count; i++) {
					total += Double.parseDouble(tableOrdered.getValueAt(i, 2).toString());
				}
				lblTotalPrice.setText(total + "");
			}
		});

		panelControl.add(btnUpdateOrder);

		JPanel panelAddDrink = new JPanel();
		panelAddDrink.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAddDrink.setBounds(0, 85, 605, 40);
		panelBillDetail.add(panelAddDrink);
		panelAddDrink.setLayout(null);

		JLabel lblDrinkTitle = new JLabel("Tên Món:");
		lblDrinkTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDrinkTitle.setBounds(0, 0, 93, 40);
		panelAddDrink.add(lblDrinkTitle);

		lblDrinkName = new JLabel("");
		lblDrinkName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDrinkName.setBounds(99, 0, 229, 40);
		panelAddDrink.add(lblDrinkName);

		JLabel lblQuantityTitle = new JLabel("SL:");
		lblQuantityTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuantityTitle.setBounds(335, 0, 42, 40);
		panelAddDrink.add(lblQuantityTitle);

		txtQuantity = new JTextField();
		txtQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtQuantity.setBounds(379, 0, 74, 40);
		panelAddDrink.add(txtQuantity);
		txtQuantity.setColumns(10);

		JButton btnAddDrink = new JButton("THÊM MÓN");
		btnAddDrink.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddDrink.setBounds(465, 0, 128, 40);
		// Sự kiện cho nút thêm món
		btnAddDrink.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ID của bàn
				int id = -1;
				try {
					id = Integer.parseInt(tableID);
					int index = tableDrink.getSelectedRow();
					if (id > 0) {
						String drinkName = lblDrinkName.getText();
						int number = Integer.parseInt(txtQuantity.getText());
						if (index != -1 && number > 0) {
							drinkPrice = Double.parseDouble(tableDrink.getValueAt(index, 1).toString()) * number;
							tableModelOrdered.addRow(new Object[] { drinkName, number, drinkPrice });
							tableModelOrdered.fireTableDataChanged();
							// Cập nhật lại thành tiền
							int rowCount = tableModelOrdered.getRowCount();
							double total = 0;
							if (rowCount > 0) {
								for (int i = 0; i < rowCount; i++) {
									total += Double.parseDouble(tableModelOrdered.getValueAt(i, 2).toString());
								}
								lblTotalPrice.setText(total + "");
							}
							// Cập nhật vào CSDL
							try {
								int MaTU = Integer.parseInt(tableDrink.getValueAt(index, 2).toString());
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Calendar calobj = Calendar.getInstance();

								// Kiểm tra bàn đã có hoá đơn chưa
								if (soHD <= 0) {
									// Lấy số hoá đơn cuối cùng trong bảng để tạo ra số hoá đơn mới
									String sql = "SELECT SoHD FROM hoadon ORDER BY SoHD DESC LIMIT 1";
									conn = DBConnection.getConnection();
									statement = conn.createStatement();
									rs = statement.executeQuery(sql);
									if (rs.next()) {
										soHD = rs.getInt(1) + 1;
									} else {
										soHD = 1;
									}

									statement.close();
									conn.close();
									if (soHD > 0) {
										// Bàn chưa có hoá đơn, tạo ra số hoá đơn mới
										df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										calobj = Calendar.getInstance();
										String thoidiem = df.format(calobj.getTime()).toString();
										int MaNV = user.getId();
										sql = "INSERT INTO hoadon VALUES(" + soHD + ",0.0," + MaNV + "," + MaKH + ",'"
												+ thoidiem + "');";
										conn = DBConnection.getConnection();
										statement = conn.createStatement();
										statement.executeUpdate(sql);
									}
									statement.close();
									rs.close();

								}
								if (soHD > 0) {
									// Thêm món
									String sql2 = "insert into coffeeshop.chitiethoadon values(" + soHD + "," + MaTU
											+ "," + number + ",'" + df.format(calobj.getTime()) + "')";
									Connection conn2 = DBConnection.getConnection();
									Statement stm2 = conn2.createStatement();
									stm2.executeUpdate(sql2);
								}
								// Cập nhật lại tổng tiền trong bảng hoá đơn
								if (soHD > 0) {
									double trigia = Double.parseDouble(lblTotalPrice.getText());
									conn = DBConnection.getConnection();
									String sql = "UPDATE hoadon SET TriGia =" + trigia + "WHERE SoHD = " + soHD;
									statement = conn.createStatement();
									statement.executeUpdate(sql);
									statement.close();
									conn.close();
								}
								soHD = -1;
								Message.messageBox("Thêm món thành công", "THÔNG BÁO");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {
							Message.messageBox("Lỗi rồi !!!!", "THÔNG BÁO");
						}
					}
				} catch (NumberFormatException e2) {
					// TODO: handle exception
					Message.messageBox("Vui lòng chọn bàn !!!!", "THÔNG BÁO");
				}
			}

		});
		panelAddDrink.add(btnAddDrink);

		JPanel panelLogin = new JPanel();
		panelLogin.setBounds(1269, 0, 325, 43);
		panelLogin.setBackground(Color.WHITE);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);

		JLabel lblLoginAs = new JLabel("LOGIN AS:");
		lblLoginAs.setForeground(Color.BLUE);
		lblLoginAs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLoginAs.setBounds(12, 0, 109, 43);
		panelLogin.add(lblLoginAs);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsername.setBounds(133, 0, 192, 43);
		lblUsername.setText(user.getUsername().toUpperCase());
		panelLogin.add(lblUsername);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 98, 988, 766);

		// TODO: Load khu và bàn từ csdl
		loadTable();
		contentPane.add(tabbedPane);

		loadDrink();

		// Mặc định load tiêu đề cột lên
		loadOrdered(-1);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		// TODO: PHÂN QUYỀN
		// Phân quyền chỉ Admin mới có quyền quản lý nhân viên
		if (user.getPermission().toLowerCase().equals("admin")) {

		} else if (user.getPermission().toLowerCase().equals("staff")) {
			mntmStaffManager.setEnabled(false);
			// mntmCustomerManager.setEnabled(false);
			// mntmSupplierManager.setEnabled(false);
			// mntmDrinkManager.setEnabled(false);
		}
	}

	public static void loadTable() {
		// LOAD KHU TỪ CSDL
		int soKhu = 0;
		try {
			String sql = "SELECT COUNT(*) FROM VITRI";
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				soKhu = rs.getInt(1);
			}

			if (soKhu > 0) {
				JPanel[] area = new MyArea[soKhu];
				for (int i = 0; i < soKhu; i++) {
					area[i] = new MyArea(i + 1);
					tabbedPane.add("KHU " + (i + 1), area[i]);
					tabbedPane.setIconAt(i, new ImageIcon(AdminFrame.class.getResource("/resources/logo.jpg")));
					tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
	}

	// TODO : Lấy chi tiết hoá đơn của từng bàn vào bảng
	@SuppressWarnings("unchecked")
	public static void loadOrdered(int MaBan) {
		// TODO Auto-generated method stub
		Vector<String> vtColumn = new Vector<String>();
		Vector<String> vtRow = new Vector<String>();
		@SuppressWarnings("rawtypes")
		Vector vtData = new Vector();
		vtColumn.add("Mặt hàng");
		vtColumn.add("Số lượng");
		vtColumn.add("Thành tiền");
		vtColumn.add("MaTU");
		vtColumn.add("Gia");
		soHD = -1;
		double total = 0;
		try {
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			// cho phép gửi câu lệnh sql
			String sql = "SELECT TenTU,GiaBan,chitiethoadon.SoLuong,Chitiethoadon.SoHD, thucuong.MaTU FROM"
					+ " thucuong,chitiethoadon,hoadon,chonban,ban " + "WHERE thucuong.MaTU = chitiethoadon.MaTU "
					+ "AND chitiethoadon.SoHD = hoadon.SoHD " + "AND hoadon.MaKH= chonban.MaKH "
					+ "AND chonban.MaBan= ban.MaBan AND chonban.NgayGioTra IS NULL AND ban.MaBan=" + MaBan
					+ " ORDER BY chonban.NgayGioDen";
			rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					soHD = rs.getInt(4);
					vtRow = new Vector<String>();
					vtRow.add(rs.getString(1));
					int MaTU = rs.getInt(5);
					// Lấy giá bán
					Connection conn2 = DBConnection.getConnection();
					String sql2 = "SELECT Gia FROM coffeeshop.banggia where matu = " + MaTU
							+ " order by NgayBan desc limit 1;";
					Statement stm2 = conn2.createStatement();
					ResultSet rs2 = stm2.executeQuery(sql2);
					Double gia = 0.0;
					if (rs2.next()) {
						gia = rs2.getDouble(1);
					}

					vtRow.add(rs.getString(3));
					// TODO: Giá bán * Số lượng
					double giaban = gia * rs.getDouble(3);
					total += giaban;
					vtRow.add(giaban + "");
					vtRow.add(MaTU + "");
					vtRow.add(gia + "");

					vtData.add(vtRow);
				}
				// TODO: Thành tiền
				lblTotalPrice.setText(total + "");
			} else {
				soHD = -1;
			}
			statement.close();
			conn.close();

			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

			tableModelOrdered = new DefaultTableModel(vtData, vtColumn);

			tableOrdered.setModel(tableModelOrdered);
			tableModelOrdered.fireTableDataChanged();

			tableOrdered.getColumnModel().getColumn(0).setResizable(false);
			tableOrdered.getColumnModel().getColumn(0).setPreferredWidth(150);
			tableOrdered.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

			tableOrdered.getColumnModel().getColumn(1).setResizable(false);
			tableOrdered.getColumnModel().getColumn(1).setPreferredWidth(80);
			tableOrdered.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

			tableOrdered.getColumnModel().getColumn(2).setResizable(false);
			tableOrdered.getColumnModel().getColumn(2).setPreferredWidth(80);
			tableOrdered.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

			tableOrdered.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
			tableOrdered.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
			tableOrdered.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(0);
			tableOrdered.getColumnModel().getColumn(3).setMaxWidth(0);
			tableOrdered.getColumnModel().getColumn(3).setPreferredWidth(0);
			tableOrdered.getColumnModel().getColumn(3).setMinWidth(0);

			tableOrdered.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
			tableOrdered.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
			tableOrdered.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(0);
			tableOrdered.getColumnModel().getColumn(4).setMaxWidth(0);
			tableOrdered.getColumnModel().getColumn(4).setPreferredWidth(0);
			tableOrdered.getColumnModel().getColumn(4).setMinWidth(0);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// Lấy dữ liệu từ CSDL bỏ vào Jtable
	@SuppressWarnings("unchecked")
	public void loadDrink() {
		Vector<String> vtColumn = new Vector<String>();
		Vector<String> vtRow = new Vector<String>();
		@SuppressWarnings("rawtypes")
		Vector vtData = new Vector();
		vtColumn.add("Mặt hàng");
		vtColumn.add("Giá");
		vtColumn.add("MaTU");
		try {
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			// cho phép gửi câu lệnh sql
			String sql = "SELECT thucuong.MaTU,TenTU FROM THUCUONG";
			rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					vtRow = new Vector<String>();
					vtRow.add(rs.getString(2) + "");
					int MaTU = rs.getInt(1);

					// Lấy giá mới nhất
					String sql2 = "SELECT Gia FROM banggia  where MaTU = " + MaTU + " order by NgayBan desc limit 1;";
					Connection conn2 = DBConnection.getConnection();
					Statement stm2 = conn2.createStatement();
					ResultSet rs2 = stm2.executeQuery(sql2);
					Double gia = 0.0;
					if (rs2.next()) {
						gia = rs2.getDouble(1);
					}
					vtRow.add(gia + "");
					vtRow.add(MaTU + "");
					vtData.add(vtRow);
				}
			}

			statement.close();
			conn.close();

			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);

			tableModel = new DefaultTableModel(vtData, vtColumn);

			tableDrink.setModel(tableModel);
			tableModel.fireTableDataChanged();
			tableDrink.getColumnModel().getColumn(0).setResizable(false);
			tableDrink.getColumnModel().getColumn(0).setPreferredWidth(150);
			tableDrink.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

			tableDrink.getColumnModel().getColumn(1).setResizable(false);
			tableDrink.getColumnModel().getColumn(1).setPreferredWidth(80);
			tableDrink.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

			tableDrink.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
			tableDrink.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
			tableDrink.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(0);
			tableDrink.getColumnModel().getColumn(2).setMaxWidth(0);
			tableDrink.getColumnModel().getColumn(2).setPreferredWidth(0);
			tableDrink.getColumnModel().getColumn(2).setMinWidth(0);

		} catch (Exception ex) {
		}
	}
}
