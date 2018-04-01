package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.AppRolesModel;
import DataAccessLayer.Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppRolesDAO {
    protected static final Logger LOGGER= Logger.getLogger(AppRolesDAO.class.getName());
    private static  final String insertStatementString="INSERT INTO AppRoles (RoleName)"
            + " VALUES (?)";

    private final static String findStatementString = "SELECT * FROM AppRoles where RoleId = ?";

    private final static String selectAllStatementString="SELECT * FROM AppRoles";

    public static ArrayList<AppRolesModel> GetAllRoles()
    {
        ArrayList<AppRolesModel> appRoles = new ArrayList<AppRolesModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                AppRolesModel appRole=new AppRolesModel(rs.getInt("RoleId"),rs.getString("RoleName"));
                appRoles.add(appRole);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AppRolesDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return appRoles;
    }
    public static AppRolesModel findById(int RoleId)
    {
        AppRolesModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1,RoleId);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new AppRolesModel(rs.getInt("RoleId"),rs.getString("RoleName"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"AppRolesDAO:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static int insert(String RoleName) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, RoleName);
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "AppRolesDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}
