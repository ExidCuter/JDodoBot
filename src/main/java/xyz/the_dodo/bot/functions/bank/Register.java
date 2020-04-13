package xyz.the_dodo.bot.functions.bank;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;

@BotService(command = "bank.register", description = "Creates a bank account", usage = "bank.register", category = CommandCategoryEnum.BANK)
public class Register extends IFunction {
    public Register(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        if (!BankUtils.bankAccountExists(messageParams.getUser())) {
            BankUtils.createBankAccount(messageParams.getUser());

            messageParams.getTextChannel().sendMessage("BankAccount created! Your account number is: " + messageParams.getUser().getId() + " with starting balance of 100 ₪.").queue();
        } else
            messageParams.getTextChannel().sendMessage("You already have a bank account!").queue();
    }
}
