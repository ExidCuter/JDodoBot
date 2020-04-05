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
@Table(name = "t_prefix")
public class Prefix extends Identificator {
    @Column(name = "prefix")
    private String prefix;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;
}
