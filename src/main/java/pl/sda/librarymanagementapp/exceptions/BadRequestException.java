package pl.sda.librarymanagementapp.exceptions;

public class BadRequestException extends RuntimeException {
    public  BadRequestException (String message){
        super("BAd Request:" + message);
    }

}
