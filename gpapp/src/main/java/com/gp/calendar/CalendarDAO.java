package com.gp.calendar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gp.users.UserDTO;
import com.gp.util.DAOException;
import com.gp.util.SQLHandler;
import com.mysql.jdbc.PreparedStatement;

public class CalendarDAO extends SQLHandler {
  protected static final String FIELDS_INSERT = "date, invitee_id, inviter_id, status, type ";
  protected static final String FIELDS_RETURN = "id, " + FIELDS_INSERT;
  protected static String INSERT_SQL = "insert into calendar ( " + FIELDS_INSERT + " ) "
      + "values(?,?,?,?,?)";
  protected static String SELECT_SQL = "select " + FIELDS_RETURN + " from calendar where id = ?";
  protected static String SELECT_SQL_2 = "select " + FIELDS_RETURN + " from calendar where invitee_id = ? OR inviter_id = ? ";
  protected static String UPDATE_SQL = "update calendar set date = ?, "
      + "invitee_id = ?, inviter_id = ?, status = ? , type = ? " + "where id = ?";
  protected static String DELETE_SQL = "delete from calendar where id = ?";

  public CalendarDAO(String contextName) {
    super(contextName);
  }

  public boolean create(CalendarDTO appt) throws Exception {
    Connection conn = super.getConnectionJDBC();

    boolean valid = true;
    Statement stmt = null;

    try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(INSERT_SQL)) {

      int i = 1;
      prepStmt.setString(i++, appt.getDate().toString());
      prepStmt.setString(i++, appt.getInviteeId());
      prepStmt.setString(i++, appt.getInviterId());
      prepStmt.setString(i++, appt.getStatus().toString());
      prepStmt.setString(i++, appt.getType().toString());
      prepStmt.executeUpdate();

      stmt = conn.createStatement();
      stmt = conn.createStatement();

     

      /*
       * Ideally we would return the new appoinment id ResultSet rs = stmt
       * .executeQuery("select business_id from business where business_name='" +
       * business.getBusinessName() + "' limit 1"); if (rs.next()) {
       * business.setBid(rs.getString("business_id")); } rs.close();
       */


    } catch (Exception ex) {
      ex.printStackTrace();
      valid = false;
    } finally {


      close(stmt);
      close(conn);

    }
    return valid;
  }


public List<CalendarDTO> find(UserDTO user) {
  
  List<CalendarDTO> list = new LinkedList<>();
  
  //UserDTO user = null;
  Connection conn = super.getConnectionJDBC();
  ResultSet rs = null;

  try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(SELECT_SQL_2)) {
    prepStmt.setString(1, user.getUid());
    prepStmt.setString(2,  user.getUid());
    rs = prepStmt.executeQuery();
    while (rs.next()) {
     // "date, invitee_id, inviter_id, status, type ";
      // create DTO using data from rs
      CalendarDTO appt = new CalendarDTO();
      int i = 1;
      appt.setId(rs.getString(i++));
      appt.setDate(rs.getTimestamp(i++));
      appt.setInviteeId(rs.getString(i++));
      appt.setInviterId(rs.getString(i++));
      appt.setStatus(CalendarDTO.Status.valueOf(rs.getString(i++)));
      appt.setType(CalendarDTO.Type.valueOf(rs.getString(i++)));
      list.add(appt);
    }

    rs.close();

  } catch (Exception ex) {
    ex.printStackTrace();
  } finally {

    close(conn);
  }

  return list;
}
}
/*
 * 
 * public UserDTO find(String apptId) { UserDTO user = null; Connection conn =
 * super.getConnectionJDBC(); ResultSet rs = null;
 * 
 * try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(SELECT_SQL)) {
 * prepStmt.setString(1, userID.toString()); rs = prepStmt.executeQuery(); if (rs.next()) { //
 * create DTO using data from rs user = new UserDTO(); int i = 1; user.setUid(rs.getString(i++));
 * user.setUsername(rs.getString(i++)); user.setEmail(rs.getString(i++));
 * user.setPassword(rs.getString(i++)); user.setFullName(rs.getString(i++));
 * user.setType(UserDTO.Type.valueOf(rs.getString(i++))); }
 * 
 * rs.close();
 * 
 * } catch (Exception ex) { ex.printStackTrace(); } finally {
 * 
 * close(conn); }
 * 
 * return user; }
 * 
 * public UserDTO findbyEmail(String email) { UserDTO user = null; Connection conn =
 * super.getConnectionJDBC(); java.sql.PreparedStatement prepStmt = null; ResultSet rs = null;
 * 
 * try { // setup statement and retrieve results prepStmt = conn.prepareStatement("select " +
 * FIELDS_RETURN + " from user where email = ?"); prepStmt.setString(1, email); rs =
 * prepStmt.executeQuery(); if (rs.next()) { // create DTO using data from rs user = new UserDTO();
 * int i = 1; user.setUid(rs.getString(i++)); user.setUsername(rs.getString(i++));
 * user.setEmail(rs.getString(i++)); user.setPassword(rs.getString(i++));
 * user.setFullName(rs.getString(i++));
 * 
 * }
 * 
 * rs.close(); prepStmt.close(); conn.close(); prepStmt = null; conn = null;
 * 
 * } catch (Exception ex) { ex.printStackTrace(); } finally {
 * 
 * close(conn); }
 * 
 * return user; }
 * 
 * 
 * 
 * public boolean existAs(String value, ColumnName column) {
 * 
 * System.out.println("value : " + value);
 * 
 * boolean exist = false; Connection conn = super.getConnectionJDBC(); final String SELECT_EXISTANCE
 * = "select user_id from user where "; String field = null;
 * 
 * 
 * 
 * switch (column) { case EMAIL: field = "email"; break; case USERNAME: field = "username"; break;
 * default: break; }
 * 
 * try (Statement stmt = conn.createStatement()) { // I could set limit to infinite, then count if
 * there is more than // 1; ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + field + "='" + value
 * + "' limit 1");
 * 
 * System.out.println(SELECT_EXISTANCE + field + "='" + value + "' limit 1");
 * 
 * if (rs.next()) {
 * 
 * exist = true;// rs.getInt("userID");
 * 
 * } rs.close(); stmt.close(); conn.close();
 * 
 * } catch (Exception ex) { ex.printStackTrace();
 * 
 * } finally {
 * 
 * close(conn);
 * 
 * }
 * 
 * return exist; }
 * 
 * public boolean existDifferent(String uid, String value, ColumnName column) {
 * 
 * boolean exist = false; Connection conn = super.getConnectionJDBC(); final String SELECT_EXISTANCE
 * = "select user_id from user where "; String field = null;
 * 
 * 
 * 
 * switch (column) { case EMAIL: field = "email"; break; case USERNAME: field = "username"; break;
 * default: break; }
 * 
 * try (Statement stmt = conn.createStatement()) {
 * 
 * ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + field + "='" + value + "' AND user_id != '" +
 * uid + "' limit 1");
 * 
 * 
 * if (rs.next()) {
 * 
 * exist = true;// rs.getInt("userID");
 * 
 * } rs.close(); stmt.close(); conn.close();
 * 
 * } catch (Exception ex) { ex.printStackTrace();
 * 
 * } finally {
 * 
 * close(conn);
 * 
 * }
 * 
 * return exist; }
 * 
 * public String verifyUserAndGetID(String username, String password) { String userID = null;
 * Connection conn = super.getConnectionJDBC(); final String SELECT_EXISTANCE =
 * "select user_id, username, password from user where "; try (Statement stmt =
 * conn.createStatement()) {
 * 
 * ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + "username ='" + username + "' limit 1");
 * 
 * if (rs.next()) {
 * 
 * if (password.equals(rs.getString("password"))) {
 * 
 * userID = rs.getString("user_id");
 * 
 * } } rs.close(); stmt.close(); conn.close();
 * 
 * } catch (Exception ex) { ex.printStackTrace();
 * 
 * } finally { close(conn); }
 * 
 * return userID; }
 * 
 * public void update(UserDTO user) {
 * 
 * String statement = UPDATE_SQL; Connection conn = super.getConnectionJDBC(); int rowCount; try
 * (java.sql.PreparedStatement prepStmt = conn.prepareStatement(statement)) { int i = 1;
 * prepStmt.setString(i++, user.getUsername()); prepStmt.setString(i++, user.getEmail());
 * prepStmt.setString(i++, user.getPassword()); prepStmt.setString(i++, user.getFullName());
 * prepStmt.setString(i++, user.getType().toString()); prepStmt.setString(i++, user.getUid());
 * rowCount = prepStmt.executeUpdate(); prepStmt.close(); if (rowCount == 0) { throw new
 * DAOException("Update Error: User: " + user.getUid()); }
 * 
 * } catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); } finally {
 * close(conn); } }
 * 
 * public void updateField(String value, ColumnName column, UserDTO userInfo) throws DAOException {
 * 
 * String field = null; switch (column) { case EMAIL: field = "email"; break; case USERNAME: field =
 * "username"; break; case PASSWORD: field = "password"; break; default: break; }
 * 
 * String statement = "update user set " + field + " = ? where user_id = ?"; Connection conn =
 * super.getConnectionJDBC();
 * 
 * try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(statement)) {
 * prepStmt.setString(1, value); prepStmt.setString(2, (userInfo.getUid())); int rowCount =
 * prepStmt.executeUpdate(); prepStmt.close(); if (rowCount == 0) { throw new
 * DAOException("Update Error: User: " + userInfo.getUid()); }
 * 
 * } catch (SQLException e) { e.printStackTrace(); } finally { close(conn); }
 * 
 * }
 * 
 * public void addManagesRelation(UserDTO user, BusinessDTO business) throws SQLException {
 * Connection conn = super.getConnectionJDBC(); final String query =
 * "insert into manages(user_id, business_id) values (" + user.getUid() + "," + business.getBid() +
 * ")";
 * 
 * try (Statement stmt = conn.createStatement()) { int rs = stmt.executeUpdate(query); if (rs == 0)
 * { throw new DAOException("error addManagesRelation(): User: " + user.getUid()); }
 * 
 * } catch (Exception ex) { ex.printStackTrace();
 * 
 * } finally { close(conn); } }
 * 
 * }
 */
