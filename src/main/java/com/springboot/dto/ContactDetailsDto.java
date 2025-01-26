package com.springboot.dto;

import lombok.Data;

@Data
public class ContactDetailsDto {
	private Long phoneSeqNumber;
	private String typeCode;
	private int countryCode;
	private int areaCode;
	private Long number;
	private String email;

}
