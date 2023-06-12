package com.lipari.app.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lipari.app.exception.DataException;


public class BaseDao {

	protected DbConnection dbConnection;

	@Autowired
	public BaseDao(DbConnection dbConnection) {
		this.dbConnection = Objects.requireNonNull(dbConnection);
	}

}
