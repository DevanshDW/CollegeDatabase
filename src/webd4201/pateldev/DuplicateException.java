 /**
 * DuplicateException - this file contains extends the generic Exception class so that
 * 			we have a student Exception, this one will be used to flag an attempt
 *                      at a duplicate record in our database
 * @author Devansh Patel
 * @version 1.0 (05 February 2020)
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
public class DuplicateException extends Exception
{
    /**
     * Overload default constructor and it does not take any arguments
     */
    public DuplicateException()
    { super();}
    
    /**
     * Parameterized constructor for Exception class and it takes string argument.
     * The argument will help to show error message.
     * @param message
     * 
     */
    
    public DuplicateException(String message)
    { super(message);}
}