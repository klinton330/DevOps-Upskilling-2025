package com.ms.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "account_number")
	@Id
	private Long accountNumber;
	@Column(name = "account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;

}
