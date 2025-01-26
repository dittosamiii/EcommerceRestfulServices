package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
