package xyz.the_dodo.bot.Functions.bank;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

public class Balance extends IFunction {
    public Balance(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        User author;
        BankAccount ba;
        EmbedBuilder embMsg;

        author = p_messageParams.getUser();

        if (BankUtils.bankAccoutnExists(author)) {
            ba = BankUtils.m_bankService.findByUserDiscordId(author.getId());

            embMsg = new EmbedBuilder();
            embMsg.setTitle(author.getName() + "'s bank balance");
            embMsg.setThumbnail(author.getAvatarUrl());
            embMsg.setColor(p_messageParams.getMessage().getMember().getColor());
            embMsg.addField("Account Number", author.getId(), false);
            embMsg.addField("Balance", String.valueOf(ba.getBalance()) + " ₪", true);
            embMsg.addField("Last Pay", ba.getLastPay().toString(), true);

            p_messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
        }
        else p_messageParams.getTextChannel().sendMessage("You don't have an account!").queue();
    }
}
