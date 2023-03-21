package net.javaguides.springboot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class EmailAlreadyExistsException extends RuntimeException{
    private String meggage;
    public EmailAlreadyExistsException(String message){
        super(message);
    }

}
