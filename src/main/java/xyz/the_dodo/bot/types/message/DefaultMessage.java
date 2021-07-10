package xyz.the_dodo.bot.types.message;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.apache.commons.collections4.Bag;
import org.jetbrains.annotations.NotNull;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.config.BotConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Formatter;
import java.util.List;

public class DefaultMessage implements Message {
    public static BotConfig config = BeanUtils.getBean(BotConfig.class);

    List<User> mentionedUsers;

    @Override
    public Bag<User> getMentionedUsersBag() {
        return null;
    }

    @Override
    public Bag<TextChannel> getMentionedChannelsBag() {
        return null;
    }

    @Override
    public Bag<Role> getMentionedRolesBag() {
        return null;
    }

    @Override
    public OffsetDateTime getTimeEdited() {
        return null;
    }

    @Override
    public Bag<Emote> getEmotesBag() {
        return null;
    }

    @Override
    public RestAction<Void> clearReactions(@Nonnull String unicode) {
        return null;
    }

    @Override
    public RestAction<Void> clearReactions(@Nonnull Emote emote) {
        return null;
    }

    @Override
    public RestAction<Void> removeReaction(@Nonnull Emote emote) {
        return null;
    }

    @Override
    public RestAction<Void> removeReaction(@Nonnull Emote emote, @Nonnull User user) {
        return null;
    }

    @Override
    public RestAction<Void> removeReaction(@Nonnull String unicode) {
        return null;
    }

    @Override
    public RestAction<Void> removeReaction(@Nonnull String unicode, @Nonnull User user) {
        return null;
    }

    @Override
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull Emote emote) {
        return null;
    }

    @Override
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull String unicode) {
        return null;
    }

    @Override
    public MessageReaction.ReactionEmote getReactionByUnicode(@Nonnull String unicode) {
        return null;
    }

    @Override
    public MessageReaction.ReactionEmote getReactionById(@Nonnull String id) {
        return null;
    }

    @Override
    public MessageReaction.ReactionEmote getReactionById(long id) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> suppressEmbeds(boolean suppressed) {
        return null;
    }

    @Override
    public boolean isSuppressedEmbeds() {
        return false;
    }

    @Override
    public EnumSet<MessageFlag> getFlags() {
        return null;
    }

    @Override
    public List<User> getMentionedUsers() {
        return mentionedUsers;
    }

    @Override
    public List<TextChannel> getMentionedChannels() {
        return new ArrayList<>();
    }

    @Override
    public List<Role> getMentionedRoles() {
        return new ArrayList<>();
    }

    @Override
    public List<Member> getMentionedMembers(Guild guild) {
        return new ArrayList<>();
    }

    @Override
    public List<Member> getMentionedMembers() {
        return new ArrayList<>();
    }

    @Override
    public List<IMentionable> getMentions(MentionType... types) {
        return new ArrayList<>();
    }

    @Override
    public boolean isMentioned(IMentionable mentionable, MentionType... types) {
        return false;
    }

    @Override
    public boolean mentionsEveryone() {
        return false;
    }

    @Override
    public boolean isEdited() {
        return false;
    }

    @Override
    public User getAuthor() {
        return config.getBotAsMember().getUser();
    }

    @Override
    public Member getMember() {
        return config.getBotAsMember();
    }

    @Override
    public String getContentDisplay() {
        return null;
    }

    @Override
    public String getContentRaw() {
        return null;
    }

    @Override
    public String getContentStripped() {
        return null;
    }

    @Override
    public List<String> getInvites() {
        return null;
    }

    @Override
    public String getNonce() {
        return null;
    }

    @Override
    public boolean isFromType(ChannelType type) {
        return false;
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.TEXT;
    }

    @Override
    public boolean isWebhookMessage() {
        return false;
    }

    @Override
    public MessageChannel getChannel() {
        return null;
    }

    @Override
    public PrivateChannel getPrivateChannel() {
        return null;
    }

    @Override
    public TextChannel getTextChannel() {
        return null;
    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public Guild getGuild() {
        return null;
    }

    @Override
    public List<Attachment> getAttachments() {
        return null;
    }

    @Override
    public List<MessageEmbed> getEmbeds() {
        return null;
    }

    @Override
    public List<Emote> getEmotes() {
        return null;
    }

    @Override
    public List<MessageReaction> getReactions() {
        return null;
    }

    @Override
    public boolean isTTS() {
        return false;
    }

    @Override
    public MessageAction editMessage(CharSequence newContent) {
        return null;
    }

    @Override
    public MessageAction editMessage(MessageEmbed newContent) {
        return null;
    }

    @Override
    public MessageAction editMessageFormat(String format, Object... args) {
        return null;
    }

    @Override
    public MessageAction editMessage(Message newContent) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> delete() {
        return null;
    }

    @Override
    public JDA getJDA() {
        return null;
    }

    @Override
    public boolean isPinned() {
        return false;
    }

    @Override
    public RestAction<Void> pin() {
        return null;
    }

    @Override
    public RestAction<Void> unpin() {
        return null;
    }

    @Override
    public RestAction<Void> addReaction(Emote emote) {
        return null;
    }

    @Override
    public RestAction<Void> addReaction(String unicode) {
        return null;
    }

    @Override
    public RestAction<Void> clearReactions() {
        return null;
    }

    @Override
    public MessageType getType() {
        return null;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {

    }

    @Nullable
    @Override
    public MessageActivity getActivity() {
        return null;
    }

    @Override
    public String getJumpUrl() {
        return null;
    }

    @Override
    public long getIdLong() {
        return 0;
    }

    public DefaultMessage(@NotNull String[] parameters, Guild guild) {
        User user;
        ArrayList<User> menionedUsers;

        menionedUsers = new ArrayList<>();

        for (String parameter : parameters) {
            parameter = parameter.replace("<", "")
                    .replace("@", "")
                    .replace(">", "");

            try {
                user = guild.getMemberById(parameter).getUser();
            } catch (Exception e) {
                user = null;
            }

            if (user != null)
                menionedUsers.add(user);
        }

        this.mentionedUsers = menionedUsers;
    }
}
