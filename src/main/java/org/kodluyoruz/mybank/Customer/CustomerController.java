package org.kodluyoruz.mybank.Customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto customerDto) {

        return customerService.create(customerDto.toCustomer()).toCustomerDto();

    }

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable("id") Integer id) {
        return customerService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toCustomerDto();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        customerService.delete(id);
        //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toCustomerDto();
    }
    @PutMapping("/{id}")
    public void update(@PathVariable("id")  Integer id, Customer customer){

        customerService.update(id,customer);

    }

}
