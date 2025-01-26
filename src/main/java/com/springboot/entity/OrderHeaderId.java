package com.springboot.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHeaderId implements Serializable {
	private Long orderId;
	private String externalOrderId;
}
