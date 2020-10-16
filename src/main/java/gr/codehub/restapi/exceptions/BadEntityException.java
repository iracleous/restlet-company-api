package gr.codehub.restapi.exceptions;

public class BadEntityException extends Exception{
    public BadEntityException(String message) {
        super(message);
    }
}
