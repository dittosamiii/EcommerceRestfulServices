package com.springboot.service;

import java.util.List;

import com.springboot.dto.AddressDto;
import com.springboot.dto.OrderHeaderDto;

public interface AddressService {

	OrderHeaderDto createAddress(Long orderId, AddressDto addressDto);

	List<AddressDto> getAddressesByOrderId(Long orderId);

	AddressDto getAddressById(Long orderId, Long addressSeqNum);

	AddressDto updateAddress(Long orderId, Long addressSeqNum, AddressDto addressDto);

	void deleteAddressById(Long seqNum);

}
