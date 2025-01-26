package com.springboot.dto;

import com.springboot.entity.OrderHeader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
	private OrderHeader orderHeader;
}
