package xyz.the_dodo.database.types;

import lombok.*;
import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_rules")
public class Rules extends Identificator {
    private String rules;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "server_id")
    private Server server;
}
