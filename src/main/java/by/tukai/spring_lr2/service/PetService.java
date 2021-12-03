package by.tukai.spring_lr2.service;

import by.tukai.spring_lr2.dto.PetOutDto;
import by.tukai.spring_lr2.dto.PetRegistrDto;
import by.tukai.spring_lr2.model.Pet;

import java.util.List;

public interface PetService {
    void add(PetRegistrDto petRegistrDto);
    List<PetRegistrDto> getPets(Long id);
    void delete(Long id);
    List<PetOutDto> getPetsByFio(String fio);
    Pet findById(Long id);
}
