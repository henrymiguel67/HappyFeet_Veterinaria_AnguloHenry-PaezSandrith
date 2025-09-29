package org.example.Repository;

public class MascotaRepositoryException extends RuntimeException {
    public MascotaRepositoryException(String message) {
        super(message);
    }

    public MascotaRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
