package at.spengergasse.springtest.service;

import at.spengergasse.springtest.domain.*;
import at.spengergasse.springtest.domain.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    private @Mock UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        assumeThat(userRepository).isNotNull();
    }

    @Test
    void testSavedUserReturnsSavedUser() {
        User user = User.builder()
                .name("Maxim")
                .role(Role.ADMIN)
                .nose(new Nose())
                .email(new Email("maxim@gmail.com"))
                .address(new Address("spengergasse", "wien", "1050"))
                .build();

        System.out.println(user.getId());

        when(userRepository.save(user)).thenReturn(user);

        User new_user = userService.saveUser(user);

        System.out.println(new_user.getId());

        assertNotNull(new_user);
        verify(userRepository,times(1)).save(user);

        assertEquals(new_user, user);
    }

    @Test
    void testFindUsersByName() {
        String name = "Maxim";

        User user1 = User.builder()
                .name(name)
                .role(Role.ADMIN)
                .nose(new Nose())
                .email(new Email("maxim@gmail.com"))
                .address(new Address("spengergasse", "wien", "1050"))
                .build();

        User user2 = User.builder()
                .name(name)
                .role(Role.ADMIN)
                .nose(new Nose())
                .email(new Email("maxim@gmail.com"))
                .address(new Address("spengergasse", "wien", "1050"))
                .build();

        List<User> users =new ArrayList<User>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findByName(name)).thenReturn(users);

        List<User> usersFound = userService.findUserByName(name);

        assertEquals(2, usersFound.size());

        assertArrayEquals(usersFound.toArray(), users.toArray());
    }
}

/*

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserServiceTest {

    private UserService userService;

    // stub vs mock: A Mock is a placeholder (looks like what you need/polymorphic, does not do anything -> stupid but can be trained for specific purposes, cases e.g. db connection breakdown)
    private @Mock UserRepository userRepository; // everything that is a component/dependency that the class i am testing relies on should be cut and replaced with a mock version

    private @Autowired HttpBinConnector httpBin;


    @BeforeEach
    void setup() {
        assumeThat(userRepository).isNotNull(); // assumption is expressed and if new mock created properly service is created. (precondition)
        userService = new UserService(userRepository, httpBin);

    }

    @Test
    void ensureFetchUsersWithNoProvidedRequestParamsCallsFindAll() {
        // interaction-based testing
        // given
        var firstName = Optional.<String>empty();
        var lastName = Optional.<String>empty();

        var jeff = jeff();
        var shosh = shosh();

        when(userRepository.findAll()).thenReturn(List.of(jeff, shosh));

        //when
        var result = userService.fetchUsersLike(firstName, lastName);

        //then
        assertThat(result).containsExactlyInAnyOrder(jeff, shosh);

        verify(userRepository, times(1)).findAll(); // 1 invokation by default -> can be omitted
        // verify that on the repo I once called findAll()
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void ensureFetchUsersWithProvidedRequestParamsCallsFindByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase() {
        // interaction-based testing
        // given
        var firstName = "je%";
        var lastName = "blo%";

        var jeff = jeff();
        var shosh = shosh();

        when(userRepository.findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(firstName, lastName)).thenReturn(List.of(jeff));

        //when
        var result = userService.fetchUsersLike(Optional.of(firstName), Optional.of(lastName));

        //then
        assertThat(result).containsExactlyInAnyOrder(jeff);

        verify(userRepository, times(1)).findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(any(), any()); // 1 invokation by default -> can be omitted
        // verify that on the repo I once called findAll()
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void ensureFetchUsersWithProvidedRequestParamsCallsFindAllByFirstNameLikeIgnoreCase() {
        // interaction-based testing
        // given
        var firstName = "je%";
        var lastName = Optional.<String>empty();
        var jeff = jeff();
        var shosh = shosh();

        when(userRepository.findAllByFirstNameLikeIgnoreCase(firstName)).thenReturn(List.of(jeff));

        //when
        var result = userService.fetchUsersLike(Optional.of(firstName), lastName);

        //then
        assertThat(result).containsExactlyInAnyOrder(jeff);

        verify(userRepository, times(1)).findAllByFirstNameLikeIgnoreCase(any()); // 1 invokation by default -> can be omitted
        // verify that on the repo I once called findAll()
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void ensureFetchUsersWithProvidedRequestParamsCallsFindAllByLastNameLikeIgnoreCase() {
        // interaction-based testing
        // given
        var firstName = Optional.<String>empty();
        var lastName = "jo%";
        var jeff = jeff();
        var shosh = shosh();

        when(userRepository.findAllByLastNameLikeIgnoreCase(lastName)).thenReturn(List.of(jeff));

        //when
        var result = userService.fetchUsersLike(firstName, Optional.of(lastName));

        //then
        assertThat(result).containsExactlyInAnyOrder(jeff);

        verify(userRepository, times(1)).findAllByLastNameLikeIgnoreCase(any()); // 1 invokation by default -> can be omitted
        // verify that on the repo I once called findAll()
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testDelete() {

        //given
        var jeff = jeff();

        // Call the method under test
        userService.delete(jeff);

        // Verify that delete was called with the expected user
        verify(userRepository, times(1)).delete(eq(jeff));
    }

}

*/