package by.tukai.spring_lr2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PetRegistrDto {
    private Long id;

    @NotBlank(message = "Name must be specified")
    private String name;

    @NotBlank(message = "Type must be specified")
    private String type;

    @NotBlank(message = "Breed must be specified")
    private String breed;

    @NotBlank(message = "Gender must be specified")
    private String gender;

    @NotBlank(message = "Bday must be specified")
    private String bday;

    private Long idUser;
}
