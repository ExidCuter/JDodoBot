package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_stats")
public class Stats extends Identificator {
    Long numOfFiles, numOfMessages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User m_user;

    public Long getNumOfFiles() {
        return numOfFiles;
    }

    public void setNumOfFiles(Long p_numOfFiles) {
        numOfFiles = p_numOfFiles;
    }

    public Long getNumOfMessages() {
        return numOfMessages;
    }

    public void setNumOfMessages(Long p_getNumOfFiles) {
        numOfMessages = p_getNumOfFiles;
    }

    public User getUser() {
        return m_user;
    }

    public void setUser(User p_user) {
        m_user = p_user;
    }

    public Stats() {
    }

    public Stats(Long id) {
        super(id);
    }
}
