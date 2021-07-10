package xyz.the_dodo.bot.tests.bot.JDAMocks;

import xyz.the_dodo.bot.types.message.MessageParams;

public class MessageParamsMock {
    public static MessageParams get(String content, Long userId, Long guildId) {
        return new MessageParams(new JDAMessageMock(new JDAUserMock(userId), new JDAGuildMock(guildId), content));
    }
}
