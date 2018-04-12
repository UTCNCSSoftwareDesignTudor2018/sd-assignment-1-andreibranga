package DataAccessLayer.Models;

import DataAccessLayer.DAO.AppRolesDAO;

import java.util.Objects;

public class AppUserRolesModel {
    private int roleId;
    private String userId;
    private AppRolesModel appRolesByRoleId;

    public AppUserRolesModel(int roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserRolesModel that = (AppUserRolesModel) o;
        return roleId == that.roleId &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, userId);
    }

    public AppRolesModel getAppRolesByRoleId() {
        return AppRolesDAO.findById(this.roleId);
    }

    public void setAppRolesByRoleId(AppRolesModel appRolesByRoleId) {
        this.appRolesByRoleId = appRolesByRoleId;
    }
}
