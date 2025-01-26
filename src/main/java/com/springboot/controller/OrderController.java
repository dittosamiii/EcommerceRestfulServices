package com.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.OrderHeaderDto;
import com.springboot.dto.Request;
import com.springboot.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderHeaderDto> createOrder(@RequestBody Request Header) {
		return new ResponseEntity<>(orderService.createOrder(Header), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<OrderHeaderDto> getOrderById(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@GetMapping
	public ResponseEntity<List<OrderHeaderDto>> getOrders() {
		return ResponseEntity.ok(orderService.getOrders());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<OrderHeaderDto> updateOrder(@PathVariable Long id, @RequestBody Request Header) {
		return ResponseEntity.ok(orderService.updateOrderById(id, Header));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id){
		orderService.deleteOrderById(id);
		return ResponseEntity.ok("Order Deleted Successfully!");
	}

}
