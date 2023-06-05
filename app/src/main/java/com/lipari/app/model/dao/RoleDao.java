package com.lipari.app.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.vo.Role;

public class RoleDao extends BaseDao {
	public List<Role> getAllRoles() throws DataException {
		List<Role> l = new ArrayList<>();
		String sql = "SELECT * FROM t_role";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.add(new Role(rs.getInt(1),rs.getString(2)));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}
	public Role getRole(int id) throws DataException {

		String sql = "SELECT * FROM t_role WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Role(rs.getInt(1),rs.getString(2));
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return null;
	}
	public Role getRole(String descrizione) throws DataException {

		String sql = "SELECT * FROM t_role WHERE descrizione=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, descrizione.toLowerCase());			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Role(rs.getInt(1),rs.getString(2));
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return null;
	}
	public boolean setRole(int id,String descrizione) throws DataException {

		String sql = "INSERT INTO t_role (id,descrizione) VALUES (?,?)";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.setString(1, descrizione.toLowerCase());			
			var rs = ps.executeUpdate();
			if (rs==1) {
				return true;
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}
	public boolean updateRole(int id,int newId, String newDescr) throws DataException {

		String sql = "UPDATE t_role SET id=?, descrizione=? WHERE id=?; ";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, newId);			
			ps.setString(2, newDescr.toLowerCase());
			ps.setInt(1, id);	
			var rs = ps.executeUpdate();
			if (rs==1) {
				return true;
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}
	public boolean deleteRole(int id) throws DataException {

		String sql = "DELETE from t_role WHERE id=? ";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);			
			var rs = ps.executeUpdate();
			if (rs==1) {
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
