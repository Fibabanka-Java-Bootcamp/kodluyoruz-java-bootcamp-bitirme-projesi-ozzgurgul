package org.kodluyoruz.mybank.Customer;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;
    private String name;
    private String surname;


    public Customer toCustomer() {
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .build();
    }
}

