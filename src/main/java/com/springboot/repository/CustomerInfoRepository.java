package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.CustomerInfo;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {

}
