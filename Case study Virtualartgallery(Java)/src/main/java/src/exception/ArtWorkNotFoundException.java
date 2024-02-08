package src.exception;
/**
 * Exception thrown when an artwork cannot be found in the system.
 * This is typically used to signal that an operation involving a specific artwork
 * cannot be completed because the artwork does not exist or is not available.
 */
public class ArtWorkNotFoundException extends Exception {
    /**
     * Constructs a new ArtWorkNotFoundException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for 
     *                later retrieval by the Throwable.getMessage() method.
     */
    public ArtWorkNotFoundException(String message) {
        super(message);
    }
}
