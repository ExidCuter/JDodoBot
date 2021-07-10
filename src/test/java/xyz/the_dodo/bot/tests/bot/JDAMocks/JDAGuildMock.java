package xyz.the_dodo.bot.tests.bot.JDAMocks;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.GuildManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.MemberAction;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.requests.restaction.order.CategoryOrderAction;
import net.dv8tion.jda.api.requests.restaction.order.ChannelOrderAction;
import net.dv8tion.jda.api.requests.restaction.order.RoleOrderAction;
import net.dv8tion.jda.api.requests.restaction.pagination.AuditLogPaginationAction;
import net.dv8tion.jda.api.utils.cache.MemberCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.cache.SortedSnowflakeCacheView;
import net.dv8tion.jda.api.utils.concurrent.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class JDAGuildMock implements Guild {
    private long id;

    public JDAGuildMock(long id) {
        this.id = id;
    }

    @Nonnull
    @Override
    public RestAction<EnumSet<Region>> retrieveRegions(boolean includeDeprecated) {
        return null;
    }

    @Nonnull
    @Override
    public MemberAction addMember(@Nonnull String accessToken, @Nonnull String userId) {
        return null;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void pruneMemberCache() {

    }

    @Override
    public boolean unloadMember(long userId) {
        return false;
    }

    @Override
    public int getMemberCount() {
        return 0;
    }

    @Nonnull
    @Override
    public String getName() {
        return "MOCK GUILD";
    }

    @Nullable
    @Override
    public String getIconId() {
        return "ICON ID";
    }

    @Nonnull
    @Override
    public Set<String> getFeatures() {
        return null;
    }

    @Nullable
    @Override
    public String getSplashId() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<String> retrieveVanityUrl() {
        return null;
    }

    @Nullable
    @Override
    public String getVanityCode() {
        return null;
    }

    @Nullable
    @Override
    public String getDescription() {
        return "DESC";
    }

    @Nullable
    @Override
    public String getBannerId() {
        return null;
    }

    @Nonnull
    @Override
    public BoostTier getBoostTier() {
        return null;
    }

    @Override
    public int getBoostCount() {
        return 0;
    }

    @Nonnull
    @Override
    public List<Member> getBoosters() {
        return null;
    }

    @Override
    public int getMaxMembers() {
        return 0;
    }

    @Override
    public int getMaxPresences() {
        return 0;
    }

    @Nonnull
    @Override
    public RestAction<MetaData> retrieveMetaData() {
        return null;
    }

    @Nullable
    @Override
    public VoiceChannel getAfkChannel() {
        return null;
    }

    @Nullable
    @Override
    public TextChannel getSystemChannel() {
        return null;
    }

    @Nullable
    @Override
    public Member getOwner() {
        return null;
    }

    @Override
    public long getOwnerIdLong() {
        return 0;
    }

    @Nonnull
    @Override
    public Timeout getAfkTimeout() {
        return null;
    }

    @Nonnull
    @Override
    public String getRegionRaw() {
        return null;
    }

    @Override
    public boolean isMember(@Nonnull User user) {
        return true;
    }

    @Nonnull
    @Override
    public Member getSelfMember() {
        return null;
    }

    @Nullable
    @Override
    public Member getMember(@Nonnull User user) {
        return null;
    }

    @Nonnull
    @Override
    public MemberCacheView getMemberCache() {
        return null;
    }

    @Nonnull
    @Override
    public SortedSnowflakeCacheView<Category> getCategoryCache() {
        return null;
    }

    @Nonnull
    @Override
    public SortedSnowflakeCacheView<StoreChannel> getStoreChannelCache() {
        return null;
    }

    @Nonnull
    @Override
    public SortedSnowflakeCacheView<TextChannel> getTextChannelCache() {
        return null;
    }

    @Nonnull
    @Override
    public SortedSnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        return null;
    }

    @Nonnull
    @Override
    public List<GuildChannel> getChannels(boolean includeHidden) {
        return null;
    }

    @Nonnull
    @Override
    public SortedSnowflakeCacheView<Role> getRoleCache() {
        return null;
    }

    @Nonnull
    @Override
    public SnowflakeCacheView<Emote> getEmoteCache() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<ListedEmote>> retrieveEmotes() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<ListedEmote> retrieveEmoteById(@Nonnull String id) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<Ban>> retrieveBanList() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Ban> retrieveBanById(@Nonnull String userId) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Integer> retrievePrunableMemberCount(int days) {
        return null;
    }

    @Nonnull
    @Override
    public Role getPublicRole() {
        return null;
    }

    @Nullable
    @Override
    public TextChannel getDefaultChannel() {
        return null;
    }

    @Nonnull
    @Override
    public GuildManager getManager() {
        return null;
    }

    @Nonnull
    @Override
    public AuditLogPaginationAction retrieveAuditLogs() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> leave() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> delete() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> delete(@Nullable String mfaCode) {
        return null;
    }

    @Nonnull
    @Override
    public AudioManager getAudioManager() {
        return null;
    }

    @Nonnull
    @Override
    public JDA getJDA() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<Invite>> retrieveInvites() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<Webhook>> retrieveWebhooks() {
        return null;
    }

    @Nonnull
    @Override
    public List<GuildVoiceState> getVoiceStates() {
        return null;
    }

    @Nonnull
    @Override
    public VerificationLevel getVerificationLevel() {
        return null;
    }

    @Nonnull
    @Override
    public NotificationLevel getDefaultNotificationLevel() {
        return null;
    }

    @Nonnull
    @Override
    public MFALevel getRequiredMFALevel() {
        return null;
    }

    @Nonnull
    @Override
    public ExplicitContentLevel getExplicitContentLevel() {
        return null;
    }

    @Override
    public boolean checkVerification() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Nonnull
    @Override
    public CompletableFuture<Void> retrieveMembers() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Member> retrieveMemberById(long id, boolean update) {
        return null;
    }

    @Nonnull
    @Override
    public Task<List<Member>> retrieveMembersByPrefix(@Nonnull String prefix, int limit) {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<Void> moveVoiceMember(@Nonnull Member member, @Nullable VoiceChannel voiceChannel) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> modifyNickname(@Nonnull Member member, @Nullable String nickname) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Integer> prune(int days, boolean wait) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> kick(@Nonnull Member member, @Nullable String reason) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> kick(@Nonnull String userId, @Nullable String reason) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> ban(@Nonnull User user, int delDays, @Nullable String reason) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> ban(@Nonnull String userId, int delDays, @Nullable String reason) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> unban(@Nonnull String userId) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> deafen(@Nonnull Member member, boolean deafen) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> mute(@Nonnull Member member, boolean mute) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> addRoleToMember(@Nonnull Member member, @Nonnull Role role) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> removeRoleFromMember(@Nonnull Member member, @Nonnull Role role) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member member, @Nullable Collection<Role> rolesToAdd, @Nullable Collection<Role> rolesToRemove) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> modifyMemberRoles(@Nonnull Member member, @Nonnull Collection<Role> roles) {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Void> transferOwnership(@Nonnull Member newOwner) {
        return null;
    }

    @Nonnull
    @Override
    public ChannelAction<TextChannel> createTextChannel(@Nonnull String name) {
        return null;
    }

    @Nonnull
    @Override
    public ChannelAction<VoiceChannel> createVoiceChannel(@Nonnull String name) {
        return null;
    }

    @Nonnull
    @Override
    public ChannelAction<Category> createCategory(@Nonnull String name) {
        return null;
    }

    @Nonnull
    @Override
    public RoleAction createRole() {
        return null;
    }

    @Nonnull
    @Override
    public AuditableRestAction<Emote> createEmote(@Nonnull String name, @Nonnull Icon icon, @Nonnull Role... roles) {
        return null;
    }

    @Nonnull
    @Override
    public ChannelOrderAction modifyCategoryPositions() {
        return null;
    }

    @Nonnull
    @Override
    public ChannelOrderAction modifyTextChannelPositions() {
        return null;
    }

    @Nonnull
    @Override
    public ChannelOrderAction modifyVoiceChannelPositions() {
        return null;
    }

    @Nonnull
    @Override
    public CategoryOrderAction modifyTextChannelPositions(@Nonnull Category category) {
        return null;
    }

    @Nonnull
    @Override
    public CategoryOrderAction modifyVoiceChannelPositions(@Nonnull Category category) {
        return null;
    }

    @Nonnull
    @Override
    public RoleOrderAction modifyRolePositions(boolean useAscendingOrder) {
        return null;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }
}
