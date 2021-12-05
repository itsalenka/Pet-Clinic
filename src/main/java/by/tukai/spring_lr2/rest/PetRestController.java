package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.AppointmentOutDto;
import by.tukai.spring_lr2.dto.PetRegistrDto;
import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.exceptions.PetException;
import by.tukai.spring_lr2.mapping.PetMapper;
import by.tukai.spring_lr2.repository.PetRep;
import by.tukai.spring_lr2.service.AppointmentService;
import by.tukai.spring_lr2.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pet")
public class PetRestController {

    private final PetService petService;
    private final PetMapper petMapper;
    private final PetRep PetRep;
    private final AppointmentService appointmentService;

    @Autowired
    public PetRestController(PetService petService, PetMapper petMapper, PetRep petRep, AppointmentService appointmentService) {
        this.petService = petService;
        this.petMapper = petMapper;
        this.PetRep = petRep;
        this.appointmentService = appointmentService;
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPets(@PathVariable(value = "id") Long id) throws Exception {
        List<PetRegistrDto> list = petService.getPets(id);
            if(list.size() == 0)
                return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addPet(@Valid @RequestBody PetRegistrDto petRegistrDto){
        petService.add(petRegistrDto);
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePet(@PathVariable(value = "id") Long id){
        petService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity petHistory(@PathVariable(value = "id") Long id, @RequestParam(value="sort") int sort) throws PetException {
        List<AppointmentOutDto> list = appointmentService.getAppointments(id, sort);
        if(list.size() == 0)
            return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
