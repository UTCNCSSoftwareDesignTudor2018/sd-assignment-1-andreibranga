package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.YearOfStudySubjectsModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YearOfStudySubjectsDAO {
    protected static final Logger LOGGER= Logger.getLogger(YearOfStudySubjectsDAO.class.getName());
    private static  final String assignSubjectToYearStatementString="INSERT INTO YearOfStudySubjects (UserId,StudentId)"
            + " VALUES (?,?)";


    private final static String GetAllYearOfStudySubjectsStatementString="SELECT * FROM YearOfStudySubjects";
    private final static String GetAllYearOfStudySubjectsForYearStatementString="SELECT * FROM YearOfStudySubjects WHERE YearOfStudyId = ?";


    public static ArrayList<YearOfStudySubjectsModel> GetAllYearOfStudySubjects()
    {
        ArrayList<YearOfStudySubjectsModel> subjects = new ArrayList<YearOfStudySubjectsModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(GetAllYearOfStudySubjectsForYearStatementString, Statement.RETURN_GENERATED_KEYS);

            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                YearOfStudySubjectsModel subject=new YearOfStudySubjectsModel(rs.getInt("SubjectId"),rs.getInt("YearOfStudyId"));
                subjects.add(subject);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "YearOfStudySubjectsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return subjects;
    }
    public static ArrayList<YearOfStudySubjectsModel> GetAllYearOfStudySubjectsForYear(int yearId)
    {
        ArrayList<YearOfStudySubjectsModel> subjects = new ArrayList<YearOfStudySubjectsModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(GetAllYearOfStudySubjectsForYearStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,yearId);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                YearOfStudySubjectsModel subject=new YearOfStudySubjectsModel(rs.getInt("SubjectId"),rs.getInt("YearOfStudyId"));
                subjects.add(subject);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "YearOfStudySubjectsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return subjects;
    }

    public static void MakeUserStudent(int SubjectId, int YearOfStudyId ) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(assignSubjectToYearStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, SubjectId);
            insertStatement.setInt(2, YearOfStudyId);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "YearOfStudySubjectsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
