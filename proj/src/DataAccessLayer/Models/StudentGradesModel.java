package DataAccessLayer.Models;

import java.util.Objects;

public class StudentGradesModel {
    private int studentId;
    private int subjectId;
    private Float grade;

    public StudentGradesModel(int studentId, int subjectId, Float grade) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGradesModel that = (StudentGradesModel) o;
        return studentId == that.studentId &&
                subjectId == that.subjectId &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, subjectId, grade);
    }
}
