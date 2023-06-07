package com.lipari.app.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.lipari.app.exception.DataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao extends BaseDao{
	
	
	
	public AddressDao(DbConnection dbConnection) {
		super(dbConnection);
		// TODO Auto-generated constructor stub
	}

	public List<String> getAllAddress(int userId) throws DataException {
		List<String> l = new ArrayList<>();
		String sql = "SELECT indirizzo FROM t_address WHERE userId=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.add(rs.getString(1));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}

	public boolean setAddress( int userId, String indirizzo) throws DataException {
		String sql = "INSERT INTO t_address (userId,indirizzo) VALUES (?,?);";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(3, userId);
			ps.setString(1, indirizzo);			
			var rs = ps.executeUpdate();
			while (rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}
	public boolean updateAddress( int userId, String indirizzo) throws DataException {
		/*
		 * String sql = "INSERT INTO t_address (userId,indirizzo) VALUES (?,?);"; try
		 * (PreparedStatement ps = getConnection().prepareStatement(sql)) { ps.setInt(3,
		 * userId); ps.setString(1, indirizzo); var rs = ps.executeUpdate(); while (rs
		 * == 1) { return true; } } catch (SQLException e) { throw new DataException(e);
		 * } catch (Exception e) { throw new DataException(e); }
		 */
		return false;
	}
	public boolean deleteAddress( int userId, String indirizzo) throws DataException {
		String sql = "DELETE FROM t_address WHERE userId=? AND indirizzo=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(3, userId);
			ps.setString(1, indirizzo);			
			var rs = ps.executeUpdate();
			while (rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}
}
