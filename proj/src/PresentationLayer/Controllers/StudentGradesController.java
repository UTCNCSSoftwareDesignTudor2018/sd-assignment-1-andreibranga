package PresentationLayer.Controllers;

import DataAccessLayer.Models.AppUsersModel;
import DataAccessLayer.Models.StudentsModel;
import DataAccessLayer.Models.SubjectsModel;
import BussinessLogicLayer.Services.GradingService;
import BussinessLogicLayer.Services.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class StudentGradesController
{
    private AppUsersModel user;

    public StudentsModel getStudent() {
        return student;
    }

    public void setStudent(StudentsModel student) {
        this.student = student;
    }

    private StudentsModel student;

    public AppUsersModel getUser() {
        return user;
    }

    public void setUser(AppUsersModel user) {
        this.user = user;
    }
    public void init()
    {


        String detail="Student: "+student.getStudentId() + " |  Group: "+ StudentService.getStudentGroup(student.getStudentId()).getGroupName()
                + " | Year: "+ StudentService.getYearOfStudyForStudent(student.getStudentId()).getYearName();
        textFieldOpen.setText(detail);

        ArrayList<SubjectsModel> subjects= StudentService.getAllSubjectsForStudent(student.getStudentId());
        subjectName.setCellValueFactory(new PropertyValueFactory<SubjectsModel,String>("subjectName"));
        description.setCellValueFactory(new PropertyValueFactory<SubjectsModel,String>("description"));
        subjectsTable.getItems().setAll(subjects);
        System.out.println("successs"+student.getStudentName());

    }

    @FXML
    void onClick(MouseEvent event) throws IOException {
        if (event.getClickCount() > 1) {
            onEdit();
        }
    }


    public void onEdit() throws IOException {
        // check the table's selected item and get selected item
        if (subjectsTable.getSelectionModel().getSelectedItem() != null) {
            SubjectsModel selected = subjectsTable.getSelectionModel().getSelectedItem();
            subjectNName.setText(selected.getSubjectName());
            gradeField.setText(GradingService.getGradeforSubject(student.getStudentId(),selected.getId()));
        }
    }

    @FXML
    private Pane textFieldDetail;

    @FXML
    private Text textFieldOpen;

    @FXML
    private TableView<SubjectsModel> subjectsTable;

    @FXML
    private TableColumn<SubjectsModel, String> subjectName;

    @FXML
    private TableColumn<SubjectsModel, String> description;

    @FXML
    private TextField subjectNName;

    @FXML
    private TextField gradeField;
}
