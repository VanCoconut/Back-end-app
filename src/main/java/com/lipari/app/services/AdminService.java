package com.lipari.app.services;

import java.util.List;

import com.lipari.app.model.dao.UserDao;
import com.lipari.app.model.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.dao.OrderDao;
import com.lipari.app.model.vo.Order;

@Service
public class AdminService {
	
	OrderDao orderDao;
	UserDao userDao;

	@Autowired
	public AdminService(OrderDao orderDao, UserDao userDao) {
		this.orderDao = orderDao;
		this.userDao = userDao;
	}

	public ResponseEntity<?> getUserById(int id){
			return userDao.existsById(id)
					? ResponseEntity.status(HttpStatus.OK).body(userDao.getUserById(id))
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
	public List<Order> retrieveAdminOrders(){
		try {
			return orderDao.getAadminOrders();
		} catch (DataException e) {
			e.printStackTrace();
		}
		return null;
	}

}
