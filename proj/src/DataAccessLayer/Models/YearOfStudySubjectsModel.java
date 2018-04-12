package DataAccessLayer.Models;

import DataAccessLayer.DAO.SubjectsDAO;
import DataAccessLayer.DAO.YearOfStudyDAO;

import java.util.Objects;

public class YearOfStudySubjectsModel {
    private int subjectId;
    private int yearOfStudyId;
    private SubjectsModel subjectsBySubjectId;
    private YearOfStudyModel yearOfStudyByYearOfStudyId;



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
        return SubjectsDAO.findById(subjectId);
    }

    public void setSubjectsBySubjectId(SubjectsModel subjectsBySubjectId) {
        this.subjectsBySubjectId = subjectsBySubjectId;
    }

    public YearOfStudyModel getYearOfStudyByYearOfStudyId() {
        return YearOfStudyDAO.findById(yearOfStudyId);
    }

    public void setYearOfStudyByYearOfStudyId(YearOfStudyModel yearOfStudyByYearOfStudyId) {
        this.yearOfStudyByYearOfStudyId = yearOfStudyByYearOfStudyId;
    }
}
