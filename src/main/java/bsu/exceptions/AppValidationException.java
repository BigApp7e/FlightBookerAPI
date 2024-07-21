package bsu.exceptions;

public class AppValidationException extends RuntimeException{
    public AppValidationException(String message){
        super(message);
    }
}
