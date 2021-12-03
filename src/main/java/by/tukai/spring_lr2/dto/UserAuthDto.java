package by.tukai.spring_lr2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {
    @NotBlank(message = "Имя пользователя должно быть задано")
    private String username;

    @NotBlank(message = "Пароль должен быть задан")
    //@Size(min = 8, max = 16, message = "Пароль должен быть длины от 8 до 16 символов")
    private String password;
}
