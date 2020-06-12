package webd4201.pateldev;


/**
 * This is a student class which inherits methods and attributes from user
 * class. This also returns the details of student such as name, Id number,
 * current year and enrolled year.
 *
 * @author Devansh Patel
 * @version 1.0 (2020/1/21)
 * @since 1.0
 */

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student extends User {
	/**
	 * Stores a constant for program code
	 */
	public static final String DEFAULT_PROGRAM_CODE = "UNDC";
	/**
	 * Stores a constant for description
	 */
	public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
	/**
	 * Stores default constant for program description
	 */
	public static final int DEFAULT_YEAR = 1;

	/**
	 * initializes the connection to the database
	 * 
	 * @param c
	 */
	public static void initialize(Connection c) {
		StudentDA.initialize(c);
	}

	/**
	 * Retrieves the number of records with the key
	 * 
	 * @param key
	 * @return
	 * @throws NotFoundException
	 */
	public static Student retrieve(long key) throws NotFoundException {
            try {
                return StudentDA.retrieve(key);
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
	}

	/**
	 * Terminates the connection
	 */
	public static void terminate() {
		StudentDA.terminate();
	}

    Student(long id, String password, String firstName, String lastName, String emailAddress, java.sql.Date enrolDate, java.sql.Date lastAccess, char type, boolean enabled, String programCode, String programDescription, int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	/**
	 * Creates a new student object
	 * @return 
	 * 
	 * @throws DuplicateException
	 * @throws InvalidNameException 
	 * @throws InvalidIdException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SQLException 
	 */
	public boolean create() throws DuplicateException, NoSuchAlgorithmException, InvalidIdException, InvalidNameException, SQLException {
		return StudentDA.create(this);
	}

	/**
	 * Deletes a student object
	 * 
	 * @throws NotFoundException
	 */
	public int delete() throws NotFoundException {
            try {
                return StudentDA.delete(this);
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
	}

	/**
	 * updates an existing student object
	 * 
	 * @throws NotFoundException
	 */
	public int update() throws NotFoundException {
            try {
                return StudentDA.update(this);
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
	}
	/**
	 * Authenticates a user record with matching id and password
	 * @param id
	 * @param password
	 * @return 
	 * @throws NotFoundException
	 * @throws InvalidNameException
	 * @throws InvalidIdException
	 * @throws NoSuchAlgorithmException 
	 * @throws SQLException 
	 */
	public static Student authenticate(long id, String password) throws NotFoundException, InvalidNameException, InvalidIdException, NoSuchAlgorithmException, SQLException, InvalidUserDataException {
		return StudentDA.authenticate(id, password);
	}
	/**
	 * Links to the change password function in userDA
	 * @param id
	 * @param password
	 * @return
	 * @throws NotFoundException
	 */
	public static int changePassword(long id, String password) throws NotFoundException {
		return StudentDA.changePassword(id, password);
	}
	/**
	 * A variable that holds the code
	 */
	private String programCode;
	/**
	 * A program that holds the program description that is attached to the code
	 */
	private String programDescription;
	/**
	 * A variable that holds the current year that the student is in
	 */
	private int year;
	/**
	 * A vector that will be used to hold the students grades
	 */
	private Vector<Mark> marks;

	/**
	 * Accesses the class variable programCode
	 * 
	 * @return programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * mutates the programCode class variable
	 * 
	 * @param programCode
	 */
	public final void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	/**
	 * Accesses the program description class variable
	 * 
	 * @return programDescription
	 */
	public String getProgramDescription() {
		return programDescription;
	}

	/**
	 * mutates the programDescription variable
	 * 
	 * @param programDescription
	 */
	public final void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	/**
	 * accesses the class variable year to get the year the student is in for school
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * mutates the year class variable
	 * 
	 * @param year
	 */
	public final void setYear(int year) {
		this.year = year;
	}

	/**
	 * An accesor that is able to retrieve a vector that store the marks of a
	 * student
	 * 
	 * @return marks
	 */
	public Vector<Mark> getMarks() {
		return marks;
	}

	/**
	 * A mutator so that we can manipulate the vector of marks
	 * 
	 * @param marks
	 */
	public final void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}

	/**
	 * The Param Constructor for a student that sets every student attribute
	 * 
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param programCode
	 * @param programDescription
	 * @param year
	 * @param marks
	 * @throws InvalidIdException
	 * @throws InvalidNameException
	 * @throws NoSuchAlgorithmException 
	 */
	public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year,
			Vector<Mark> marks) throws InvalidIdException, InvalidNameException, NoSuchAlgorithmException {
		super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		setProgramCode(programCode);
		setProgramDescription(programDescription);
		setYear(year);
		setMarks(marks);
	}

	/**
	 * An overloaded constructor that will take every student attribute except for
	 * the vector of grades
	 * 
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAcess
	 * @param enrollDate
	 * @param enabled
	 * @param type
	 * @param programCode
	 * @param programDescription
	 * @param year
	 * @throws InvalidNameException
	 * @throws InvalidIdException
	 * @throws NoSuchAlgorithmException 
	 */
	public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAcess,
			Date enrollDate, boolean enabled, char type, String programCode, String programDescription, int year)
			throws InvalidNameException, InvalidIdException, NoSuchAlgorithmException {
		this(id, password, firstName, lastName, emailAddress, lastAcess, enrollDate, enabled, type, programCode,
				programDescription, year, new Vector<Mark>());

	}

	/**
	 * Default constructor setting a student to the default variable constants
	 * 
	 * @throws InvalidNameException
	 * @throws InvalidIdException
	 * @throws NoSuchAlgorithmException 
	 */
	public Student() throws InvalidNameException, InvalidIdException, NoSuchAlgorithmException {
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(),
				new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION,
				DEFAULT_YEAR);

	}

	/**
	 * Overrides the toString method to output a formatted
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		String suffix = "";

		switch (getYear()) {
		case 1:
			suffix = "st";
			break;
		case 2:
			suffix = "nd";
			break;
		case 3:
			suffix = "rd";
			break;
		case 4:
			suffix = "th";
		default:
			suffix = "";
			break;
		}

		return String.format(
				"\nStudent Info for: " + "\n\t%s %s (%s) " + "\n\tCurrently in %s" + suffix + " year of \"%s\" (%s)"
						+ "\n\tEnrolled: " + DF.format(getEnrolDate()),
				getFirstName(), getLastName(), getId(), getYear(), getProgramDescription(), getProgramCode());

	}

    boolean getEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
