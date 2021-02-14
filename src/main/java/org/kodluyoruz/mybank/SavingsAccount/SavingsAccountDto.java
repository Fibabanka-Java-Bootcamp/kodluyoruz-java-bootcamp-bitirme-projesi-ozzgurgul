package org.kodluyoruz.mybank.SavingsAccount;


import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccountDto {

    private Long id;
    private Long accountNumber;
    private BigDecimal accountBalance;



    public SavingsAccount toSavingsAccount() {
        return SavingsAccount.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .accountBalance(this.accountBalance)
                .build();
    }
}
