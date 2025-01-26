package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerInfo {

	@Id
	private Long orderId;

	private String id;
	private String firstName;
	private String lastName;
	private String purchaserType;

	@OneToOne
	@MapsId
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private OrderHeader orderHeader;

	@OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("customerInfo")
	private List<Address> address;

	@OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("customerInfo")
	private List<ContactDetails> contactDetails;

}
