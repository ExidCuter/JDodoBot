package xyz.the_dodo.bot.functions.bank;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.bot.types.SlotMashina;
import xyz.the_dodo.database.types.BankAccount;

public class Slot extends IFunction {
    public Slot(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.BANK;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        BankAccount ba;
        Double amount;

        if (p_messageParams.getParameters().length == 1) {
            try {
                amount = Double.parseDouble(p_messageParams.getParameters()[0]);
            } catch (Exception e) {
                amount = null;
            }

            if (amount != null && amount > 0) {
                if (BankUtils.bankAccountExists(p_messageParams.getUser())) {
                    ba = BankUtils.m_bankService.findByUserDiscordId(p_messageParams.getUser().getId());

                    if (ba.getBalance() >= amount)
                        SlotMashina.slotMashinas(amount, p_messageParams.getTextChannel(), ba);
                    else
                        p_messageParams.getTextChannel().sendMessage("You are too poor!").queue();
                } else
                    p_messageParams.getTextChannel().sendMessage("You have to register a bank account!").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("Can't slot that!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage(getEmbededHelp().build()).queue();
    }
}
