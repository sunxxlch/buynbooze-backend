package com.buynbooze.CheckoutService.DTO;

import jakarta.persistence.Transient;
import lombok.*;

import java.io.Serializable;

@Data
@Getter @Setter
public class AddressDTO implements Serializable {

    private String first_name;
    private String last_name;

    private Long mobile;
    private String house;
    private String street;
    private String area;
    private String city;
    private int zipcode;
    private String country;
}
