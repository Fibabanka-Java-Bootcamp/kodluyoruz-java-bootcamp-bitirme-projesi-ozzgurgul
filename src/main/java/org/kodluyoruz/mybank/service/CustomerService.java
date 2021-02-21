package org.kodluyoruz.mybank.service;


import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        }







