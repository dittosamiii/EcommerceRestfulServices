package com.springboot.dto;

import java.time.LocalDateTime;

import com.springboot.entity.CustomerInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeaderDto {
	private Long orderId;
	private String externalOrderId;
	private String sourceId;
	private String destinationId;
	private LocalDateTime orderDateTime;
	private String orderType;
	private CustomerInfo billingCustomer;

}
