package at.spengergasse.springtest.presentation.dto;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.domain.Role;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private Email email;
    private String name;
    private Role role;
    private Address address;
}
