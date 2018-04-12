package DataAccessLayer.Models;

import java.util.Objects;

public class AppRolesModel {
    private int roleId;
    private String roleName;

    public AppRolesModel(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRolesModel that = (AppRolesModel) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleName);
    }
}
