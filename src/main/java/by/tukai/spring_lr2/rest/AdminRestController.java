package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.mapping.UserMapper;
import by.tukai.spring_lr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public AdminRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

//    @GetMapping(value = "users/{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id")Long id){
//        User user= userService.findById(id);
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//       UserDto userDto = userMapper.toDto(user);
//
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
}
