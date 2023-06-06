package com.lipari.app.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.lipari.app.exception.DataException;
import org.springframework.stereotype.Repository;

public class BaseDao {

	DbConnection dbCon = null;

	public Connection getConnection() throws DataException {
		try {
			dbCon = DbConnection.getInstance();
			return dbCon.initService();
		} catch (SQLException e) {
			throw new DataException(e);
		} catch (Exception e) {
			throw new DataException(e);
		}
	}

}
