package xyz.the_dodo.bot.functions.bank;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.SlotMashina;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

@BotService(command = "slot", description = "Play the slots!!", usage = "slot <AMOUNT>", category = CommandCategoryEnum.BANK)
public class Slot extends IFunction {
    public Slot(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        BankAccount ba;
        Double amount;

        if (messageParams.getParameters().length == 1) {
            try {
                amount = Double.parseDouble(messageParams.getParameters()[0]);
            } catch (Exception e) {
                amount = null;
            }

            if (amount != null && amount > 0) {
                if (BankUtils.bankAccountExists(messageParams.getUser())) {
                    ba = BankUtils.m_bankService.findByUserDiscordId(messageParams.getUser().getId());

                    if (ba.getBalance() >= amount)
                        SlotMashina.slotMashinas(amount, messageParams.getTextChannel(), ba);
                    else
                        messageParams.getTextChannel().sendMessage("You are too poor!").queue();
                } else
                    messageParams.getTextChannel().sendMessage("You have to register a bank account!").queue();
            } else
                messageParams.getTextChannel().sendMessage("Can't slot that!").queue();
        } else
            messageParams.getTextChannel().sendMessage(getEmbededHelp().build()).queue();
    }
}
