package DataAccessLayer.DAO;

import DataAccessLayer.Models.StudentProfileModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentProfileDao {
    protected static final Logger LOGGER= Logger.getLogger(StudentProfileDao.class.getName());
    private static  final String insertStatementString="INSERT INTO StudentProfile (StudentId)"
            + " VALUES (?)";

    private final static String findStatementString = "SELECT * FROM StudentProfile where StudentId = ?";

    private final static String selectAllStatementString="SELECT * FROM StudentProfile";

    public static ArrayList<StudentProfileModel> GetAllStudentProfiles()
    {
        ArrayList<StudentProfileModel> profiles = new ArrayList<StudentProfileModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                StudentProfileModel profile=new StudentProfileModel(rs.getInt("StudentId"));
                profiles.add(profile);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentProfileDao:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return profiles;
    }
    public static StudentProfileModel findById(int StudentId)
    {
        StudentProfileModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1,StudentId);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new StudentProfileModel(rs.getInt("StudentId"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"StudentProfileDao:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void insert(int StudentId) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,StudentId);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "StudentProfileDao:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
