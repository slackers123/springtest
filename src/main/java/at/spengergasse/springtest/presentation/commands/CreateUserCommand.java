package at.spengergasse.springtest.presentation.commands;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Role;

public record CreateUserCommand(
        Email email,
        String name,
        Address address
) {
}
