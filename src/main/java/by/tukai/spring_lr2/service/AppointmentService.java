package by.tukai.spring_lr2.service;

import by.tukai.spring_lr2.dto.AppointmentOutDto;
import by.tukai.spring_lr2.dto.NewAppointment;

import java.text.ParseException;
import java.util.List;

public interface AppointmentService {
    List<AppointmentOutDto> getAppointments(Long id);
    void add(NewAppointment ap) throws ParseException;
}
