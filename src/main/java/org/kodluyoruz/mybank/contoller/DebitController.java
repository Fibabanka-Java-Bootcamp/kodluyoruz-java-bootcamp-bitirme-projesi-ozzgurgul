package org.kodluyoruz.mybank.contoller;

import org.kodluyoruz.mybank.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;

    @PostMapping("/create/{accountNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createDebit(@PathVariable String accountNumber){
        return debitService.createDebit(accountNumber);

    }


}