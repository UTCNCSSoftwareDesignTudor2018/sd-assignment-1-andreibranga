package DataAccessLayer.DAO;

import DataAccessLayer.Models.YearOfStudyModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YearOfStudyDAO {
    protected static final Logger LOGGER= Logger.getLogger(YearOfStudyDAO.class.getName());
    private static  final String insertStatementString="INSERT INTO YearOfStudy (YearName,Description)"
            + " VALUES (?,?)";

    private final static String findStatementString = "SELECT * FROM YearOfStudy where Id = ?";

    private final static String selectAllStatementString="SELECT * FROM YearOfStudy";

    public static ArrayList<YearOfStudyModel> GetAllYearsOfStudy()
    {
        ArrayList<YearOfStudyModel> years = new ArrayList<YearOfStudyModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                YearOfStudyModel year=new YearOfStudyModel(rs.getInt("Id"),
                        rs.getString("YearName"),rs.getString("Description"));
                years.add(year);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "YearOfStudyDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return years;
    }
    public static YearOfStudyModel findById(int Id)
    {
        YearOfStudyModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1,Id);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new YearOfStudyModel(rs.getInt("Id"),
                    rs.getString("YearName"),rs.getString("Description"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"YearOfStudyDAO:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void insert(String YearName,String Description) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,YearName);
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
