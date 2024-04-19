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
  public void insert(Department obj) throws DbException {
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
  public void update(Department obj) throws DbException {
	PreparedStatement pst = null;

	try {
	  String query = "UPDATE department SET Name = ? WHERE Id = ?";

	  pst = this.conn.prepareStatement( query );

	  pst.setString( 1, obj.getName() );
	  pst.setInt( 2, obj.getId() );

	  pst.executeUpdate();
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	}
  }

  @Override
  public void deleteById(Integer id) throws DbException {
	PreparedStatement pst = null;

	try {
	  String query = "DELETE FROM department WHERE Id = ?";
	  pst = this.conn.prepareStatement( query );

	  pst.setInt( 1, id );

	  pst.executeUpdate();
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	}
  }

  @Override
  public Department findById(Integer id) throws DbException {
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	  String query = "SELECT Id, Name FROM department WHERE Id = ?";

	  pst = this.conn.prepareStatement( query );

	  pst.setInt( 1, id );

	  rs = pst.executeQuery();

	  if ( rs.next() ) {
		return instantiateDepartment( rs );
	  }
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	  DB.closeResultSet( rs );
	}

	return null;
  }

  @Override
  public List<Department> findAll() throws DbException {
	return null;
  }

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
	return new Department().setId( rs.getInt( "Id" ) ).setName( rs.getString( "Name" ) );
  }
}
