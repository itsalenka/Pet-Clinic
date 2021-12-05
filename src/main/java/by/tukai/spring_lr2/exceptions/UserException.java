package by.tukai.spring_lr2.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception{
    public UserException(String mes){ super(mes);}

}
