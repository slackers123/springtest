package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Role;
import at.spengergasse.springtest.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    private User severinUser;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        severinUser = TestFixtures.severinUser();
    }

    @Test
    public void testGetByName() {
        User original = userRepository.save(severinUser);

        User new_user = userRepository.findByName("Severin");
        assertEquals(new_user, original);
    }

    @Test
    public void testGetByEmail() {
        User original = userRepository.save(severinUser);

        User new_user = userRepository.findByEmail(severinUser.email);

        assertEquals(original, new_user);
    }
}