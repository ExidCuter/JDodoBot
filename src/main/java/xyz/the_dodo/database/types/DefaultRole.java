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
@Table(name = "t_default_role")
public class DefaultRole extends Identificator {
    private String discordId;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;
}
