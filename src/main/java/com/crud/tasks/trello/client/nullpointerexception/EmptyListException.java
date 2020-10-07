package com.crud.tasks.trello.client.nullpointerexception;

public class EmptyListException extends Exception{
    public EmptyListException (final String message){
        super(message);
    }
}
