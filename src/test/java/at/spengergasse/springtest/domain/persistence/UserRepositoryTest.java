package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    private User severinUser;
    private Nose serverinNose;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        severinUser = TestFixtures.severinUser();
    }

    @Test
    public void testGetByName() {
        User original = userRepository.save(severinUser);
        List<User> new_user = userRepository.findByName(severinUser.name);
        System.out.println(severinUser.toString());
        assertEquals(new_user.get(0), original);
    }

    @Test
    public void testGetByEmail() {
        User original = userRepository.save(severinUser);
        List<User> new_user = userRepository.findByEmail(severinUser.email);

        assertEquals(original, new_user.get(0));
    }
}