package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class SearchStaff extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 556372448237438983L;
	private JPanel contentPane;
	public static ResultSet rs;
	private JTable staffTable;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public SearchStaff() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		setBounds(100, 100, 1280, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("KẾT QUẢ TÌM KIẾM NHÂN VIÊN");
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBackground(new Color(121, 85, 72));
		lblTitle.setBounds(0, 0, 1262, 57);
		contentPane.add(lblTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 1262, 666);
		contentPane.add(scrollPane);

		staffTable = new JTable();
		staffTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		staffTable.getTableHeader().setResizingAllowed(false);
		staffTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(staffTable);
		staffTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(staffTable);

		setVisible(true);
		setLocationRelativeTo(null);
		loadTable();
	}

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
			staffTable.getColumnModel().getColumn(1).setPreferredWidth(180);
			staffTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(2).setResizable(false);
			staffTable.getColumnModel().getColumn(2).setPreferredWidth(50);
			staffTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(3).setResizable(false);
			staffTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			staffTable.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(4).setResizable(false);
			staffTable.getColumnModel().getColumn(4).setPreferredWidth(250);
			staffTable.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(5).setResizable(false);
			staffTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			staffTable.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(6).setResizable(false);
			staffTable.getColumnModel().getColumn(6).setPreferredWidth(180);
			staffTable.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);

			staffTable.getColumnModel().getColumn(7).setResizable(false);
			staffTable.getColumnModel().getColumn(7).setPreferredWidth(60);
			staffTable.getColumnModel().getColumn(7).setCellRenderer(leftRenderer);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}
