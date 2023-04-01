package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJBDC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dp = instantiateDepartment(rs);
				Seller se = instantiateSeller(rs, dp);
				return se;
			}
			
			return null;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResulSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dp = new Department();
		dp.setId(rs.getInt("DepartmentId"));
		dp.setName(rs.getString("DepName"));
		return dp;
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
		Seller se = new Seller();
		se.setId(rs.getInt("Id"));
		se.setName(rs.getString("Name"));
		se.setEmail(rs.getString("Email"));
		se.setBaseSalary(rs.getDouble("BaseSalary"));
		se.setBirthDate(rs.getDate("BirthDate"));
		se.setDepartment(dp);
		return se;
	}
}
