package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
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
