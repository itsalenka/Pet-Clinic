package by.tukai.spring_lr2.mapping;

import by.tukai.spring_lr2.dto.AppointmentOutDto;
import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.model.Appointment;
import by.tukai.spring_lr2.service.PetService;
import by.tukai.spring_lr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppointmentMapper {
    private final UserService userService;
    private final PetService petService;

    @Autowired
    public AppointmentMapper(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
    }

    public AppointmentOutDto toAppointmentOutDto (Appointment appointment){
        AppointmentOutDto dto = new AppointmentOutDto();
        dto.setId(appointment.getId());
        dto.setComplaints(appointment.getComplaintsC());
        dto.setDiagnosis(appointment.getDiagnosis());
        return dto;
    }

    public Appointment toModel(NewAppointment dto){
        Appointment ap = new Appointment();
        ap.setWeight(Float.parseFloat(dto.getWeight()));
        ap.setTemp(Float.parseFloat(dto.getTemp()));
        ap.setHistory(dto.getHistory());
        ap.setAnamnesis(dto.getAnamnesis());
        ap.setComplaintsC(dto.getComplaints());
        ap.setConditionC(dto.getCondition());
        ap.setDiagnostics(dto.getDiagnostics());
        ap.setDiagnosis(dto.getDiagnosis());
        ap.setPurpose(dto.getPurpose());
        ap.setUser(userService.findById(dto.getIdDoctor()));
        ap.setPet(petService.findById(dto.getIdPet()));
        return ap;
    }
}
