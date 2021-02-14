package org.kodluyoruz.mybank.Customer;

import org.kodluyoruz.mybank.Card.CardService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CardService cardService;

    public CustomerService(CustomerRepository customerRepository, CardService cardService) {
        this.customerRepository = customerRepository;
        this.cardService = cardService;
    }

    public Customer create(Customer customer){

        return customerRepository.save(customer);

    }
    public Optional<Customer> get(Integer id) {
        return customerRepository.findById(id);
    }

    public void delete(Integer id){

        customerRepository.deleteById(id);
    }

    public Customer update(Integer id,Customer customer){

        customer.setId(customer.getId());
        customer.setName(customer.getName());
        customer.setSurname(customer.getSurname());

        return customerRepository.save(customer);

    }

}

