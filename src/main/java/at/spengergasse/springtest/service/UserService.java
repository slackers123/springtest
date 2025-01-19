package at.spengergasse.springtest.service;

import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Role;
import at.spengergasse.springtest.domain.User;
import at.spengergasse.springtest.domain.persistence.UserRepository;
import at.spengergasse.springtest.presentation.commands.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //final and not null are required args
@Service
@Transactional
public class UserService {
    private final UserRepository repository; //final means you cannot modify it after

    //
    @Transactional(readOnly = true)
    public List<User> fetchAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) { return repository.findById(id); }

    @Transactional(readOnly = true)
    public List<User> findUserByEmail(Email email) { return repository.findByEmail(email); }

    @Transactional(readOnly = true)
    public List<User> findUserByName(String name) { return repository.findByName(name); }

    public User createUser(CreateUserCommand cmd) {
        System.out.println(cmd.toString());
        User user = User.builder()
                .email(cmd.email())
                .name(cmd.name())
                .role(Role.USER)
                .address(cmd.address())
                .build();

        return repository.save(user);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
