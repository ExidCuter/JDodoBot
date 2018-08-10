package xyz.the_dodo.database.interfaces.services;

import net.dv8tion.jda.core.entities.Guild;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.types.BankAccount;

import java.util.List;


public interface IBankService  extends ICRUD<BankAccount>{
	BankAccount findByUserDiscordId(String discordId);
}
