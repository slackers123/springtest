package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
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

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    @Builder
    public User(Email email, String name, Role role, Address address, Long id, LocalDateTime createdAt, LocalDateTime updatedAt,Nose nose) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.name = name;
        this.role = role;
        this.address = address;
        this.nose = nose;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", address=" + address +
                ", nose=" + nose +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", email=" + email +
                '}';
    }
}
