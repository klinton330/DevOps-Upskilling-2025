package com.ms.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.ms.accounts.entity.Account;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
	public Optional<Account> findByCustomerId(Long customerId);
	
	@Transactional
	@Modifying
	public void deleteByCustomerId(Long customerId);
}
