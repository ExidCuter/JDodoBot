package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_bank_account")
public class BankAccount extends Identificator {
    private double balance;
    private LocalDateTime lastPay;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LocalDateTime getLastPay()
    {
        return lastPay;
    }

    public void setLastPay(LocalDateTime p_time)
    {
        lastPay = p_time;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
