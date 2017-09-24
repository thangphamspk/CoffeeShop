package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

	private static PreparedStatement stmt;
	private static Connection conn;
	private static ResultSet rs;
	
	//Hàm đăng nhập hệ thống, trả về true nếu đăng nhập thành công
	public static boolean doLogin(String username, String password) {
		String sql = "SELECT id,username,password,permission FROM Users WHERE username = ? AND password = ?";
		conn = DBConnection.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
