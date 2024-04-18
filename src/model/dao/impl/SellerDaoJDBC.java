package model.dao.impl;

import database.DB;
import database.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
  private final Connection conn;

  public SellerDaoJDBC(Connection conn) {
	this.conn = conn;
  }

  @Override
  public void insert(Seller obj) {
	PreparedStatement pst = null;

	try {
	  String query = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)" +
			  "VALUES (?, ?, ? ,?, ?)";

	  pst = this.conn.prepareStatement( query );

	  pst.setString( 1, obj.getName() );
	  pst.setString( 2, obj.getEmail() );
	  pst.setDate(
			  3,
			  Date.valueOf( obj.getBirthDate().toInstant()
					  .atZone( ZoneId.systemDefault() )
					  .toLocalDate() )
	  );
	  pst.setDouble( 4, obj.getBaseSalary() );
	  pst.setInt( 5, obj.getDepartment().getId() );

	  pst.executeUpdate();
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	}
  }

  @Override
  public void update(Seller obj) {

  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Seller findById(Integer id) throws DbException {
	PreparedStatement st = null;
	ResultSet rs = null;

	try {
	  String query =
			  "SELECT seller.*, dep.Name as DepName FROM seller " + "INNER JOIN Department" + " " +
					  "as dep ON seller.departmentId = dep.Id " + "WHERE seller.Id = ?";

	  st = this.conn.prepareStatement( query );
	  st.setInt( 1, id );
	  rs = st.executeQuery();

	  if ( rs.next() ) {
		Department dep = instantiateDepartment( rs );
		return instantiateSeller( rs, dep );
	  }
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( st );
	  DB.closeResultSet( rs );
	}

	return null;
  }

  @Override
  public List<Seller> findAll() {
	List<Seller> sellers = new ArrayList<>();
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	  String query = "SELECT seller.*, dep.Name as DepName " + "FROM seller " + "INNER JOIN " +
			  "department as dep " + "ON seller.departmentId = dep.Id " + "ORDER BY seller.Name";

	  pst = this.conn.prepareStatement( query );

	  rs = pst.executeQuery();

	  while (rs.next()) {
		Department dep = instantiateDepartment( rs );
		Seller seller = instantiateSeller( rs, dep );

		sellers.add( seller );
	  }
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	  DB.closeResultSet( rs );
	}

	return sellers;
  }

  @Override
  public List<Seller> findByDepartmentId(Integer id) throws DbException {
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<Seller> sellers = new ArrayList<>();
	String query = "SELECT seller.*, dep.Name as DepName " + "FROM seller " + "INNER JOIN " +
			"department as dep ON seller.DepartmentId = dep.Id " + "WHERE seller.DepartmentId = ?";

	try {
	  pst = this.conn.prepareStatement( query );
	  pst.setInt( 1, id );

	  rs = pst.executeQuery();

	  Department dep = null;
	  while (rs.next()) {
		if ( dep == null ) {
		  dep = instantiateDepartment( rs );
		}

		Seller seller = instantiateSeller( rs, dep );

		sellers.add( seller );
	  }
	} catch (SQLException e) {
	  throw new DbException( e.getMessage() );
	} finally {
	  DB.closePreparedStatement( pst );
	  DB.closeResultSet( rs );
	}

	return sellers;
  }

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
	return new Department().setId( rs.getInt( "DepartmentId" ) )
			.setName( rs.getString( "DepName" ) );
  }

  private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
	return new Seller().setId( rs.getInt( "Id" ) )
			.setName( rs.getString( "Name" ) )
			.setEmail( rs.getString( "Email" ) )
			.setBaseSalary( rs.getDouble( "BaseSalary" ) )
			.setBirthDate( rs.getDate( "BirthDate" ) )
			.setDepartment( dep );
  }
}
