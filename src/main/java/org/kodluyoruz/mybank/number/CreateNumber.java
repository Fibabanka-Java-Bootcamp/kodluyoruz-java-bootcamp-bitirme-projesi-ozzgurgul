package org.kodluyoruz.mybank.number;

import java.util.Random;

public class CreateNumber {

    public String createNumber(){

        Random random = new Random();
        String number = "";

        for(int i=0;i<15;i++)
            number+=String.valueOf(random.nextInt(9));


        return  number;
    }

    public String iban(String accountNumber){

        Random random = new Random();
        String iban="TR";

        for(int i=0;i<10;i++)
            iban+=String.valueOf(random.nextInt(9));

        iban+=accountNumber;

        return iban;

    }

}

