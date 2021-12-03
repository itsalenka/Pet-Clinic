package by.tukai.spring_lr2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserAboutDto {
    private Long id;

    private String username;

    private  String name;

    @Pattern(regexp = "^(.){8,}$", message = "Пароль должен соответствовать паттерну /^(.){8,}$/")
    private String phoneNumber;

    @Email(message = "Не валидный email")
    private String email;
}
