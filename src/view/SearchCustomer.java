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

public class SearchCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1492075463076727968L;	
	private JPanel contentPane;
	public static ResultSet rs;
	private JTable CustomerTable;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public SearchCustomer() {
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

		JLabel lblTitle = new JLabel("KẾT QUẢ TÌM KIẾM KHÁCH HÀNG");
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

		CustomerTable = new JTable();
		CustomerTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CustomerTable.getTableHeader().setResizingAllowed(false);
		CustomerTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(CustomerTable);
		CustomerTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(CustomerTable);

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
		vtColumn.add("Mã khách hàng");
		vtColumn.add("Họ và tên");
		vtColumn.add("Giới tính");
		vtColumn.add("Ngày Sinh");
		vtColumn.add("Địa chỉ");
		vtColumn.add("Số điện thoại");
		vtColumn.add("Mã loại khách hàng");

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
				vtRow.add(rs.getInt(7) + "");
				vtData.add(vtRow);
			}

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
}
