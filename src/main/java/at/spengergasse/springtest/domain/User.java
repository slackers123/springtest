package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Embedded
    public Email email;
    public String name;
    @Enumerated(EnumType.STRING)
    public Role role;
    @Embedded
    public Address address;
}
