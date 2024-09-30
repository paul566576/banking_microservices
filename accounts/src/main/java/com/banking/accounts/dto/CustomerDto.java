package com.banking.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomerDto
{
	@NotEmpty(message = "Name can't be null or empty")
	@Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30 ")
	private String name;
	@NotEmpty(message = "Email can't be null or empty")
	@Email(message = "Email address should be valid value")
	private String email;
	@NotEmpty(message = "MobileNumber can't be null or empty")
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
	private String mobileNumber;
	private AccountsDto accountsDto;
}
