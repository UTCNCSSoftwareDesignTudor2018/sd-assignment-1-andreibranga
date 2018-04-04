package BussinessLogicLayer.Services;

import BussinessLogicLayer.Models.AppRolesModel;
import BussinessLogicLayer.Models.AppUserRolesModel;
import BussinessLogicLayer.Models.AppUsersModel;
import DataAccessLayer.DAO.AppRolesDAO;
import DataAccessLayer.DAO.AppUserRolesDao;
import DataAccessLayer.DAO.AppUsersDAO;
import DataAccessLayer.DAO.UserProfileDAO;

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

}
