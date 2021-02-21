package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.entity.DebitCard;
import org.kodluyoruz.mybank.entity.DepositAccount;
import org.kodluyoruz.mybank.number.CreateNumber;
import org.kodluyoruz.mybank.repository.DebitRepository;
import org.kodluyoruz.mybank.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DebitService {

    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private DepositRepository depositRepository;

    private final CreateNumber createNumber = new CreateNumber();


    public ResponseEntity<Object> createDebit(String accountNumber){

        DepositAccount depositAccount=depositRepository.findByAccountNumber(accountNumber);

        DebitCard debitCard = new DebitCard();

        debitCard.setCardNumber(createNumber.createNumber());
        debitCard.setDepositAccount(depositAccount);

        depositAccount.setDebitCard(debitCard);

        debitRepository.save(debitCard);
        depositRepository.save(depositAccount);

        return ResponseEntity.ok().body("Banka kartı tanımlandı");

    }


}
