package org.kodluyoruz.mybank.SavingsAccount;


import org.kodluyoruz.mybank.Customer.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/customer/account/savingsaccount")
public class SavingsAccountController {

    private final SavingAccountService savingAccountService;

    public SavingsAccountController(SavingAccountService savingAccountService) {
        this.savingAccountService = savingAccountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccountDto create(@RequestBody SavingsAccountDto savingsAccountDto){

        return savingAccountService.create(savingsAccountDto.toSavingsAccount()).toSavingsAccountDto();

    }

    @GetMapping("/{id}")
    public SavingsAccountDto get(@PathVariable("id") Long id) {
        return savingAccountService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toSavingsAccountDto();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        savingAccountService.delete(id);
        //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toCustomerDto();
    }
}
