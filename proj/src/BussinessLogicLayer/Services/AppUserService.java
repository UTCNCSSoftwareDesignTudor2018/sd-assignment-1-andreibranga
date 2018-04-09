package BussinessLogicLayer.Services;

import BussinessLogicLayer.Models.AppRolesModel;
import BussinessLogicLayer.Models.AppUserRolesModel;
import BussinessLogicLayer.Models.AppUsersModel;
import BussinessLogicLayer.Models.StudentsModel;
import DataAccessLayer.DAO.*;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.ArrayList;
import java.util.Optional;

public class AppUserService {
    public static AppUsersModel GetUserById(String userid)
    {
        return AppUsersDAO.findById(userid);
    }

    public static AppUsersModel GetUserLogin(String username,String password)
    {
        AppUsersModel user;
        Optional<AppUsersModel> found = Optional.empty();
        for (AppUsersModel p : AppUsersDAO.GetAllUsers()) {
            if (p.getUserName().equals(username)
                    && p.getPasswordHash().equals(password)) {
                found = Optional.of(p);
                break;
            }
        }
        try
        {
            user= found.get();

        }
        catch (Exception ex)
        {
            user=null;
        }

        return user;
    }

    public static ArrayList<AppUserRolesModel> GetUserRoles(String userId)
    {
        return AppUserRolesDao.GetAllRolesForUser(userId);
    }

    public static void EditUserProfile(String userId,String name,String surname,String midname,
                                String phone,String nationality, String Country,
                                String County,String address,String zip)
    {
        UserProfileDAO.update(userId,name,surname,midname,phone,nationality,Country,
                County,address,zip);
    }

    public static String AddUser(String userName,String password,String email)
    {
        AppUsersDAO.insert(userName,password,email);

        AppUsersModel found = null;
        for (AppUsersModel p : AppUsersDAO.GetAllUsers()) {
            if (p.getUserName().equals(userName)) {
                found = p;
                break;
            }
        }
        AppUsersModel user= found;

        AppUserRolesDao.AddUserToRole(found.getUserId(),2);

        return user.getUserId();
    }


    public static void AddUserProfile(String userId,String name,String surname,String midName,
                               String phone,String Nationality,String country,String county,
                               String address,String zip)
    {
        UserProfileDAO.insert(userId,name,surname,midName,
                phone,Nationality,country,county,address,zip);
    }



}
