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

import com.springboot.dto.AddressDto;
import com.springboot.dto.OrderHeaderDto;
import com.springboot.service.AddressService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders/addresses")
@AllArgsConstructor
public class AddressController {

	private AddressService addressService;

	@PostMapping("{orderId}")
	public ResponseEntity<OrderHeaderDto> createAddress(@PathVariable Long orderId,
			@RequestBody AddressDto addressDto) {
		return new ResponseEntity<>(addressService.createAddress(orderId, addressDto), HttpStatus.CREATED);
	}

	@GetMapping("{orderId}/{addressSeqNum}")
	public ResponseEntity<AddressDto> getAddress(@PathVariable Long orderId, @PathVariable Long addressSeqNum) {
		return ResponseEntity.ok(addressService.getAddressById(orderId, addressSeqNum));
	}

	@GetMapping("{orderId}")
	public ResponseEntity<List<AddressDto>> getAddresses(@PathVariable Long orderId) {
		return ResponseEntity.ok(addressService.getAddressesByOrderId(orderId));
	}

	@PutMapping("{orderId}/{addressSeqNum}")
	public ResponseEntity<AddressDto> updateAddress(@PathVariable Long orderId, @PathVariable Long addressSeqNum,
			@RequestBody AddressDto addressDto) {
		return ResponseEntity.ok(addressService.updateAddress(orderId, addressSeqNum, addressDto));
	}

	@DeleteMapping("{seqNum}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long seqNum) {
		addressService.deleteAddressById(seqNum);
		return ResponseEntity.ok("Deleted Successfully!");
	}

}
