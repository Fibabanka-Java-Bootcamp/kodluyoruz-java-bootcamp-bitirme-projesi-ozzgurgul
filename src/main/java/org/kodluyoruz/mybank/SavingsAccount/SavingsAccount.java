package org.kodluyoruz.mybank.SavingsAccount;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SavingsAccount {

    @Id
    @GeneratedValue
    private Long id;

    private Long accountNumber;

    private BigDecimal accountBalance;


    public SavingsAccountDto toSavingsAccountDto() {
        return SavingsAccountDto.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .accountBalance(this.accountBalance)
                .build();
    }

}
