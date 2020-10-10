package com.crud.tasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyListException extends RuntimeException{

    public EmptyListException() {
        super("Trello returned null body");
    }
}
