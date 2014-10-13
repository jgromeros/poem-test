package co.jg.exceptions;

/**
 * Application exception
 * @author Juan Gabriel Romero
 */
public class RuleException extends RuntimeException {

    private static final long serialVersionUID = 4477155069911769880L;

    /**
     * Constructor. Sets the message
     * @param message
     */
    public RuleException(String message) {
        super(message);
    }

    /**
     * Constructor. Sets the message
     * @param message
     */
    public RuleException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
