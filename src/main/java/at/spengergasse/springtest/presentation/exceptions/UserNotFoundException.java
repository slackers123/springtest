package at.spengergasse.springtest.presentation.exceptions;

public class UserNotFoundException extends RuntimeException {
    public Long id;
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
        this.id = id;
    }
}
