package at.spengergasse.springtest.service;

import at.spengergasse.springtest.domain.*;
import at.spengergasse.springtest.domain.persistence.UserRepository;
import at.spengergasse.springtest.presentation.commands.CreateUserCommand;
import org.h2.command.ddl.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

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
                .role(Role.USER)
                .email(new Email("maxim@gmail.com"))
                .address(new Address("spengergasse", "wien", "1050"))
                .build();

        CreateUserCommand cmd = new CreateUserCommand(new Email("maxim@gmail.com"), "Maxim", new Address("spengergasse", "wien", "1050"));

        when(userRepository.save(user)).thenReturn(user);

        User new_user = userService.createUser(cmd);

        assertNotNull(new_user);
        verify(userRepository,times(1)).save(user);

        assertEquals(new_user, user);
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