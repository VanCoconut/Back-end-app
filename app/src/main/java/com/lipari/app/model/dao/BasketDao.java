package com.lipari.app.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.vo.Basket;

public class BasketDao extends BaseDao {

	public List<Basket> getBasket(String orderId) throws DataException {
		List<Basket> l = new ArrayList<>();
		String sql = "SELECT * FROM t_basket WHERE orderId=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.add(new Basket(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}
	
	/*
	 * public Map<String,Basket> getAllBasket(String orderId) throws DataException {
	 * Map<String,Basket> l = new HashMap()<>(); String sql =
	 * "SELECT * FROM t_basket WHERE orderId=?"; try (PreparedStatement ps =
	 * getConnection().prepareStatement(sql)) { ps.setString(1, orderId); ResultSet
	 * rs = ps.executeQuery(); while (rs.next()) { l.add(orderId,new
	 * Basket(rs.getString(1), rs.getInt(2), rs.getInt(3))); } return l; } catch
	 * (SQLException e) { throw new DataException(e); } catch (Exception e) { throw
	 * new DataException(e); } }
	 */
	

	public boolean setBasket(String orderId, int productId, int qta) throws DataException {
		String sql = "INSERT INTO t_basket (orderId,productId,quantita) VALUES (?,?,?);";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, orderId);
			ps.setInt(2, productId);
			ps.setInt(3, qta);
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

	public boolean updateBasket(String orderId, int productId, int qta) throws DataException {
		String sql = "UPDATE t_basket SET productId=?,quantita=? WHERE orderId=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(2, productId);
			ps.setInt(3, qta);
			ps.setString(1, orderId);
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

	public boolean deleteBasket(String orderId, int productId) throws DataException {
		String sql = "DELETE from t_basket WHERE orderId=? AND productId=? ";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, orderId);
			ps.setInt(2, productId);
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
