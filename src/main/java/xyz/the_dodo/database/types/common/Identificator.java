package xyz.the_dodo.database.types.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class Identificator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
