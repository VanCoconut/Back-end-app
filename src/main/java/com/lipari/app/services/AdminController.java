package com.lipari.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.dao.OrderDao;
import com.lipari.app.model.vo.Order;

@Service
public class AdminController {
	
	OrderDao od;
	
	public AdminController() {
		
	}
	
	public AdminController(OrderDao od) {
		this.od = od;
	}



	public List<Order> retrieveAdminOrders(){
		
		try {
			return od.getAadminOrders();
		} catch (DataException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
