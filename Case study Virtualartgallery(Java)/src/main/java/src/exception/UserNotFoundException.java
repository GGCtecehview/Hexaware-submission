package src.exception;
/**
 * Exception thrown when a user cannot be found in the system.
 * This is typically used to indicate that an operation requiring a specific user
 * cannot proceed because the user does not exist or is not recognized.
 */

public class UserNotFoundException extends Exception {
    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for 
     *                later retrieval by the Throwable.getMessage() method.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
