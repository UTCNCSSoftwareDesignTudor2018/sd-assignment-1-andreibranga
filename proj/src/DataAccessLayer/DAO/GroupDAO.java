package DataAccessLayer.DAO;

import BussinessLogicLayer.Models.AppUsersModel;
import BussinessLogicLayer.Models.GroupModel;
import DataAccessLayer.Connection.ConnectionFactory;
import javassist.runtime.Desc;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupDAO {
    protected static final Logger LOGGER= Logger.getLogger(GroupDAO.class.getName());
    private static  final String insertStatementString="INSERT INTO Group (YearOfStudyId,GroupName,Description,IsActive)"
            + " VALUES (?,?,?,?)";

    private final static String findStatementString = "SELECT * FROM Group where Id = ?";

    private final static String selectAllStatementString="SELECT * FROM Group";

    public static ArrayList<GroupModel> GetAllGroups()
    {
        ArrayList<GroupModel> groups = new ArrayList<GroupModel>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try
        {
            updateStatement=dbConnection.prepareStatement(selectAllStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.execute();
            ResultSet rs = updateStatement.getResultSet();
            while(rs.next())
            {
                GroupModel group=new GroupModel(rs.getInt("Id"),rs.getInt("YearOfStudyId"),rs.getString("GroupName"),
                        rs.getString("Description"),rs.getBoolean("IsActive"));
                groups.add(group);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "GroupDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return groups;
    }
    public static GroupModel findById(int groupId)
    {
        GroupModel toReturn=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs= null;
        try {
            findStatement=dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1,groupId);
            rs=findStatement.executeQuery();
            rs.next();
            toReturn=new GroupModel(rs.getInt("Id"),rs.getInt("YearOfStudyId"),rs.getString("GroupName"),
                    rs.getString("Description"),rs.getBoolean("IsActive"));

        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING,"GroupDAO:findById "+e.getMessage());
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void insert(int yearOfStudyId,String GroupName,String Description,
                              boolean isActive) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,yearOfStudyId);
            insertStatement.setString(2, GroupName);
            insertStatement.setString(3, Description);
            insertStatement.setBoolean(4, isActive);


            insertStatement.executeUpdate();

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "GroupDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
