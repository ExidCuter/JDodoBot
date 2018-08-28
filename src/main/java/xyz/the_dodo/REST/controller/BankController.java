package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.types.BankAccount;

@CrossOrigin
@RestController
@RequestMapping("/api/bank")
public class BankController
{
	@Autowired
	private IBankService m_BankService;


	@RequestMapping(value = "/account/{discordId}", method = RequestMethod.GET)
	public ResponseEntity getUserById(@PathVariable String discordId) {
		BankAccount bankAccount;

		bankAccount = m_BankService.findByUserDiscordId(discordId);

		return ResponseEntity.ok(bankAccount);
	}
}
