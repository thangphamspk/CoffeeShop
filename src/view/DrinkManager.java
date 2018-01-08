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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.DBConnection;

import javax.swing.JLabel;

public class DrinkManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6908481891951152550L;
	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable DrinkTable;
	private JTextField txtDrinkName;
	private JTextField txtUnit;
	private JTextField txtStored;
	private JTextField txtCost;
	private JTextField txtPrice;
	private JComboBox<String> cbxDrinkID;
	private JComboBox<String> cbxTypeOfDrink;
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
	public DrinkManager() {
		//Đóng Form thì mở lại cửa sổ admin
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
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
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		JLabel lblTitle = new JLabel("DANH MỤC ĐỒ UỐNG");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(121, 85, 72));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1594, 57);
		contentPane.add(lblTitle);
		scrollPane.setBounds(0, 290, 1594, 575);
		contentPane.add(scrollPane);

		DrinkTable = new JTable() {
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
		DrinkTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DrinkTable.setRowSelectionAllowed(true);
		DrinkTable.getTableHeader().setReorderingAllowed(false);
		// Không cho phép thay đổi độ dài cột
		DrinkTable.getTableHeader().setResizingAllowed(false);
		DrinkTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(DrinkTable);
		DrinkTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setViewportView(DrinkTable);
		
		// Sự kiện click trên cell
				DrinkTable.addMouseListener(new MouseListener() {

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
						int index = DrinkTable.getSelectedRow();
						if (index != -1) {
							// Đổ dữ liệu vào form edit
							cbxDrinkID.setSelectedItem(DrinkTable.getValueAt(index, 0) + "");
							txtDrinkName.setText(DrinkTable.getValueAt(index, 1).toString());
							cbxTypeOfDrink.setSelectedItem(DrinkTable.getValueAt(index, 2));
							txtUnit.setText(DrinkTable.getValueAt(index, 3).toString());
							txtStored.setText(DrinkTable.getValueAt(index, 4).toString());
							txtCost.setText(DrinkTable.getValueAt(index, 5).toString());
							txtPrice.setText(DrinkTable.getValueAt(index, 6).toString());
						}
					}
				});
				
				JPanel editablePanel = new JPanel();
				editablePanel.setBounds(0, 70, 985, 154);
				contentPane.add(editablePanel);
				editablePanel.setLayout(null);
				JLabel lblDrinkName = new JLabel("Tên thức uống");
				lblDrinkName.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblDrinkName.setBounds(12, 62, 165, 30);
				editablePanel.add(lblDrinkName);

				txtDrinkName = new JTextField();
				txtDrinkName.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtDrinkName.setBounds(189, 62, 338, 30);
				editablePanel.add(txtDrinkName);
				txtDrinkName.setColumns(10);
				txtDrinkName.setEditable(false);

				JLabel lblPrice = new JLabel("Giá bán");
				lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblPrice.setBounds(565, 62, 102, 30);
				editablePanel.add(lblPrice);

				txtPrice = new JTextField();
				txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
				txtPrice.setColumns(10);
				txtPrice.setBounds(702, 62, 252, 30);
				txtPrice.setEditable(false);
				editablePanel.add(txtPrice);

				JLabel lblStored = new JLabel("Tồn kho");
				lblStored.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblStored.setBounds(12, 108, 165, 30);
				editablePanel.add(lblStored);

				txtStored = new JTextField();
				txtStored.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtStored.setColumns(10);
				txtStored.setBounds(189, 108, 338, 30);
				txtStored.setEditable(false);
				editablePanel.add(txtStored);

				JLabel lblCost = new JLabel("Giá vốn");
				lblCost.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblCost.setBounds(565, 16, 102, 30);
				editablePanel.add(lblCost);

				txtCost = new JTextField();
				txtCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
				txtCost.setColumns(10);
				txtCost.setBounds(702, 16, 252, 30);
				txtCost.setEditable(false);
				editablePanel.add(txtCost);

				JLabel lblUnit = new JLabel("Đơn vị tính");
				lblUnit.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblUnit.setBounds(263, 16, 102, 30);
				editablePanel.add(lblUnit);

				txtUnit = new JTextField();
				txtUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtUnit.setColumns(10);
				txtUnit.setBounds(377, 16, 150, 30);
				txtUnit.setEditable(false);
				editablePanel.add(txtUnit);

				JLabel lblTypeOfDrink = new JLabel("Mã loại nước");
				lblTypeOfDrink.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblTypeOfDrink.setBounds(575, 113, 133, 30);
				editablePanel.add(lblTypeOfDrink);

				cbxTypeOfDrink = new JComboBox<String>();
				cbxTypeOfDrink.setSelectedItem(null);
				cbxTypeOfDrink.setEditable(false);
				cbxTypeOfDrink.setEnabled(false);
				cbxTypeOfDrink.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cbxTypeOfDrink.setBounds(722, 114, 60, 30);
				cbxTypeOfDrink.setSelectedItem(null);
				cbxTypeOfDrink.setEnabled(false);
				cbxTypeOfDrink.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getStateChange() == ItemEvent.SELECTED) {
							showTypeOfDrink();
						}
					}

					private void showTypeOfDrink() {

					}
				});
				editablePanel.add(cbxTypeOfDrink);
				
				JLabel lblMaKH = new JLabel("Mã nước uống");
				lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblMaKH.setBounds(12, 16, 165, 30);
				editablePanel.add(lblMaKH);

				cbxDrinkID = new JComboBox<String>();
				cbxDrinkID.setSelectedItem(null);
				cbxDrinkID.setEditable(false);
				cbxDrinkID.setEnabled(false);
				cbxDrinkID.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cbxDrinkID.setBounds(189, 16, 72, 30);
				cbxDrinkID.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getStateChange() == ItemEvent.SELECTED) {
							showDrink();
						}
					}

					private void showDrink() {
						// TODO Auto-generated method stub

					}
				});
				editablePanel.add(cbxDrinkID);
				
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

				btnSave = new JButton("Thêm TU");
				btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnSave.setBounds(656, 0, 154, 40);
				btnSave.setActionCommand("Save");
				btnSave.addActionListener(action);
				buttonPanel.add(btnSave);

				JPanel searchPanel = new JPanel();
				searchPanel.setBounds(997, 70, 597, 207);
				contentPane.add(searchPanel);
				searchPanel.setLayout(null);

				JLabel lblSearchTitle = new JLabel("TÌM KIẾM THỨC UỐNG");
				lblSearchTitle.setForeground(Color.WHITE);
				lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblSearchTitle.setOpaque(true);
				lblSearchTitle.setBackground(new Color(33, 150, 243));
				lblSearchTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lblSearchTitle.setBounds(0, 0, 597, 45);
				searchPanel.add(lblSearchTitle);

				lblSearch = new JLabel("Tìm theo:");
				lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblSearch.setBounds(4, 58, 88, 30);
				searchPanel.add(lblSearch);

				JComboBox<String> cbxSearchType = new JComboBox<String>();
				cbxSearchType.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cbxSearchType.addItem("Mã thức uống");
				cbxSearchType.addItem("Tên thức uống");
				cbxSearchType.setBounds(104, 58, 262, 30);
				searchPanel.add(cbxSearchType);

				txtSearchString = new JTextField();
				txtSearchString.setFont(new Font("Tahoma", Font.PLAIN, 18));
				txtSearchString.setColumns(10);
				txtSearchString.setBounds(4, 98, 362, 30);
				searchPanel.add(txtSearchString);

				JButton btnSeach = new JButton("Tìm kiếm");
				btnSeach.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnSeach.setBounds(370, 98, 223, 30);
				btnSeach.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							String sql;
							// Tìm theo mã thức uống
							if (cbxSearchType.getSelectedIndex() == 0) {
								int MaTU = 0;
								try {
									MaTU = Integer.parseInt(txtSearchString.getText());
									sql = "SELECT * FROM coffeeshop.thucuong where MaTU = " + MaTU;
									conn = DBConnection.getConnection();
									statement = conn.createStatement();
									rs = statement.executeQuery(sql);
									if (rs.next()) {
										txtDrinkName.setText(rs.getString(2));
										cbxTypeOfDrink.setSelectedItem(rs.getString(3));
										txtUnit.setText(rs.getString(4));
										txtStored.setText(rs.getString(5));
										txtCost.setText(rs.getString(6));
										txtPrice.setText(rs.getString(7));
										int n = cbxDrinkID.getItemCount();
										for (int i = 0; i < n; i++) {
											if (Integer.parseInt(cbxDrinkID.getItemAt(i)) == MaTU) {
												cbxDrinkID.setSelectedIndex(i);
											}
										}

										statement.close();
										conn.close();

									} else {
										txtDrinkName.setText("");
										txtPrice.setText("");
										txtCost.setText("");
										txtUnit.setText("");
										txtStored.setText("");
										cbxTypeOfDrink.setSelectedIndex(-1);
										cbxDrinkID.setSelectedIndex(-1);
									}

								} catch (NumberFormatException ex) {
									// TODO: handle exception
									Message.messageBox("Mã thức uống không hợp lệ", "THÔNG BÁO");
								}
							} else {
								// Tìm theo tên thức uống
								String ten = txtSearchString.getText().trim().toLowerCase();
								if (ten.length() > 0) {
									sql = "SELECT * FROM thucuong WHERE TenTU LIKE '%" + ten + "%'";
									conn = DBConnection.getConnection();
									statement = conn.createStatement();
									rs = statement.executeQuery(sql);
									if (rs.next())
										SearchDrink.rs = rs;
									new SearchDrink();
									rs.close();
									statement.close();
									conn.close();
								} else {
									Message.messageBox("Vui lòng nhập tên thức uống cần tìm kiếm", "Thông Báo");
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
	
	protected void edit() {
		// TODO Auto-generated method stub
				int index = DrinkTable.getSelectedRow();
				if (index != -1) {
					setEditableForm(true);
					btnUpdate.setEnabled(true);
					btnCancel.setEnabled(true);
				} else {
					Message.messageBox("Bạn chưa chọn đồ uống!!!", "THÔNG BÁO");
				}
			}
	private void setEditableForm(boolean editable) {
		txtDrinkName.setEditable(editable);
		txtUnit.setEditable(editable);
		txtPrice.setEditable(editable);
		txtCost.setEditable(editable);
		txtStored.setEditable(editable);
		cbxTypeOfDrink.setEnabled(editable);
	}
	protected void save() {
		// TODO Auto-generated method stub
		int MaTU = -1;
		try {
			// Lấy thông tin chỉnh sửa
			String TenTU = txtDrinkName.getText().trim();
			String DonViTinh = txtUnit.getText().trim();
			String TonKho = txtStored.getText().trim();
			String GiaGoc = txtCost.getText();
			String loaiTU = cbxTypeOfDrink.getSelectedItem().toString();
			String GiaBan = txtPrice.getText();
			if (TenTU.length() > 0 && DonViTinh.length() > 0 && TonKho.length() > 0 && GiaGoc.length() > 0 && GiaBan.length() > 0) {
				// Generate MaTU
				String sql = "SELECT MaTU FROM thucuong ORDER BY MaTU DESC LIMIT 1";
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				rs = statement.executeQuery(sql);
				if (rs.next()) {
					MaTU = rs.getInt(1) + 1;
				}
				if (MaTU != -1) {
					sql = "INSERT INTO thucuong VALUES(" + MaTU + ",'" + TenTU + "'," + loaiTU + ",'" + DonViTinh
							+ "','" + TonKho + "','" + GiaGoc + "'," + GiaBan + ")";
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					statement.executeUpdate(sql);
					Message.messageBox("Thêm thức uống mới thành công", "THÔNG BÁO");
					setEditableForm(false);
					loadTable();
					btnEdit.setEnabled(true);
					btnCancel.setEnabled(false);
					txtDrinkName.setText("");
					txtUnit.setText("");
					txtStored.setText("");
					txtCost.setText("");
					txtPrice.setText("");
				}
			} else {
				Message.messageBox("Vui lòng kiểm tra lại thông tin thức uống mới", "THÔNG BÁO");
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
	
	// Thêm thức uống
		protected void add() {
			// TODO Auto-generated method stub
			setEditableForm(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			txtDrinkName.setText("");
			txtUnit.setText("");
			txtStored.setText("");
			txtCost.setText("");
			txtPrice.setText("");
			txtDrinkName.requestFocus();
			cbxTypeOfDrink.setSelectedIndex(-1);
		}
		
		protected void update() {
			// TODO Auto-generated method stub
			try {
				// Lấy thông tin chỉnh sửa
				int MaTU = Integer.parseInt(cbxDrinkID.getSelectedItem().toString());
				String TenTU = txtDrinkName.getText().trim();
				String DonViTinh = txtUnit.getText().trim();
				String TonKho = txtStored.getText().trim();
				String GiaGoc = txtCost.getText();
				String GiaBan = txtPrice.getText();
				String loaiTU = cbxTypeOfDrink.getSelectedItem().toString();
				String sql = "UPDATE thucuong SET TenTU = '" + TenTU + "', MaNhomTU = " + loaiTU + ", DonViTinh = '"
						+ DonViTinh + "', TonKho = '" + TonKho + "', GiaVon ='" + GiaGoc + "', GiaBan = " 
						+ GiaBan + " WHERE MaTU = " + MaTU;
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				statement.executeUpdate(sql);
				Message.messageBox("Cập nhật thức uống thành công", "THÔNG BÁO");
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

		@SuppressWarnings("unchecked")
		public void loadTable() {
			Vector<String> vtColumn = new Vector<String>();
			Vector<String> vtRow = new Vector<String>();
			@SuppressWarnings("rawtypes")
			Vector vtData = new Vector();
			vtColumn.add("Mã thức uống");
			vtColumn.add("Tên thức uống");
			vtColumn.add("Mã nhóm thức uống");
			vtColumn.add("Đơn vị tính");
			vtColumn.add("Tồn kho");
			vtColumn.add("Giá Vốn");
			vtColumn.add("Giá bán");

			try {
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				// cho phép gửi câu lệnh sql
				String sql = "SELECT * FROM thucuong";
				rs = statement.executeQuery(sql);
				if (rs != null) {
					while (rs.next()) {
						vtRow = new Vector<String>();
						vtRow.add(rs.getInt(1) + "");
						vtRow.add(rs.getString(2));
						vtRow.add(rs.getInt(3) + "");
						vtRow.add(rs.getString(4));
						vtRow.add(rs.getString(5));
						vtRow.add(rs.getString(6));
						vtRow.add(rs.getString(7));
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

				DrinkTable.setModel(tableModel);
				tableModel.fireTableDataChanged();
				DrinkTable.getColumnModel().getColumn(0).setResizable(false);
				DrinkTable.getColumnModel().getColumn(0).setPreferredWidth(20);
				DrinkTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

				DrinkTable.getColumnModel().getColumn(1).setResizable(false);
				DrinkTable.getColumnModel().getColumn(1).setPreferredWidth(80);
				DrinkTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

				DrinkTable.getColumnModel().getColumn(2).setResizable(false);
				DrinkTable.getColumnModel().getColumn(2).setPreferredWidth(20);
				DrinkTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

				DrinkTable.getColumnModel().getColumn(3).setResizable(false);
				DrinkTable.getColumnModel().getColumn(3).setPreferredWidth(50);
				DrinkTable.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);

				DrinkTable.getColumnModel().getColumn(4).setResizable(false);
				DrinkTable.getColumnModel().getColumn(4).setPreferredWidth(20);
				DrinkTable.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);

				DrinkTable.getColumnModel().getColumn(5).setResizable(false);
				DrinkTable.getColumnModel().getColumn(5).setPreferredWidth(20);
				DrinkTable.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);

				DrinkTable.getColumnModel().getColumn(6).setResizable(false);
				DrinkTable.getColumnModel().getColumn(6).setPreferredWidth(20);
				DrinkTable.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);

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
				String sql = "SELECT DISTINCT MaTU FROM thucuong";
				statement = conn.createStatement();
				// Câu lệnh truy vấn SQL
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					cbxDrinkID.addItem(rs.getInt(1) + "");
					cbxDrinkID.setSelectedItem(null);
				}

				// Lấy danh sách loại khách hàng đổ vào combo box
				sql = "SELECT DISTINCT MaNhomTU FROM thucuong";
				statement = conn.createStatement();
				// Câu lệnh truy vấn SQL
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					cbxTypeOfDrink.addItem(rs.getInt(1) + "");
					cbxTypeOfDrink.setSelectedItem(null);

				}

				statement.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				Message.messageBox("Error:\n" + e2.getMessage(), "ERROR");
			}
		}
}