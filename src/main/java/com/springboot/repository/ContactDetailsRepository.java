package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.ContactDetails;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

}
