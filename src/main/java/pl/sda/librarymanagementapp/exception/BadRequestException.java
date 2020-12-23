package pl.sda.librarymanagementapp.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super("BAD REQUEST: "+ message);
    }
}
