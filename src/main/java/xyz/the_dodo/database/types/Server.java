package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_server")
public class Server extends Identificator {
    private String discordId;

    private boolean saveDeleted;

    public boolean isSaveDeleted() {
        return saveDeleted;
    }

    public void setSaveDeleted(boolean saveDeleted) {
        this.saveDeleted = saveDeleted;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }
}
