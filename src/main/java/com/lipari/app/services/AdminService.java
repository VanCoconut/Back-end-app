package com.lipari.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.dao.OrderDao;
import com.lipari.app.model.vo.Order;

@Service
public class AdminService {
	
	OrderDao od;

	@Autowired
	public AdminService(OrderDao od) {
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
