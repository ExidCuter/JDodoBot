package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

public class DeletedMessage extends Identificator {
    private String message;
    private String fileLocation;

    private User m_user;
    private Server m_server;



    public String getMessage() {
        return message;
    }

    public void setMessage(String p_message) {
        message = p_message;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String p_fileLocation) {
        fileLocation = p_fileLocation;
    }

    public User getUser() {
        return m_user;
    }

    public void setUser(User p_user) {
        m_user = p_user;
    }

    public Server getServer() {
        return m_server;
    }

    public void setServer(Server p_server) {
        m_server = p_server;
    }
}
