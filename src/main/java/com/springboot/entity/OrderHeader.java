package com.springboot.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	@SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1, initialValue = 1000)
	private Long orderId;

	@Column(unique = true)
	private String externalOrderId;

	private String sourceId;
	private String destinationId;
	@CreationTimestamp
	private LocalDateTime orderDateTime;
	private String orderType;

	@OneToOne(mappedBy = "orderHeader", cascade = CascadeType.ALL)
	@JsonManagedReference
	private CustomerInfo billingCustomer;
}
