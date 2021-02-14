package org.kodluyoruz.mybank.SavingsAccount;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavingAccountService {

    private final SavingsAccountRepository savingsAccountRepository;

    public SavingAccountService(SavingsAccountRepository savingsAccountRepository) {
        this.savingsAccountRepository = savingsAccountRepository;
    }

public SavingsAccount create(SavingsAccount savingsAccount){

        return savingsAccountRepository.save(savingsAccount);

}
    public Optional<SavingsAccount> get(Long id) {
        return savingsAccountRepository.findById(id);
    }

    public void delete(Long id){

        savingsAccountRepository.deleteById(id);
    }

}
