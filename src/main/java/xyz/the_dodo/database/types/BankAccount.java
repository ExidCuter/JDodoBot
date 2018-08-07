package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.*;

@Entity
@Table(name = "t_bank_account")
public class BankAccount extends Identificator {
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
