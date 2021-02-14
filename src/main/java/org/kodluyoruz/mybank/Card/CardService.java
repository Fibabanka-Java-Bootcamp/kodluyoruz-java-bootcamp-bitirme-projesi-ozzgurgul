package org.kodluyoruz.mybank.Card;


import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.PrimaryAccount.PrimaryAccount;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CardService {

private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card create(Card card){

    return cardRepository.save(card);

}

    public Optional<Card> get(Long id) {
        return cardRepository.findById(id);
    }

    public Card findById(Long id){
        return cardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));

    }
    public  Card save(Card card){

        return cardRepository.save(card);
    }
}
