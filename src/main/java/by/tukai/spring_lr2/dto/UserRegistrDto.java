package by.tukai.spring_lr2.dto;


import by.tukai.spring_lr2.validator.CellPhone;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrDto {

    @Pattern(regexp = "^[\\w-]{3,}[0-9a-zA-Z]$", message = "Username must match the pattern /^[\\w-]{3,30}[0-9a-zA-Z]$/")
    private String username;

    @NotBlank(message = "Fio must be specified")
    private  String name;

    @CellPhone(message = "Invalid phone number")
    private String phoneNumber;

    @Pattern(regexp = "^(.){8,}$", message = "Password must match the pattern /^(.){8,}$/")
    private String password;

    @Email(message = "Invalid email")
    private String email;
}