package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Embedded
    public Email email;
    public String name;
    @Enumerated(EnumType.STRING)
    public Role role;
    @Embedded
    public Address address;

    @OneToOne
    @JoinColumn(name = "nose_id")
    public Nose nose;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messages> messages = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", address=" + address +
                ", nose=" + nose +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", email=" + email +
                '}';
    }
}
