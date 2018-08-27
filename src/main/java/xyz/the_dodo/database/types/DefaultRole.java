package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_default_role")
public class DefaultRole extends Identificator {
    private String discordId;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public DefaultRole() { }

    public DefaultRole(Long id) {
        super(id);
    }
}
