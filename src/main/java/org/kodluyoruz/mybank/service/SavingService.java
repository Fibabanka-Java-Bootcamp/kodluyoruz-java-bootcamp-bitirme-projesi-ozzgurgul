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

        if(customerRepository.findById(customer_id) == null){

            return ResponseEntity.ok(customer_id+ " id'sine sahip kullanıcı bulunamadı.");
        }

        SavingAccount savingAccount = new SavingAccount();

        Customer customer = customerRepository.findById(customer_id);

        savingAccount.setBalance(balance);
        savingAccount.setCustomer(customer);

        String accountNumber = createNumber.createNumber();
        savingAccount.setAccountNumber(accountNumber);
        savingAccount.setIbanNo(createNumber.iban(accountNumber));

        customer.setSavingAccount(savingAccount);
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
        if(balance>2000){

            return ResponseEntity.ok().body("Tek seferde çekebileceğiniz max tutar 2000 TL");
        }

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

    public ResponseEntity<Object> transferToSaving(String fromIban,String toIban,double amount){

        SavingAccount receiver = savingRepository.findByIbanNo(toIban);
        SavingAccount sender =savingRepository.findByIbanNo(fromIban);

        if(amount>sender.getBalance())
            return ResponseEntity.ok().body("Yetersiz bakiye");

        if(!receiver.getCustomer().equals(sender.getCustomer()))
            return ResponseEntity.ok().body("Farklı kişiye transfer yapamazsınız");

        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        savingRepository.save(receiver);
        savingRepository.save(sender);

        return ResponseEntity.ok().body("Transfer gerçekleştirildi");

    }

}
