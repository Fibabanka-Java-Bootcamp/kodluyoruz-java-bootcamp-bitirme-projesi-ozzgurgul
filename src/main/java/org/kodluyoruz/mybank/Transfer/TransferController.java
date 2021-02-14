package org.kodluyoruz.mybank.Transfer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    ResponseEntity<?> newTransfer(@RequestBody Transfer transfer) {
        Transfer newTransfer = transferService.doTransfer(transfer);
        return ResponseEntity.ok(newTransfer);
    }
    /*@PostMapping
    public  Transfer doTransfer(@RequestBody Transfer transfer) {
        return transferService.doTransfer(transfer);

    }*/

}
