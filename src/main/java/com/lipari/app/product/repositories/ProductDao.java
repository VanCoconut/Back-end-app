package com.lipari.app.product.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.commons.repositories.BaseDao;
import com.lipari.app.product.entities.Product;
import com.lipari.app.utils.DbConnection;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends BaseDao {

	public ProductDao(DbConnection dbConnection) {
		super(dbConnection);
	}

	public List<Product> getAllProduct() {
		List<Product> l = new ArrayList<>();
		String sql = "SELECT * FROM t_product";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()){
				throw new DataException("no product found");
			}
			while (rs.next()) {
				l.add(new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getInt(5)));
			}
			return l;
		} catch (SQLException e) {
			throw new DataException("Error retrieving products from the database", e);
		}
	}

	public Product getProduct(int id) {

		String sql = "SELECT * FROM t_product WHERE id=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()){
				throw new DataException("no product found");
			}
			while(rs.next()) {
				return new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getInt(5));
			}

		} catch (SQLException e) {
			throw new DataException(e);
		}
		return null;
	}

	public boolean setProduct(int codice, String descrizione, float costo, int magazzino) {

		String sql = "INSERT INTO t_product (codice,descrizione,costo,magazzino) VALUES (?,?,?,?)";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, codice);
			ps.setString(2, descrizione);
			ps.setFloat(3, costo);
			ps.setInt(4, codice);

			var rs = ps.executeUpdate();
			if (rs == 1) {
				return true;
			}
		} catch (Exception e) {
			throw new DataException(e);
		}
		return false;
	}

	public void updateProduct(Product product) {
		String sql = "UPDATE t_product SET codice=?,descrizione=?,costo=?,magazzino=? WHERE id=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {

			ps.setInt(1, product.getCodice());
			ps.setString(2, product.getDescrizione());
			ps.setFloat(3, product.getCosto());
			ps.setInt(4, product.getMagazzino());
			ps.setInt(5,product.getId());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataException("Failed to update product");
			}
		} catch (SQLException e) {
			throw new DataException("Error updating product: "+ e.getMessage());
		}
	}

	public void deleteProduct(int id) {

		String sql = "DELETE FROM t_product WHERE id=?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				throw new DataException("Failed to delete product");
			}
		} catch (SQLException e) {
			throw new DataException("Error deleting product: "+ e.getMessage());
		}
	}

	public boolean existsById(int productId){
		String query= "SELECT COUNT(*) FROM t_product WHERE id=?";
		try(PreparedStatement ps = dbConnection.openConnection().prepareStatement(query)) {
			ps.setInt(1,productId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int count = rs.getInt(1);
				return count > 0;
			}
		}catch (SQLException e){
			throw new DataException("Error checking existence of codice: " + e.getMessage());
		}
		return false;
	}

	public boolean existsByCodice(int codice) {
		String sql = "SELECT COUNT(*) FROM t_product WHERE codice = ?";
		try (PreparedStatement ps = dbConnection.openConnection().prepareStatement(sql)) {
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			throw new DataException("Error checking existence of codice: " + e.getMessage());
		}
		return false;
	}
}
