package org.kodluyoruz.mybank.service;


import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<Object> createCustomer(String name,String surname){

        Customer customer = new Customer();

        customer.setLastName(name);
        customer.setName(surname);

        customerRepository.save(customer);

        return ResponseEntity.ok("Müşteri tanımlandı");

    }

    public List<Customer> findAll(){

        return customerRepository.findAll();
    }
    public  ResponseEntity<Object> deleteCustomer(long id) {

        if(customerRepository.findById(id) == null){

            return ResponseEntity.ok(id+ " id'sine sahip kullanıcı bulunamadı.");
        }

        if (customerRepository.findById(id).getDepositAccount() == null && customerRepository.findById(id).getSavingAccount() == null  && customerRepository.findById(id).getCreditCard() == null ) {

            customerRepository.deleteById(id);
            return ResponseEntity.ok("Kullanıcı kaydı silindi");

        }

        if (customerRepository.findById(id).getDepositAccount().getBalance() > 0  || customerRepository.findById(id).getSavingAccount().getBalance() > 0 || customerRepository.findById(id).getCreditCard().getCurrentLimit() < customerRepository.findById(id).getCreditCard().getTotalLimit()  ) {

            return  ResponseEntity.ok("Kullanıcı kaydı silinemiyor");

        }

        customerRepository.deleteById(id);
        return ResponseEntity.ok("Kullanıcı kaydı silindi");


    }
    public ResponseEntity<Object> updateCustomer(long id, String name,String surname){

        if(customerRepository.findById(id) == null){

            return ResponseEntity.ok(id+ " id'sine sahip kullanıcı bulunamadı.");
        }

        Customer customer = customerRepository.findById(id);
        customer.setName(name);
        customer.setLastName(surname);

        customerRepository.save(customer);


        return ResponseEntity.ok("Müşteri Güncellendi");

    }



        }







