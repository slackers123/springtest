package at.spengergasse.springtest.service;

import at.spengergasse.springtest.domain.User;
import at.spengergasse.springtest.domain.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository repository;



    @Transactional(readOnly = true)
    public List<User> fetchAll() {
        return repository.findAll();
    }
}
