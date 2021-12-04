package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.PetRegistrDto;
import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.dto.UserAdminDto;
import by.tukai.spring_lr2.dto.UserRegistrDto;
import by.tukai.spring_lr2.mapping.UserMapper;
import by.tukai.spring_lr2.model.User;
import by.tukai.spring_lr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserRegistrDto user){
        ResponseDto responseDto= new ResponseDto();
        try {
            userService.register(user, "ROLE_DOCTOR");
        }catch (Exception e){
            responseDto.setError(e.getMessage());
        } finally {
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }
    }

    @GetMapping("/users")
    public ResponseEntity users(){
        try {
            List<UserAdminDto> list = userService.users();
            if(list.size() == 0)
                return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.OK);
        }
    }
}
