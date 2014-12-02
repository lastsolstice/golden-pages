package com.gp.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gp.business.BusinessDTO;
import com.gp.users.UserDTO.ColumnName;
import com.gp.util.DAOException;
import com.gp.util.SQLHandler;
import com.mysql.jdbc.PreparedStatement;

public class UserDAO extends SQLHandler {
  protected static final String FIELDS_INSERT = "username, email, password, fullname, type";
  protected static final String FIELDS_RETURN = "user_id, " + FIELDS_INSERT;
  protected static String INSERT_SQL = "insert into user ( " + FIELDS_INSERT + " ) "
      + "values(?,?,?,?,?)";
  protected static String SELECT_SQL = "select " + FIELDS_RETURN + " from user where user_id = ?";
  protected static String UPDATE_SQL = "update user set username = ?, "
      + "email = ?, password = ?, fullname = ? , type = ? " + "where user_id = ?";
  protected static String DELETE_SQL = "delete from user where user_id = ?";

  public UserDAO(String contextName) {
    super(contextName);
  }

  public boolean create(UserDTO user) throws Exception {
    Connection conn = super.getConnectionJDBC();
    boolean valid = true;
   
    Statement stmt = null;

    try( java.sql.PreparedStatement prepStmt = conn.prepareStatement(INSERT_SQL)) {

      int i = 1;
      prepStmt.setString(i++, user.getUsername());
      prepStmt.setString(i++, user.getEmail());
      prepStmt.setString(i++, user.getPassword());
      prepStmt.setString(i++, user.getFullName());
      prepStmt.setString(i++, user.getType().toString());

      System.out.println(prepStmt);

      // execute Statement
      prepStmt.executeUpdate();

      stmt = conn.createStatement();
      ResultSet rs =
          stmt.executeQuery("select user_id, password from user where username='"
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
      conn = null;

    } catch (Exception ex) {
      valid = false;
      ex.printStackTrace();
    } finally {

      close(stmt);
      close(conn);

      
    }
    return valid;

  }

  public UserDTO find(String userID) {
    UserDTO user = null;
    Connection conn = super.getConnectionJDBC();
    ResultSet rs = null;

    try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(SELECT_SQL)) {
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
        user.setType(UserDTO.Type.valueOf(rs.getString(i++)));
      }

      rs.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {

      close(conn);
    }

    return user;
  }

  public UserDTO findbyEmail(String email) {
    UserDTO user = null;
    Connection conn = super.getConnectionJDBC();
    java.sql.PreparedStatement prepStmt = null;
    ResultSet rs = null;

    try {
      // setup statement and retrieve results
      prepStmt = conn.prepareStatement("select " + FIELDS_RETURN + " from user where email = ?");
      prepStmt.setString(1, email);
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

      close(conn);
    }

    return user;
  }

  /*
   * @param userName, passWord
   * 
   * @return userID or -1 if doesnt exists
   */

  public boolean existAs(String value, ColumnName column) {

    System.out.println("value : " + value);

    boolean exist = false;
    Connection conn = super.getConnectionJDBC();
    final String SELECT_EXISTANCE = "select user_id from user where ";
    String field = null;

    /*
     * If we want to find by username and then by email
     */

    switch (column) {
      case EMAIL:
        field = "email";
        break;
      case USERNAME:
        field = "username";
        break;
      default:
        break;
    }

    try (Statement stmt = conn.createStatement()) {
      // I could set limit to infinite, then count if there is more than
      // 1;
      ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + field + "='" + value + "' limit 1");

      System.out.println(SELECT_EXISTANCE + field + "='" + value + "' limit 1");

      if (rs.next()) {

        exist = true;// rs.getInt("userID");

      }
      rs.close();
      stmt.close();
      conn.close();

    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {

      close(conn);

    }

    return exist;
  }

  public boolean existDifferent(String uid, String value, ColumnName column) {

    boolean exist = false;
    Connection conn = super.getConnectionJDBC();
    final String SELECT_EXISTANCE = "select user_id from user where ";
    String field = null;

    /*
     * If we want to find by username and then by email
     */

    switch (column) {
      case EMAIL:
        field = "email";
        break;
      case USERNAME:
        field = "username";
        break;
      default:
        break;
    }

    try (Statement stmt = conn.createStatement()) {

      ResultSet rs =
          stmt.executeQuery(SELECT_EXISTANCE + field + "='" + value + "' AND user_id != '" + uid
              + "' limit 1");


      if (rs.next()) {

        exist = true;// rs.getInt("userID");

      }
      rs.close();
      stmt.close();
      conn.close();

    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {

      close(conn);

    }

    return exist;
  }

  public String verifyUserAndGetID(String username, String password) {
    String userID = null;
    Connection conn = super.getConnectionJDBC();
    final String SELECT_EXISTANCE = "select user_id, username, password from user where ";
    try (Statement stmt = conn.createStatement()) {

      ResultSet rs = stmt.executeQuery(SELECT_EXISTANCE + "username ='" + username + "' limit 1");

      if (rs.next()) {

        if (password.equals(rs.getString("password"))) {

          userID = rs.getString("user_id");

        }
      }
      rs.close();
      stmt.close();
      conn.close();

    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {
      close(conn);
    }

    return userID;
  }

  public void update(UserDTO user) {

    String statement = UPDATE_SQL;
    Connection conn = super.getConnectionJDBC();
    int rowCount;
    try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(statement)) {
      int i = 1;
      prepStmt.setString(i++, user.getUsername());
      prepStmt.setString(i++, user.getEmail());
      prepStmt.setString(i++, user.getPassword());
      prepStmt.setString(i++, user.getFullName());
      prepStmt.setString(i++, user.getType().toString());
      prepStmt.setString(i++, user.getUid());
      rowCount = prepStmt.executeUpdate();
      prepStmt.close();
      if (rowCount == 0) {
        throw new DAOException("Update Error: User: " + user.getUid());
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      close(conn);
    }
  }

  public void updateField(String value, ColumnName column, UserDTO userInfo) throws DAOException {

    String field = null;
    switch (column) {
      case EMAIL:
        field = "email";
        break;
      case USERNAME:
        field = "username";
        break;
      case PASSWORD:
        field = "password";
        break;
      default:
        break;
    }

    String statement = "update user set " + field + " = ? where user_id = ?";
    Connection conn = super.getConnectionJDBC();

    try (java.sql.PreparedStatement prepStmt = conn.prepareStatement(statement)) {
      prepStmt.setString(1, value);
      prepStmt.setString(2, (userInfo.getUid()));
      int rowCount = prepStmt.executeUpdate();
      prepStmt.close();
      if (rowCount == 0) {
        throw new DAOException("Update Error: User: " + userInfo.getUid());
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(conn);
    }

  }

  public void addManagesRelation(UserDTO user, BusinessDTO business) throws SQLException {
    Connection conn = super.getConnectionJDBC();
    final String query =
        "insert into manages(user_id, business_id) values (" + user.getUid() + ","
            + business.getBid() + ")";

    try (Statement stmt = conn.createStatement()) {
      int rs = stmt.executeUpdate(query);
      if (rs == 0) {
        throw new DAOException("error addManagesRelation(): User: " + user.getUid());
      }

    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {
      close(conn);
    }
  }
  
  public Integer findManagerId(String businessId) throws SQLException {
    Connection conn = super.getConnectionJDBC();
    final String query =
        "select user_id from manages where business_id = " + businessId;
    int id = -1;
    try (Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      if (rs.next()) {
        id = Integer.parseInt(rs.getString(1));
      }

    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {
      close(conn);
    }
    return id;
  }
  
}
