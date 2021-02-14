package org.kodluyoruz.mybank.Shopping;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.Card.Card;
import org.kodluyoruz.mybank.Card.CardService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ShoppingService {

    private final ShoppingRepository shoppingRepository;
    private final CardService cardService;

    @Transactional
    public Shopping doShopping(Shopping shopping){

        Card Card = cardService.findById(shopping.getCard().getId());
        Card.setBalance(Card.getBalance().subtract(shopping.getAmount()));
        cardService.save(Card);

        return shoppingRepository.save(shopping);
    }

}
