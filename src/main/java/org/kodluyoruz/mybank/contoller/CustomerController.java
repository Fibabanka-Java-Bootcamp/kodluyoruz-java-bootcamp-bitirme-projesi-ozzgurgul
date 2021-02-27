package org.kodluyoruz.mybank.contoller;

import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/{name}/{surname}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createCustomer(@Valid @PathVariable String name, @Valid @PathVariable String surname){
        try {
            return customerService.createCustomer(name,surname);
        } catch (Exception e) {
            return ResponseEntity.ok("Ad ve soyad boş bırakılamaz");
        }

    }

    @GetMapping("/listAll")
    public List<Customer> findAll(){
        return customerService.findAll();
    }


    @DeleteMapping("/{id}")

    public  ResponseEntity<Object> deleteCustomer(@PathVariable long id){

        return customerService.deleteCustomer(id);
    }

    @PutMapping("/{id}/{name}/{surname}")

    public  ResponseEntity<Object> updateCustomer(@PathVariable long id, @PathVariable String name, @PathVariable  String surname){

        try {
            return customerService.updateCustomer(id,name,surname);
        } catch (Exception e) {
            return ResponseEntity.ok("Ad ve soyad boş bırakılamaz");
        }

    }

}

