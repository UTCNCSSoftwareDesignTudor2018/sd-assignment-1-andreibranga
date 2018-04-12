package DataAccessLayer.Models;

import java.util.Objects;

public class GroupModel {
    private int id;
    private String groupName;
    private String description;
    private boolean isActive;
    private int yearOfStudyId;

    public GroupModel(int id,int yearOfStudyId,String groupName, String description, boolean isActive) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.isActive = isActive;
        this.yearOfStudyId=yearOfStudyId;
    }

    public int getYearOfStudyId() {
        return yearOfStudyId;
    }

    public void setYearOfStudyId(int yearOfStudyId) {
        this.yearOfStudyId = yearOfStudyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupModel that = (GroupModel) o;
        return id == that.id &&
                isActive == that.isActive &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, groupName, description, isActive);
    }
}
