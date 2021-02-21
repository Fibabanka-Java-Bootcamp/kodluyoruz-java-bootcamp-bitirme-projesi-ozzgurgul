package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRepository extends JpaRepository<DebitCard,Long> {

    DebitCard findByCardNumber(String cardNumber);

}
