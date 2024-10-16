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
    Email email;
    String name;
    @Enumerated(EnumType.STRING)
    Role role;
    @Embedded
    Address address;
}
