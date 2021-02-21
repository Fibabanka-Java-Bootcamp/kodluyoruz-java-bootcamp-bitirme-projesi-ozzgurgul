package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.entity.DepositAccount;
import org.kodluyoruz.mybank.entity.SavingAccount;
import org.kodluyoruz.mybank.number.CreateNumber;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.repository.DepositRepository;
import org.kodluyoruz.mybank.repository.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SavingService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private DepositRepository depositRepository;

    private final CreateNumber createNumber = new CreateNumber();


    public ResponseEntity<Object> createSaving(long customer_id, long balance){

        SavingAccount savingAccount = new SavingAccount();

        Customer customer = customerRepository.findById(customer_id);

        savingAccount.setBalance(balance);
        savingAccount.setCustomer(customer);

        String accountNumber = createNumber.createNumber();
        savingAccount.setAccountNumber(accountNumber);
        savingAccount.setIbanNo(createNumber.iban(accountNumber));



        savingRepository.save(savingAccount);
        return ResponseEntity.ok().body("Birikim hesabı oluşturuldu");
    }

    public ResponseEntity<Object> addBalance(String accountNumber,double balance){

        SavingAccount savingAccount = savingRepository.findByAccountNumber(accountNumber);

        savingAccount.setBalance(savingAccount.getBalance()+balance);
        savingRepository.save(savingAccount);
        return ResponseEntity.ok().body("Tutar eklendi");
    }

    public ResponseEntity<Object> reduceBalance(String accountNumber,double balance){

        SavingAccount savingAccount = savingRepository.findByAccountNumber(accountNumber);

        if(balance>savingAccount.getBalance())
            return ResponseEntity.ok().body("Bakiye yetersiz");

        savingAccount.setBalance(savingAccount.getBalance()-balance);
        savingRepository.save(savingAccount);
        return ResponseEntity.ok().body("Tutar düşüldü");
    }
    public ResponseEntity<Object> transferToDeposit(String fromIban,String toIban,double amount){

        DepositAccount receiver = depositRepository.findByIbanNo(toIban);
        SavingAccount sender =savingRepository.findByIbanNo(fromIban);

        if(amount>sender.getBalance())
            return ResponseEntity.ok().body("Bakiye yetersiz");


        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        depositRepository.save(receiver);
        savingRepository.save(sender);

        return ResponseEntity.ok().body("Transfer gerçekleştirildi");

    }


}
