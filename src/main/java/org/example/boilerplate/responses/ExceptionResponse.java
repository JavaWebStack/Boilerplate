package org.example.boilerplate.responses;

public class ExceptionResponse extends ActionResponse {

    public String errorMessage;
    public String exception;

    public ExceptionResponse(Throwable throwable){
        errorMessage = throwable.getMessage();
        exception = throwable.getClass().getName();
    }
}
