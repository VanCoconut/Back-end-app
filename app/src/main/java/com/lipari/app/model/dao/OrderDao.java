package com.lipari.app.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.vo.Order;

public class OrderDao extends BaseDao {
	
	public List<Order> getAadminOrders() throws DataException {
		List<Order> l = new ArrayList<>();
		String sql = "SELECT * FROM t_order";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.add(new Order(rs.getString(1),rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getString(4)));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}
	public List<Order> getAllOrders(int userId) throws DataException {
		List<Order> l = new ArrayList<>();		
		String sql = "SELECT * FROM t_order WHERE userId=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.add(new Order(rs.getString(1),rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getString(4)));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}
	public Order getOrder(String id) throws DataException {		
		String sql = "SELECT * FROM t_order WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Order(rs.getString(1),rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getString(4));
			}
			
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return null;
		
	}
	public boolean setOrder(String id,int userId,LocalDate data,String indirizzo) throws DataException {

		String sql = "INSERT INTO t_order (id,userId,data,indirizzo) VALUES (?,?,?,?);";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, id);
			ps.setInt(2, userId);			
			ps.setDate(3, Date.valueOf(data));
			ps.setString(4, indirizzo);			
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
	public boolean updateOrder(String id,LocalDate data,String indirizzo) throws DataException {

		String sql = "UPDATE t_order SET data=?,indirizzo=? WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(data));
			ps.setString(1, id);
			ps.setString(1, id);	
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
	public boolean deleteOrder(String id) throws DataException {

		String sql = "DELETE FROM t_order WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, id);	
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
