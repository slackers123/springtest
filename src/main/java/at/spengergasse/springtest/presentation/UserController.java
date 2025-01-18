package at.spengergasse.springtest.presentation;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Role;
import at.spengergasse.springtest.domain.User;
import at.spengergasse.springtest.presentation.assemblers.UserModelAssembler;
import at.spengergasse.springtest.presentation.commands.CreateUserCommand;
import at.spengergasse.springtest.presentation.dto.UserDto;
import at.spengergasse.springtest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
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
    public ResponseEntity<UserDto> deleteUser(@RequestParam Long userId) {
        Optional<User> toDelete = service.findUserById(userId);

        if (toDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            User user = toDelete.get();
            service.deleteUserById(userId);
            return ResponseEntity.ok(assembler.toModel(user));
        }
    }


//    @GetMapping("")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<List<ChatDto>> getAllChats() {
//
//        List<ChatDto> returnValue = modelMapper.map(service.fetchAll(), new TypeToken<List<ChatDto>>() {
//        }.getType());
//
//        return returnValue.isEmpty()
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(returnValue);
//    }
//
//    @GetMapping("like")
//    public ResponseEntity<List<ChatDto>> getChatsLike(@RequestParam Optional<Boolean> isgroup, @RequestParam Optional<String> chatName) {
//        List<ChatDto> returnValue = modelMapper.map(service.fetchChatsLike(isgroup, chatName), new TypeToken<List<ChatDto>>() {
//        }.getType());
//        return (returnValue.isEmpty())
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(returnValue);
//        // if the list is empty noFound is returned, if there are values the returnValue is returned.
//    }
//
//    @GetMapping("by/{key}")
//    public ResponseEntity<ChatDto> getChatByKey(@PathVariable String key) {
//
//        ChatDto returnValue = this.modelMapper.map(service.fetchChatByKey(key), ChatDto.class);
//        return returnValue == null
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(returnValue);
//    }
//
//
//    @GetMapping("by/{key}/users")
//    public ResponseEntity<List<UserDto>> fetchChatUsersByKey(@PathVariable String key) {
//
//        Chat chat = service.fetchChatByKey(key);
//
//        if (chat == null)
//            return ResponseEntity.notFound().build();
//
//        List<UserDto> returnValue = chat
//                .getChat_members()
//                .stream()
//                .map(ChatMember::getUser)
//                .map(u -> this.modelMapper.map(u, UserDto.class)).toList();
//        return ResponseEntity.ok(returnValue);
//    }


//    @PutMapping("by/{key}")
//    public ResponseEntity<ChatDto> updateChatByKey(@PathVariable String key, @RequestBody @Valid CreateChatsCommand chat) {
//        Chat updateChat = service.fetchChatByKey(key);
//
//        if (updateChat == null)
//            return ResponseEntity.notFound().build();
//
//        updateChat.setChatName(chat.group_name());
//        updateChat.setDescription(chat.group_description());
//
//        service.saveChat(updateChat);
//
//        System.out.println(updateChat);
//
//        return ResponseEntity.ok(modelMapper.map(updateChat, ChatDto.class));
//    }

//    @PatchMapping("by/{key}/chatName")
//    public ResponseEntity<ChatDto> updateChatNameByKey(@PathVariable String key, @RequestBody @Valid String chatName) {
//        Chat updateChat = service.fetchChatByKey(key);
//
//        if (updateChat == null)
//            return ResponseEntity.notFound().build();
//
//        updateChat.setChatName(chatName);
//
//        service.saveChat(updateChat);
//
//        return ResponseEntity.ok(modelMapper.map(updateChat, ChatDto.class));
//    }

//    @PatchMapping("by/{key}/description")
//    public ResponseEntity<ChatDto> updateDescriptionByKey(@PathVariable String key, @RequestBody @Valid String description) {
//        Chat updateChat = service.fetchChatByKey(key);
//
//        if (updateChat == null)
//            return ResponseEntity.notFound().build();
//
//        updateChat.setDescription(description);
//
//        service.saveChat(updateChat);
//
//        return ResponseEntity.ok(modelMapper.map(updateChat, UserDto.class));
//    }

//    @DeleteMapping("/{key}")
//    public HttpEntity<Void> deleteUser(@PathVariable String key) {
//
//    }

//    @PostMapping
//    public ResponseEntity<ChatDto> createChat(@RequestBody @Valid CreateUserCommand cmd) {
//
//    }
}
