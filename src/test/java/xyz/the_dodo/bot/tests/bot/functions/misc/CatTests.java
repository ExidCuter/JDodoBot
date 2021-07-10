package xyz.the_dodo.bot.tests.bot.functions.misc;

import org.junit.Test;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.misc.Cat;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.bot.tests.bot.JDAMocks.MessageParamsMock;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

import static org.assertj.core.api.Assertions.assertThat;

public class CatTests extends AbstractTest {
    private final IFunction function = new Cat("f", "", "", false, CommandCategoryEnum.FUN);

    @Test
    public void test_cat() {
        assertThat(this.function.prepare(MessageParamsMock.get("f", 666L, 666L)).getResponseQueue()).isNotNull()
                .extracting("responseType")
                .contains(BotResponseTypeEnum.FILE_PNG);
    }
}
