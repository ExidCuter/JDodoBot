package xyz.the_dodo.bot.tests.bot.JDAMocks;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class JDAUserMock implements User {
    private long id;

    public JDAUserMock(long id) {
        this.id = id;
    }

    @Nonnull
    @Override
    public String getName() {
        return "Test user";
    }

    @Nonnull
    @Override
    public String getDiscriminator() {
        return "Test Desscriminator";
    }

    @Nullable
    @Override
    public String getAvatarId() {
        return "10321321132";
    }

    @Nonnull
    @Override
    public String getDefaultAvatarId() {
        return "10321321132";
    }

    @Nonnull
    @Override
    public String getAsTag() {
        return "TAG";
    }

    @Override
    public boolean hasPrivateChannel() {
        return false;
    }

    @Nonnull
    @Override
    public RestAction<PrivateChannel> openPrivateChannel() {
        return null;
    }

    @Nonnull
    @Override
    public List<Guild> getMutualGuilds() {
        return null;
    }

    @Override
    public boolean isBot() {
        return false;
    }

    @Nonnull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Nonnull
    @Override
    public String getAsMention() {
        return "@TEST USER";
    }

    @Override
    public long getIdLong() {
        return this.id;
    }
}
