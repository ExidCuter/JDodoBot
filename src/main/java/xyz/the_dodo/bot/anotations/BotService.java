package xyz.the_dodo.bot.anotations;

import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BotService {
    String command() default "";

    String description() default "";

    String usage() default "";

    CommandCategoryEnum category() default CommandCategoryEnum.FUN;

    boolean isService() default false;
}

