package org.kodluyoruz.mybank.Card;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder

public class CardDto {

    private Long id;

    private Long cardNumber;

    private BigDecimal balance;

    private  Boolean debitCard;

    public Card toCard() {

        return Card.builder()
                .id(this.id)
                .cardNumber(this.cardNumber)
                .balance(this.balance)
                .debitCard(this.debitCard)
                .build();
    }

}
