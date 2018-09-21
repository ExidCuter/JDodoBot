package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.*;

@Entity
@Table(name = "t_subscription")
public class Subscription extends Identificator {
    @Column(name = "channel_id")
    private String m_channelId;

    @Column(name = "command")
    private String m_command;

    @Column(name = "timer")
    private Integer m_timer;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server m_server;

    public String getChannelId() {
        return m_channelId;
    }

    public void setChannelId(String p_channelId) {
        m_channelId = p_channelId;
    }

    public Integer getTimer() {
        return m_timer;
    }

    public void setTimer(Integer p_timer) {
        m_timer = p_timer;
    }

    public Server getServer() {
        return m_server;
    }

    public void setServer(Server p_server) {
        m_server = p_server;
    }

    public String getCommand() {
        return m_command;
    }

    public void setCommand(String p_command) {
        m_command = p_command;
    }
}