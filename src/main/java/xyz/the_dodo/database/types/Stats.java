package xyz.the_dodo.database.types;

import lombok.*;
import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_user_stats")
public class Stats extends Identificator {
    Long numOfFiles;
    Long numOfMessages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
