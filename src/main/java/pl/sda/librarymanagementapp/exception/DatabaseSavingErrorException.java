package pl.sda.librarymanagementapp.exception;

public class DatabaseSavingErrorException extends RuntimeException{
    public DatabaseSavingErrorException(String message){
        super("Database error: " + message);
    }
}
