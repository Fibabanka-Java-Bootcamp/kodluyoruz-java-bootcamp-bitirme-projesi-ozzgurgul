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
public class DepositService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private SavingRepository savingRepository;

    private final CreateNumber createNumber = new CreateNumber();


    public ResponseEntity<Object> createDeposit(long customer_id, long balance){

        if(customerRepository.findById(customer_id) == null){

            return ResponseEntity.ok(customer_id+ " id'sine sahip kullanıcı bulunamadı.");
        }

        DepositAccount depositAccount = new DepositAccount();

        Customer customer = customerRepository.findById(customer_id);

        depositAccount.setBalance(balance);
        depositAccount.setCustomer(customer);

        String accountNumber = createNumber.createNumber();
        depositAccount.setAccountNumber(accountNumber);
        depositAccount.setIbanNo(createNumber.iban(accountNumber));

        customer.setDepositAccount(depositAccount);
        depositRepository.save(depositAccount);
        return ResponseEntity.ok().body("Mevduat hesabı oluşturuldu");
    }

    public ResponseEntity<Object> addBalance(String accountNumber,double balance){

        DepositAccount depositAccount = depositRepository.findByAccountNumber(accountNumber);

        depositAccount.setBalance(depositAccount.getBalance()+balance);
        depositRepository.save(depositAccount);
        return ResponseEntity.ok().body("Tutar eklendi");
    }

    public ResponseEntity<Object> reduceBalance(String accountNumber,double balance){


        DepositAccount depositAccount = depositRepository.findByAccountNumber(accountNumber);
        if(balance>2000){

            return ResponseEntity.ok().body("Tek seferde çekebileceğiniz max tutar 2000 TL");
        }

        if(balance>depositAccount.getBalance())
            return ResponseEntity.ok().body("Bakiye yetersiz");

        depositAccount.setBalance(depositAccount.getBalance()-balance);
        depositRepository.save(depositAccount);
        return ResponseEntity.ok().body("Tutar düşüldü");
    }


    public ResponseEntity<Object> transferToDeposit(String fromIban,String toIban,double amount){

        DepositAccount sender = depositRepository.findByIbanNo(fromIban);
        DepositAccount receiver =depositRepository.findByIbanNo(toIban);

        if(amount>sender.getBalance())
            return ResponseEntity.ok().body("Bakiye yetersiz");

        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        depositRepository.save(sender);
        depositRepository.save(receiver);

        return ResponseEntity.ok().body("Transfer gerçekleştirildi");

    }

    public ResponseEntity<Object> transferToSaving(String fromIban,String toIban,double amount){

        DepositAccount sender = depositRepository.findByIbanNo(fromIban);
        SavingAccount receiver =savingRepository.findByIbanNo(toIban);

        if(amount>sender.getBalance())
            return ResponseEntity.ok().body("Bakiye yetersiz");

        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        depositRepository.save(sender);
        savingRepository.save(receiver);

        return ResponseEntity.ok().body("Transfer gerçekleştirildi");

    }

}
