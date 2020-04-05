package xyz.the_dodo.database.types;

import lombok.*;
import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_server")
public class Server extends Identificator {
    private String discordId;
    private boolean saveDeleted;
}
