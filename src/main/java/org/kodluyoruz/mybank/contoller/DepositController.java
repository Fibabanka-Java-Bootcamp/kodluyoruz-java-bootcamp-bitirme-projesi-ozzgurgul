package org.kodluyoruz.mybank.contoller;


import org.kodluyoruz.mybank.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/v1/customer/deposit")
@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping("/{id}/{balance}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAccount(@PathVariable long id, @PathVariable long balance){
        return depositService.createDeposit(id,balance);
    }
    @PostMapping("/addBalance/{accountNumber}/{balance}")
    public ResponseEntity<Object> addBalance(@PathVariable String accountNumber,@PathVariable double balance){

        try {
            return depositService.addBalance(accountNumber,balance);
        } catch (Exception e) {

            return ResponseEntity.ok("Hesap numarası yanlış");

        }

    }
    @PostMapping("/reduceBalance/{accountNumber}/{balance}")
    public ResponseEntity<Object> reduceBalance(@PathVariable String accountNumber,@PathVariable double balance){

        try {
            return depositService.reduceBalance(accountNumber,balance);
        } catch (Exception e) {

            return ResponseEntity.ok("Hesap numarası yanlış");

        }

    }

    @PostMapping("/transferToDeposit/{fromIban}/{toIban}/{amount}")
    public ResponseEntity<Object> transferToDeposit(@PathVariable String fromIban,@PathVariable String toIban,@PathVariable double amount){
        return depositService.transferToDeposit(fromIban,toIban,amount);
    }

    @PostMapping("/transferToSaving/{fromIban}/{toIban}/{amount}")
    public ResponseEntity<Object> transferToSaving(@PathVariable String fromIban,@PathVariable String toIban,@PathVariable double amount){

        try {

            return depositService.transferToSaving(fromIban,toIban,amount);

        } catch (Exception e) {

            return ResponseEntity.ok(" İban numarasını kontrol edin");

        }


    }
}
