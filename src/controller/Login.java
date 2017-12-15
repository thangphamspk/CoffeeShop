package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import view.AdminFrame;

public class Login {

	private static PreparedStatement stmt;
	private static Connection conn;
	private static ResultSet rs;

	// Hàm đăng nhập hệ thống, trả về true nếu đăng nhập thành công
	public static User doLogin(String username, String password) {
		User user = new User();
		String sql = "SELECT id,username,password,permission FROM Users WHERE username = ? AND password = ?";
		conn = DBConnection.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPermission(rs.getString(4));
				stmt.close();
				conn.close();
				AdminFrame.user = user;
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return null;
	}
}
