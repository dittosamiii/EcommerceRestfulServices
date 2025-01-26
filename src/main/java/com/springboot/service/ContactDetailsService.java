package com.springboot.service;

import java.util.List;

import com.springboot.dto.ContactDetailsDto;
import com.springboot.dto.OrderHeaderDto;

public interface ContactDetailsService {

	OrderHeaderDto createContact(Long orderId, ContactDetailsDto contactDetailsDto);

	List<ContactDetailsDto> getContactsByOrderId(Long orderId);

	ContactDetailsDto getContactById(Long orderId, Long phoneSeqNum);

	ContactDetailsDto updateContact(Long orderId, Long phoneSeqNum, ContactDetailsDto contactDetailsDto);

	void deleteContactById(Long phoneSeqNum);
}
