package xyz.the_dodo.bot.Functions.misc;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;

public class Magic8Ball extends IFunction {
    private String[] answers = {
            "Yes definitely",
            "You may rely on it",
            "As I see it, yes",
            "Most likely",
            "Outlook good",
            "Yes",
            "Signs point to yes",
            "Reply hazy try again",
            "It is certain",
            "It is decidedly so",
            "Without a doubt",
            "Ask again later",
            "Better not tell you now",
            "Cannot predict now",
            "Concentrate and ask again",
            "Don't count on it",
            "My reply is no",
            "My sources say no",
            "Outlook not so good",
            "Very doubtful"
    };

    public Magic8Ball(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        try {
            p_messageParams.getTextChannel().sendMessage(answers[RandomGen.rndNm(answers.length)]).complete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}