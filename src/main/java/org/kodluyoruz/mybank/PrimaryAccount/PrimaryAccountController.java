package org.kodluyoruz.mybank.PrimaryAccount;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/customer/account/primaryaccount")
public class PrimaryAccountController {

 private final PrimaryAccountService primaryAccountService;

    public PrimaryAccountController(PrimaryAccountService primaryAccountService) {
        this.primaryAccountService = primaryAccountService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrimaryAccountDto create(@RequestBody PrimaryAccountDto primaryAccountDto){

        return primaryAccountService.create(primaryAccountDto.toPrimaryAccount()).toPrimaryAccountDto();

    }

    @GetMapping("/{id}")
    public PrimaryAccountDto get(@PathVariable("id") Long id) {
        return primaryAccountService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toPrimaryAccountDto();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        primaryAccountService.delete(id);
        //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toCustomerDto();
    }
}
