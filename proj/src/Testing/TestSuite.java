package Testing;

import BussinessLogicLayer.Models.AppRolesModel;
import BussinessLogicLayer.Models.AppUserRolesModel;
import BussinessLogicLayer.Models.AppUsersModel;
import DataAccessLayer.Connection.ConnectionFactory;
import DataAccessLayer.DAO.AppRolesDAO;
import DataAccessLayer.DAO.AppUserRolesDao;
import DataAccessLayer.DAO.AppUsersDAO;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.sql.Connection;
import java.util.ArrayList;

public class TestSuite {
    @Test
    public void testSuiteConfiguration()
    {
        System.out.println("Test configuration....");
        System.out.println("Success");

    }

    @Test
    public void testConnection()
    {
        try{
            Connection dbConnection= ConnectionFactory.getConnection();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetAllAppRoles()
    {
        ArrayList<AppRolesModel> roles= AppRolesDAO.GetAllRoles();

        for (AppRolesModel item:roles
             ) {
            System.out.println(item.getRoleId()+"    "+item.getRoleName());
        }
    }

    @Test
    public void testGetAppRoleById()
    {
        AppRolesModel role=AppRolesDAO.findById(1);
        System.out.println(role.getRoleId()+"    "+role.getRoleName());

    }

    @Test
    public void testInsertAppRole()
    {
        int id=AppRolesDAO.insert("Student");

        System.out.println(id);
    }

    @Test
    public void testAddUser()
    {
        AppUsersDAO.insert("abranga","qwerty123","abranga96@gmail.com");

    }

    @Test
    public void testGetAllUsers()
    {
        ArrayList<AppUsersModel> users=AppUsersDAO.GetAllUsers();
        for (AppUsersModel item:users
             ) {
            System.out.println(item.getUserId()+" "+item.getEmail()+" "+item.getUserName()+" "+item.getPasswordHash());
        }
    }


    @Test
    public void getUserById()
    {
        AppUsersModel user=AppUsersDAO.findById("1d71d0b5-5549-47e4-9d36-c76baa24812e");
        System.out.println(user.getUserId()+" "+user.getEmail()+" "+user.getUserName()+" "+user.getPasswordHash());

    }

    @Test
    public void AddUserToRole()
    {
        AppUserRolesDao.AddUserToRole("1d71d0b5-5549-47e4-9d36-c76baa24812e",2);
    }

    @Test void GetUserRoles()
    {
        ArrayList<AppUserRolesModel>  roles=AppUserRolesDao.GetAllRolesForUser("1d71d0b5-5549-47e4-9d36-c76baa24812e");
        for (AppUserRolesModel item:roles
             ) {
            System.out.println(item.getRoleId()+" "+item.getUserId());
        }
    }
}
