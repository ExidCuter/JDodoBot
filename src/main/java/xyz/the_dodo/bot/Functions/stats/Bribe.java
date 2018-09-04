package xyz.the_dodo.bot.Functions.stats;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.BankAccount;
import xyz.the_dodo.database.types.Stats;

public class Bribe extends IFunction {
    public Bribe(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        int calculation, max;
        double amount;
        Stats userStats;
        BankAccount bankAccount;

        userStats = StatsUtils.statsExists(p_messageParams.getUser());

        if (userStats != null) {
            if (p_messageParams.getParameters().length > 0) {
                try {
                    amount = Double.parseDouble(p_messageParams.getParameters()[0]);
                    if (amount < 1)
                        throw new Exception("Zero is not valid here!");
                } catch (Exception e) {
                    p_messageParams.getTextChannel().sendMessage("What am I supposed to do with this? NO GO!").queue();
                    return;
                }
                if (BankUtils.bankAccountExists(p_messageParams.getUser())) {
                    bankAccount = BankUtils.m_bankService.findByUserDiscordId(p_messageParams.getUser().getId());

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

                            p_messageParams.getTextChannel().sendMessage("*" + p_messageParams.getUser().getAsMention() + "'s stats magically increased by " + max + "*").queue();

                        } else {
                            bankAccount.setBalance(bankAccount.getBalance() - amount);

                            BankUtils.m_bankService.save(bankAccount);

                            p_messageParams.getTextChannel().sendMessage("*Takes the money* \nI ain't that cheap!!!").queue();
                        }
                    } else
                        p_messageParams.getTextChannel().sendMessage("*Smirks*\nYou are too poor!").queue();
                } else
                    p_messageParams.getTextChannel().sendMessage("You don't even have a bank account. I won't take cookies!").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("You need to give me something! I won't do stuff for free you know!").queue();
        }
    }
}
