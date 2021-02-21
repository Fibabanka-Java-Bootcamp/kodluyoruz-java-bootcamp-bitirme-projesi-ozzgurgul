package org.kodluyoruz.mybank.repository;


import org.kodluyoruz.mybank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByCreditCardNumber(String cardNumber);

}
