package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.AppUserRolesModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppUserRolesDao {
    protected static final Logger LOGGER= Logger.getLogger(AppUserRolesDao.class.getName());
    private static  final String addUserToRoleStatementString="INSERT INTO AppUserRoles (RoleId,UserId)"
            + " VALUES (?,?)";


    private final static String GetRolesForUserStatementString="SELECT * FROM AppUserRoles WHERE UserId =?";


    public static ArrayList<AppUserRolesModel> GetAllRolesForUser(String userId)
    {
        ArrayList<AppUserRolesModel> appUserRoles = new ArrayList<AppUserRolesModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(GetRolesForUserStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, userId);

            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                AppUserRolesModel appUserRole=new AppUserRolesModel(rs.getInt("RoleId"),rs.getString("UserId"));
                appUserRoles.add(appUserRole);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AppUserRolesDao:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return appUserRoles;
    }

    public static void AddUserToRole(String UserId, int RoleId ) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(addUserToRoleStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, RoleId);
            insertStatement.setString(2, UserId);

            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "AppUserRolesDao:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
