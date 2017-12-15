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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import controller.DBConnection;

public class SupplierManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -466921755583261683L;
	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable SupplierTable;
	private JTextField txtFullName;
	private JTextField txtPhoneNumber;
	private JTextField txtAddress;
	private JComboBox<String> cbxSupplierID;
	private DefaultTableModel tableModel;
	private Connection conn;
	private Statement statement;
	private ResultSet rs;
	private JButton btnEdit, btnNew, btnUpdate, btnExit, btnCancel;

	ActionListener action = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			switch (command) {
			// Cập nhật nhà cung cấp
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
	public SupplierManager() {
		// Đóng Form thì mở lại cửa sổ admin
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						dispose();
						new AdminFrame();
						//super.windowClosing(e);
					}
				});
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
		JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(121, 85, 72));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1594, 57);
		contentPane.add(lblTitle);
		scrollPane.setBounds(0, 290, 1594, 575);
		contentPane.add(scrollPane);

		SupplierTable = new JTable() {
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
		SupplierTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SupplierTable.setRowSelectionAllowed(true);
		SupplierTable.getTableHeader().setReorderingAllowed(false);
		// Không cho phép thay đổi độ dài cột
		SupplierTable.getTableHeader().setResizingAllowed(false);
		SupplierTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(SupplierTable);
		SupplierTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setViewportView(SupplierTable);
		
		SupplierTable.addMouseListener(new MouseListener() {

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
				int index = SupplierTable.getSelectedRow();
				if (index != -1) {
					// Đổ dữ liệu vào form edit
					cbxSupplierID.setSelectedItem(SupplierTable.getValueAt(index, 0) + "");
					txtFullName.setText(SupplierTable.getValueAt(index, 1).toString());
					txtPhoneNumber.setText(SupplierTable.getValueAt(index, 2).toString());
					txtAddress.setText(SupplierTable.getValueAt(index, 3).toString());

				}
			}
		});
		
		JPanel editablePanel = new JPanel();
		editablePanel.setBounds(0, 70, 985, 154);
		contentPane.add(editablePanel);
		editablePanel.setLayout(null);
		JLabel lblFullName = new JLabel("Tên nhà cung cấp :");
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFullName.setBounds(12, 62, 179, 30);
		editablePanel.add(lblFullName);

		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFullName.setBounds(201, 62, 326, 30);
		editablePanel.add(txtFullName);
		txtFullName.setColumns(10);
		txtFullName.setEditable(false);
		

		JLabel lblPhoneNumber = new JLabel("Số điện thoại:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhoneNumber.setBounds(565, 62, 138, 30);
		editablePanel.add(lblPhoneNumber);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(713, 62, 250, 30);
		txtPhoneNumber.setEditable(false);
		editablePanel.add(txtPhoneNumber);
		

		JLabel lblAddress = new JLabel("Địa chỉ :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddress.setBounds(22, 107, 79, 30);
		editablePanel.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAddress.setColumns(10);
		txtAddress.setBounds(201, 108, 326, 30);
		txtAddress.setEditable(false);
		editablePanel.add(txtAddress);
		
		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp:");
		lblMaNCC.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNCC.setBounds(12, 16, 165, 30);
		editablePanel.add(lblMaNCC);

		cbxSupplierID = new JComboBox<String>();
		cbxSupplierID.setSelectedItem(null);
		cbxSupplierID.setEditable(false);
		cbxSupplierID.setEnabled(false);
		cbxSupplierID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxSupplierID.setBounds(201, 17, 50, 30);
		cbxSupplierID.addItemListener(new ItemListener() {
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
		editablePanel.add(cbxSupplierID);
		
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

		btnSave = new JButton("Thêm NCC");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(656, 0, 154, 40);
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(action);
		buttonPanel.add(btnSave);

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(997, 70, 597, 207);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);

		JLabel lblSearchTitle = new JLabel("TÌM KIẾM NHÀ CUNG CẤP");
		lblSearchTitle.setForeground(Color.WHITE);
		lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSearchTitle.setOpaque(true);
		lblSearchTitle.setBackground(new Color(33, 150, 243));
		lblSearchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchTitle.setBounds(0, 0, 597, 45);
		searchPanel.add(lblSearchTitle);

		lblSearch = new JLabel("Tìm theo:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSearch.setBounds(2, 55, 88, 30);
		searchPanel.add(lblSearch);

		JComboBox<String> cbxSearchType = new JComboBox<String>();
		cbxSearchType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxSearchType.addItem("Mã nhà cung cấp");
		cbxSearchType.addItem("Tên nhà cung cấp");
		cbxSearchType.setBounds(102, 55, 262, 30);
		searchPanel.add(cbxSearchType);

		txtSearchString = new JTextField();
		txtSearchString.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSearchString.setColumns(10);
		txtSearchString.setBounds(2, 98, 362, 30);
		searchPanel.add(txtSearchString);

		JButton btnSeach = new JButton("Tìm kiếm");
		btnSeach.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSeach.setBounds(366, 98, 227, 30);
		btnSeach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String sql;
					// Tìm theo MaNCC
					if (cbxSearchType.getSelectedIndex() == 0) {
						int MaNCC = 0;
						try {
							MaNCC = Integer.parseInt(txtSearchString.getText());
							sql = "SELECT * FROM coffeeshop.nhacungcap where MaNCC = " + MaNCC;
							conn = DBConnection.getConnection();
							statement = conn.createStatement();
							rs = statement.executeQuery(sql);
							if (rs.next()) {
								txtFullName.setText(rs.getString(2));
								txtPhoneNumber.setText(rs.getString(3));
								txtAddress.setText(rs.getString(4));
								int n = cbxSupplierID.getItemCount();
								for (int i = 0; i < n; i++) {
									if (Integer.parseInt(cbxSupplierID.getItemAt(i)) == MaNCC) {
										cbxSupplierID.setSelectedIndex(i);
									}
								}
								statement.close();
								conn.close();
							}
							 else {
								txtFullName.setText("");
								txtAddress.setText("");
								txtPhoneNumber.setText("");
								cbxSupplierID.setSelectedIndex(-1);
							}

						} catch (NumberFormatException ex) {
							// TODO: handle exception
							Message.messageBox("Mã nhà cung cấp không hợp lệ", "THÔNG BÁO");
						}
					} else {
						// Tìm theo tên nhà cung cấp
						String tenNCC = txtSearchString.getText().trim().toLowerCase();
						if (tenNCC.length() > 0) {
							sql = "SELECT * FROM nhacungcap WHERE TenNCC LIKE '%" + tenNCC + "%'";
							conn = DBConnection.getConnection();
							statement = conn.createStatement();
							rs = statement.executeQuery(sql);
							if (rs != null)
								SearchSupplier.rs = rs;
							new SearchSupplier();
							rs.close();
							statement.close();
							conn.close();
						} else {
							Message.messageBox("Vui lòng nhập tên nhà cung cấp cần tìm kiếm", "THÔNG BÁO");
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
	}
	
	protected void save() {
		// TODO Auto-generated method stub
		int MaNCC = -1;
		try {
			// Lấy thông tin chỉnh sửa
			String TenNCC = txtFullName.getText().trim();
			String SoDienThoai = txtPhoneNumber.getText().trim();
			String DiaChi = txtAddress.getText().trim();
			if (TenNCC.length() > 0 && SoDienThoai.length() > 0 && DiaChi.length() > 0) {
				// Generate MaNV
				String sql = "SELECT MaNCC FROM nhacungcap ORDER BY MaNCC DESC LIMIT 1";
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				rs = statement.executeQuery(sql);
				if (rs.next()) {
					MaNCC = rs.getInt(1) + 1;
				}
				if (MaNCC != -1) {
					sql = "INSERT INTO nhacungcap VALUES(" + MaNCC + ",'" + TenNCC + "'," + SoDienThoai + ",'" + DiaChi + "')";
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					statement.executeUpdate(sql);
					Message.messageBox("Thêm nhà cung cấp mới thành công", "THÔNG BÁO");
					setEditableForm(false);
					loadTable();
					btnEdit.setEnabled(true);
					btnCancel.setEnabled(false);
					txtFullName.setText("");
					txtPhoneNumber.setText("");
					txtAddress.setText("");
				}
			} else {
				Message.messageBox("Vui lòng kiểm tra lại thông tin nhà cung cấp mới", "THÔNG BÁO");
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

	// Thêm nhà cung cấp
		protected void add() {
			// TODO Auto-generated method stub
			setEditableForm(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			txtFullName.setText("");
			txtPhoneNumber.setText("");
			txtAddress.setText("");
			txtFullName.requestFocus();
		}
		// Cho phép chỉnh sửa thông tin
		protected void edit() {
			// TODO Auto-generated method stub
			int index = SupplierTable.getSelectedRow();
			if (index != -1) {
				setEditableForm(true);
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
			} else {
				Message.messageBox("Bạn chưa chọn thông tin nhà cung cấp!!!", "THÔNG BÁO");
			}
		}
		// TODO: Set Editable Form
		private void setEditableForm(boolean editable) {
			txtFullName.setEditable(editable);
			txtPhoneNumber.setEditable(editable);
			txtAddress.setEditable(editable);
		}
		// Cập nhật nhà cung cấp
		protected void update() {
			// TODO Auto-generated method stub
			try {
				// Lấy thông tin chỉnh sửa
				int MaNCC = Integer.parseInt(cbxSupplierID.getSelectedItem().toString());
				String TenNCC = txtFullName.getText().trim();
				String SoDienThoai = txtPhoneNumber.getText().trim();
				String DiaChi = txtAddress.getText().trim();
				String sql = "UPDATE nhacungcap SET TenNCC = '" + TenNCC + "', SoDienThoai = '" + SoDienThoai + "',"
						+ " DiaChi = '" + DiaChi + "' WHERE MaNCC = " + MaNCC;
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				statement.executeUpdate(sql);
				Message.messageBox("Cập nhật nhà cung cấp thành công", "THÔNG BÁO");
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
			vtColumn.add("Mã NCC");
			vtColumn.add("Tên NCC");
			vtColumn.add("Số điện thoại");
			vtColumn.add("Địa chỉ");


			try {
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				// cho phép gửi câu lệnh sql
				String sql = "SELECT * FROM nhacungcap";
				rs = statement.executeQuery(sql);
				if (rs != null) {
					while (rs.next()) {
						vtRow = new Vector<String>();
						vtRow.add(rs.getInt(1) + "");
						vtRow.add(rs.getString(2));
						vtRow.add(rs.getString(3));
						vtRow.add(rs.getString(4));
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

				SupplierTable.setModel(tableModel);
				tableModel.fireTableDataChanged();
				SupplierTable.getColumnModel().getColumn(0).setResizable(false);
				SupplierTable.getColumnModel().getColumn(0).setPreferredWidth(20);
				SupplierTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

				SupplierTable.getColumnModel().getColumn(1).setResizable(false);
				SupplierTable.getColumnModel().getColumn(1).setPreferredWidth(80);
				SupplierTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

				SupplierTable.getColumnModel().getColumn(2).setResizable(false);
				SupplierTable.getColumnModel().getColumn(2).setPreferredWidth(20);
				SupplierTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

				SupplierTable.getColumnModel().getColumn(3).setResizable(false);
				SupplierTable.getColumnModel().getColumn(3).setPreferredWidth(400);
				SupplierTable.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);


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
				String sql = "SELECT DISTINCT MaNCC FROM NHACUNGCAP";
				statement = conn.createStatement();
				// Câu lệnh truy vấn SQL
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					cbxSupplierID.addItem(rs.getInt(1) + "");
					cbxSupplierID.setSelectedItem(null);
				}
				statement.close();
				conn.close();
				
			} catch (SQLException e2) {
				// TODO: handle exception
				Message.messageBox("Error:\n" + e2.getMessage(), "ERROR");
		}
	}
}


