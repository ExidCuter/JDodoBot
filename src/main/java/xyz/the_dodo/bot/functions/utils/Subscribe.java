package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.bot.utils.SubsUtils;

@BotService(command = "sub", description = "Subscribes to a command and triggers it at an interval.", usage = "sub <interval> <command> ([sub 3 shoot @person)] - triggers \"shoot\" once every 30 minutes)", category = CommandCategoryEnum.UTILS)
public class Subscribe extends IFunction {
    public Subscribe(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        int timerMulti;
        String command;

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            try {
                timerMulti = Integer.parseUnsignedInt(messageParams.getParameters()[0]);

                if (timerMulti < 1)
                    throw new Exception("Invalid number");

                command = StringUtils.glueStringsBackTogether(messageParams.getParameters(), " ", 1);

                SubsUtils.addSubscriptionToGuild(command, timerMulti, messageParams.getGuild(), messageParams.getTextChannel());

                messageParams.getTextChannel().sendMessage("Command `" + command + "` will be triggered every " + timerMulti * 10 + " minutes.").queue();
            } catch (Exception e) {
                messageParams.getTextChannel().sendMessage("`" + messageParams.getParameters()[0] + "` is not a valid number").queue();
            }

        } else {
            messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
        }

        return this;
    }
}
