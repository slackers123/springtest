package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String message_text;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
