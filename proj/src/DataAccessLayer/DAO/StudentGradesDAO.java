package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.StudentEnrollmentModel;
import BussinessLogicLayer.Models.StudentGradesModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentGradesDAO
{
    protected static final Logger LOGGER= Logger.getLogger(StudentGradesDAO.class.getName());
    private static  final String addGradeForStudentStatementString="INSERT INTO StudentGrades (StudentId,SubjectId,Grade)"
            + " VALUES (?,?,?)";


    private final static String GetGradesForStudentStatementString="SELECT * FROM StudentGrades WHERE StudentId =?";


    public static ArrayList<StudentGradesModel> GetAllGradesForStudent(int StudentId)
    {
        ArrayList<StudentGradesModel> grades = new ArrayList<StudentGradesModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(GetGradesForStudentStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, StudentId);


            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                StudentGradesModel grade=new StudentGradesModel(rs.getInt("StudentId"),rs.getInt("SubjectId"),
                        rs.getFloat("Grade"));
                grades.add(grade);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentGradesDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return grades;
    }

    public static void AddGradeToStudent(int StudentId, int SubjectId,Float Grade ) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(addGradeForStudentStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, StudentId);
            insertStatement.setInt(2, SubjectId);
            insertStatement.setFloat(3, Grade);


            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "StudentGradesDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
