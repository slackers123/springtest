package at.spengergasse.springtest.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {
    String street;
    String city;
    String zipCode;

    @Override
    public String toString() {
        return street + " " + city + " " + zipCode;
    }
}
