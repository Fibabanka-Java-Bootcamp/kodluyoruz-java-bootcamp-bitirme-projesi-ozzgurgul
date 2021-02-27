package org.kodluyoruz.mybank.contoller;

import org.kodluyoruz.mybank.entity.Transaction;
import org.kodluyoruz.mybank.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/creditCard")
public class CreditCardController {

    @Autowired
    private CreditService creditService;

    @PostMapping("/{id}/{limit}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createCreditCard(@PathVariable long id,@PathVariable double limit){
        return creditService.createCreditCard(id,limit);
    }


    @PostMapping("/reduceBalance/{cardNumber}/{amount}")
    public ResponseEntity<Object> reduceBalance(@PathVariable String cardNumber, @PathVariable double amount){

        try {
            return creditService.reduceBalance(cardNumber,amount);
        } catch (Exception e) {

            return ResponseEntity.ok("Hesap numarası yanlış");

        }

    }

    @PostMapping("/payDebtFromAccount/{cardNumber}/{amount}/{accountNumber}")
    public ResponseEntity<Object> payDebtFromAccount(@PathVariable String cardNumber,@PathVariable double amount,@PathVariable String accountNumber){

        try {
            return creditService.payDebtFromAccount(cardNumber,amount,accountNumber);
        } catch (Exception e) {

            return ResponseEntity.ok("Hesap numarası yanlış");
        }

    }

    @PostMapping("/payDebtFromDebit/{debitCardNumber}/{amount}/{creditCardNumber}")
    public ResponseEntity<Object> debtFromDebit(@PathVariable String debitCardNumber,@PathVariable double amount,@PathVariable String creditCardNumber){

        try {
            return creditService.debtFromDebit(debitCardNumber,amount,creditCardNumber);
        } catch (Exception e) {

            return ResponseEntity.ok("Hesap numarası yanlış");

        }
    }

    @GetMapping("/getStates/{cardNumber}")
    public List<Transaction> getStates(@PathVariable String cardNumber){ return creditService.getStates(cardNumber); }

    @GetMapping("/getDebt/{cardNumber}")
    public ResponseEntity<Object> getDebt(String cardNumber){
        return creditService.getDebt(cardNumber);
    }
}
