package at.spengergasse.springtest.presentation;

import at.spengergasse.springtest.domain.User;
import at.spengergasse.springtest.presentation.assemblers.UserModelAssembler;
import at.spengergasse.springtest.presentation.commands.CreateUserCommand;
import at.spengergasse.springtest.presentation.dto.UserDto;
import at.spengergasse.springtest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.springtest.presentation.APIBase.API;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserController.BASE_ROUTE)
public class UserController {

    protected static final String BASE_ROUTE = API + "/user";
    private final UserService service;

    private final UserModelAssembler assembler;

    @GetMapping("")
    public CollectionModel<UserDto> getAllUsers() {

        List<User> userList = service.fetchAll();

        CollectionModel<UserDto> result = assembler.toCollectionModel(userList);

        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        result.add(selfLink);

        return result;
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        Optional<User> returnValue = service.findUserById(userId);
        if (returnValue.isPresent()) {
            UserDto userDto = assembler.toModel(returnValue.get());
            return ResponseEntity.ok(userDto);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserCommand cmd) {
        System.out.println(cmd.address().toString());
        User new_user = service.createUser(cmd);

        System.out.println("new_user: " + new_user);

        return ResponseEntity.ok(assembler.toModel(new_user));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId) {
        Optional<User> toDelete = service.findUserById(userId);

        if (toDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            User user = toDelete.get();
            service.deleteUserById(userId);
            return ResponseEntity.ok(assembler.toModel(user));
        }
    }
}
