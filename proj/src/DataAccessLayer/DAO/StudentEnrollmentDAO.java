package DataAccessLayer.DAO;

import DataAccessLayer.Models.StudentEnrollmentModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentEnrollmentDAO {
    protected static final Logger LOGGER= Logger.getLogger(StudentEnrollmentDAO.class.getName());
    private static  final String addStudentToGroupStatementString="INSERT INTO StudentEnrollment (StudentId,GroupId)"
            + " VALUES (?,?)";


    private final static String GetGroupsForStudentStatementString="SELECT * FROM StudentEnrollment WHERE StudentId =?";


    public static ArrayList<StudentEnrollmentModel> GetAllGroupsForStudent(int StudentId)
    {
        ArrayList<StudentEnrollmentModel> enrollments = new ArrayList<StudentEnrollmentModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(GetGroupsForStudentStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, StudentId);

            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                StudentEnrollmentModel enrollment=new StudentEnrollmentModel(rs.getInt("StudentId"),rs.getInt("GroupId"));
                enrollments.add(enrollment);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentEnrollmentDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return enrollments;
    }

    public static void AddStudentToGroup(int StudentId, int GroupId ) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(addStudentToGroupStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, StudentId);
            insertStatement.setInt(2, GroupId);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "StudentEnrollmentDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
