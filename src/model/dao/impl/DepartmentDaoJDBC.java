package model.dao.impl;

import database.DB;
import database.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
  private Connection conn;

  public DepartmentDaoJDBC(Connection conn) {
	this.conn = conn;
  }

  @Override
  public void insert(Department obj) {
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	  String query = "INSERT INTO department (Name) VALUES (?)";

	  pst = this.conn.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );

	  pst.setString( 1, obj.getName() );

	  int affectedRows = pst.executeUpdate();

	  if ( affectedRows > 0 ) {
		rs = pst.getGeneratedKeys();
		rs.next();

		obj.setId( rs.getInt( 1 ) );
	  } else {
		throw new DbException( "Unexpected error! No rows affected!" );
	  }
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	  DB.closeResultSet( rs );
	}
  }

  @Override
  public void update(Department obj) {

  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Department findById(Integer id) {
	return null;
  }

  @Override
  public List<Department> findAll() {
	return null;
  }
}
