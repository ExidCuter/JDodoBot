package xyz.the_dodo.bot.Functions.bank;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;

public class Register extends IFunction
{
    public Register(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.BANK;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (!BankUtils.bankAccountExists(p_messageParams.getUser())) {
            BankUtils.createBankAccount(p_messageParams.getUser());
            p_messageParams.getTextChannel().sendMessage("BankAccount created! Your account number is: " + p_messageParams.getUser().getId() + " with starting balance of 100 ₪.").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("You already have a bank account!").queue();
    }
}
