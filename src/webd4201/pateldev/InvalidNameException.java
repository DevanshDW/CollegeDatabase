/**
 * This is a exception class for getting user Names.
 *
 * @author Devansh Patel
 * @version 1.0 (2020/1/21)
 * @since 1.0
 */
package webd4201.pateldev;

    
  /**
     *serializable class so, it does not declare a static final serialVersionUID field type long
     */
    @SuppressWarnings("serial")
    
    /**
     * Specialized exception class
     */
    
public class InvalidNameException extends Exception {
    
     
     /**
     * Parameterized constructor for Exception class and it takes string argument.
     * The argument will help to show error message.
     * @param message
     * 
     */
    public InvalidNameException(String message) {
		super(message);
	}
    /**
     * Overload default constructor and it does not take any arguments
     */
    public InvalidNameException() {
		super();
	}    
    }  
