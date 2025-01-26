package com.springboot.service;

import java.util.List;

import com.springboot.dto.OrderHeaderDto;
import com.springboot.dto.Request;

public interface OrderService {
	OrderHeaderDto createOrder(Request Header);
	
	OrderHeaderDto getOrderById(Long id);
	
	List<OrderHeaderDto> getOrders();
	
	OrderHeaderDto updateOrderById(Long id, Request Header);
	
	void deleteOrderById(Long id);
}
