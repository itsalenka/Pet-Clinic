package by.tukai.spring_lr2.service;

import by.tukai.spring_lr2.dto.UserAboutDto;
import by.tukai.spring_lr2.exceptions.RegistrationException;
import by.tukai.spring_lr2.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void register(User user, String role) throws RegistrationException;

    List<User> getAll();

    List<String> getAllFio();

    boolean findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);

    void update(UserAboutDto userAboutDto);
}
