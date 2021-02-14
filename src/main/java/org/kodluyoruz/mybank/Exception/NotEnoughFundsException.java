package org.kodluyoruz.mybank.Exception;

public class NotEnoughFundsException  extends IllegalArgumentException{
    public NotEnoughFundsException(Long id) {
        super( " id:" +id + " numaralı hesapta işleminiz için yeterli bakiye bulunmamaktadır.");
    }
}
