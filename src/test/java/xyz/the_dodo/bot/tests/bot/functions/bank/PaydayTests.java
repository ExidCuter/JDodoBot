package xyz.the_dodo.bot.tests.bot.functions.bank;

import org.junit.Test;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.bank.PayDay;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.bot.tests.bot.JDAMocks.MessageParamsMock;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class PaydayTests extends AbstractTest {
    private final IFunction paydayFunction = new PayDay("f", "", "", false, CommandCategoryEnum.BANK);


    @Test
    public void test_getPayday() {
        IFunction payday = paydayFunction.prepare(MessageParamsMock.get("f", 666L, 666L));

        assertThat(payday.getResponseQueue()).isNotNull()
                .extracting("responseType", "response")
                .contains(
                        tuple(BotResponseTypeEnum.TEXT, "You received 100 ₪.")
                );
    }

    @Test
    public void test_alreadyGotPayday() {
        IFunction payday = paydayFunction.prepare(MessageParamsMock.get("f", 69L, 666L));
        payday = payday.prepare(MessageParamsMock.get("payday", 69L, 666L));

        assertThat(payday.getResponseQueue()).isNotNull()
                .extracting("responseType", "response")
                .contains(
                        tuple(BotResponseTypeEnum.TEXT, "You received 100 ₪."),
                        tuple(BotResponseTypeEnum.TEXT, "You already got your pay!")
                );
    }

    @Test
    public void test_noAccount() {
        IFunction payday = paydayFunction.prepare(MessageParamsMock.get("f", 667L, 666L));

        assertThat(payday.getResponseQueue()).isNotNull()
                .extracting("responseType", "response")
                .contains(
                        tuple(BotResponseTypeEnum.TEXT, "You need to register an account!")
                );
    }
}
