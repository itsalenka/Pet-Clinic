package by.tukai.spring_lr2.service;

import by.tukai.spring_lr2.dto.AppointmentInfoDto;
import by.tukai.spring_lr2.dto.AppointmentOutDto;
import by.tukai.spring_lr2.dto.NewAppointment;
import by.tukai.spring_lr2.exceptions.AppointmentException;
import by.tukai.spring_lr2.exceptions.PetException;
import by.tukai.spring_lr2.exceptions.UserException;
import by.tukai.spring_lr2.model.Appointment;
import by.tukai.spring_lr2.model.Pet;

import java.text.ParseException;
import java.util.List;

public interface AppointmentService {
    List<AppointmentOutDto> getAppointments(Long id, int sort) throws PetException;

    void add(NewAppointment ap) throws ParseException, UserException, PetException;

    AppointmentInfoDto getInfo (Long id) throws AppointmentException;

    List<Appointment> findAllByPet(Pet pet);

    void save(Appointment appointment);

    void deleteById(Long id);

    Appointment findById(Long id) throws AppointmentException;
}
