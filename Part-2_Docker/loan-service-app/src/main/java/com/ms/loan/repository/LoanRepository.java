package com.ms.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.loan.entity.Loans;

public interface LoanRepository extends JpaRepository<Loans, Long> {
	
	 Optional<Loans>findByMobileNumber(String mobileNo);
     Optional<Loans>findByLoanNumber(String loanNo);
}
