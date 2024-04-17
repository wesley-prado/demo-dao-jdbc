package model.dao.impl;

import database.DB;
import database.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {

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
			String query = "SELECT seller.*, dep.Name as DepName FROM seller "
					+ "INNER JOIN Department as dep ON seller.departmentId = dep.Id "
					+ "WHERE seller.Id = ?";
			st = this.conn.prepareStatement( query );
			st.setInt( 1, id );
			rs = st.executeQuery();

			if ( rs.next() ) {
				Department dep = instantiateDepartment( rs );
				Seller seller = instantiateSeller( rs, dep );

				return seller;
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
		return null;
	}

	@Override
	public List<Seller> findByDepartmentId(Integer id) throws DbException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Seller> sellers = new ArrayList<>();
		String query = "SELECT seller.*, dep.Name as DepName " +
				"FROM seller " +
				"INNER JOIN department as dep ON seller.DepartmentId = dep.Id " +
				"WHERE seller.DepartmentId = ?";

		try {
			pst = this.conn.prepareStatement( query );
			pst.setInt( 1, id );

			rs = pst.executeQuery();

			Department dep = null;
			while ( rs.next() ) {
				if(dep == null){
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
		Department dep = new Department()
				.setId( rs.getInt( "DepartmentId" ) )
				.setName( rs.getString( "DepName" ) );

		return dep;
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller()
				.setId( rs.getInt( "Id" ) )
				.setName( rs.getString( "Name" ) )
				.setEmail( rs.getString( "Email" ) )
				.setBaseSalary( rs.getDouble( "BaseSalary" ) )
				.setBirthDate( rs.getDate( "BirthDate" ) )
				.setDepartment( dep );

		return seller;
	}
}
