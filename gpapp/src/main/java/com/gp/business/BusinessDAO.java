package com.gp.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gp.business.BusinessDTO.ColumnName;
import com.gp.util.DAOException;
import com.gp.util.SQLHandler;
import com.mysql.jdbc.PreparedStatement;


public class BusinessDAO extends SQLHandler {
	protected static final String FIELDS_INSERT = "business_name, description";
	protected static final String FIELDS_RETURN = "business_id, "
			+ FIELDS_INSERT;
	protected static String INSERT_SQL = "insert into business ( "
			+ FIELDS_INSERT + " ) " + "values(?,?)";
	protected static String SELECT_SQL = "select " + FIELDS_RETURN
			+ " from business where business_id = ?";
	protected static String UPDATE_SQL = "update business set business_name = ?, description = ?";
	protected static String DELETE_SQL = "delete from user where business_id = ?";

	public BusinessDAO(String contextName) {
		super(contextName);
	}

	public void create(BusinessDTO business) throws Exception {
		Connection conn = super.getConnectionJDBC();

		java.sql.PreparedStatement prepStmt = null;
		Statement stmt = null;

		try {
			// create and setup
			System.out.println(INSERT_SQL);

			prepStmt = conn.prepareStatement(INSERT_SQL);
			int i = 1;
			prepStmt.setString(i++, business.getBusinessName());
			prepStmt.setString(i++, business.getDescription());
			// System.out.println(prepStmt);

			// execute Statement
			prepStmt.executeUpdate();

			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select business_id from business where business_name='"
							+ business.getBusinessName() + "' limit 1");

			if (rs.next()) {

				business.setBid(rs.getString("business_id"));

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

	public BusinessDTO find(String businessID) {
		BusinessDTO business = null;
		Connection conn = super.getConnectionJDBC();
		java.sql.PreparedStatement prepStmt = null;
		ResultSet rs = null;

		try {
			// setup statement and retrieve results
			prepStmt = conn.prepareStatement(SELECT_SQL);
			prepStmt.setString(1, businessID.toString());
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				// create DTO using data from rs
				business = new BusinessDTO();
				int i = 1;
				business.setBid(rs.getString(i++));
				business.setBusinessName(rs.getString(i++));
				business.setDescription(rs.getString(i++));

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

		return business;
	}

	/*
	 * @param userName, passWord
	 * 
	 * @return userID or -1 if doesnt exists
	 */

	public boolean existAs(String value, ColumnName column) {

		System.out.println("value : " + value);

		boolean exist = false;
		Statement stmt = null;
		Connection conn = super.getConnectionJDBC();
		final String SELECT_EXISTANCE = "select business_id from business where ";
		String field = null;

		/*
		 * If we want to find by username and then by email
		 */

		switch (column) {
		case BUSINESSNAME:
			field = "business_name";
			break;
		default:
			break;
		}

		try {
			stmt = conn.createStatement();
			// I could set limit to infinite, then count if there is more than
			// 1;
			ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + field + "='"
					+ value + "' limit 1");

			System.out.println(SELECT_EXISTANCE + field + "='" + value
					+ "' limit 1");

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
}
