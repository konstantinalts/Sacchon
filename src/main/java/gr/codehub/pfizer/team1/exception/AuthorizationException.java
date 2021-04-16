package gr.codehub.pfizer.team1.exception;

public class AuthorizationException extends Exception{
    public AuthorizationException(String description){
        super(description);
    }
}