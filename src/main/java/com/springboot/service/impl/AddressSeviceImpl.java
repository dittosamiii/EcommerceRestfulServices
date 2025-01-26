package com.springboot.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.dto.AddressDto;
import com.springboot.dto.OrderHeaderDto;
import com.springboot.entity.Address;
import com.springboot.entity.CustomerInfo;
import com.springboot.entity.OrderHeader;
import com.springboot.repository.AddressRepository;
import com.springboot.repository.OrderHeaderRepository;
import com.springboot.service.AddressService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressSeviceImpl implements AddressService {

	private AddressRepository addressRepository;
	private OrderHeaderRepository orderHeaderRepository;
	private ModelMapper modelMapper;

	@Override
	public OrderHeaderDto createAddress(Long orderId, AddressDto addressDto) {

		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order With this id doesn't exists!"));

		CustomerInfo customerInfo = orderHeader.getBillingCustomer();
		List<Address> addresses = customerInfo.getAddress();
		Address address = modelMapper.map(addressDto, Address.class);
		address.setCustomerInfo(customerInfo);
		addresses.add(address);

		OrderHeader orderHeaderDb = orderHeaderRepository.save(orderHeader);

		return modelMapper.map(orderHeaderDb, OrderHeaderDto.class);
	}

	@Override
	public AddressDto getAddressById(Long orderId, Long addressSeqNum) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order Not Found!"));

		List<Address> addresses = orderHeader.getBillingCustomer().getAddress();
		for (Address address : addresses) {
			if (address.getAddressSeqNumber().equals(addressSeqNum)) {
				return modelMapper.map(address, AddressDto.class);
			}
		}
		throw new RuntimeException("Address not belongs to this order id or invalid address id or orderId");
	}

	@Override
	public List<AddressDto> getAddressesByOrderId(Long orderId) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order Not Found!"));

		List<Address> addresses = orderHeader.getBillingCustomer().getAddress();
		return addresses.stream().map((address) -> modelMapper.map(address, AddressDto.class)).toList();
	}

	@Override
	public AddressDto updateAddress(Long orderId, Long addressSeqNum, AddressDto addressDto) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order Not Found!"));

		List<Address> addresses = orderHeader.getBillingCustomer().getAddress();
		for (Address address : addresses) {
			if (address.getAddressSeqNumber().equals(addressSeqNum)) {
				address.setType(addressDto.getType());
				address.setAddress1(addressDto.getAddress1());
				address.setAddress2(addressDto.getAddress2());
				address.setCity(addressDto.getCity());
				address.setState(addressDto.getState());
				address.setCountry(addressDto.getCountry());
				address.setPostalCode(addressDto.getPostalCode());

				addressRepository.save(address);

				return modelMapper.map(address, AddressDto.class);
			}
		}
		throw new RuntimeException("Address not belongs to this order id or invalid address id or orderId");
	}

	@Override
	public void deleteAddressById(Long seqNum) {
		addressRepository.findById(seqNum).orElseThrow(() -> new RuntimeException("Address Not Found!"));
		addressRepository.deleteById(seqNum);
	}

}
