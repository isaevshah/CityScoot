package kz.project.moped.infrastructure.persistense.postgresql.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
