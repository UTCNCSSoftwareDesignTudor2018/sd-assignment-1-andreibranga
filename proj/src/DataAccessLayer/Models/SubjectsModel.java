package DataAccessLayer.Models;

import java.util.Objects;

public class SubjectsModel {
    private int id;
    private String subjectName;
    private String description;

    public SubjectsModel(int id, String subjectName, String description) {
        this.id = id;
        this.subjectName = subjectName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectsModel that = (SubjectsModel) o;
        return id == that.id &&
                Objects.equals(subjectName, that.subjectName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subjectName, description);
    }
}
