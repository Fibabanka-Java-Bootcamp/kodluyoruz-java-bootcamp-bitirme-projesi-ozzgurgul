package org.kodluyoruz.mybank.contoller;

import org.kodluyoruz.mybank.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customer/saving")
@RestController
public class SavingController {

    @Autowired
    private SavingService savingService;

    @PostMapping("/{id}/{balance}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAccount(@PathVariable long id, @PathVariable long balance){
        return savingService.createSaving(id,balance);
    }

    @PostMapping("/addBalance/{accountNumber}/{balance}")
    public ResponseEntity<Object> addBalance(@PathVariable String accountNumber,@PathVariable double balance){

        try {

            return savingService.addBalance(accountNumber,balance);

        } catch (Exception e) {

            return ResponseEntity.ok(" Hesap numarası yanlış");

        }



    }
    @PostMapping("/reduceBalance/{accountNumber}/{balance}")
    public ResponseEntity<Object> reduceBalance(@PathVariable String accountNumber,@PathVariable double balance){

        try {

            return savingService.reduceBalance(accountNumber,balance);

        } catch (Exception e) {

            return ResponseEntity.ok(" Hesap numarası yanlış");

        }


    }
    @PostMapping("/transferToDeposit/{fromIban}/{toIban}/{amount}")
    public ResponseEntity<Object> transferToDeposit(@PathVariable String fromIban,@PathVariable String toIban,@PathVariable double amount){

        try {

            return savingService.transferToDeposit(fromIban,toIban,amount);

        } catch (Exception e) {

            return ResponseEntity.ok(" İban numarasını kontrol edin");

        }


    }

    @PostMapping("/transferToSaving/{fromIban}/{toIban}/{amount}")
    public ResponseEntity<Object> transferToSaving(@PathVariable String fromIban,@PathVariable String toIban,@PathVariable double amount){

        try {

            return savingService.transferToSaving(fromIban,toIban,amount);

        } catch (Exception e) {

            return ResponseEntity.ok(" İban numarasını kontrol edin");

        }

    }

}

