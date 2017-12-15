package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.DBConnection;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StaffManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2971328289884241741L;
	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable staffTable;
	private JTextField txtFullName;
	private JTextField txtBirthday;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JTextField txtPhoneNumber;
	private JComboBox<String> cbxStaffID;
	private JComboBox<String> cbxGender;
	private JComboBox<String> cbxStatus;
	private DefaultTableModel tableModel;
	private Connection conn;
	private Statement statement;
	private ResultSet rs;
	private JButton btnEdit, btnNew, btnUpdate, btnExit, btnCancel;
	private JDateChooser getDate;

	// Action Listener
	ActionListener action = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			switch (command) {
			// Cập nhật nhân viên
			case "Update":
				update();
				break;
			case "Edit":
				edit();
				btnNew.setEnabled(false);
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

	/**
	 * Create the frame.
	 */
	public StaffManager() {
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
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(121, 85, 72));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1594, 57);
		contentPane.add(lblTitle);
		scrollPane.setBounds(0, 290, 1594, 575);
		contentPane.add(scrollPane);

		staffTable = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7673810051747310534L;

			// Không cho phép sửa nội dung của ô
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

		};

		staffTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		staffTable.setRowSelectionAllowed(true);
		staffTable.getTableHeader().setReorderingAllowed(false);
		// Không cho phép thay đổi độ dài cột
		staffTable.getTableHeader().setResizingAllowed(false);
		staffTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(staffTable);
		staffTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setViewportView(staffTable);

		// Sự kiện click trên cell
		staffTable.addMouseListener(new MouseListener() {

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
				int index = staffTable.getSelectedRow();
				if (index != -1) {
					// Đổ dữ liệu vào form edit
					cbxStaffID.setSelectedItem(staffTable.getValueAt(index, 0) + "");
					txtFullName.setText(staffTable.getValueAt(index, 1).toString());
					cbxGender.setSelectedItem(staffTable.getValueAt(index, 2));
					txtBirthday.setText(staffTable.getValueAt(index, 3).toString());
					txtAddress.setText(staffTable.getValueAt(index, 4).toString());
					txtPhoneNumber.setText(staffTable.getValueAt(index, 5).toString());
					txtEmail.setText(staffTable.getValueAt(index, 6).toString());
					cbxStatus.setSelectedItem(staffTable.getValueAt(index, 7));
				}
			}
		});

		JPanel editablePanel = new JPanel();
		editablePanel.setBounds(0, 70, 985, 154);
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

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(565, 16, 102, 30);
		editablePanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(702, 16, 280, 30);
		txtEmail.setEditable(false);
		editablePanel.add(txtEmail);

		JLabel lblPhoneNumber = new JLabel("Điện thoại :");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhoneNumber.setBounds(263, 16, 102, 30);
		editablePanel.add(lblPhoneNumber);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(377, 16, 150, 30);
		txtPhoneNumber.setEditable(false);
		editablePanel.add(txtPhoneNumber);

		JLabel lblGender = new JLabel("Giới tính :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGender.setBounds(565, 108, 102, 30);
		editablePanel.add(lblGender);

		JLabel lblStatus = new JLabel("Trạng thái :");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStatus.setBounds(774, 108, 115, 30);
		editablePanel.add(lblStatus);

		cbxGender = new JComboBox<String>();
		cbxGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxGender.setBounds(702, 108, 60, 30);
		cbxGender.setSelectedItem(null);
		cbxGender.setEnabled(false);
		editablePanel.add(cbxGender);

		cbxStatus = new JComboBox<String>();
		cbxStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxStatus.setBounds(888, 108, 94, 30);
		cbxStatus.setSelectedItem(null);
		cbxStatus.setEnabled(false);
		editablePanel.add(cbxStatus);

		JLabel lblMaNV = new JLabel("Mã nhân viên :");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(12, 16, 165, 30);
		editablePanel.add(lblMaNV);

		cbxStaffID = new JComboBox<String>();
		cbxStaffID.setSelectedItem(null);
		cbxStaffID.setEditable(false);
		cbxStaffID.setEnabled(false);
		cbxStaffID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxStaffID.setBounds(189, 16, 50, 30);
		cbxStaffID.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					setEditableForm(false);
					btnUpdate.setEnabled(false);
					btnCancel.setEnabled(false);
				}
			}
		});

		editablePanel.add(cbxStaffID);

		// TODO: Quản lý chọn ngày sinh nhân viên
		getDate = new JDateChooser();
		getDate.setBounds(962, 62, 20, 30);
		getDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				JDateChooser chooser = (JDateChooser) e.getSource();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				txtBirthday.setText(sdf.format(chooser.getDate()));

			}
		});
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -60);// Không thuê nhân viên lớn hơn 60 tuổi
		Date min = cal.getTime();

		cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -16);// Không thuê nhân viên dưới 16 tuổi
		Date max = cal.getTime();
		getDate.getJCalendar().setMinSelectableDate(min);
		getDate.getJCalendar().setMaxSelectableDate(max);
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

		btnSave = new JButton("Thêm NV");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(656, 0, 154, 40);
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(action);
		buttonPanel.add(btnSave);

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(997, 70, 597, 207);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);

		JLabel lblSearchTitle = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblSearchTitle.setForeground(Color.WHITE);
		lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSearchTitle.setOpaque(true);
		lblSearchTitle.setBackground(new Color(33, 150, 243));
		lblSearchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchTitle.setBounds(0, 0, 597, 45);
		searchPanel.add(lblSearchTitle);

		lblSearch = new JLabel("Tìm theo:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSearch.setBounds(0, 55, 88, 30);
		searchPanel.add(lblSearch);

		JComboBox<String> cbxSearchType = new JComboBox<String>();
		cbxSearchType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxSearchType.addItem("Mã nhân viên");
		cbxSearchType.addItem("Họ tên nhân viên");
		cbxSearchType.setBounds(100, 55, 262, 30);
		searchPanel.add(cbxSearchType);

		txtSearchString = new JTextField();
		txtSearchString.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSearchString.setColumns(10);
		txtSearchString.setBounds(0, 98, 362, 30);
		searchPanel.add(txtSearchString);

		JButton btnSeach = new JButton("Tìm kiếm");
		btnSeach.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSeach.setBounds(396, 98, 172, 30);
		btnSeach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String sql;
					// Tìm theo MaNV
					if (cbxSearchType.getSelectedIndex() == 0) {
						int MaNV = 0;
						try {
							MaNV = Integer.parseInt(txtSearchString.getText());
							sql = "SELECT * FROM coffeeshop.nhanvien where MaNV = " + MaNV;
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
								txtEmail.setText(rs.getString(7));
								if (rs.getBoolean(8) == true) {
									cbxStatus.setSelectedIndex(0);
								} else {
									cbxStatus.setSelectedIndex(1);
								}
								int n = cbxStaffID.getItemCount();
								for (int i = 0; i < n; i++) {
									if (Integer.parseInt(cbxStaffID.getItemAt(i)) == MaNV) {
										cbxStaffID.setSelectedIndex(i);
									}
								}

								statement.close();
								conn.close();

							} else {
								txtFullName.setText("");
								txtBirthday.setText("");
								txtAddress.setText("");
								txtPhoneNumber.setText("");
								txtEmail.setText("");
								cbxStatus.setSelectedIndex(-1);
								cbxGender.setSelectedIndex(-1);
								cbxStaffID.setSelectedIndex(-1);
							}

						} catch (NumberFormatException ex) {
							// TODO: handle exception
							Message.messageBox("Mã nhân viên không hợp lệ", "THÔNG BÁO");
						}
					} else {
						// Tìm theo Họ tên nhân viên
						String hoten = txtSearchString.getText().trim().toLowerCase();
						if (hoten.length() > 0) {
							sql = "SELECT * FROM nhanvien WHERE HoTenNV LIKE '%" + hoten + "%'";
							conn = DBConnection.getConnection();
							statement = conn.createStatement();
							rs = statement.executeQuery(sql);
							if (rs != null)
								SearchStaff.rs = rs;
							new SearchStaff();
							rs.close();
							statement.close();
							conn.close();
						} else {
							Message.messageBox("Vui lòng nhập họ tên cần tìm kiếm", "THÔNG BÁO");
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

	protected void save() {
		// TODO Auto-generated method stub
		int MaNV = -1;
		try {
			// Lấy thông tin chỉnh sửa
			String HoTenNV = txtFullName.getText().trim();
			String DiaChi = txtAddress.getText().trim();
			String SoDienThoai = txtFullName.getText().trim();
			String Email = txtEmail.getText().trim();
			String NgaySinh = txtBirthday.getText();
			String gioitinh = cbxGender.getSelectedItem().toString();
			String GioiTinh = "0"; // Nữ
			if (gioitinh.toLowerCase().equals("nam")) {
				GioiTinh = "1";
			}
			String trangthai = cbxStatus.getSelectedItem().toString();
			String TrangThai = "0"; // Nghỉ việc
			if (trangthai.toLowerCase().equals("đang làm")) {
				TrangThai = "1";
			}
			if (HoTenNV.length() > 0 && DiaChi.length() > 0 && SoDienThoai.length() > 0 && NgaySinh.length() > 0
					&& Email.length() > 0) {
				// Generate MaNV
				String sql = "SELECT MaNV FROM nhanvien ORDER BY MaNV DESC LIMIT 1";
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				rs = statement.executeQuery(sql);
				if (rs.next()) {
					MaNV = rs.getInt(1) + 1;
				}
				if (MaNV != -1) {
					sql = "INSERT INTO nhanvien VALUES(" + MaNV + ",'" + HoTenNV + "'," + GioiTinh + ",'" + NgaySinh
							+ "','" + DiaChi + "','" + SoDienThoai + "','" + Email + "'," + TrangThai + ")";
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					statement.executeUpdate(sql);
					Message.messageBox("Thêm nhân viên mới thành công", "THÔNG BÁO");
					setEditableForm(false);
					loadTable();
					btnEdit.setEnabled(true);
					btnCancel.setEnabled(false);
					txtFullName.setText("");
					txtBirthday.setText("");
					txtAddress.setText("");
					txtPhoneNumber.setText("");
					txtEmail.setText("");
				}
			} else {
				Message.messageBox("Vui lòng kiểm tra lại thông tin nhân viên mới", "THÔNG BÁO");
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

	// Thêm nhân viên
	protected void add() {
		// TODO Auto-generated method stub
		setEditableForm(true);
		btnEdit.setEnabled(false);
		btnCancel.setEnabled(true);
		txtFullName.setText("");
		txtAddress.setText("");
		txtPhoneNumber.setText("");
		txtBirthday.setText("");
		txtEmail.setText("");
		txtFullName.requestFocus();
		cbxGender.setSelectedIndex(0);
		cbxStatus.setSelectedIndex(0);
	}

	// Cho phép chỉnh sửa thông tin
	protected void edit() {
		// TODO Auto-generated method stub
		int index = staffTable.getSelectedRow();
		if (index != -1) {
			setEditableForm(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(true);
		} else {
			Message.messageBox("Bạn chưa chọn nhân viên!!!", "THÔNG BÁO");
		}
	}

	// TODO: Set Editable Form
	private void setEditableForm(boolean editable) {
		getDate.setEnabled(editable);
		txtFullName.setEditable(editable);
		txtAddress.setEditable(editable);
		txtEmail.setEditable(editable);
		txtPhoneNumber.setEditable(editable);
		cbxGender.setEnabled(editable);
		cbxStatus.setEnabled(editable);
	}

	// Cập nhật nhân viên
	protected void update() {
		// TODO Auto-generated method stub
		try {
			// Lấy thông tin chỉnh sửa
			int MaNV = Integer.parseInt(cbxStaffID.getSelectedItem().toString());
			String HoTenNV = txtFullName.getText().trim();
			String DiaChi = txtAddress.getText().trim();
			String SoDienThoai = txtFullName.getText().trim();
			String Email = txtEmail.getText().trim();
			String NgaySinh = txtBirthday.getText();
			String gioitinh = cbxGender.getSelectedItem().toString();
			String GioiTinh = "0"; // Nữ
			if (gioitinh.toLowerCase().equals("nam")) {
				GioiTinh = "1";
			}
			String trangthai = cbxStatus.getSelectedItem().toString();
			String TrangThai = "0"; // Nghỉ việc
			if (trangthai.toLowerCase().equals("đang làm")) {
				TrangThai = "1";
			}
			String sql = "UPDATE nhanvien SET HoTenNV = '" + HoTenNV + "', GioiTinh = " + GioiTinh + ", NgaySinh = '"
					+ NgaySinh + "', DiaChi = '" + DiaChi + "', SoDienThoai ='" + SoDienThoai + "', Email = '" + Email
					+ "', TrangThai = " + TrangThai + " WHERE MaNV = " + MaNV;
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			Message.messageBox("Cập nhật nhân viên thành công", "THÔNG BÁO");
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
		vtColumn.add("Mã nhân viên");
		vtColumn.add("Họ và tên");
		vtColumn.add("Giới tính");
		vtColumn.add("Ngày Sinh");
		vtColumn.add("Địa chỉ");
		vtColumn.add("Số điện thoại");
		vtColumn.add("Email");
		vtColumn.add("Trạng thái");

		try {
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			// cho phép gửi câu lệnh sql
			String sql = "SELECT * FROM nhanvien";
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
					vtRow.add(rs.getString(7));
					String trangThai = "Đang làm";
					if (rs.getBoolean(8) == false) {
						trangThai = "Nghỉ việc";
					}
					vtRow.add(trangThai);
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

			staffTable.setModel(tableModel);
			tableModel.fireTableDataChanged();
			staffTable.getColumnModel().getColumn(0).setResizable(false);
			staffTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			staffTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

			staffTable.getColumnModel().getColumn(1).setResizable(false);
			staffTable.getColumnModel().getColumn(1).setPreferredWidth(150);
			staffTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(2).setResizable(false);
			staffTable.getColumnModel().getColumn(2).setPreferredWidth(20);
			staffTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(3).setResizable(false);
			staffTable.getColumnModel().getColumn(3).setPreferredWidth(50);
			staffTable.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(4).setResizable(false);
			staffTable.getColumnModel().getColumn(4).setPreferredWidth(400);
			staffTable.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(5).setResizable(false);
			staffTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			staffTable.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(6).setResizable(false);
			staffTable.getColumnModel().getColumn(6).setPreferredWidth(220);
			staffTable.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(7).setResizable(false);
			staffTable.getColumnModel().getColumn(7).setPreferredWidth(50);
			staffTable.getColumnModel().getColumn(7).setCellRenderer(leftRenderer);

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
			// Lấy danh sách mã nhân viên đổ vào combo box
			String sql = "SELECT DISTINCT MaNV FROM NHANVIEN";
			statement = conn.createStatement();
			// Câu lệnh truy vấn SQL
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxStaffID.addItem(rs.getInt(1) + "");
				cbxStaffID.setSelectedItem(null);
			}

			// Lấy danh sách giới tính đổ vào combo box
			sql = "SELECT DISTINCT GioiTinh FROM NHANVIEN";
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

			// Lấy danh sách trạng thái đổ vào combo box
			sql = "SELECT DISTINCT TrangThai FROM NHANVIEN";
			statement = conn.createStatement();
			// Câu lệnh truy vấn SQL
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbxStatus.setSelectedItem(null);
				String trangThai = "Đang làm";
				if (rs.getBoolean(1) == false) {
					trangThai = "Nghỉ việc";
				}
				cbxStatus.addItem(trangThai);
			}

			statement.close();
			conn.close();
		} catch (SQLException e2) {
			// TODO: handle exception
			Message.messageBox("Error:\n" + e2.getMessage(), "ERROR");
		}
	}
}
