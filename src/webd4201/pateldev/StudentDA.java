package webd4201.pateldev;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
/**
 * StudentDA - this file is contains all of the data access methods, that
 * actually get/set data to the database. It also includes all the CRUD methods for the student
 *
 * @author Devansh Patel
 * @version 1.0 (05 February 2020)
 * @since 1.0
 */

import java.util.Vector;
import java.sql.*;

/**
 *
 * @author Devansh
 */
public class StudentDA {
    
    
    /**
     * To format date
     */
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");
    static boolean psGetGrades;
    
    private static java.sql.Date getSqlDate(java.util.Date upDate){
        java.sql.Date aDate = new java.sql.Date(upDate.getTime());
        return aDate;
    }
    
    static Vector<Student> student = new Vector<Student>();	// contains Student references
    static Student aStudent;

    // declare variables for the database connection
    static Connection aConnection;
    static Statement aStatement;
    

    // declare static variables for all Student instance attribute values
    static long id;
    static String password;
    static String firstName;
    static String lastName;
    static String emailAddress;
    static java.sql.Date lastAccess;
    static java.sql.Date enrolDate;
    static boolean enabled;
    static char type;
    static String programCode;
    static String programDescription;
    static int year;
    
  //  static String 

    /**
     * Function to create hashing
     * @param aPswd
     * @return 
     */
    public static String hashPswd(String aPswd){
        
        String hashedPswd = null;
        
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(aPswd.getBytes());
            byte[] bytesForHashingString = md.digest();
            
            StringBuilder sb = new StringBuilder();
            
            // loop through the given values
            for (int i=0; i< bytesForHashingString.length; i++)
                
            {
                //Format the string
                String.format("%02x",Integer.toHexString(bytesForHashingString[i]));
                
                //append the string format
                sb.append(String.format("%02x", Integer.toHexString(bytesForHashingString[i])));
                
            }
            
            
            hashedPswd = sb.toString();
            return hashedPswd;
        }
        catch(NoSuchAlgorithmException eror){
            
            //printing the errors
            System.out.println(eror);
        }
        
        return hashedPswd;  //return the hashed password
    }
    
    
    
    
    /**
     * To establish the database connection
     * @param c 
     */
    public static void initialize(Connection c) {
        try {
            aConnection = c;
            aStatement = aConnection.createStatement();
        } catch (SQLException e) {
           
            System.out.println(e);
        }
    }

    /**
     * To close the database connection
     */
    public static void terminate() {
        try { 	// close the statement
            aStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    
    
    /**
     * The method retrieves the record of the student
     * @param aId
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    
    public static Student retrieve(long aId) throws NotFoundException, SQLException { // retrieve Customer and Boat data
        aStudent = null;
        // define the SQL query statement using the phone number key
        
        //System.out.println(sqlQuery);
        
        
        // execute the SQL query statement
        PreparedStatement st = aConnection.prepareStatement("SELECT Users.Id, Password, FirstName, LastName, EmailAddress, LastAccess"
                + "EnrolDate, Enabled, Type, ProgramCode, ProgramDescription, Year"
                + "FROM Users, Students WHERE Users.id = Students.id AND Users.Id = ?");
        st.setLong(1,id);
        try {
            ResultSet d = st.executeQuery();
            // next method sets cursor & returns true if there is data
            
            if (d.next()) {	// extract the data
                id = d.getLong("Id");
                password = d.getString("Password");
                firstName = d.getString("FirstName");
                lastName = d.getString("LastName");
                emailAddress = d.getString("EmailAddress");
                lastAccess = d.getDate("LastAccess");
                enrolDate = d.getDate("EnrolDate");
                enabled = d.getBoolean("Enabled");
                type = (d.getString("Type")).charAt(0);
                programCode = d.getString("ProgramCode");
                programDescription = d.getString("ProgramDescription");
                year = d.getInt("Year");

                // create student
                aStudent = new Student(id, password, firstName, lastName, emailAddress, enrolDate, lastAccess, type, enabled, programCode, programDescription, year);

            } else // nothing was retrieved
            {
                throw (new NotFoundException("Problem retrieving Student record, Program Code " + aId + " does not exist in the system."));
            }
            d.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return aStudent;
    }
    
    /**
     * The method creates the object of student 
     * @param aStudent
     * @return
     * @throws DuplicateException
     * @throws SQLException 
     */
    public static boolean create(Student aStudent) throws DuplicateException, SQLException {
        boolean inserted = false; //insertion success flag
        // retrieve the Student attribute values
        id = aStudent.getId();              
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = getSqlDate(aStudent.getLastAccess());
       enrolDate = getSqlDate(aStudent.getEnrolDate());
        enabled = aStudent.getEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();

        // create the SQL insert statement using attribute values
        String sqlInsert = "INSERT INTO Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate,Type, Enabled) VALUES (?,?,?,?,?,?,?,?,?);\n INSERT INTO Students (Id, ProgramCode, ProgramDescription, Year) VALUES (?,?,?,?)";
            
        PreparedStatement st = aConnection.prepareStatement(sqlInsert);
            
        st.setLong(1, id);
        st.setString(2, password);
        st.setString(3, firstName);
        st.setString(4, lastName);
        st.setString(5, emailAddress);
        st.setDate(6, lastAccess);
        st.setDate(7, enrolDate);
        st.setString(8, String.valueOf(type));
        st.setBoolean(9, enabled);
        st.setLong(10, id);
        st.setString(11, programCode);
        st.setString(12, programDescription);
        st.setInt(13, year);
        
        // see if this Student already exists in the database
        try {
            retrieve(id);
            throw (new DuplicateException("Problem with creating Student record, Id " + id + " already exists in the system."));
        } // if NotFoundException, add Student to database
        catch (NotFoundException e) {
            try {  // execute the SQL update statement
                inserted = st.execute();
            } catch (SQLException ee) {
                System.out.println(ee);
            }
        }
        return inserted;
    }

    /**
     *
     * @param aStudent
     * @return
     * @throws NotFoundException
     * @throws SQLException
     */
    public static int delete(Student aStudent) throws NotFoundException, SQLException {
        int records = 0;
        // retrieve the phone no (key)+0.

         
        id = aStudent.getId();
        PreparedStatement st = aConnection.prepareStatement("DELETE Sudent WHERE Id=?;\n DELETE User WHERE Id=?");
         
         st.setLong(1, id);
         st.setLong(2, id);
        
        

        // see if this customer already exists in the database
        try {
            Student.retrieve(id);  //used to determine if record exists for the passed Customer
            // if found, execute the SQL update statement
            records = st.executeUpdate();
        } catch (NotFoundException e) {
            throw new NotFoundException("Student with phone number " + year
                    + " cannot be deleted, does not exist.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;
    }
    
    /**
     * The method updates the record of the students.
     * @param aStudent
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    public static int update(Student aStudent) throws NotFoundException, SQLException {
        int records = 0;  //records updated in method
            
        PreparedStatement st = aConnection.prepareStatement("UPDATE Users SET Password= ?, FirstName=?, LastName=?, EnrolDate=?,Type=?,Enabled=? WHERE Id=?; \n UPDATE Students SET Programcode=?, ProgramDescription=?, Year=? WHERE Id=?");
               
        
        st.setString(1, password);
        st.setString(2, firstName);
        st.setString(3, lastName);
        st.setString(4, emailAddress);
        st.setDate(5, lastAccess);
        st.setDate(6, enrolDate);
        st.setString(7, String.valueOf(type));
        st.setBoolean(8, enabled);
        st.setLong(9, id);
        st.setString(10, programCode);
        st.setString(11, programDescription);
        st.setInt(12, year);
        st.setLong(13, id);
        
        
        // retrieve the customer argument attribute values
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = getSqlDate(aStudent.getLastAccess());
        enrolDate = getSqlDate(aStudent.getEnrolDate());
        enabled = aStudent.getEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();

        String sqlU = "Update the programCode=?," + "programDescription=?," 
                + "year=?," + "Where id =?";
        
        //to print details
        System.out.println(sqlU);
        
        
        try {
            Student.retrieve(id);  //determine if there is a Customer recrod to be updated
            // if found, execute the SQL update statement
            records = st.executeUpdate();
        } catch (NotFoundException e) {
            throw new NotFoundException("Student with id  " + id
                    + " cannot be updated, does not exist in the system.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;
    }
    
    /**
     * Authentic method to return student object by confirming
     *  student Id and Password
     * @param aId
     * @param aPswd
     * @return
     * @throws NotFoundException
     * @throws SQLException
     * @throws InvalidUserDataException
     */
    public static Student authenticate(long aId, String aPswd) 
           throws NotFoundException, SQLException, InvalidUserDataException {
        aStudent = null;
        
        PreparedStatement forAuthentication = aConnection.prepareStatement("SELECT Users.Id, Password, FirstName, LastName, EmailAddress, LastAccess"
                + "EnrolDate, Type, Enabled, ProgramCode, ProgramDescription, Year"
                + "FROM Users, Students WHERE Users.Id = Students.Id AND Users.Id = ? AND Users.Password = ?");
        
       try {
           forAuthentication.setLong(1, aId);
           forAuthentication.setString(2, hashPswd(aPswd));
           
            ResultSet d = forAuthentication.executeQuery();
            
            // next method sets cursor & returns true if there is data
            
            if(d.next()){       // extract the data
                id = d.getLong("Id");
                password = d.getString("Password");
                firstName = d.getString("FirstName");
                lastName = d.getString("LastName");
                emailAddress = d.getString("EmailAddress");
                lastAccess = d.getDate("LastAccess");
                enrolDate = d.getDate("EnrolDate");
                enabled = d.getBoolean("Enabled");
                type = (d.getString("Type")).charAt(0);
                programCode = d.getString("ProgramCode");
                programDescription = d.getString("ProgramDescription");
                year = d.getInt("Year");

                     // create student
              
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, enrolDate, lastAccess, type, enabled, programCode, programDescription, year);
                

            } else // nothing was retrieved
            {
                throw (new NotFoundException(" Sorry !!, Password is not found"));
            }
            d.close();
        } catch (SQLException e) {
            
            //print th error
            System.out.println(e);
        }

        return aStudent;
    }   

    static String getProgramDescription(String programCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static int changePassword(long id, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
