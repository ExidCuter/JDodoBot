package xyz.the_dodo.database.types.common;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Identificator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
