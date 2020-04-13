package xyz.the_dodo.bot.functions.bank;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

import java.time.format.DateTimeFormatter;

@BotService(command = "bank.balance", description = "Gets balance of your bank account", usage = "bank.balance", category = CommandCategoryEnum.BANK)
public class Balance extends IFunction {
    public Balance(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        User author;
        BankAccount ba;
        EmbedBuilder embMsg;
        DateTimeFormatter formatter;

        author = messageParams.getUser();

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (BankUtils.bankAccountExists(author)) {
            ba = BankUtils.bankService.findByUserDiscordId(author.getId());

            embMsg = new EmbedBuilder();
            embMsg.setTitle(author.getName() + "'s bank balance");
            embMsg.setThumbnail(author.getAvatarUrl());
            embMsg.setColor(messageParams.getMessage().getMember().getColor());
            embMsg.addField("Account Number", author.getId(), false);
            embMsg.addField("Balance", String.valueOf(ba.getBalance()) + " ₪", true);
            embMsg.addField("Last Pay", formatter.format(ba.getLastPay()), true);

            messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
        } else
            messageParams.getTextChannel().sendMessage("You don't have an account!").queue();
    }
}
