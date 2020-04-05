package xyz.the_dodo.database.types;

import lombok.*;
import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_bank_account")
public class BankAccount extends Identificator {
    private double balance;
    private LocalDateTime lastPay;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
