package by.tukai.spring_lr2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDto {
    private String username;
    private String role;
    private String token;
    private String error;
}