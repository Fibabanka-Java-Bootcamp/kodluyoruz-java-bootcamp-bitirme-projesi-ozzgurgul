package org.kodluyoruz.mybank.repository;


import org.kodluyoruz.mybank.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    CreditCard findByCardNumber(String cardNumber);

}