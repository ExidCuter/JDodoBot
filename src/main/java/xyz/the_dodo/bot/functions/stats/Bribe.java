package xyz.the_dodo.bot.functions.stats;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.BankAccount;
import xyz.the_dodo.database.types.Stats;

@BotService(command = "bribe", description = "Bribes the bot. Increases your stats score!", usage = "bribe <AMOUNT OF MONEY>", category = CommandCategoryEnum.STATS)
public class Bribe extends IFunction {
    public Bribe(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        int calculation, max;
        double amount;
        Stats userStats;
        BankAccount bankAccount;

        userStats = StatsUtils.statsExists(messageParams.getUser());

        if (userStats != null) {
            if (messageParams.getParameters().length > 0) {
                try {
                    amount = Double.parseDouble(messageParams.getParameters()[0]);
                    if (amount < 1)
                        throw new Exception("Zero is not valid here!");
                } catch (Exception e) {
                    messageParams.getTextChannel().sendMessage("What am I supposed to do with this? NO GO!").queue();
                    return;
                }

                if (BankUtils.bankAccountExists(messageParams.getUser())) {
                    bankAccount = BankUtils.m_bankService.findByUserDiscordId(messageParams.getUser().getId());

                    if (bankAccount.getBalance() >= amount) {
                        if ((RandomGen.rndNm(3) == 1 && amount > 1000) || amount > 20000) {
                            calculation = (int) Math.log10(amount) * (int) amount;

                            max = RandomGen.rndNm(calculation / 2, calculation);

                            if (max > 100)
                                max = max / 2;

                            bankAccount.setBalance(bankAccount.getBalance() - amount);

                            userStats.setNumOfMessages(userStats.getNumOfMessages() + max);

                            BankUtils.m_bankService.save(bankAccount);
                            StatsUtils.m_statsService.save(userStats);

                            messageParams.getTextChannel().sendMessage("*" + messageParams.getUser().getAsMention() + "'s stats magically increased by " + max + "*").queue();

                        } else {
                            bankAccount.setBalance(bankAccount.getBalance() - amount);

                            BankUtils.m_bankService.save(bankAccount);

                            messageParams.getTextChannel().sendMessage("*Takes the money* \nI ain't that cheap!!!").queue();
                        }
                    } else
                        messageParams.getTextChannel().sendMessage("*Smirks*\nYou are too poor!").queue();
                } else
                    messageParams.getTextChannel().sendMessage("You don't even have a bank account. I won't take cookies!").queue();
            } else
                messageParams.getTextChannel().sendMessage("You need to give me something! I won't do stuff for free you know!").queue();
        }
    }
}
