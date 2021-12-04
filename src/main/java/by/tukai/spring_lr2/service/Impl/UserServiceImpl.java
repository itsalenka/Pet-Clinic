package by.tukai.spring_lr2.service.Impl;

import by.tukai.spring_lr2.dto.UserAboutDto;
import by.tukai.spring_lr2.dto.UserAdminDto;
import by.tukai.spring_lr2.dto.UserRegistrDto;
import by.tukai.spring_lr2.exceptions.RegistrationException;
import by.tukai.spring_lr2.mapping.UserMapper;
import by.tukai.spring_lr2.model.Role;
import by.tukai.spring_lr2.model.Status;
import by.tukai.spring_lr2.model.User;
import by.tukai.spring_lr2.repository.RoleRep;
import by.tukai.spring_lr2.repository.UserRep;
import by.tukai.spring_lr2.service.MailSender;
import by.tukai.spring_lr2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRep userRep;
    private final RoleRep roleRep;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRep userRep, RoleRep roleRep, PasswordEncoder passwordEncoder, MailSender mailSender, UserMapper userMapper) {
        this.userRep = userRep;
        this.roleRep = roleRep;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.userMapper = userMapper;
    }

    @Override
    public void register(UserRegistrDto userRegistrDto, String role) throws RegistrationException {

        User user = userMapper.toModel(userRegistrDto);
        if (userRep.findByUsername(user.getUsername()) != null) {
            throw new RegistrationException("Username is already taken");
        }
        if (userRep.findByEmail(user.getEmail()) != null) {
            throw new RegistrationException("Email is already taken");
        }
        Role roleUser = roleRep.findByName(role);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        userRep.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRep.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public List<String> getAllFio() {
        List<User> result = userRep.findAll();
        List<String> list = new ArrayList<>();
        for (User user: result) {
            if (user.getRoles().contains(roleRep.findByName("ROLE_USER")))
                list.add(user.getName());
        }
        log.info("IN getAllString - {} users found", result.size());
        return list;
    }

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }


    public boolean findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User findByEmail(String email) {
        User result = userRep.findByEmail(email);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRep.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRep.deleteById(id);
        log.info("IN delete - user with id: ${id} successfully deleted");
    }

    @Override
    public void update(UserAboutDto userAboutDto){
        Optional<User> user = userRep.findById(userAboutDto.getId());
        user.get().setPhoneNumber(userAboutDto.getPhoneNumber());
        user.get().setEmail(userAboutDto.getEmail());
        userRep.save(user.get());
    }

    @Override
    public List<UserAdminDto> users() {
        List<User> users =userRep.findAll();
        List<UserAdminDto> usersdto = new ArrayList<>();
        for (User u: users) {
            usersdto.add(userMapper.toUserAdminDto(u));
        }
        return usersdto;
    }

}
