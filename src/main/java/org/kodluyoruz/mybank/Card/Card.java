package org.kodluyoruz.mybank.Card;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.Customer.Customer;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    private Long cardNumber;

    private BigDecimal balance;

    private  Boolean debitCard;


    public CardDto toCardDto(){

       return CardDto.builder()
               .id(this.id)
               .cardNumber(this.cardNumber)
               .balance(this.balance)
               .debitCard(this.debitCard)
               .build();


    }

}
