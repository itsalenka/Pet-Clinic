package by.tukai.spring_lr2.security.jwt;

import by.tukai.spring_lr2.model.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TokenProvider {
    String createToken(String username);

    Authentication getAuthentication(String token);

    String getUsername(String token);

    String resolveToken(HttpServletRequest req);

    boolean validateToken(String token);
}
