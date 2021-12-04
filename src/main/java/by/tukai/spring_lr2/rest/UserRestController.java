package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.dto.UserAboutDto;
import by.tukai.spring_lr2.mapping.UserMapper;
import by.tukai.spring_lr2.repository.UserRep;
import by.tukai.spring_lr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRep userRep;

    @Autowired
    public UserRestController(UserService userService, UserMapper userMapper, UserRep userRep) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userRep = userRep;
    }

    @GetMapping("/about")
    public ResponseEntity getUser(Principal principal){
        UserAboutDto user = userMapper.toUserAboutDto(userService.findByUsername(principal.getName()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity save(@Valid @RequestBody UserAboutDto userAboutDto){
            userService.update(userAboutDto);
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }
}
