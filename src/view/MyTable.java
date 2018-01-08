package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import controller.DBConnection;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

public class MyTable extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4785882950997293442L;
	private JLabel lblTable, lblTableName;
	private int id;
	private boolean status;
	private boolean isFree;
	private Date NgayGioTra;
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getNgayGioTra() {
		return NgayGioTra;
	}

	public void setNgayGioTra(Date ngayGioTra) {
		NgayGioTra = ngayGioTra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JLabel getLblTable() {
		return lblTable;
	}

	public void setLblTable(JLabel lblTable) {
		this.lblTable = lblTable;
	}

	public JLabel getLblTableName() {
		return lblTableName;
	}

	public void setLblTableName(JLabel lblTableName) {
		this.lblTableName = lblTableName;
	}

	/**
	 * Create the panel.
	 */
	public MyTable() {
		setLayout(null);
		setFree(true);
		lblTable = new JLabel("");
		lblTable.setBounds(0, 0, 148, 75);
		lblTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTable.setIcon(new ImageIcon(MyTable.class.getResource("/resources/bantrong.png")));
		add(lblTable);

		lblTableName = new JLabel("");
		lblTableName.setBounds(0, 80, 148, 44);
		lblTableName.setForeground(Color.BLUE);
		lblTableName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTableName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTableName);

		addMouseListener(this);
		setBackground(new Color(129, 212, 250));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// // TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// Cập nhật chi tiết hoá đơn
		AdminFrame.loadOrdered(id);
		AdminFrame.tableID = id + "";
		// Lấy thông tin bàn và khách hàng đẩy lên chi tiết hoá đơn
		AdminFrame.lblTableName.setText("");
		AdminFrame.lblCustomerName.setText("");
		try {
			conn = DBConnection.getConnection();
			String sql = "select khachhang.HoTenKH, chonban.NgayGioTra, khachhang.MaKH from ban,khachhang,chonban "
					+ "where ban.MaBan=chonban.MaBan and " + "chonban.MaKH=khachhang.MaKH AND Ban.MaBan =" + id;
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				if (rs.getDate(2) == null) {
					setFree(false);
					AdminFrame.lblTableName.setText(lblTableName.getText());
					AdminFrame.lblCustomerName.setText(rs.getString(1));
					AdminFrame.MaKH = rs.getInt(3);
				} else {
					AdminFrame.lblTableName.setText("");
					AdminFrame.lblCustomerName.setText("");
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// Set tableName
	public void setTableName(String TableName) {
		this.lblTableName.setText(TableName);
	}

	// Set tableImage
	public void setImage(String url) {
		lblTable.setIcon(new ImageIcon(MyTable.class.getResource(url)));
	}

	// Set enable, disable Table
	public void setEnabledTable(Container container, boolean enabled) {
		Component[] components = container.getComponents();
		if (components.length > 0) {
			for (Component component : components) {
				component.setEnabled(enabled);
				if (component instanceof Container) {
					setEnabledTable((Container) component, enabled);
				}
			}
		}
	}
}
