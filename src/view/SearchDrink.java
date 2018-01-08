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

public class SearchDrink extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7210999928037417851L;
	private JPanel contentPane;
	public static ResultSet rs;
	private JTable DrinkTable;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public SearchDrink() {
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

		JLabel lblTitle = new JLabel("KẾT QUẢ TÌM KIẾM ĐỒ UỐNG");
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

		DrinkTable = new JTable();
		DrinkTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DrinkTable.getTableHeader().setResizingAllowed(false);
		DrinkTable.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(DrinkTable);
		DrinkTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(DrinkTable);

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
		vtColumn.add("Mã thức uống");
		vtColumn.add("Tên thức uống");
		vtColumn.add("Mã loại thức uống");
		vtColumn.add("Đơn vị tính");
		vtColumn.add("Tồn kho");
		vtColumn.add("Giá vốn");
		vtColumn.add("Giá bán");

		try {
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
}