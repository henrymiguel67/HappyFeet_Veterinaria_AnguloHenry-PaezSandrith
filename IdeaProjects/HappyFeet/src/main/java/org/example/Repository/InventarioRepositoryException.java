package org.example.Repository;

public class InventarioRepositoryException extends RuntimeException {
    
    public InventarioRepositoryException(String message) {
        super(message);
    }
    
    public InventarioRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InventarioRepositoryException(Throwable cause) {
        super(cause);
    }
}