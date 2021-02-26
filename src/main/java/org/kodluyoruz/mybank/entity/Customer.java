package org.kodluyoruz.mybank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deposit_id",referencedColumnName = "id")
    private DepositAccount depositAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "saving_id",referencedColumnName = "id")
    private SavingAccount savingAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id",referencedColumnName = "id")
    private CreditCard creditCard;

}