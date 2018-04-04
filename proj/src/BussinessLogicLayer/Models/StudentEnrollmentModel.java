package BussinessLogicLayer.Models;

import DataAccessLayer.DAO.GroupDAO;

import java.util.Objects;

public class StudentEnrollmentModel {
    private int studentId;
    private int groupId;
    private GroupModel groupByGroupId;

    public StudentEnrollmentModel(int studentId, int groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrollmentModel that = (StudentEnrollmentModel) o;
        return studentId == that.studentId &&
                groupId == that.groupId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, groupId);
    }

    public GroupModel getGroupByGroupId() {
        return GroupDAO.findById(groupId);
    }

    public void setGroupByGroupId(GroupModel groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }
}
