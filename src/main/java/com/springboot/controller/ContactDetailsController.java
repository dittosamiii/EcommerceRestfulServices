package com.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.ContactDetailsDto;
import com.springboot.dto.OrderHeaderDto;
import com.springboot.service.ContactDetailsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders/contacts")
@AllArgsConstructor
public class ContactDetailsController {
	private ContactDetailsService contactDetailsService;

	@PostMapping("{orderId}")
	public ResponseEntity<OrderHeaderDto> createAddress(@PathVariable Long orderId,
			@RequestBody ContactDetailsDto contactDto) {
		return new ResponseEntity<>(contactDetailsService.createContact(orderId, contactDto), HttpStatus.CREATED);
	}

	@GetMapping("{orderId}")
	public ResponseEntity<List<ContactDetailsDto>> getContacts(@PathVariable Long orderId) {
		return ResponseEntity.ok(contactDetailsService.getContactsByOrderId(orderId));
	}

	@GetMapping("{orderId}/{phoneSeqNum}")
	public ResponseEntity<ContactDetailsDto> getContact(@PathVariable Long orderId, @PathVariable Long phoneSeqNum) {
		return ResponseEntity.ok(contactDetailsService.getContactById(orderId, phoneSeqNum));
	}
	
	@PutMapping("{orderId}/{phoneSeqNum}")
	public ResponseEntity<ContactDetailsDto> updateContact(@PathVariable Long orderId, @PathVariable Long phoneSeqNum,
			@RequestBody ContactDetailsDto contactDto) {
		return ResponseEntity.ok(contactDetailsService.updateContact(orderId, phoneSeqNum, contactDto));
	}

	@DeleteMapping("{phoneSeqNum}")
	public ResponseEntity<String> deleteContact(@PathVariable Long phoneSeqNum) {
		contactDetailsService.deleteContactById(phoneSeqNum);
		return ResponseEntity.ok("Contact Deleted Successfully!");
	}
}
