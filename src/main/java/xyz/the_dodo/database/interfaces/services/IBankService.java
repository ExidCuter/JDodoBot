package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.BankAccount;

public interface IBankService  extends ICRUD<BankAccount>{
	BankAccount findByUserDiscordId(String discordId);
}
