package com.ms.loan.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="loans")
@Setter
@Getter
public class LoanContactInfoDTO {
	private String message;
	private Map<String,String>contactDetails;
	private List<String>onCallSupport;

}
