package com.banking.cards.controller;

import com.banking.cards.constants.CardsConstants;
import com.banking.cards.dto.CardDto;
import com.banking.cards.dto.ResponseDto;
import com.banking.cards.service.CardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("cards/api")
@RequiredArgsConstructor
@Validated
public class CardsController
{
	private final CardsService cardsService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createCard(final @Valid @RequestBody CardDto cardDto)
	{
		cardsService.createCard(cardDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}

	@GetMapping("/fetchCardByCardNumber")
	public ResponseEntity<CardDto> fetchCardByCardNumber(
			final @Pattern(regexp = "(^$|[0-9]{16})", message = "Card number must be 16 digits") @RequestParam String cardNumber)
	{
		final CardDto card = cardsService.fetchCardsByCardNumber(cardNumber);
		return ResponseEntity.status(HttpStatus.OK).body(card);
	}

	@GetMapping("/fetchCardByMobileNumber")
	public ResponseEntity<List<CardDto>> fetchCardByMobileNumber(
			final @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits") @RequestParam String mobileNumber)
	{
		final List<CardDto> cards = cardsService.fetchCardsByMobileNumber(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cards);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCard(final @Valid @RequestBody CardDto cardDto)
	{
		if (cardsService.updateCard(cardDto))
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.MESSAGE_200, CardsConstants.STATUS_200));
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteCard(
			final @Pattern(regexp = "(^$|[0-9]{16})", message = "Card number must be 16 digits") @RequestParam String cardNumber)
	{
		if (cardsService.deleteCard(cardNumber))
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.MESSAGE_200, CardsConstants.STATUS_200));
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
	}

}
