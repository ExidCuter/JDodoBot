package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_rules")
public class Rules extends Identificator {
    private String rules;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server m_server;

    public String getRules() {
        return rules;
    }

    public void setRules(String p_rules) {
        rules = p_rules;
    }

    public Server getServer() {
        return m_server;
    }

    public void setServer(Server p_server) {
        m_server = p_server;
    }
}
