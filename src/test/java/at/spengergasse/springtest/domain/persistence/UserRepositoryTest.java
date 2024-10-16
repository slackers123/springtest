package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Role;
import at.spengergasse.springtest.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User severin = User.builder()
                .name("Severin")
                .role(Role.ADMIN)
                .email(new Email("severin.gebesmair@gmail.com"))
                .address(new Address("Spengergasse", "wien", "1050"))
                .build();
        User maxim = User.builder()
                .name("Maxim")
                .role(Role.USER)
                .email(new Email("maxim.heller@gmail.com"))
                .address(new Address("Bahnhofstraße", "wien", "1010"))
                .build();
        User leo = User.builder()
                .name("Leo")
                .role(Role.GUEST)
                .email(new Email("leo.stanislaus.steiner@gmail.com"))
                .address(new Address("Karlskirche", "wien", "1040"))
                .build();
    }
}