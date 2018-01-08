package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.DBConnection;

public class CustomerManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3321824675765824843L;
	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable CustomerTable;
	private JTextField txtFullName;
	private JTextField txtBirthday;
	private JTextField txtAddress;
	private JTextField txtPhoneNumber;
	private JComboBox<String> cbxCustomerID;
	private JComboBox<String> cbxGender;
	private JComboBox<String> cbxTypeOfCustomer;
	private DefaultTableModel tableModel;
	private Connection conn;
	private Statement statement;
	private ResultSet rs;
	private JButton btnEdit, btnUpdate, btnExit, btnCancel, btnNew;
	private JDateChooser getDate;
	/**
	 * Create the frame.
	 */
	ActionListener action = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {
			// Cập nhật khách hàng
			case "Update":
				update();
				break;
			case "Edit":
				edit();
				break;
			case "New":
				add();
				break;
			case "Cancel":
				setEditableForm(false);
				btnCancel.setEnabled(false);
				btnUpdate.setEnabled(false);
				btnEdit.setEnabled(true);
				btnNew.setEnabled(true);
				break;
			case "Save":
				save();
				break;
			default:
				break;
			}
		}
	};
	private JLabel lblSearch;
	private JTextField txtSearchString;
	private JButton btnSave;

	public CustomerManager() {
		// Đóng Form thì mở lại cửa sổ admin
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AdminFrame();
				// super.windowClosing(e);
			}
		});

		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(121, 85, 72));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1594, 57);
		contentPane.add(lblTitle);
		scrollPane.setBounds(0, 290, 1594, 575);
		contentPane.add(scrollPane);

		CustomerTable = new JTable() {

			private static final long serialVersionUID = 7673810051747310534L;

			// Không cho phép sửa nội dung của ô
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

		};

		CustomerTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CustomerTable.setRowSelectionAllowed(true);
		CustomerTable.getTableHeader().setReorderingAllowed(false);
		// Không cho phép thay đổi độ dài cột
		CustomerTable.getTableHeader().setResizingAllowed(false);
		CustomerTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(CustomerTable);
		CustomerTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setViewportView(CustomerTable);

		// Sự kiện click trên cell
		CustomerTable.addMouseListener(new MouseListener() {

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
				int index = CustomerTable.getSelectedRow();
				if (index != -1) {
					// Đổ dữ liệu vào form edit
					cbxCustomerID.setSelectedItem(CustomerTable.getValueAt(index, 0) + "");
					txtFullName.setText(CustomerTable.getValueAt(index, 1).toString());
					cbxGender.setSelectedItem(CustomerTable.getValueAt(index, 2));
					txtBirthday.setText(CustomerTable.getValueAt(index, 3).toString());
					txtAddress.setText(CustomerTable.getValueAt(index, 4).toString());
					txtPhoneNumber.setText(CustomerTable.getValueAt(index, 5).toString());
					cbxTypeOfCustomer.setSelectedItem(CustomerTable.getValueAt(index, 6));
				}
			}
		});

		JPanel editablePanel = new JPanel();
		editablePanel.setBounds(0, 70, 996, 154);
		contentPane.add(editablePanel);
		editablePanel.setLayout(null);
		JLabel lblFullName = new JLabel("Họ và tên :");
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFullName.setBounds(12, 62, 165, 30);
		editablePanel.add(lblFullName);

		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFullName.setBounds(189, 62, 338, 30);
		editablePanel.add(txtFullName);
		txtFullName.setColumns(10);
		txtFullName.setEditable(false);

		JLabel lblBirthday = new JLabel("Ngày sinh :");
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBirthday.setBounds(565, 62, 102, 30);
		editablePanel.add(lblBirthday);

		txtBirthday = new JTextField();
		txtBirthday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(702, 62, 252, 30);
		txtBirthday.setEditable(false);
		editablePanel.add(txtBirthday);

		JLabel lblAddress = new JLabel("Địa chỉ :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddress.setBounds(12, 108, 165, 30);
		editablePanel.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAddress.setColumns(10);
		txtAddress.setBounds(189, 108, 338, 30);
		txtAddress.setEditable(false);
		editablePanel.add(txtAddress);

		JLabel lblPhoneNumber = new JLabel("Điện thoại :");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhoneNumber.setBounds(286, 16, 102, 30);
		editablePanel.add(lblPhoneNumber);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(387, 16, 140, 30);
		txtPhoneNumber.setEditable(false);
		editablePanel.add(txtPhoneNumber);

		JLabel lblGender = new JLabel("Giới tính :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGender.setBounds(565, 108, 102, 30);
		editablePanel.add(lblGender);

		cbxGender = new JComboBox<String>();
		cbxGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxGender.setBounds(667, 108, 85, 30);
		cbxGender.setSelectedItem(null);
		cbxGender.setEnabled(false);
		editablePanel.add(cbxGender);

		JLabel lblTypeOfCustomer = new JLabel("Loại khách :");
		lblTypeOfCustomer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfCustomer.setBounds(764, 104, 115, 30);
		editablePanel.add(lblTypeOfCustomer);

		cbxTypeOfCustomer = new JComboBox<String>();
		cbxTypeOfCustomer.setSelectedItem(null);
		cbxTypeOfCustomer.setEditable(false);
		cbxTypeOfCustomer.setEnabled(false);
		cbxTypeOfCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxTypeOfCustomer.setBounds(891, 109, 91, 30);
		cbxTypeOfCustomer.setSelectedItem(null);
		cbxTypeOfCustomer.setEnabled(false);
		cbxTypeOfCustomer.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					showTypeOfCustomer();
				}
			}

			private void showTypeOfCustomer() {

			}
		});
		editablePanel.add(cbxTypeOfCustomer);

		JLabel lblMaKH = new JLabel("Mã khách hàng :");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKH.setBounds(12, 16, 165, 30);
		editablePanel.add(lblMaKH);

		cbxCustomerID = new JComboBox<String>();
		cbxCustomerID.setSelectedItem(null);
		cbxCustomerID.setEditable(false);
		cbxCustomerID.setEnabled(false);
		cbxCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxCustomerID.setBounds(189, 16, 72, 30);
		cbxCustomerID.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					showCustomer();
				}
			}

			private void showCustomer() {
				// TODO Auto-generated method stub

			}
		});
		editablePanel.add(cbxCustomerID);

		// TODO: Quản lý chọn ngày sinh nhân viên
		getDate = new JDateChooser();
		getDate.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getDate.setBounds(962, 62, 20, 30);
		getDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				JDateChooser chooser = (JDateChooser) e.getSource();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				txtBirthday.setText(sdf.format(chooser.getDate()));

			}
		});
		getDate.setEnabled(false);
		editablePanel.add(getDate);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 237, 985, 41);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);

		btnEdit = new JButton("Chỉnh sửa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setBounds(8, 0, 154, 40);
		btnEdit.setActionCommand("Edit");
		btnEdit.addActionListener(action);
		buttonPanel.add(btnEdit);

		btnNew = new JButton("Mở form");
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNew.setBounds(494, 0, 154, 40);
		btnNew.setActionCommand("New");
		btnNew.addActionListener(action);
		buttonPanel.add(btnNew);

		btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose();
				// new AdminFrame();
				System.exit(1);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(818, 0, 154, 40);
		buttonPanel.add(btnExit);

		btnUpdate = new JButton("Lưu thay đổi");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setActionCommand("Update");
		btnUpdate.addActionListener(action);
		btnUpdate.setBounds(332, 0, 154, 40);
		btnUpdate.setEnabled(false);
		buttonPanel.add(btnUpdate);

		btnCancel = new JButton("Huỷ");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.setActionCommand("Cancel");
		btnCancel.setBounds(170, 0, 154, 40);
		btnCancel.addActionListener(action);
		btnCancel.setEnabled(false);
		buttonPanel.add(btnCancel);

		btnSave = new JButton("Thêm KH");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(656, 0, 154, 40);
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(action);
		buttonPanel.add(btnSave);

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(997, 70, 597, 207);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);

		JLabel lblSearchTitle = new JLabel("TÌM KIẾM KHÁCH HÀNG");
		lblSearchTitle.setForeground(Color.WHITE);
		lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSearchTitle.setOpaque(true);
		lblSearchTitle.setBackground(new Color(33, 150, 243));
		lblSearchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchTitle.setBounds(0, 0, 597, 45);
		searchPanel.add(lblSearchTitle);

		lblSearch = new JLabel("Tìm theo:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSearch.setBounds(10, 58, 88, 30);
		searchPanel.add(lblSearch);

		JComboBox<String> cbxSearchType = new JComboBox<String>();
		cbxSearchType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxSearchType.addItem("Mã khách hàng");
		cbxSearchType.addItem("Họ tên khách hàng");
		cbxSearchType.setBounds(110, 58, 262, 30);
		searchPanel.add(cbxSearchType);

		txtSearchString = new JTextField();
		txtSearchString.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSearchString.setColumns(10);
		txtSearchString.setBounds(8, 98, 362, 30);
		searchPanel.add(txtSearchString);

		JButton btnSeach = new JButton("Tìm kiếm");
		btnSeach.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSeach.setBounds(378, 98, 211, 30);
		btnSeach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String sql;
					// Tìm theo MaKH
					if (cbxSearchType.getSelectedIndex() == 0) {
						int MaKH = 0;
						try {
							MaKH = Integer.parseInt(txtSearchString.getText());
							sql = "SELECT * FROM coffeeshop.khachhang where MaKH = " + MaKH;
							conn = DBConnection.getConnection();
							statement = conn.createStatement();
							rs = statement.executeQuery(sql);
							if (rs.next()) {
								txtFullName.setText(rs.getString(2));
								if (rs.getBoolean(3) == true) {
									cbxGender.setSelectedIndex(1);
								} else {
									cbxGender.setSelectedIndex(0);
								}
								txtBirthday.setText(rs.getString(4));
								txtAddress.setText(rs.getString(5));
								txtPhoneNumber.setText(rs.getString(6));
								cbxTypeOfCustomer.setSelectedItem(rs.getString(7));
								int n = cbxCustomerID.getItemCount();
								for (int i = 0; i < n; i++) {
									if (Integer.parseInt(cbxCustomerID.getItemAt(i)) == MaKH) {
										cbxCustomerID.setSelectedIndex(i);
									}
								}

								statement.close();
								conn.close();

							} else {
								txtFullName.setText("");
								txtBirthday.setText("");
								txtAddress.setText("");
								txtPhoneNumber.setText("");
								cbxTypeOfCustomer.setSelectedIndex(-1);
								cbxGender.setSelectedIndex(-1);
								cbxCustomerID.setSelectedIndex(-1);
							}

						} catch (NumberFormatException ex) {
							// TODO: handle exception
							Message.messageBox("Mã khách hàng không hợp lệ", "THÔNG BÁO");
						}
					} else {
						// Tìm theo Họ tên khách hàng
						String hoten = txtSearchString.getText().trim().toLowerCase();
						if (hoten.length() > 0) {
							sql = "SELECT * FROM khachhang WHERE HoTenKH LIKE '%" + hoten + "%'";
							conn = DBConnection.getConnection();
							statement = conn.createStatement();
							rs = statement.executeQuery(sql);
							if (rs != null)
								SearchCustomer.rs = rs;
							new SearchCustomer();
							rs.close();
							statement.close();
							conn.close();
						} else {
							Message.messageBox("Vui lòng nhập họ tên cần tìm kiếm", "Thông Báo");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
				}
			}
		});
		searchPanel.add(btnSeach);

		loadTable();
		loadDataCBX();
		// Disable các nút thêm sửa xoá

	}

	// Cho phép chỉnh sửa thông tin
	protected void edit() {
		// TODO Auto-generated method stub
		int index = CustomerTable.getSelectedRow();
		if (index != -1) {
			setEditableForm(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(true);
		} else {
			Message.messageBox("Bạn chưa chọn khách hàng!!!", "THÔNG BÁO");
		}
	}

	private void setEditableForm(boolean editable) {
		getDate.setEnabled(editable);
		txtFullName.setEditable(editable);
		txtAddress.setEditable(editable);
		txtPhoneNumber.setEditable(editable);
		cbxGender.setEnabled(editable);
		cbxTypeOfCustomer.setEnabled(editable);
	}

	protected void save() {
		// TODO Auto-generated method stub
		int MaKH = -1;
		try {
			// Lấy thông tin chỉnh sửa
			String HoTenKH = txtFullName.getText().trim();
			String DiaChi = txtAddress.getText().trim();
			String SoDienThoai = txtPhoneNumber.getText().trim();
			String NgaySinh = txtBirthday.getText();
			String gioitinh = cbxGender.getSelectedItem().toString();
			String GioiTinh = "0"; // Nữ
			if (gioitinh.toLowerCase().equals("nam")) {
				GioiTinh = "1";
			}
			String loaikhach = cbxTypeOfCustomer.getSelectedItem().toString();
			if (HoTenKH.length() > 0 && DiaChi.length() > 0 && SoDienThoai.length() > 0 && NgaySinh.length() > 0) {
				// Generate MaNV
				String sql = "SELECT MaKH FROM khachhang ORDER BY MaKH DESC LIMIT 1";
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				rs = statement.executeQuery(sql);
				if (rs.next()) {
					MaKH = rs.getInt(1) + 1;
				}
				if (MaKH != -1) {
					sql = "INSERT INTO khachhang VALUES(" + MaKH + ",'" + HoTenKH + "'," + GioiTinh + ",'" + NgaySinh
							+ "','" + DiaChi + "','" + SoDienThoai + "'," + loaikhach + ")";
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					statement.executeUpdate(sql);
					Message.messageBox("Thêm khách hàng mới thành công", "THÔNG BÁO");
					setEditableForm(false);
					loadTable();
					btnEdit.setEnabled(true);
					btnCancel.setEnabled(false);
					txtFullName.setText("");
					txtBirthday.setText("");
					txtAddress.setText("");
					txtPhoneNumber.setText("");
				}
			} else {
				Message.messageBox("Vui lòng kiểm tra lại thông tin khách hàng mới", "THÔNG BÁO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (NumberFormatException ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
		} catch (Exception ex2) {
			// TODO: handle exception
			System.out.println(ex2.getMessage());
		}
	}

	// Thêm khách hàng
	protected void add() {
		// TODO Auto-generated method stub
		setEditableForm(true);
		btnEdit.setEnabled(false);
		btnCancel.setEnabled(true);
		txtFullName.setText("");
		txtAddress.setText("");
		txtPhoneNumber.setText("");
		txtBirthday.setText("");
		txtFullName.requestFocus();
		cbxGender.setSelectedIndex(0);
		cbxTypeOfCustomer.setSelectedIndex(-1);
	}

	protected void update() {
		// TODO Auto-generated method stub
		try {
			// Lấy thông tin chỉnh sửa
			int MaKH = Integer.parseInt(cbxCustomerID.getSelectedItem().toString());
			String HoTenKH = txtFullName.getText().trim();
			String DiaChi = txtAddress.getText().trim();
			String SoDienThoai = txtPhoneNumber.getText().trim();
			String NgaySinh = txtBirthday.getText();
			String gioitinh = cbxGender.getSelectedItem().toString();
			String GioiTinh = "0"; // Nữ
			if (gioitinh.toLowerCase().equals("nam")) {
				GioiTinh = "1";
			}
			String loaikhach = cbxTypeOfCustomer.getSelectedItem().toString();
			String sql = "UPDATE khachhang SET HoTenKH = '" + HoTenKH + "', GioiTinh = " + GioiTinh + ", NgaySinh = '"
					+ NgaySinh + "', DiaChi = '" + DiaChi + "', SoDienThoai ='" + SoDienThoai + "', MaLoaiKH = "
					+ loaikhach + " WHERE MaKH = " + MaKH;
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			Message.messageBox("Cập nhật khách hàng thành công", "THÔNG BÁO");
			// tableModel.fireTableDataChanged();
			setEditableForm(false);
			loadTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (NumberFormatException ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
		} catch (Exception ex2) {
			// TODO: handle exception
			System.out.println(ex2.getMessage());
		}

	}

	// TODO: Lấy dữ liệu từ CSDL bỏ vào Jtable
	@SuppressWarnings("unchecked")
	public void loadTable() {
		Vector<String> vtColumn = new Vector<String>();
		Vector<String> vtRow = new Vector<String>();
		@SuppressWarnings("rawtypes")
		Vector vtData = new Vector();
		vtColumn.add("Mã khách hàng");
		vtColumn.add("Họ và tên");
		vtColumn.add("Giới tính");
		vtColumn.add("Ngày Sinh");
		vtColumn.add("Địa chỉ");
		vtColumn.add("Số điện thoại");
		vtColumn.add("Mã Loại Khách");

		try {
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			// cho phép gửi câu lệnh sql
			String sql = "SELECT * FROM khachhang";
			rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					vtRow = new Vector<String>();
					vtRow.add(rs.getInt(1) + "");
					vtRow.add(rs.getString(2));

					String gioiTinh = "N/A";
					if (rs.getBoolean(3) == true) {
						gioiTinh = "Nam";
					} else if (rs.getBoolean(3) == false) {
						gioiTinh = "Nữ";
					}
					vtRow.add(gioiTinh);
					vtRow.add(rs.getString(4));
					vtRow.add(rs.getString(5));
					vtRow.add(rs.getString(6));
					vtRow.add(rs.getInt(7) + "");
					vtData.add(vtRow);
				}
			}

			statement.close();
			conn.close();

			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			tableModel = new DefaultTableModel(vtData, vtColumn);

			CustomerTable.setModel(tableModel);
			tableModel.fireTableDataChanged();
			CustomerTable.getColumnModel().getColumn(0).setResizable(false);
			CustomerTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			CustomerTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

			CustomerTable.getColumnModel().getColumn(1).setResizable(false);
			CustomerTable.getColumnModel().getColumn(1).setPreferredWidth(150);
			CustomerTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

			CustomerTable.getColumnModel().getColumn(2).setResizable(false);
			CustomerTable.getColumnModel().getColumn(2).setPreferredWidth(20);
			CustomerTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

			CustomerTable.getColumnModel().getColumn(3).setResizable(false);
			CustomerTable.getColumnModel().getColumn(3).setPreferredWidth(50);
			CustomerTable.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);

			CustomerTable.getColumnModel().getColumn(4).setResizable(false);
			CustomerTable.getColumnModel().getColumn(4).setPreferredWidth(400);
			CustomerTable.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);

			CustomerTable.getColumnModel().getColumn(5).setResizable(false);
			CustomerTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			CustomerTable.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);

			CustomerTable.getColumnModel().getColumn(6).setResizable(false);
			CustomerTable.getColumnModel().getColumn(6).setPreferredWidth(50);
			CustomerTable.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// Load dữ liệu cho comboBox
	private void loadDataCBX() {
		// Load TableID vào comboBox
		try {
			// Tạo kết nối
			conn = DBConnection.getConnection();
			// Lấy danh sách mã khách hàng đổ vào combo box
			String sql = "SELECT DISTINCT MaKH FROM KHACHHANG";
			statement = conn.createStatement();
			// Câu lệnh truy vấn SQL
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxCustomerID.addItem(rs.getInt(1) + "");
				cbxCustomerID.setSelectedItem(null);
			}

			// Lấy danh sách giới tính đổ vào combo box
			sql = "SELECT DISTINCT GioiTinh FROM KHACHHANG";
			statement = conn.createStatement();
			// Câu lệnh truy vấn SQL
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxGender.setSelectedItem(null);
				String gioiTinh = "N/A";
				if (rs.getBoolean(1) == false) {
					gioiTinh = "Nữ";
				} else if (rs.getBoolean(1) == true) {
					gioiTinh = "Nam";
				}
				cbxGender.addItem(gioiTinh);
			}

			// Lấy danh sách loại khách hàng đổ vào combo box
			sql = "SELECT DISTINCT MaLoaiKH FROM khachhang";
			statement = conn.createStatement();
			// Câu lệnh truy vấn SQL
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxTypeOfCustomer.addItem(rs.getInt(1) + "");
				cbxTypeOfCustomer.setSelectedItem(null);

			}

			statement.close();
			conn.close();
		} catch (SQLException e2) {
			// TODO: handle exception
			Message.messageBox("Error:\n" + e2.getMessage(), "ERROR");
		}
	}
}
