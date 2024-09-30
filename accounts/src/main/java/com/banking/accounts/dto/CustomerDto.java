package com.banking.accounts.dto;

import lombok.Data;


@Data
public class CustomerDto
{
	private Long accountNumber;
	private String name;
	private String email;
	private String mobileNumber;
}
