package com.gp.util;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SQLHandler {

  // private String dBName;
  // private String tableName;
  // private String statementSQL;
  DataSource datasource;

  public SQLHandler(String contextName) {

    InitialContext ctx;
    try {
      ctx = new InitialContext();
      datasource = (DataSource) ctx.lookup("java:comp/env/" + contextName); // jdbc/expRecDB //This
                                                                            // name is defined in
                                                                            // context.xml and is
                                                                            // variable
    } catch (NamingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  public Connection getConnectionJDBC() {

    Connection conn = null;
    try {


      conn = datasource.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Connection to MySQL failed");
    }


    return conn;
  }

  public void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (Exception sqlex) {
        closeable = null;
      }

    }
  }

  public void close(AutoCloseable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (Exception sqlex) {
        closeable = null;
      }

    }
  }



  public void executeUpdateJDBC(String statement) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    try {
      conn = this.getConnectionJDBC();
      stmt = conn.createStatement();

      stmt.executeUpdate(statement);
      System.out.println("1 row affected");
    } catch (SQLException s) {
      s.printStackTrace();
      System.out.println("SQL updates statement is not executed!");
    }
  }


}
