package by.tukai.spring_lr2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminDto {
    private Long id;

    private String username;

    private String name;

    private String phoneNumber;

    private String email;

    private String roles;
}
