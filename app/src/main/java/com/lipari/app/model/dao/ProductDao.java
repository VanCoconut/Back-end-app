package com.lipari.app.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.vo.Product;

public class ProductDao extends BaseDao {
	public List<Product> getAllProduct() throws DataException {
		List<Product> l = new ArrayList<>();
		String sql = "SELECT * FROM t_product";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.add(new Product(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getInt(5)));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}
	public Product getProduct(int id) throws DataException {

		String sql = "SELECT * FROM t_product WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
		return null;
	}
	public boolean setProduct(int codice,String descrizione,float costo,int magazzino) throws DataException {

		String sql = "INSERT INTO t_product (codice,descrizione,costo,magazzino) VALUES (?,?,?,?)";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, codice);
			ps.setString(2, descrizione);
			ps.setFloat(3, costo);
			ps.setInt(4, codice);
			
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
	public boolean updateProduct(int id,int codice,String descrizione,float costo,int magazzino) throws DataException {

		String sql = "UPDATE t_product SET codice=?,descrizione=?,costo=?,magazzino=? WHERE id=?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setInt(1, codice);	
			ps.setString(2, descrizione);
			ps.setFloat(3, costo);
			ps.setInt(4, magazzino);
			ps.setInt(5, id);			
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
	public boolean deleteProduct(int id) throws DataException {

		String sql = "DELETE FROM t_product WHERE id=?";
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
