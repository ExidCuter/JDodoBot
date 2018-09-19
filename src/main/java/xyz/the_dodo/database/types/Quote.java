package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "t_quote")
public class Quote extends Identificator {
    private String person;
    private String quote;
    private LocalDate wheno;

    public LocalDate getWheno()
    {
        return wheno;
    }

    public void setWheno(LocalDate p_wheno)
    {
        wheno = p_wheno;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
