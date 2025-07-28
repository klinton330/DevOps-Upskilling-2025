package com.ms.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "customer_name")
	private String name;
	@Column(name = "customer_email")
	private String email;
	@Column(name = "customer_mobileNumber")
	private String mobileNumber;

}
