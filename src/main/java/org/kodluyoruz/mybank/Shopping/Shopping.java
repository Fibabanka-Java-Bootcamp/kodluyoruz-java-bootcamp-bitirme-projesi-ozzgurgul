package org.kodluyoruz.mybank.Shopping;


import lombok.Data;
import org.kodluyoruz.mybank.Card.Card;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
public class Shopping {

    @Id
    private Long id;

@ManyToOne
    private Card card;

private BigDecimal amount;


}
