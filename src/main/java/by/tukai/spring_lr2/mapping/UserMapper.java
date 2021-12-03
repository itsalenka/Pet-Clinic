package by.tukai.spring_lr2.mapping;

import by.tukai.spring_lr2.dto.UserAboutDto;
import by.tukai.spring_lr2.dto.UserAuthDto;
import by.tukai.spring_lr2.dto.UserDto;
import by.tukai.spring_lr2.dto.UserRegistrDto;
import by.tukai.spring_lr2.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toModel(UserRegistrDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public User toModel(UserDto userDto){
       User user = new User();
       user.setEmail(userDto.getEmail());
       return user;
    }

    public User toModel(UserAuthDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserAboutDto toUserAboutDto(User user){
        UserAboutDto userAboutDto = new UserAboutDto();
        userAboutDto.setUsername(user.getUsername());
        userAboutDto.setName(user.getName());
        userAboutDto.setPhoneNumber(user.getPhoneNumber());
        userAboutDto.setEmail(user.getEmail());
        userAboutDto.setId(user.getId());
        return userAboutDto;
    }

}
