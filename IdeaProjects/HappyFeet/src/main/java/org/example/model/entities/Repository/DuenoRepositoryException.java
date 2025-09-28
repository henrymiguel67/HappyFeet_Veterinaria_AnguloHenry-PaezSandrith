package org.example.model.entities.Repository;

public class DuenoRepositoryException extends RuntimeException {

    public DuenoRepositoryException(String message) {
        super(message);
    }

    public DuenoRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
