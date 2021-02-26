package org.kodluyoruz.mybank.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;
    private double totalLimit;
    private double currentLimit;


    @OneToOne(mappedBy = "creditCard")
    @JsonIgnore
    private Customer customer;



}
