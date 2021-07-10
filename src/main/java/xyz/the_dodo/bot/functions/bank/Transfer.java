package xyz.the_dodo.bot.functions.bank;

import net.dv8tion.jda.api.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

@BotService(command = "bank.transfer", description = "Transfers money!!", usage = "bank.transfer <USER MENTION/ACCOUNT NUMBER> <AMOUNT>", category = CommandCategoryEnum.BANK)
public class Transfer extends IFunction {
    public Transfer(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        User user, user2;
        double amount;
        BankAccount ba, ba2;

        user = messageParams.getUser();

        if (BankUtils.bankAccountExists(messageParams.getUser())) {
            if (messageParams.getParameters().length == 2) {
                if (messageParams.getMessage().getMentionedUsers().size() > 0) {
                    user2 = messageParams.getMessage().getMentionedUsers().get(0);
                } else {
                    try {
                        user2 = messageParams.getGuild().getMemberById(messageParams.getParameters()[0]).getUser();
                    } catch (Exception e) {
                        user2 = null;
                    }
                }

                if (user2 != null && BankUtils.bankAccountExists(user2)) {
                    try {
                        amount = Double.parseDouble(messageParams.getParameters()[1]);
                    } catch (Exception e) {
                        messageParams.getTextChannel().sendMessage("NaN! `" + messageParams.getParameters()[1] + "` is not a number!").queue();
                        return null;
                    }

                    ba = BankUtils.bankService.findByUserDiscordId(user.getId());
                    ba2 = BankUtils.bankService.findByUserDiscordId(user2.getId());

                    if (ba.getBalance() >= amount && amount > 0) {
                        ba.setBalance(ba.getBalance() - amount);
                        ba2.setBalance(ba2.getBalance() + amount);

                        BankUtils.bankService.save(ba);
                        BankUtils.bankService.save(ba2);

                        messageParams.getTextChannel().sendMessage("Transferred `" + amount + "` ₪ from `" + user.getId() + "` to `" + user2.getId() + "`.").queue();
                    } else {
                        messageParams.getTextChannel().sendMessage("You don't have the money").queue();
                    }
                } else {
                    messageParams.getTextChannel().sendMessage("Account `" + messageParams.getParameters()[0] + "` doesn't exist!").queue();
                }
            } else {
                messageParams.getTextChannel().sendMessage("Unknown parameters!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("You need to register an account!").queue();
        }

        return this;
    }
}
