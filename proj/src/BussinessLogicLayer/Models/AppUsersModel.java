package BussinessLogicLayer.Models;

import DataAccessLayer.DAO.AppUserRolesDao;
import DataAccessLayer.DAO.UserProfileDAO;

import java.util.ArrayList;
import java.util.Objects;

public class AppUsersModel {
    private String userId;
    private String userName;
    private String passwordHash;
    private String email;
    private UserProfileModel userProfile;
    public AppUsersModel(String userId, String userName, String passwordHash, String email) {
        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public UserProfileModel getUserProfile() {
        return UserProfileDAO.findById(userId);
    }

    public boolean IsInRole(String roleName)
    {
        ArrayList<AppUserRolesModel> roles= AppUserRolesDao.GetAllRolesForUser(userId);

        for (AppUserRolesModel item:roles
             ) {
            if(item.getAppRolesByRoleId().getRoleName().equals(roleName))
            {
                return true;
            }


        }
        return false;
    }

    public void setUserProfile(UserProfileModel userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUsersModel that = (AppUsersModel) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(passwordHash, that.passwordHash) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, passwordHash, email);
    }
}
