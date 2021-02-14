package org.kodluyoruz.mybank.Shopping;


import org.kodluyoruz.mybank.Transfer.Transfer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public Shopping doShopping(@RequestBody Shopping shopping) {
        return shoppingService.doShopping(shopping);

    }


}
