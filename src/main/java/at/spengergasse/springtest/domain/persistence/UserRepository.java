package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

    User findByEmail(Email email);
}