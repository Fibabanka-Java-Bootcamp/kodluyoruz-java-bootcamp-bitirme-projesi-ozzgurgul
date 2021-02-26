package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findById(long id);
    List<Customer> findAll();
    Customer deleteById(long id);

}
