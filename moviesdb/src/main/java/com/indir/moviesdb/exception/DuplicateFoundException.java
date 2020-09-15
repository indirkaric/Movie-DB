package com.indir.moviesdb.exception;

public class DuplicateFoundException extends RuntimeException{
    public DuplicateFoundException(String message){
        super(message);
    }
    public DuplicateFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public DuplicateFoundException(Throwable cause){
        super(cause);
    }
}
