package view;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import controller.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MyArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2907038343333847170L;
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	private int soBan = 0;

	/**
	 * Create the panel.
	 */
	public MyArea(int MaVT) {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1025, 767);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 12));
		add(panel);
		setVisible(true);
		// Đếm số bàn của từng khu
		try {
			// String sql = "SELECT COUNT(*) FROM BAN WHERE TrangThai = 1 AND
			// MaVT =" + MaVT;
			String sql = "SELECT COUNT(*) FROM BAN WHERE MaVT =" + MaVT;
			conn = DBConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				soBan = rs.getInt(1);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (soBan > 0) {
			// Lấy thông tin các bàn trong khu
			int[] tableID = new int[soBan];
			String[] tableName = new String[soBan];
			boolean[] status = new boolean[soBan];
			try {
				// String sql = "SELECT * FROM BAN WHERE TrangThai = 1 AND MaVT
				// = " + MaVT;
				String sql = "SELECT * FROM BAN WHERE MaVT = " + MaVT;
				conn = DBConnection.getConnection();
				statement = conn.createStatement();
				rs = statement.executeQuery(sql);
				int i = 0;
				while (rs.next()) {
					tableID[i] = rs.getInt(1);
					tableName[i] = rs.getString(2);
					status[i++] = rs.getBoolean(4);
				}
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Tạo danh sách các bàn
			JPanel[] table = (JPanel[]) createMyTable(soBan);
			int i = 0;
			while (i < soBan) {
				table[i] = new MyTable();
				table[i].setBorder(null);
				((MyTable) table[i]).setId(tableID[i]);
				((MyTable) table[i]).setTableName("BÀN " + tableName[i]);
				// Set cứng kích thước của bàn
				table[i].setPreferredSize(new Dimension(150, 150));
				table[i].setBorder(new EtchedBorder());

				// Load thông tin bàn (trống, có khách, đang hỏng
				try {
					String sql = "SELECT NgayGioTra From CHONBAN WHERE MaBan =" + tableID[i];
					conn = DBConnection.getConnection();
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					while (rs.next()) {
						if (rs.getDate(1) == null) {
							((MyTable) table[i]).setImage("/resources/bancokhach.png");
							break;
						}
					}
					rs.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Set trạng thái bàn
				((MyTable) table[i]).setStatus(status[i]);
				if (((MyTable) table[i]).isStatus() == false) {
					((MyTable) table[i]).setImage("/resources/banhong.png");
					// Disable bàn hỏng
					((MyTable) table[i]).setEnabledTable(table[i], false);
				}

				panel.add(table[i]);
				i++;
			}

		} else {
			Message.messageBox("Khu " + MaVT + " trống!!!!", "THÔNG BÁO");
		}
		setVisible(true);
	}

	// Tạo ra n bàn với kích thước ngẫu nhiên
	public static MyTable[] createMyTable(int n) {
		Random r = new Random(0);
		MyTable[] c = new MyTable[n];
		int m = n;
		while (m > 0) {
			int i = r.nextInt(n);
			if (c[i] == null) {
				c[i] = new MyTable();
				int w = 5 * (2 + r.nextInt(20));
				int h = 5 * (2 + r.nextInt(20));
				c[i].setPreferredSize(new Dimension(w, h));
				c[i].setBorder(new EtchedBorder());
				m--;
			}
		}
		return c;
	}

}
