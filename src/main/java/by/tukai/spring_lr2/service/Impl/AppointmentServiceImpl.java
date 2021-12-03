package by.tukai.spring_lr2.service.Impl;

import by.tukai.spring_lr2.dto.AppointmentOutDto;
import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.mapping.AppointmentMapper;
import by.tukai.spring_lr2.model.Appointment;
import by.tukai.spring_lr2.model.Pet;
import by.tukai.spring_lr2.model.Status;
import by.tukai.spring_lr2.repository.AppointmentRep;
import by.tukai.spring_lr2.repository.PetRep;
import by.tukai.spring_lr2.service.AppointmentService;
import by.tukai.spring_lr2.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final PetRep petRep;
    private final AppointmentRep appointmentRep;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(PetService petService, PetRep petRep, AppointmentRep appointmentRep, AppointmentMapper appointmentMapper) {
        this.petRep = petRep;
        this.appointmentRep = appointmentRep;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentOutDto> getAppointments(Long id) {
        Pet pet =  petRep.findById(id).get();
        System.out.println(pet);
        List<Appointment> list = appointmentRep.findAllByPet(pet);
        List<AppointmentOutDto> listD = new ArrayList<>();
        for (Appointment a:list) {
            listD.add(appointmentMapper.toAppointmentOutDto(a));
        }
        System.out.println(listD);
        return listD;
    }

    @Override
    public void add(NewAppointment ap) throws ParseException {
        Appointment nap = appointmentMapper.toModel(ap);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate d1 = LocalDate .parse(nap.getPet().getBday(), dateFormat);
        LocalDate  now = LocalDate.now();
        Period period = Period.between(d1, now);
        nap.setAge(period.getYears() + "y. " + period.getMonths() + "m.");
        Date date = new Date();
        nap.setCreated(date);
        nap.setUpdated(date);
        nap.setStatus(Status.ACTIVE);
        System.out.println(nap);
        appointmentRep.save(nap);
    }
}
