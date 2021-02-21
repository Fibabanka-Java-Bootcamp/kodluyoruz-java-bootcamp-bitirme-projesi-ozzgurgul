package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.DepositAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositAccount,Long> {

    DepositAccount findByAccountNumber(String accountNumber);
    DepositAccount findByIbanNo(String iban);

}
