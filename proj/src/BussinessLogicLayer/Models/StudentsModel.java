package BussinessLogicLayer.Models;

import DataAccessLayer.DAO.AppUsersDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class StudentsModel {
    private String userId;
    private SimpleIntegerProperty studentId;
    private AppUsersModel usersModel;
    private SimpleStringProperty StudentName=new SimpleStringProperty("blank");

    public String getStudentName() {
        return getUsersModel().getUserProfile().getName()+" "
                +getUsersModel().getUserProfile().getMidName()+" "
                +getUsersModel().getUserProfile().getSurname();
    }

    public void setStudentName(SimpleStringProperty studentName) {
        StudentName = studentName;
    }

    public AppUsersModel getUsersModel() {
        return AppUsersDAO.findById(userId);
    }

    public void setUsersModel(AppUsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public StudentsModel(String userId, SimpleIntegerProperty studentId) {
        this.userId = userId;
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(SimpleIntegerProperty studentId) {
        this.studentId = studentId;
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
        StudentsModel that = (StudentsModel) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }
}
