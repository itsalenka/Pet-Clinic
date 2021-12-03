package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.dto.ResponseDto;
import by.tukai.spring_lr2.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/appointment")
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

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody NewAppointment ap){
        try {
            appointmentService.add(ap);
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.OK);
        }
    }
}
