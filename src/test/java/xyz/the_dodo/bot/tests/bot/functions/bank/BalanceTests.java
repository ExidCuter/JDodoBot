package xyz.the_dodo.bot.tests.bot.functions.bank;

import org.junit.Test;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.bank.Balance;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.bot.tests.bot.JDAMocks.MessageParamsMock;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

public class BalanceTests extends AbstractTest {
    private final IFunction bankBalanceFunction = new Balance("f", "", "", false, CommandCategoryEnum.BANK);

    @Test
    public void test_balanceNoAccount() {
        IFunction balance = bankBalanceFunction.prepare(MessageParamsMock.get("f", 1L, 1L));

        balance.getResponseQueue().forEach(botResponse -> {
            assert botResponse.getResponse().toString().equals("You don't have an account!");
        });
    }

    @Test
    public void test_balanceAccount() {
        IFunction balance = bankBalanceFunction.prepare(MessageParamsMock.get("f", 666L, 666L));

        balance.getResponseQueue().forEach(botResponse -> {
            assert botResponse.getResponseType() == BotResponseTypeEnum.EMBED;
        });
    }
}
