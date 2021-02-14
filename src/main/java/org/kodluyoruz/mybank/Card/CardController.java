package org.kodluyoruz.mybank.Card;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDto create(@RequestBody CardDto cardDto){

        return cardService.create(cardDto.toCard()).toCardDto();

    }

    @GetMapping("/{id}")
    public CardDto get(@PathVariable("id") Long id){

        return cardService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toCardDto();

    }

}
