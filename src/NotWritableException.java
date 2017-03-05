/**
 * Exception class for signalling user the file is not currently writable
 * 
 * @version  1.0
 * @author   Robbe Louage & Elias Storme
 * 
 * 
 */
public class NotWritableException extends RuntimeException {
    
    /**
	 * Automatically generated exception ID
	 */
	private static final long serialVersionUID = 7422344796018755413L;

	/**
     * Initialise the not writable exception with given value.
     * 
     * @param   value
     *          The value for the new illegal denominator exception.
     * @post    The value of the new illegal denominator exception is set
     *          to the given value.
     *          | new.getValue() == value
     */
    public NotWritableException() {
	}
}