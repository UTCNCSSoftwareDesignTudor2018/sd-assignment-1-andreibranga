package BussinessLogicLayer.Models;

import java.util.Objects;

public class YearOfStudySubjectsModel {
    private int subjectId;
    private int yearOfStudyId;
    private SubjectsModel subjectsBySubjectId;
    private YearOfStudyModel yearOfStudyByYearOfStudyId;

    public YearOfStudySubjectsModel(int subjectId, int yearOfStudyId, SubjectsModel subjectsBySubjectId, YearOfStudyModel yearOfStudyByYearOfStudyId) {
        this.subjectId = subjectId;
        this.yearOfStudyId = yearOfStudyId;
        this.subjectsBySubjectId = subjectsBySubjectId;
        this.yearOfStudyByYearOfStudyId = yearOfStudyByYearOfStudyId;
    }

    public YearOfStudySubjectsModel(int subjectId, int yearOfStudyId) {
        this.subjectId = subjectId;
        this.yearOfStudyId = yearOfStudyId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getYearOfStudyId() {
        return yearOfStudyId;
    }

    public void setYearOfStudyId(int yearOfStudyId) {
        this.yearOfStudyId = yearOfStudyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearOfStudySubjectsModel that = (YearOfStudySubjectsModel) o;
        return subjectId == that.subjectId &&
                yearOfStudyId == that.yearOfStudyId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(subjectId, yearOfStudyId);
    }

    public SubjectsModel getSubjectsBySubjectId() {
        return subjectsBySubjectId;
    }

    public void setSubjectsBySubjectId(SubjectsModel subjectsBySubjectId) {
        this.subjectsBySubjectId = subjectsBySubjectId;
    }

    public YearOfStudyModel getYearOfStudyByYearOfStudyId() {
        return yearOfStudyByYearOfStudyId;
    }

    public void setYearOfStudyByYearOfStudyId(YearOfStudyModel yearOfStudyByYearOfStudyId) {
        this.yearOfStudyByYearOfStudyId = yearOfStudyByYearOfStudyId;
    }
}
