package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.AppointmentInfoDto;
import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.exceptions.AppointmentException;
import by.tukai.spring_lr2.exceptions.PetException;
import by.tukai.spring_lr2.exceptions.UserException;
import by.tukai.spring_lr2.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/api/appointment/")
public class AppointmentRestController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentRestController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping("/add")
    public ModelAndView addAppointment(@RequestParam(value="idu") Long idu, @RequestParam(value="idp") Long idp, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addAppointment");
        NewAppointment ap = new NewAppointment();
        ap.setIdDoctor(idu);
        ap.setIdPet(idp);
        model.addAttribute("ap", ap);
        return modelAndView;
    }

    @PostMapping("/doctor/save")
    public ResponseEntity save(@Valid @RequestBody NewAppointment ap) throws ParseException, UserException, PetException {
        appointmentService.add(ap);
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        appointmentService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/doctor/check")
    public ResponseEntity check(){
        return new ResponseEntity(new ResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable(value = "id") Long id, Model model) throws AppointmentException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("appointment");
        AppointmentInfoDto ap = appointmentService.getInfo(id);
        model.addAttribute("ap", ap);
        return modelAndView;
    }

    @GetMapping("/get/check")
    public ResponseEntity checkGet(){return new ResponseEntity(new ResponseDto(), HttpStatus.OK);}
}
