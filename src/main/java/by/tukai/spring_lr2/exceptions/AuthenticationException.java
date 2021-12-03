package by.tukai.spring_lr2.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationException extends Exception{

    public AuthenticationException(String mes){ super(mes);}
}
