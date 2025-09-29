package org.example.Repository;

public class FacturaRepositoryException extends RuntimeException {
    
    public FacturaRepositoryException(String message) {
        super(message);
    }
    
    public FacturaRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FacturaRepositoryException(Throwable cause) {
        super(cause);
    }
}