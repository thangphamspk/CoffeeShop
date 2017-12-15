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

public class SearchSupplier extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5449065440624769200L;
	private JPanel contentPane;
	public static ResultSet rs;
	private JTable SupplierTable;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public SearchSupplier() {
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

		JLabel lblTitle = new JLabel("KẾT QUẢ TÌM KIẾM NHÀ CUNG CẤP");
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

		SupplierTable = new JTable();
		SupplierTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SupplierTable.getTableHeader().setResizingAllowed(false);
		SupplierTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(SupplierTable);
		SupplierTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(SupplierTable);

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
		vtColumn.add("Mã nhà cung cấp");
		vtColumn.add("Tên nhà cung cấp");
		vtColumn.add("Số điện thoại");
		vtColumn.add("Địa chỉ");

		try {
			while (rs.next()) {
				vtRow = new Vector<String>();
				vtRow.add(rs.getInt(1) + "");
				vtRow.add(rs.getString(2));
				vtRow.add(rs.getString(3));
				vtRow.add(rs.getString(4));
				vtData.add(vtRow);
			}

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
			SupplierTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			SupplierTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

			SupplierTable.getColumnModel().getColumn(1).setResizable(false);
			SupplierTable.getColumnModel().getColumn(1).setPreferredWidth(180);
			SupplierTable.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

			SupplierTable.getColumnModel().getColumn(2).setResizable(false);
			SupplierTable.getColumnModel().getColumn(2).setPreferredWidth(50);
			SupplierTable.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

			SupplierTable.getColumnModel().getColumn(3).setResizable(false);
			SupplierTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			SupplierTable.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);

		
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}
