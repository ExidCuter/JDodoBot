package xyz.the_dodo.bot.Functions.bank;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

public class Transfer extends IFunction {
    public Transfer(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        User user, user2;
        double amount;
        BankAccount ba, ba2;

        user = p_messageParams.getUser();

        if (BankUtils.bankAccoutnExists(p_messageParams.getUser())) {
            if (p_messageParams.getParameters().length == 2) {
                if (p_messageParams.getMessage().getMentionedUsers().size() > 0)
                    user2 = p_messageParams.getMessage().getMentionedUsers().get(0);
                else
                    try {
                        user2 = p_messageParams.getGuild().getMemberById(p_messageParams.getParameters()[0]).getUser();
                    } catch (Exception e) {
                        user2 = null;
                    }
                if (user2 != null && BankUtils.bankAccoutnExists(user2)) {
                    try {
                        amount = Double.parseDouble(p_messageParams.getParameters()[1]);
                    } catch (Exception e) {
                        p_messageParams.getTextChannel().sendMessage("NaN! `" + p_messageParams.getParameters()[1] + "` is not a number!").queue();
                        return;
                    }

                    ba = BankUtils.m_bankService.findByUserDiscordId(user.getId());
                    ba2 = BankUtils.m_bankService.findByUserDiscordId(user2.getId());

                    if (ba.getBalance() >= amount && amount > 0) {
                        ba.setBalance(ba.getBalance() - amount);
                        ba2.setBalance(ba2.getBalance() + amount);

                        BankUtils.m_bankService.save(ba);
                        BankUtils.m_bankService.save(ba2);

                        p_messageParams.getTextChannel().sendMessage("Transferred `" + amount + "` ₪ from `" + user.getId() + "` to `" + user2.getId() + "`.").queue();
                    } else
                        p_messageParams.getTextChannel().sendMessage("You don't have the money").queue();
                } else
                    p_messageParams.getTextChannel().sendMessage("Account `" + p_messageParams.getParameters()[0] + "` doesn't exist!").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("Unknown parameters!").queue();

        } else
            p_messageParams.getTextChannel().sendMessage("You need to register an account!").queue();
    }
}
