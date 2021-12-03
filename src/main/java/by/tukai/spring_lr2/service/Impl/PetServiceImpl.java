package by.tukai.spring_lr2.service.Impl;

import by.tukai.spring_lr2.dto.PetOutDto;
import by.tukai.spring_lr2.dto.PetRegistrDto;
import by.tukai.spring_lr2.mapping.PetMapper;
import by.tukai.spring_lr2.model.Pet;
import by.tukai.spring_lr2.model.Status;
import by.tukai.spring_lr2.model.User;
import by.tukai.spring_lr2.repository.PetRep;
import by.tukai.spring_lr2.repository.UserRep;
import by.tukai.spring_lr2.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PetServiceImpl implements PetService {
    private final PetRep petRep;
    private final PetMapper petMapper;
    private final UserRep userRep;

    @Autowired
    public PetServiceImpl(PetRep petRep, PetMapper petMapper, UserRep userRep) {
        this.petRep = petRep;
        this.petMapper = petMapper;
        this.userRep = userRep;
    }

    @Override
    public void add(PetRegistrDto petRegistrDto) {
        Pet pet = petMapper.toModel(petRegistrDto);
        Date date = new Date();
        pet.setCreated(date);
        pet.setUpdated(date);
        pet.setStatus(Status.ACTIVE);
        petRep.save(pet);
    }

    @Override
    public List<PetRegistrDto> getPets(Long id) {
        List<Pet> list = petRep.findAllByUser(userRep.findById(id).get());
        List<PetRegistrDto> listp = new ArrayList<>();
        for (Pet p: list) {
            listp.add(petMapper.toPetRegistrDto(p));
        }
        return listp;
    }

    @Override
    public void delete(Long id) {
        petRep.deleteById(id);
    }

    @Override
    public List<PetOutDto> getPetsByFio(String fio) {
        User user = userRep.findByName(fio);
        List<Pet> list = petRep.findAllByUser(user);
        List<PetOutDto> listp = new ArrayList<>();
        for (Pet p: list) {
            System.out.println(p.getId());
            listp.add(petMapper.toPetOutDto(p));
        }
        return listp;
    }

    @Override
    public Pet findById(Long id) {
        return petRep.findById(id).get();
    }


}
