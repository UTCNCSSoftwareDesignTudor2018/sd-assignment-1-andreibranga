package DataAccessLayer.Models;

import java.util.Objects;

public class YearOfStudyModel {
    private int id;
    private String yearName;
    private String description;

    public YearOfStudyModel(int id, String yearName, String description) {
        this.id = id;
        this.yearName = yearName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
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
        YearOfStudyModel that = (YearOfStudyModel) o;
        return id == that.id &&
                Objects.equals(yearName, that.yearName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, yearName, description);
    }
}
