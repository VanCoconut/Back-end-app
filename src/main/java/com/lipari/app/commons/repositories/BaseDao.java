
package com.lipari.app.commons.repositories;

import java.util.Objects;

import com.lipari.app.utils.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseDao {

	protected DbConnection dbConnection;

	@Autowired
	public BaseDao(DbConnection dbConnection) {
		this.dbConnection = Objects.requireNonNull(dbConnection);
	}

}
