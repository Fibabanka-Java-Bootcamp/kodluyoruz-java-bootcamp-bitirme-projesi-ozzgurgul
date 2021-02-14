package org.kodluyoruz.mybank.Transfer;


import lombok.*;
import org.kodluyoruz.mybank.PrimaryAccount.PrimaryAccount;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
public class Transfer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private PrimaryAccount from;

    @ManyToOne
    private PrimaryAccount to;

    private BigDecimal amount;

}
