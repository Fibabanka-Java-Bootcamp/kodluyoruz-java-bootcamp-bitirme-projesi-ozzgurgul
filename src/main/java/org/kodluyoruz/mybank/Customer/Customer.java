package org.kodluyoruz.mybank.Customer;


import lombok.*;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String surname;


    public CustomerDto toCustomerDto() {
        return CustomerDto.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .build();
    }

}
