package dev.pans.React_Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptiom  extends RuntimeException{

    public ResourceNotFoundExceptiom(String message) {
        super(message);
    }
}
