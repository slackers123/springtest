package at.spengergasse.springtest.presentation.assemblers;

import at.spengergasse.springtest.domain.User;
import at.spengergasse.springtest.presentation.UserController;
import at.spengergasse.springtest.presentation.dto.UserDto;
import io.micrometer.common.lang.NonNullApi;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
public class UserModelAssembler implements RepresentationModelAssembler<User, UserDto> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto toModel(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());

        return userDto;
    }
}
