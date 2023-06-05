package com.lipari.app.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.vo.User;

public class UserDao extends BaseDao {

	public User getUser(String usr, String psw) throws DataException {

		String sql = "SELECT * FROM t_user WHERE username=? AND password=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, usr);
			ps.setString(2, psw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return null;
	}

	public boolean setUser(String nome, String cognome, String username, String password, String email, int role)
			throws DataException {

		String sql = "INSERT INTO t_user (nome,cognome,username,password,email,role) VALUES (?,?,?,?,?,?)";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, email);
			ps.setInt(6, role);
			var rs = ps.executeUpdate();
			if (rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}

	public boolean updateUser(int id, String nome, String cognome, String username, String password, String email,
			int role) throws DataException {

		String sql = "UPDATE t_user SET nome=?,cognome=?,username=?,password=?,email=?,role=? WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, email);
			ps.setInt(6, role);
			ps.setInt(7, id);
			var rs = ps.executeUpdate();
			if (rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}

	public boolean deleteUser(int id) throws DataException {

		String sql = "DELETE FROM t_user WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			var rs = ps.executeUpdate();
			if (rs == 1) {
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
