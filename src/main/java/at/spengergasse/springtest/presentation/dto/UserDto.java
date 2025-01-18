package at.spengergasse.springtest.presentation.dto;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@AllArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {
    final Email email;
    final String name;
    final Role role;
    final Address address;
    final Nose nose;
}
