package com.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long phoneSeqNumber;
	private String typeCode;
	private int countryCode;
	private int areaCode;
	private Long number;
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private CustomerInfo customerInfo;

}
