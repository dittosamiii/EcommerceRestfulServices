package com.springboot.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.dto.OrderHeaderDto;
import com.springboot.dto.Request;
import com.springboot.entity.Address;
import com.springboot.entity.ContactDetails;
import com.springboot.entity.CustomerInfo;
import com.springboot.entity.OrderHeader;
import com.springboot.repository.OrderHeaderRepository;
import com.springboot.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderHeaderRepository orderHeaderRepository;
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public OrderHeaderDto createOrder(Request Header) {
		OrderHeader orderHeader = Header.getOrderHeader();

		// fetching billing customer from order header
		CustomerInfo customerInfo = orderHeader.getBillingCustomer();
		customerInfo.setOrderHeader(orderHeader);

		// getting and setting address
		List<Address> addresses = customerInfo.getAddress();
		for (Address address : addresses) {
			address.setCustomerInfo(customerInfo);
		}

		// getting and setting contact details
		List<ContactDetails> contactDetails = customerInfo.getContactDetails();
		for (ContactDetails contactDetail : contactDetails) {
			contactDetail.setCustomerInfo(customerInfo);
		}

		// cascade type is all so it will save all the rest so we don't need the rest.
		// saving everything with this single command
		orderHeader = orderHeaderRepository.save(orderHeader);

		return modelMapper.map(orderHeader, OrderHeaderDto.class);
	}

	@Override
	public List<OrderHeaderDto> getOrders() {
		List<OrderHeader> orderHeaders = orderHeaderRepository.findAll();
		return orderHeaders.stream().map((order) -> modelMapper.map(order, OrderHeaderDto.class)).toList();
	}

	@Override
	public OrderHeaderDto getOrderById(Long id) {
		OrderHeader orderHeader = orderHeaderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order Not found"));
		return modelMapper.map(orderHeader, OrderHeaderDto.class);
	}

	@Override
	@Transactional
	public OrderHeaderDto updateOrderById(Long id, Request header) {
	    OrderHeader orderHeader = orderHeaderRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Order Not found"));

	    OrderHeader updatedOrderHeader = header.getOrderHeader();

	    // update the orderHeader with new values
	    orderHeader.setExternalOrderId(updatedOrderHeader.getExternalOrderId());
	    orderHeader.setSourceId(updatedOrderHeader.getSourceId());
	    orderHeader.setDestinationId(updatedOrderHeader.getDestinationId());
	    orderHeader.setOrderType(updatedOrderHeader.getOrderType());

	    CustomerInfo updatedCustomerInfo = updatedOrderHeader.getBillingCustomer();
	    CustomerInfo existingCustomerInfo = orderHeader.getBillingCustomer();

	    // update customer info
	    existingCustomerInfo.setId(updatedCustomerInfo.getId());
	    existingCustomerInfo.setFirstName(updatedCustomerInfo.getFirstName());
	    existingCustomerInfo.setLastName(updatedCustomerInfo.getLastName());
	    existingCustomerInfo.setPurchaserType(updatedCustomerInfo.getPurchaserType());
	    existingCustomerInfo.setOrderHeader(orderHeader);

	    // update addresses
	    List<Address> updatedAddresses = updatedCustomerInfo.getAddress();
	    List<Address> existingAddresses = existingCustomerInfo.getAddress();

	    for (Address updatedAddress : updatedAddresses) {
	        if (updatedAddress.getAddressSeqNumber() == null) {
	            // new address, add to existing addresses
	            updatedAddress.setCustomerInfo(existingCustomerInfo);
	            existingAddresses.add(updatedAddress);
	        } else {
	            // update existing address
	            for (Address existingAddress : existingAddresses) {
	                if (existingAddress.getAddressSeqNumber().equals(updatedAddress.getAddressSeqNumber())) {
	                    existingAddress.setType(updatedAddress.getType());
	                    existingAddress.setAddress1(updatedAddress.getAddress1());
	                    existingAddress.setAddress2(updatedAddress.getAddress2());
	                    existingAddress.setCity(updatedAddress.getCity());
	                    existingAddress.setState(updatedAddress.getState());
	                    existingAddress.setPostalCode(updatedAddress.getPostalCode());
	                    existingAddress.setCountry(updatedAddress.getCountry());
	                }
	            }
	        }
	    }

	    // update contact details
	    List<ContactDetails> updatedContactDetails = updatedCustomerInfo.getContactDetails();
	    List<ContactDetails> existingContactDetails = existingCustomerInfo.getContactDetails();

	    for (ContactDetails updatedContactDetail : updatedContactDetails) {
	        if (updatedContactDetail.getPhoneSeqNumber() == null) {
	            // new contact detail, add to existing contact details
	            updatedContactDetail.setCustomerInfo(existingCustomerInfo);
	            existingContactDetails.add(updatedContactDetail);
	        } else {
	            // update existing contact detail
	            for (ContactDetails existingContactDetail : existingContactDetails) {
	                if (existingContactDetail.getPhoneSeqNumber().equals(updatedContactDetail.getPhoneSeqNumber())) {
	                    existingContactDetail.setTypeCode(updatedContactDetail.getTypeCode());
	                    existingContactDetail.setCountryCode(updatedContactDetail.getCountryCode());
	                    existingContactDetail.setAreaCode(updatedContactDetail.getAreaCode());
	                    existingContactDetail.setNumber(updatedContactDetail.getNumber());
	                    existingContactDetail.setEmail(updatedContactDetail.getEmail());
	                }
	            }
	        }
	    }

	    orderHeader = orderHeaderRepository.save(orderHeader);

	    return modelMapper.map(orderHeader, OrderHeaderDto.class);
	}


	@Override
	public void deleteOrderById(Long id) {
		orderHeaderRepository.deleteById(id);
	}

}
