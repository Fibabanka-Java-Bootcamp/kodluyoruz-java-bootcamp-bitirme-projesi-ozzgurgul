package org.kodluyoruz.mybank.PrimaryAccount;


import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryAccountDto {

    private Long id;
    private Long accountNumber;
    private Boolean savingAccount;
    private BigDecimal accountBalance;


    public PrimaryAccount toPrimaryAccount() {
        return PrimaryAccount.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .savingAccount(this.savingAccount)
                .accountBalance(this.accountBalance)
                .build();
    }


}
