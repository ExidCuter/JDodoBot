package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User extends Identificator {
    private String discordId;

    private boolean banned;

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean p_banned) {
        banned = p_banned;
    }
}