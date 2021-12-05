package by.tukai.spring_lr2.service;

import by.tukai.spring_lr2.dto.PetOutDto;
import by.tukai.spring_lr2.dto.PetRegistrDto;
import by.tukai.spring_lr2.exceptions.PetException;
import by.tukai.spring_lr2.model.Pet;
import by.tukai.spring_lr2.model.User;

import java.util.List;

public interface PetService {
    void add(PetRegistrDto petRegistrDto);

    List<PetRegistrDto> getPets(Long id) throws Exception;

    List<PetOutDto> getPetsByFio(String fio) throws Exception;

    Pet findById(Long id) throws PetException;

    void save(Pet pet);

    void deleteById(Long id);

    List<Pet> findAllByUser(User user);
}
