package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.StudentsModel;
import DataAccessLayer.Connection.ConnectionFactory;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentsDao {
    protected static final Logger LOGGER= Logger.getLogger(StudentsDao.class.getName());
    private static  final String makeUserStudentStatementString="INSERT INTO Students (UserId,StudentId)"
            + " VALUES (?,?)";


    private final static String GetAllStudentsStatementString="SELECT * FROM Students";


    public static ArrayList<StudentsModel> GetAllStudents()
    {
        ArrayList<StudentsModel> students = new ArrayList<StudentsModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(GetAllStudentsStatementString, Statement.RETURN_GENERATED_KEYS);

            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                    StudentsModel student=new StudentsModel(rs.getString("UserId"), new SimpleIntegerProperty(rs.getInt("StudentId")));
                students.add(student);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentsDao:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return students;
    }

    public static void MakeUserStudent(String UserId, int StudentId ) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(makeUserStudentStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, UserId);
            insertStatement.setInt(2, StudentId);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "StudentsDao:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
