package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.entity.*;
import org.kodluyoruz.mybank.number.CreateNumber;
import org.kodluyoruz.mybank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.*;

@Service
public class CreditService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DebitRepository debitRepository;

    private final CreateNumber createNumber=new CreateNumber();

    public ResponseEntity<Object> createCreditCard(long customer_id,double limit){

        if(customerRepository.findById(customer_id) == null){

            return ResponseEntity.ok(customer_id+ " id'sine sahip kullanıcı bulunamadı.");
        }

        CreditCard creditCard = new CreditCard();

        Customer customer = customerRepository.findById(customer_id);

        creditCard.setCardNumber(createNumber.createNumber());
        creditCard.setCurrentLimit(limit);
        creditCard.setTotalLimit(limit);
       // creditCard.setCustomer(customer);

        customer.setCreditCard(creditCard);
        creditCardRepository.save(creditCard);

        return ResponseEntity.ok().body("Kredi kartı tanımlandı");
    }

    public ResponseEntity<Object> reduceBalance(String cardNumber,double amount){

        CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber);


        if((amount>creditCard.getCurrentLimit())){
            return ResponseEntity.ok().body("Yetersiz bakiye");
        }

        creditCard.setCurrentLimit(creditCard.getCurrentLimit()-amount);
        creditCardRepository.save(creditCard);

        Transaction transaction = new Transaction(0L,cardNumber,amount,"Harcama",new Date(System.currentTimeMillis()));
        transactionRepository.save(transaction);
        return ResponseEntity.ok().body("Tutar düşüldü");

    }


    public ResponseEntity<Object> payDebtFromAccount(String cardNumber,double amount,String accountNumber){

        CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber);

        DepositAccount depositAccount =depositRepository.findByAccountNumber(accountNumber);

        if(!creditCard.getCustomer().equals(depositAccount.getCustomer())){

            return ResponseEntity.ok("Farklı hesaptan borç ödeyemezsiniz");
        }

        if((amount>depositAccount.getBalance())){
            return ResponseEntity.ok().body("Yetersiz bakiye");
        }

        creditCard.setCurrentLimit(creditCard.getCurrentLimit()+amount);
        creditCardRepository.save(creditCard);

        depositAccount.setBalance(depositAccount.getBalance()-amount);
        depositRepository.save(depositAccount);


        Transaction transaction = new Transaction(0L,cardNumber,amount,"Borç ödeme",new Date(System.currentTimeMillis()));
        transactionRepository.save(transaction);
        return ResponseEntity.ok().body("Borç ödendi");

    }

    public ResponseEntity<Object> debtFromDebit(String debitCardNumber,double amount,String creditCardNumber){

        DebitCard debitCard =debitRepository.findByCardNumber(debitCardNumber);
        CreditCard creditCard = creditCardRepository.findByCardNumber(creditCardNumber);

        if(!creditCard.getCustomer().equals(debitCard.getDepositAccount().getCustomer())){

            return ResponseEntity.ok("Farklı karttan borç ödeyemezsiniz");
        }

        DepositAccount depositAccount = debitCard.getDepositAccount();

        if(amount>depositAccount.getBalance()){
            return ResponseEntity.ok().body("Yetersiz bakiye");
        }

        creditCard.setCurrentLimit(creditCard.getCurrentLimit()+amount);
        creditCardRepository.save(creditCard);

        depositAccount.setBalance(depositAccount.getBalance()-amount);
        depositRepository.save(depositAccount);


        Transaction transaction = new Transaction(0L,creditCardNumber,amount,"Borç ödeme",new Date(System.currentTimeMillis()));
        transactionRepository.save(transaction);

        return ResponseEntity.ok().body("Borç ödendi");
    }


    public List<Transaction> getStates(String cardNumber){

        return transactionRepository.findByCreditCardNumber(cardNumber);
    }

    public ResponseEntity<Object> getDebt(String cardNumber){

        CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber);
        return ResponseEntity.ok().body("Güncel borç :"+(creditCard.getTotalLimit()-creditCard.getCurrentLimit()));

    }

}
