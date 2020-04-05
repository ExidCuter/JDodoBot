package xyz.the_dodo.database.types;

import lombok.*;
import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_quote")
public class Quote extends Identificator {
    private String person;
    private String quote;
    private LocalDate wheno;
}
