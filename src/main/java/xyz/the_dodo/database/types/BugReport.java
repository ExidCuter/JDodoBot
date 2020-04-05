package xyz.the_dodo.database.types;

import lombok.*;
import xyz.the_dodo.database.types.common.Identificator;
import xyz.the_dodo.database.types.enums.BugTypeEnum;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_bug_report")
public class BugReport extends Identificator {
    private BugTypeEnum bugType;
    private String massage;
    private String stackTrace;
    private String userInfo;
    private LocalDateTime time;
}
