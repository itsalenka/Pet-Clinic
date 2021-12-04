package by.tukai.spring_lr2.rest;

import by.tukai.spring_lr2.dto.TokenResponseDto;
import by.tukai.spring_lr2.dto.UserAuthDto;
import by.tukai.spring_lr2.exceptions.AuthenticationException;
import by.tukai.spring_lr2.model.User;
import by.tukai.spring_lr2.repository.UserRep;
import by.tukai.spring_lr2.security.jwt.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final UserRep userRep;

    private final TokenProvider tokenProvider;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, UserRep userRep, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRep = userRep;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAuthDto userAuthDto) throws AuthenticationException {
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        try {
            final String username = userAuthDto.getUsername();
            final String password = userAuthDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenProvider.createToken(username);
            User user = userRep.findByUsername(username);
            tokenResponseDto.setUsername(username);
            tokenResponseDto.setRole(user.getRoles().toString());
            tokenResponseDto.setToken(token);
            System.out.println(tokenResponseDto.toString());
        } catch (Exception e) {
            log.error(e.toString());
            tokenResponseDto.setError("Invalid username or password");
        } finally {
            return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
        }
    }
}