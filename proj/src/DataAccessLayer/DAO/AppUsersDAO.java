package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.AppRolesModel;
import BussinessLogicLayer.Models.AppUsersModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppUsersDAO {
    protected static final Logger LOGGER= Logger.getLogger(AppUsersDAO.class.getName());
    private static  final String insertStatementString="INSERT INTO AppUsers (UserId,UserName,PasswordHash,Email)"
            + " VALUES (?,?,?,?)";

    private final static String findStatementString = "SELECT * FROM AppUsers where UserId = ?";

    private final static String selectAllStatementString="SELECT * FROM AppUsers";

    public static ArrayList<AppUsersModel> GetAllUsers()
    {
        ArrayList<AppUsersModel> appUsers = new ArrayList<AppUsersModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                AppUsersModel appUser=new AppUsersModel(rs.getString("UserId"),rs.getString("UserName"),
                        rs.getString("PasswordHash"),rs.getString("Email"));
                appUsers.add(appUser);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AppUsersDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return appUsers;
    }
    public static AppUsersModel findById(String UserId)
    {
        AppUsersModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1,UserId);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new AppUsersModel(rs.getString("UserId"),rs.getString("UserName"),
                    rs.getString("PasswordHash"),rs.getString("Email"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"AppUsersDAO:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void insert(String UserName,String PasswordHash,
                                String Email) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String insertedId = "";
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, UUID.randomUUID().toString());
            insertStatement.setString(2, UserName);
            insertStatement.setString(3, PasswordHash);
            insertStatement.setString(4, Email);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "AppUsersDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
