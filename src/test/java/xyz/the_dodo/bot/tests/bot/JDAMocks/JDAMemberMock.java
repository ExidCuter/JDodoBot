package xyz.the_dodo.bot.tests.bot.JDAMocks;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class JDAMemberMock implements Member {
    @Nonnull
    @Override
    public User getUser() {
        return new JDAUserMock(1L);
    }

    @Nonnull
    @Override
    public Guild getGuild() {
        return null;
    }

    @Nonnull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Nonnull
    @Override
    public OffsetDateTime getTimeJoined() {
        return null;
    }

    @Nullable
    @Override
    public OffsetDateTime getTimeBoosted() {
        return null;
    }

    @Nullable
    @Override
    public GuildVoiceState getVoiceState() {
        return null;
    }

    @Nonnull
    @Override
    public List<Activity> getActivities() {
        return null;
    }

    @Nonnull
    @Override
    public OnlineStatus getOnlineStatus() {
        return null;
    }

    @Nonnull
    @Override
    public OnlineStatus getOnlineStatus(@Nonnull ClientType type) {
        return null;
    }

    @Nonnull
    @Override
    public EnumSet<ClientType> getActiveClients() {
        return null;
    }

    @Nullable
    @Override
    public String getNickname() {
        return null;
    }

    @Nonnull
    @Override
    public String getEffectiveName() {
        return null;
    }

    @Nonnull
    @Override
    public List<Role> getRoles() {
        return null;
    }

    @Nullable
    @Override
    public Color getColor() {
        return new Color(0xFF0000);
    }

    @Override
    public int getColorRaw() {
        return 0;
    }

    @Override
    public boolean canInteract(@Nonnull Member member) {
        return false;
    }

    @Override
    public boolean canInteract(@Nonnull Role role) {
        return false;
    }

    @Override
    public boolean canInteract(@Nonnull Emote emote) {
        return false;
    }

    @Override
    public boolean isOwner() {
        return false;
    }

    @Nullable
    @Override
    public TextChannel getDefaultChannel() {
        return null;
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Nonnull
    @Override
    public String getAsMention() {
        return null;
    }

    @Nonnull
    @Override
    public EnumSet<Permission> getPermissions() {
        return null;
    }

    @Nonnull
    @Override
    public EnumSet<Permission> getPermissions(@Nonnull GuildChannel channel) {
        return null;
    }

    @Nonnull
    @Override
    public EnumSet<Permission> getPermissionsExplicit() {
        return null;
    }

    @Nonnull
    @Override
    public EnumSet<Permission> getPermissionsExplicit(@Nonnull GuildChannel channel) {
        return null;
    }

    @Override
    public boolean hasPermission(@Nonnull Permission... permissions) {
        return false;
    }

    @Override
    public boolean hasPermission(@Nonnull Collection<Permission> permissions) {
        return false;
    }

    @Override
    public boolean hasPermission(@Nonnull GuildChannel channel, @Nonnull Permission... permissions) {
        return false;
    }

    @Override
    public boolean hasPermission(@Nonnull GuildChannel channel, @Nonnull Collection<Permission> permissions) {
        return false;
    }

    @Override
    public long getIdLong() {
        return 0;
    }
}
