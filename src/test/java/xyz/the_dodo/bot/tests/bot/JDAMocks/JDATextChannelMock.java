package xyz.the_dodo.bot.tests.bot.JDAMocks;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class JDATextChannelMock implements TextChannel {

    private JDAGuildMock guild;

    public JDATextChannelMock(JDAGuildMock guild) {
        this.guild = guild;
    }


    @Nullable
    @Override
    public String getTopic() {
        return null;
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public int getSlowmode() {
        return 0;
    }

    @Nonnull
    @Override
    public ChannelAction<TextChannel> createCopy(@Nonnull Guild guild) {
        return null;
    }

    @Nonnull
    @Override
    public ChannelAction<TextChannel> createCopy() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<Webhook>> retrieveWebhooks() {
        return null;
    }

    @Nonnull
    @Override
    public WebhookAction createWebhook(@Nonnull String name) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> deleteMessages(@Nonnull Collection<Message> messages) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> deleteMessagesByIds(@Nonnull Collection<String> messageIds) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> deleteWebhookById(@Nonnull String id) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> clearReactionsById(@Nonnull String messageId) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> clearReactionsById(@Nonnull String messageId, @Nonnull String unicode) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> clearReactionsById(@Nonnull String messageId, @Nonnull Emote emote) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> removeReactionById(@Nonnull String messageId, @Nonnull String unicode, @Nonnull User user) {
        return null;
    }

    @Override
    public boolean canTalk() {
        return false;
    }

    @Override
    public boolean canTalk(@Nonnull Member member) {
        return false;
    }

    @Nonnull
    @Override
    public ChannelType getType() {
        return ChannelType.TEXT;
    }

    @Nonnull
    @Override
    public String getName() {
        return "MOCK CHANNEL";
    }

    @Nonnull
    @Override
    public Guild getGuild() {
        return this.guild;
    }

    @Nullable
    @Override
    public Category getParent() {
        return null;
    }

    @Nonnull
    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public int getPositionRaw() {
        return 0;
    }

    @Nonnull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Nullable
    @Override
    public PermissionOverride getPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        return null;
    }

    @Nonnull
    @Override
    public List<PermissionOverride> getPermissionOverrides() {
        return null;
    }

    @Nonnull
    @Override
    public List<PermissionOverride> getMemberPermissionOverrides() {
        return null;
    }

    @Nonnull
    @Override
    public List<PermissionOverride> getRolePermissionOverrides() {
        return null;
    }

    @Nonnull
    @Override
    public ChannelManager getManager() {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> delete() {
        return null;
    }

    @Nonnull
    @Override
    public PermissionOverrideAction createPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        return null;
    }

    @Nonnull
    @Override
    public PermissionOverrideAction putPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        return null;
    }

    @Nonnull
    @Override
    public InviteAction createInvite() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<Invite>> retrieveInvites() {
        return null;
    }

    @Override
    public int compareTo(@NotNull GuildChannel guildChannel) {
        return 0;
    }

    @Nonnull
    @Override
    public String getAsMention() {
        return null;
    }

    @Override
    public long getLatestMessageIdLong() {
        return 32112314421L;
    }

    @Override
    public boolean hasLatestMessage() {
        return false;
    }

    @Override
    public long getIdLong() {
        return 32112314421L;
    }


}
