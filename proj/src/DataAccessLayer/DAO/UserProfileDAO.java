package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.StudentProfileModel;
import BussinessLogicLayer.Models.UserProfileModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserProfileDAO
{
    protected static final Logger LOGGER= Logger.getLogger(UserProfileDAO.class.getName());
    private static  final String insertStatementString="INSERT INTO UserProfile (UserId,Name,Surname,MidName,Phone,Nationality,Country," +
            "County,Address,ZIP)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?)";

    private final static String findStatementString = "SELECT * FROM UserProfile where UserId = ?";

    private final static String selectAllStatementString="SELECT * FROM UserProfile";

    public static ArrayList<UserProfileModel> GetAllUserProfiles()
    {
        ArrayList<UserProfileModel> profiles = new ArrayList<UserProfileModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                UserProfileModel profile=new UserProfileModel(rs.getString("UserId"),
                        rs.getString(" Name"),
                        rs.getString("Surname"),
                        rs.getString("MidName"),
                        rs.getString("Phone"),
                        rs.getString("Nationality"),
                        rs.getString("Country"),
                        rs.getString("County"),
                        rs.getString("Address"),
                        rs.getString("ZIP"));
                profiles.add(profile);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserProfileDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return profiles;
    }
    public static UserProfileModel findById(int StudentId)
    {
        UserProfileModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1,StudentId);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new UserProfileModel(rs.getString("UserId"),
                    rs.getString(" Name"),
                    rs.getString("Surname"),
                    rs.getString("MidName"),
                    rs.getString("Phone"),
                    rs.getString("Nationality"),
                    rs.getString("Country"),
                    rs.getString("County"),
                    rs.getString("Address"),
                    rs.getString("ZIP"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"UserProfileDAO:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void insert(String UserId,String Name,String Surname,String MidName,String Phone,
                              String Nationality, String Country,String County,String Address,String ZIP) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,UserId);

            insertStatement.setString(2,Name);
            insertStatement.setString(3,Surname);

            insertStatement.setString(4,MidName);

            insertStatement.setString(5,Phone);

            insertStatement.setString(6,Nationality);

            insertStatement.setString(7,Country);

            insertStatement.setString(8,County);

            insertStatement.setString(9,Address);

            insertStatement.setString(10,ZIP);


            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "UserProfileDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void update(String UserId,String Name,String Surname,String MidName,String Phone,
                              String Nationality, String Country,String County,String Address,String ZIP) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,UserId);
            insertStatement.setString(2,Name);
            insertStatement.setString(3,Surname);
            insertStatement.setString(4,MidName);
            insertStatement.setString(5,Phone);
            insertStatement.setString(6,Nationality);
            insertStatement.setString(7,Country);
            insertStatement.setString(8,County);
            insertStatement.setString(9,Address);
            insertStatement.setString(10,ZIP);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "UserProfileDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
