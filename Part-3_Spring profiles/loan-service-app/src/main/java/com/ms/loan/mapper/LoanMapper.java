package com.ms.loan.mapper;

import com.ms.loan.dto.LoansDTO;
import com.ms.loan.entity.Loans;

public class LoanMapper {

	public static LoansDTO mapToLoansDto(Loans loans, LoansDTO loansDto) {
		loansDto.setLoanNumber(loans.getLoanNumber());
		loansDto.setLoanType(loans.getLoanType());
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setTotalLoan(loans.getTotalLoan());
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setOutstandingAmount(loans.getOutStandingAmount());
		return loansDto;
	}

	public static Loans mapToLoans(LoansDTO loansDto, Loans loans) {
		loans.setLoanNumber(loansDto.getLoanNumber());
		loans.setLoanType(loansDto.getLoanType());
		loans.setMobileNumber(loansDto.getMobileNumber());
		loans.setTotalLoan(loansDto.getTotalLoan());
		loans.setAmountPaid(loansDto.getAmountPaid());
		loans.setOutStandingAmount(loansDto.getOutstandingAmount());
		return loans;
	}

}
