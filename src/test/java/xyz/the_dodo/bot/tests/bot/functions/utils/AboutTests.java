package xyz.the_dodo.bot.tests.bot.functions.utils;

import org.junit.Test;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.utils.About;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.bot.tests.bot.JDAMocks.MessageParamsMock;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

import static org.assertj.core.api.Assertions.assertThat;

public class AboutTests extends AbstractTest {
    private final IFunction function = new About("f", "", "", false, CommandCategoryEnum.FUN);

    @Test
    public void test_about() {
        assertThat(this.function.prepare(MessageParamsMock.get("f", 666L, 666L)).getResponseQueue()).isNotNull()
                .extracting("responseType")
                .contains(BotResponseTypeEnum.EMBED);
    }
}
