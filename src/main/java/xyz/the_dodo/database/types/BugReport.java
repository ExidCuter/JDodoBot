package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.enums.BugTypeEnum;
import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_bug_report")
public class BugReport extends Identificator {
    private BugTypeEnum bugType;
    private String massage;
    private String stackTrace;
    private String userInfo;
    private LocalDateTime time;


    public BugTypeEnum getBugType() {
        return bugType;
    }

    public void setBugType(BugTypeEnum bugType) {
        this.bugType = bugType;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
