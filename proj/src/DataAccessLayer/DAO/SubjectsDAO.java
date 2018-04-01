package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.StudentProfileModel;
import BussinessLogicLayer.Models.SubjectsModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubjectsDAO
{
    protected static final Logger LOGGER= Logger.getLogger(SubjectsDAO.class.getName());
    private static  final String insertStatementString="INSERT INTO Subjects (SubjectName,Description)"
            + " VALUES (?,?)";

    private final static String findStatementString = "SELECT * FROM Subjects where Id = ?";

    private final static String selectAllStatementString="SELECT * FROM Subjects";

    public static ArrayList<SubjectsModel> GetAllSubjects()
    {
        ArrayList<SubjectsModel> subjects = new ArrayList<SubjectsModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                SubjectsModel subject=new SubjectsModel(rs.getInt("Id"),
                        rs.getString("SubjectName"),rs.getString("Description"));
                subjects.add(subject);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "SubjectsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return subjects;
    }
    public static SubjectsModel findById(int Id)
    {
        SubjectsModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1,Id);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new SubjectsModel(rs.getInt("Id"),
                    rs.getString("SubjectName"),rs.getString("Description"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"SubjectsDAO:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void insert(String SubjectName,String Description) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,SubjectName);
            insertStatement.setString(2,Description);


            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "SubjectsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
