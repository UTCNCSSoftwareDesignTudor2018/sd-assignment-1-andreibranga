package DataAccessLayer.Models;

import java.util.Objects;

public class StudentProfileModel {
    private int studentId;

    public StudentProfileModel(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProfileModel that = (StudentProfileModel) o;
        return studentId == that.studentId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId);
    }
}
