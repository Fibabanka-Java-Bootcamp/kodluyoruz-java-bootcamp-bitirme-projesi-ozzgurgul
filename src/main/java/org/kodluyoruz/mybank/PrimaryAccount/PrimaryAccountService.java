package org.kodluyoruz.mybank.PrimaryAccount;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PrimaryAccountService {

    private final PrimaryAccountRepository primaryAccountRepository;


    public PrimaryAccountService(PrimaryAccountRepository primaryAccountRepository) {
        this.primaryAccountRepository = primaryAccountRepository;
    }

    public PrimaryAccount create(PrimaryAccount primaryAccount){

        return primaryAccountRepository.save(primaryAccount);

    }
    public Optional<PrimaryAccount> get(Long id) {
        return primaryAccountRepository.findById(id);
    }

    public void delete(Long id){

      primaryAccountRepository.deleteById(id);
    }
    public PrimaryAccount findById(Long id){
        return primaryAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));

    }
    public  PrimaryAccount save(PrimaryAccount primaryAccount){

        return primaryAccountRepository.save(primaryAccount);
    }

}
