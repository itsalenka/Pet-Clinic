package by.tukai.spring_lr2.service.Impl;

import by.tukai.spring_lr2.dto.AppointmentInfoDto;
import by.tukai.spring_lr2.dto.AppointmentOutDto;
import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.exceptions.AppointmentException;
import by.tukai.spring_lr2.exceptions.PetException;
import by.tukai.spring_lr2.exceptions.UserException;
import by.tukai.spring_lr2.mapping.AppointmentMapper;
import by.tukai.spring_lr2.model.Appointment;
import by.tukai.spring_lr2.model.Pet;
import by.tukai.spring_lr2.model.Status;
import by.tukai.spring_lr2.model.User;
import by.tukai.spring_lr2.repository.AppointmentRep;
import by.tukai.spring_lr2.repository.PetRep;
import by.tukai.spring_lr2.service.AppointmentService;
import by.tukai.spring_lr2.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final PetService petService;
    private final AppointmentRep appointmentRep;
    private final AppointmentMapper appointmentMapper;
    public final JavaMailSender emailSender;

    @Autowired
    public AppointmentServiceImpl(PetService petService, PetRep petRep, PetService petService1, AppointmentRep appointmentRep, AppointmentMapper appointmentMapper, JavaMailSender emailSender) {
        this.petService = petService1;
        this.appointmentRep = appointmentRep;
        this.appointmentMapper = appointmentMapper;
        this.emailSender = emailSender;
    }

    @Override
    public List<AppointmentOutDto> getAppointments(Long id, int sort) throws PetException {
        Pet pet =  petService.findById(id);
        List<Appointment> list = findAllByPet(pet);
        if (sort == 2) {
            list.sort(Comparator.comparing(Appointment::getCreated).reversed());
        }
        List<AppointmentOutDto> listD = new ArrayList<>();
        for (Appointment a:list) {
            listD.add(appointmentMapper.toAppointmentOutDto(a));
        }
        return listD;
    }

    @Override
    public void add(NewAppointment ap) throws ParseException, UserException, PetException {
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

        save(nap);
        Pet pet = nap.getPet();
        User user = pet.getUser();
        User doctor = nap.getUser();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setSubject("Pet clinic: new appointment");
        message.setText("Hello. You visited the veterinarian today. Here is an electronic copy of the appointment.\n\n" +
                "\nOwner: " + user.getName() +
                "\nPet's name: " + pet.getName() +
                "\nType: " + pet.getType() +
                "\nBreed: " + pet.getBreed() +
                "\nGender: " + pet.getGender() +
                "\nBday: " + pet.getBday() +
                "\nAge: " + nap.getAge() +
                "\nDoctor: " + doctor.getName() +
                "\nDate: " + nap.getCreated() +
                "\nWeight: " + nap.getWeight() +
                "\nTemperature: " + nap.getTemp() +
                "\nDisease history: " + nap.getHistory() +
                "\nAnamnesis: " + nap.getAnamnesis() +
                "\nComplaints: " + nap.getComplaintsC() +
                "\nCondition at the time of inspection: " + nap.getConditionC() +
                "\nDiagnostic procedures: " + nap.getDiagnostics() +
                "\nPreliminary diagnosis:: " + nap.getDiagnosis() +
                "\nPurpose: " + nap.getPurpose() +
                "\nWe will be glad to see you again in our clinic.");
        this.emailSender.send(message);
    }


    @Override
    public AppointmentInfoDto getInfo(Long id) throws AppointmentException {
        Appointment ap = findById(id);
        return appointmentMapper.toInfoDto(ap);
    }

    @Override
    public List<Appointment> findAllByPet(Pet pet) {
        return appointmentRep.findAllByPet(pet);
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRep.save(appointment);
        log.info("In add - appointment successfully saved");
    }

    @Override
    public void deleteById(Long id) {
        appointmentRep.deleteById(id);
        log.info("In delete - appointment with id: {} successfully deleted", id);
    }

    @Override
    public Appointment findById(Long id) throws AppointmentException {
        Appointment ap = appointmentRep.findById(id).orElse(null);
        if (ap == null) {
            log.warn("In findById - appointment not found with id: " + id);
            throw new AppointmentException("appointment not found with id: " + id);
        }
        return ap;
    }
}
