package org.kodluyoruz.mybank.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DepositAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String accountNumber;
    private String ibanNo;
    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_id",referencedColumnName = "id")
    private DebitCard debitCard;


    @OneToOne(mappedBy = "depositAccount")
    @JsonIgnore
    private Customer customer;

}
