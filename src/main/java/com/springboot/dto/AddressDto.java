package com.springboot.dto;

import lombok.Data;

@Data
public class AddressDto {
	private Long addressSeqNumber;
	private String type;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
}
