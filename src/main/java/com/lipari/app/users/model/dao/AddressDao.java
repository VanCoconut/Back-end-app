package com.lipari.app.users.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lipari.app.commons.BaseDao;
import com.lipari.app.commons.DbConnection;
import com.lipari.app.exception.DataException;

@Repository
public class AddressDao extends BaseDao{
	
	
	
	public AddressDao(DbConnection dbConnection) {
		super(dbConnection);
		// TODO Auto-generated constructor stub
	}

	public List<String> getAllAddress(int userId) throws DataException {
		List<String> l = new ArrayList<>();
		String sql = "SELECT indirizzo FROM t_address WHERE user_id=?";
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
		String sql = "INSERT INTO t_address (user_id,indirizzo) VALUES (?,?);";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setString(2, indirizzo);			
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
		String sql = "DELETE FROM t_address WHERE user_id=? AND indirizzo=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setString(2, indirizzo);			
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
