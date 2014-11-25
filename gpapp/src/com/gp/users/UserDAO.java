package com.gp.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.gp.util.DAOException;
import com.gp.util.SQLHandler;
import com.mysql.jdbc.PreparedStatement;

//DATA ACCCESS OBJECT J2EE PATTERNS BOOK PAGE 467
public class UserDAO extends SQLHandler {
	protected static final String FIELDS_INSERT = "username, email, password, firstName, lastName, statusJSON";
	protected static final String FIELDS_RETURN = "userID, " + FIELDS_INSERT
			+ ", dateJoined";
	protected static String INSERT_SQL = "insert into users ( " + FIELDS_INSERT
			+ " ) " + "values(?,?,?,?,?,?)";
	protected static String SELECT_SQL = "select " + FIELDS_RETURN
			+ " from users where userID = ?";
	protected static String UPDATE_SQL = "update users set username = ?, "
			+ "email = ?, password = ?, firstName = ?, lastName = ? "
			+ "where userID = ?";
	protected static String DELETE_SQL = "delete from users where userID = ?";

	public UserDAO(String contextName) {
		super(contextName);
	}

	public void create(UserDTO user) throws Exception {
		Connection conn = super.getConnectionJDBC();

		java.sql.PreparedStatement prepStmt = null;
		Statement stmt = null;

		try {
			// create and setup
			prepStmt = conn.prepareStatement(INSERT_SQL);
			int i = 1;
			prepStmt.setString(i++, user.getUsername());
			prepStmt.setString(i++, user.getEmail());
			prepStmt.setString(i++, user.getPassword());
			prepStmt.setString(i++, user.getFullName());

			// execute Statement
			prepStmt.executeUpdate();

			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select user_id, password from users where username='"
							+ user.getUsername() + "' limit 1");

			if (rs.next()) {

				if (user.getPassword().equals(rs.getString("password"))) {

					user.setUid(rs.getString("user_id"));
				}
				// System.out.println(user.toString());

			}

			rs.close();
			prepStmt.close();
			stmt.close();
			conn.close();
			stmt = null;
			prepStmt = null;
			conn = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception sqlex) {

				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception sqlex) {

					conn = null;
				}

			}

		}

	}

	public UserDTO find(Integer userID) {
		UserDTO user = null;
		Connection conn = super.getConnectionJDBC();
		java.sql.PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			// setup statement and retrieve results
			prepStmt = conn.prepareStatement(SELECT_SQL);
			prepStmt.setString(1, userID.toString());
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				// create DTO using data from rs
				user = new UserDTO();
				int i = 1;
				user.setUid(rs.getString(i++));
				user.setUsername(rs.getString(i++));
				user.setEmail(rs.getString(i++));
				user.setPassword(rs.getString(i++));
				user.setFullName(rs.getString(i++));

			}

			rs.close();
			prepStmt.close();
			conn.close();
			prepStmt = null;
			conn = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception sqlex) {
					conn = null;
				}

			}
		}

		return user;
	}

	/*
	 * @param userName, passWord
	 * 
	 * @return userID or -1 if doesnt exists
	 */

	public boolean existAs(String value) {
		boolean exist = false;
		Statement stmt = null;
		Connection conn = super.getConnectionJDBC();
		final String SELECT_EXISTANCE = "select userID from users where ";
		String field = null;

		/*
		 * If we want to find by username and then by email switch(column){ case
		 * EMAIL: field = "email"; break; case USERNAME: field = "username";
		 * break; default: break; }
		 */

		try {
			stmt = conn.createStatement();
			// I could set limit to infinite, then count if there is more than
			// 1;
			ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + field + "='"
					+ value + "' limit 1");

			if (rs.next()) {

				exist = true;// rs.getInt("userID");

			}
			rs.close();
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception sqlex) {

				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception sqlex) {

					conn = null;
				}

			}

		}

		return exist;
	}

	public String verifyUserAndGetID(String username, String password) {
		String userID = null;
		Statement stmt = null;
		Connection conn = super.getConnectionJDBC();
		final String SELECT_EXISTANCE = "select user_id, username, password from user where ";
		// String username

		try {
			stmt = conn.createStatement();
			// I could set limit to infinite, then count if there is more than
			// 1;
			ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + "username ='"
					+ username + "' limit 1");

			if (rs.next()) {

				if (password.equals(rs.getString("password"))) {

					userID = rs.getString("user_id");

				}
			}
			rs.close();
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception sqlex) {

				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception sqlex) {

					conn = null;
				}

			}

		}

		return userID;
	}

	
	//INCOMPLETE
	public void updateField(String aField, UserDTO userInfo)
			throws DAOException {

		String statement = "update users set fieldname = ? where user_id = ?";
		Connection conn = super.getConnectionJDBC();
		java.sql.PreparedStatement prepStmt = null;
		// ResultSet rs = null;
		int rowCount;
		try {
			prepStmt = conn.prepareStatement(statement);
			prepStmt.setString(1, aField);
			prepStmt.setString(2, (userInfo.getUid()));
			rowCount = prepStmt.executeUpdate();
			prepStmt.close();
			if (rowCount == 0) {
				throw new DAOException("Update Error: User: "
						+ userInfo.getUid());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
