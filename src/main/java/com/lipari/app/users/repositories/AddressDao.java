package com.lipari.app.users.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.utils.DbConnection;
import org.springframework.stereotype.Repository;

import com.lipari.app.commons.repositories.BaseDao;

@Repository
public class AddressDao extends BaseDao{
	
	public AddressDao(DbConnection dbConnection) {
		super(dbConnection);
	}

	public List<String> getAllAddress(int userId) {
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

	public boolean setAddress(int userId, String indirizzo) {
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
	public boolean deleteAddress( int id) {
		String sql = "DELETE FROM t_address WHERE id=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, id);					
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
