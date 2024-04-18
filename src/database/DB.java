package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
  private static Connection conn = null;

  private DB() {
  }

  public static Connection getConnection() {
	if ( conn == null ) {
	  Properties props = loadProperties();
	  String url = props.getProperty( "dburl" );

	  try {
		conn = DriverManager.getConnection( url, props );
	  } catch (SQLException e) {
		e.printStackTrace();
		throw new DbException( e.getMessage() );
	  }
	}

	return conn;
  }

  public static void closeConnection() {
	if ( conn != null ) {
	  try {
		conn.close();
	  } catch (Exception e) {
		throw new DbException( e.getMessage() );
	  }
	}
  }

  private static Properties loadProperties() {
	try (FileInputStream fs = new FileInputStream( "db.properties" )) {
	  Properties props = new Properties();
	  props.load( fs );

	  return props;
	} catch (IOException e) {
	  e.printStackTrace();
	  throw new DbException( e.getMessage() );
	}
  }

  public static void closeStatement(Statement st) {
	if ( st == null )
	  return;

	try {
	  st.close();
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	}
  }

  public static void closePreparedStatement(PreparedStatement pst) {
	if ( pst == null )
	  return;

	try {
	  pst.close();
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	}
  }

  public static void closeResultSet(ResultSet rs) {
	if ( rs == null )
	  return;

	try {
	  rs.close();
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	}
  }
}
