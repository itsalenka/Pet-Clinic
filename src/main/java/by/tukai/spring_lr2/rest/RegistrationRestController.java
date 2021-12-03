package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.dto.UserRegistrDto;
import by.tukai.spring_lr2.exceptions.RegistrationException;
import by.tukai.spring_lr2.mapping.UserMapper;
import by.tukai.spring_lr2.model.User;
import by.tukai.spring_lr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "api/registration")
public class RegistrationRestController {

    private final UserMapper userMapper;
    private final UserService userService;

@Autowired
    public RegistrationRestController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrDto userRegistrDto) throws RegistrationException {
        ResponseDto  responseDto= new ResponseDto();
        try {
            User user = userMapper.toModel(userRegistrDto);
            userService.register(user, "ROLE_USER");
        }catch (Exception e){
            responseDto.setError(e.getMessage());
        } finally {
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }
    }
}
