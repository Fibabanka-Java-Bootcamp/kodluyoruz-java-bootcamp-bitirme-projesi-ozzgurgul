package org.kodluyoruz.mybank.PrimaryAccount;


import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrimaryAccount {

    @Id
    @GeneratedValue
    private Long id;

    private Long accountNumber;

    private Boolean savingAccount;

    private BigDecimal accountBalance;

    public PrimaryAccountDto toPrimaryAccountDto() {
        return PrimaryAccountDto.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .savingAccount(this.savingAccount)
                .accountBalance(this.accountBalance)
                .build();
    }
}
