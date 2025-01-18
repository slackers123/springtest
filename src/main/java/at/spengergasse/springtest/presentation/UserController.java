package at.spengergasse.springtest.presentation;

import at.spengergasse.springtest.domain.User;
import at.spengergasse.springtest.presentation.dto.UserDto;
import at.spengergasse.springtest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.springtest.presentation.APIBase.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserController.BASE_ROUTE)
public class UserController {

    protected static final String BASE_ROUTE = API + "/user";
    private final UserService service;
    private final ModelMapper modelMapper = new ModelMapper();


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllChats() {

        List<UserDto> returnValue = modelMapper.map(service.fetchAll(), new TypeToken<List<UserDto>>() {
        }.getType());

        return returnValue.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(returnValue);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        User new_user = service.saveUser(user);

        UserDto returnValue = modelMapper.map(new_user, UserDto.class);

        return (returnValue == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(returnValue);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<UserDto> deleteUser(@RequestParam Long userId) {
        Optional<User> toDelete = service.findUserById(userId);

        if (toDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            User user = toDelete.get();
            service.deleteUserById(userId);
            return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
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
