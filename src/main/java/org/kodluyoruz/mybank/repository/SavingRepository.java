package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<SavingAccount,Long> {

    SavingAccount findByAccountNumber(String accountNumber);
    SavingAccount findByIbanNo(String iban);
}
