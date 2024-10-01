package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
		name = "Customer",
		description = "Schema to hold Customer and Account information"
)
public class CustomerDto
{
	@Schema(description = "Name of the customer", example = "John Smith")
	@NotEmpty(message = "Name can't be null or empty")
	@Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30 ")
	private String name;
	@Schema(description = "Email of the customer", example = "test@test.com")
	@NotEmpty(message = "Email can't be null or empty")
	@Email(message = "Email address should be valid value")
	private String email;
	@NotEmpty(message = "MobileNumber can't be null or empty")
	@Schema(description = "Mobile number of the customer", example = "48571932698")
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
	private String mobileNumber;
	@Schema(description = "Accounts information of the customer")
	private AccountsDto accountsDto;
}
