package com.ms.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
  Optional<Customer> findByMobileNumber(String mobileNumber);
}
