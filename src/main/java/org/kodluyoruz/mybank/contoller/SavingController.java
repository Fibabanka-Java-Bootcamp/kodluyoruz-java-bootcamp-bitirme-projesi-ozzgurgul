package org.kodluyoruz.mybank.contoller;

import org.kodluyoruz.mybank.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer/saving")
@RestController
public class SavingController {

    @Autowired
    private SavingService savingService;

    @PostMapping("/create/{id}/{balance}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAccount(@PathVariable long id, @PathVariable long balance){
        return savingService.createSaving(id,balance);
    }

    @PostMapping("/addBalance/{accountNumber}/{balance}")
    public ResponseEntity<Object> addBalance(@PathVariable String accountNumber,@PathVariable double balance){

        return savingService.addBalance(accountNumber,balance);

    }
    @PostMapping("/reduceBalance/{accountNumber}/{balance}")
    public ResponseEntity<Object> reduceBalance(@PathVariable String accountNumber,@PathVariable double balance){

        return savingService.reduceBalance(accountNumber,balance);

    }
    @PostMapping("/transferToDeposit/{fromIban}/{toIban}/{amount}")
    public ResponseEntity<Object> transferToDeposit(@PathVariable String fromIban,@PathVariable String toIban,@PathVariable double amount){
        return savingService.transferToDeposit(fromIban,toIban,amount);
    }

}

