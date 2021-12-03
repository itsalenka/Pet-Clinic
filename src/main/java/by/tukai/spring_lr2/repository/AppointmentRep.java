package by.tukai.spring_lr2.repository;

import by.tukai.spring_lr2.model.Appointment;
import by.tukai.spring_lr2.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRep extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPet(Pet pet);
}

