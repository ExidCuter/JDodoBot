package xyz.the_dodo.bot.tests.bot.JDAMocks;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.apache.commons.collections4.Bag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Formatter;
import java.util.List;

public class JDAMessageMock implements Message {
    private JDAUserMock user;
    private JDAGuildMock guild;
    private String content;

    public JDAMessageMock(JDAUserMock user, JDAGuildMock guild, String content) {
        this.user = user;
        this.guild = guild;
        this.content = content;
    }

    @Nonnull
    @Override
    public List<User> getMentionedUsers() {
        ArrayList<User> list = new ArrayList<>();
        list.add(this.user);
        return list;
    }

    @Nonnull
    @Override
    public Bag<User> getMentionedUsersBag() {
        return null;
    }

    @Nonnull
    @Override
    public List<TextChannel> getMentionedChannels() {
        return null;
    }

    @Nonnull
    @Override
    public Bag<TextChannel> getMentionedChannelsBag() {
        return null;
    }

    @Nonnull
    @Override
    public List<Role> getMentionedRoles() {
        return null;
    }

    @Nonnull
    @Override
    public Bag<Role> getMentionedRolesBag() {
        return null;
    }

    @Nonnull
    @Override
    public List<Member> getMentionedMembers(@Nonnull Guild guild) {
        return null;
    }

    @Nonnull
    @Override
    public List<Member> getMentionedMembers() {
        return null;
    }

    @Nonnull
    @Override
    public List<IMentionable> getMentions(@Nonnull MentionType... types) {
        return null;
    }

    @Override
    public boolean isMentioned(@Nonnull IMentionable mentionable, @Nonnull MentionType... types) {
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

    @Nullable
    @Override
    public OffsetDateTime getTimeEdited() {
        return null;
    }

    @Nonnull
    @Override
    public User getAuthor() {
        return this.user;
    }

    @Nullable
    @Override
    public Member getMember() {
        return new JDAMemberMock();
    }

    @Nonnull
    @Override
    public String getJumpUrl() {
        return null;
    }

    @Nonnull
    @Override
    public String getContentDisplay() {
        return null;
    }

    @Nonnull
    @Override
    public String getContentRaw() {
        return this.content;
    }

    @Nonnull
    @Override
    public String getContentStripped() {
        return null;
    }

    @Nonnull
    @Override
    public List<String> getInvites() {
        return null;
    }

    @Nullable
    @Override
    public String getNonce() {
        return null;
    }

    @Override
    public boolean isFromType(@Nonnull ChannelType type) {
        return false;
    }

    @Nonnull
    @Override
    public ChannelType getChannelType() {
        return null;
    }

    @Override
    public boolean isWebhookMessage() {
        return false;
    }

    @Nonnull
    @Override
    public MessageChannel getChannel() {
        return null;
    }

    @Nonnull
    @Override
    public PrivateChannel getPrivateChannel() {
        return null;
    }

    @Nonnull
    @Override
    public TextChannel getTextChannel() {
        return new JDATextChannelMock(this.guild);
    }

    @Nullable
    @Override
    public Category getCategory() {
        return null;
    }

    @Nonnull
    @Override
    public Guild getGuild() {
        return this.guild;
    }

    @Nonnull
    @Override
    public List<Attachment> getAttachments() {
        return null;
    }

    @Nonnull
    @Override
    public List<MessageEmbed> getEmbeds() {
        return null;
    }

    @Nonnull
    @Override
    public List<Emote> getEmotes() {
        return null;
    }

    @Nonnull
    @Override
    public Bag<Emote> getEmotesBag() {
        return null;
    }

    @Nonnull
    @Override
    public List<MessageReaction> getReactions() {
        return null;
    }

    @Override
    public boolean isTTS() {
        return false;
    }

    @Nullable
    @Override
    public MessageActivity getActivity() {
        return null;
    }

    @Nonnull
    @Override
    public MessageAction editMessage(@Nonnull CharSequence newContent) {
        return null;
    }

    @Nonnull
    @Override
    public MessageAction editMessage(@Nonnull MessageEmbed newContent) {
        return null;
    }

    @Nonnull
    @Override
    public MessageAction editMessageFormat(@Nonnull String format, @Nonnull Object... args) {
        return null;
    }

    @Nonnull
    @Override
    public MessageAction editMessage(@Nonnull Message newContent) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> delete() {
        return null;
    }

    @Nonnull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Override
    public boolean isPinned() {
        return false;
    }

    @Nonnull
    @Override
    public RestAction<Void> pin() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> unpin() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> addReaction(@Nonnull Emote emote) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> addReaction(@Nonnull String unicode) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> clearReactions() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> clearReactions(@Nonnull String unicode) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> clearReactions(@Nonnull Emote emote) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> removeReaction(@Nonnull Emote emote) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> removeReaction(@Nonnull Emote emote, @Nonnull User user) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> removeReaction(@Nonnull String unicode) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> removeReaction(@Nonnull String unicode, @Nonnull User user) {
        return null;
    }

    @Nonnull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull Emote emote) {
        return null;
    }

    @Nonnull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull String unicode) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionByUnicode(@Nonnull String unicode) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(@Nonnull String id) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(long id) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> suppressEmbeds(boolean suppressed) {
        return null;
    }

    @Override
    public boolean isSuppressedEmbeds() {
        return false;
    }

    @Nonnull
    @Override
    public EnumSet<MessageFlag> getFlags() {
        return null;
    }

    @Nonnull
    @Override
    public MessageType getType() {
        return null;
    }

    @Override
    public void formatTo(Formatter formatter, int i, int i1, int i2) {

    }

    @Override
    public long getIdLong() {
        return 213211329;
    }


}
