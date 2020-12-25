package pl.sda.librarymanagementapp.exceptions;

public class BadBoundaryException extends RuntimeException{
    public  BadBoundaryException (String message){
        super("BAd Request:" + message);
    }
}
