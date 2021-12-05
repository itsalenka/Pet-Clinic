package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.dto.PetOutDto;
import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.dto.UserAboutDto;
import by.tukai.spring_lr2.exceptions.UserException;
import by.tukai.spring_lr2.mapping.UserMapper;
import by.tukai.spring_lr2.service.AppointmentService;
import by.tukai.spring_lr2.service.PetService;
import by.tukai.spring_lr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/doctor")
public class DoctorRestController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PetService petService;

    @Autowired
    public DoctorRestController(UserService userService, UserMapper userMapper, PetService petService, AppointmentService appointmentService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.petService = petService;
    }

    @GetMapping("/search/{fio}")
    public ResponseEntity getPets(@PathVariable(value = "fio") String fio) throws Exception {
            List<PetOutDto> list = petService.getPetsByFio(fio);
            if(list.size() == 0)
                return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
            System.out.println(list);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/about")
    public ResponseEntity getUser(Principal principal){
        UserAboutDto user = userMapper.toUserAboutDto(userService.findByUsername(principal.getName()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity save(@Valid @RequestBody UserAboutDto userAboutDto) throws UserException {
            userService.update(userAboutDto);
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(){
            List<String> list = userService.getAllFio();
            if (list.size() == 0)
                return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
