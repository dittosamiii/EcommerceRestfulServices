package com.springboot.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.dto.ContactDetailsDto;
import com.springboot.dto.OrderHeaderDto;
import com.springboot.entity.ContactDetails;
import com.springboot.entity.CustomerInfo;
import com.springboot.entity.OrderHeader;
import com.springboot.repository.ContactDetailsRepository;
import com.springboot.repository.OrderHeaderRepository;
import com.springboot.service.ContactDetailsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactDetailsServiceImpl implements ContactDetailsService {

	private ContactDetailsRepository contactDetailsRepository;
	private OrderHeaderRepository orderHeaderRepository;
	private ModelMapper modelMapper;

	@Override
	public OrderHeaderDto createContact(Long orderId, ContactDetailsDto contactDetailsDto) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order With this id doesn't exists!"));

		CustomerInfo customerInfo = orderHeader.getBillingCustomer();
		List<ContactDetails> contacts = customerInfo.getContactDetails();
		ContactDetails contact = modelMapper.map(contactDetailsDto, ContactDetails.class);
		contact.setCustomerInfo(customerInfo);
		contacts.add(contact);

		OrderHeader orderHeaderDb = orderHeaderRepository.save(orderHeader);

		return modelMapper.map(orderHeaderDb, OrderHeaderDto.class);
	}

	@Override
	public List<ContactDetailsDto> getContactsByOrderId(Long orderId) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order Not Found!"));

		List<ContactDetails> contacts = orderHeader.getBillingCustomer().getContactDetails();
		return contacts.stream().map((contact) -> modelMapper.map(contact, ContactDetailsDto.class)).toList();
	}

	@Override
	public ContactDetailsDto getContactById(Long orderId, Long phoneSeqNum) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order Not Found!"));

		List<ContactDetails> contacts = orderHeader.getBillingCustomer().getContactDetails();
		for (ContactDetails contact : contacts) {
			if (contact.getPhoneSeqNumber().equals(phoneSeqNum)) {
				return modelMapper.map(contact, ContactDetailsDto.class);
			}
		}
		throw new RuntimeException("Contact not belongs to this order id or invalid contact id or orderId");
	}


	@Override
	public ContactDetailsDto updateContact(Long orderId, Long phoneSeqNum, ContactDetailsDto contactDto) {
		OrderHeader orderHeader = orderHeaderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order Not Found!"));

		List<ContactDetails> contacts = orderHeader.getBillingCustomer().getContactDetails();
		for (ContactDetails contact : contacts) {
			if (contact.getPhoneSeqNumber().equals(phoneSeqNum)) {
				contact.setTypeCode(contactDto.getTypeCode());
				contact.setCountryCode(contactDto.getCountryCode());
				contact.setAreaCode(contactDto.getAreaCode());
				contact.setNumber(contactDto.getNumber());
				contact.setEmail(contactDto.getEmail());

				contactDetailsRepository.save(contact);

				return modelMapper.map(contact, ContactDetailsDto.class);
			}
		}
		throw new RuntimeException("Contact not belongs to this order id or invalid contact id or orderId");
	}

	@Override
	public void deleteContactById(Long phoneSeqNum) {
		contactDetailsRepository.findById(phoneSeqNum).orElseThrow(() -> new RuntimeException("Contact Not Found!"));
		contactDetailsRepository.deleteById(phoneSeqNum);
	}
}
