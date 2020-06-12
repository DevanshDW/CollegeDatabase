 /**
 * NotFoundException - this file contains extends the generic Exception class so that
 *                 we have a student Exception, this one will be used to flag when 
 *                 no record was found in the database (and therefore nothing can be
 *                 done to it)
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

public class NotFoundException extends Exception
{
    /**
     * Overload default constructor and it does not take any arguments
     */
	public NotFoundException()
	{ super();}
        
         /**
     * Parameterized constructor for Exception class and it takes string argument.
     * The argument will help to show error message.
     * @param message
     * 
     */
	
	public NotFoundException(String message)
	{ super(message);}
}