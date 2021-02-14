package org.kodluyoruz.mybank.Transfer;


import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.Card.Card;
import org.kodluyoruz.mybank.Card.CardService;
import org.kodluyoruz.mybank.Exception.NotEnoughFundsException;
import org.kodluyoruz.mybank.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.PrimaryAccount.PrimaryAccountService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final PrimaryAccountService primaryAccountService;
    private final CardService cardService;

    @Transactional
    public Transfer doTransfer(Transfer transfer) throws NotEnoughFundsException{

        PrimaryAccount accountFrom = primaryAccountService.findById(transfer.getFrom().getId());
        PrimaryAccount accountTo = primaryAccountService.findById(transfer.getTo().getId());
        accountFrom.setAccountBalance(accountFrom.getAccountBalance().subtract(transfer.getAmount()));
        accountTo.setAccountBalance(accountTo.getAccountBalance().add(transfer.getAmount()));
        primaryAccountService.save(accountFrom);
        primaryAccountService.save(accountFrom);
        return transferRepository.save(transfer);
    }


}
