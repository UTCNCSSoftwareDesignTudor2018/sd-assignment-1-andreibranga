package PresentationLayer.Controllers;

import DataAccessLayer.Models.AppReportModel;
import DataAccessLayer.Models.AppUsersModel;
import DataAccessLayer.Models.StudentsModel;
import DataAccessLayer.Models.SubjectsModel;
import BussinessLogicLayer.Services.AppUserService;
import BussinessLogicLayer.Services.GradingService;
import BussinessLogicLayer.Services.StudentService;
import PresentationLayer.Validators.*;
import com.sun.tools.javac.util.Pair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class StudentDetailController
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
        emailText.setText(user.getEmail());

        nameText.setText(user.getUserProfile().getName());
        midnameText.setText(user.getUserProfile().getMidName());
        surnameText.setText(user.getUserProfile().getSurname());
        phoneText.setText(user.getUserProfile().getPhone());
        nationalityText.setText(user.getUserProfile().getNationality());
        CountryText.setText(user.getUserProfile().getCountry());
        CountyText.setText(user.getUserProfile().getCounty());
        addressText.setText(user.getUserProfile().getAddress());
        zipText.setText(user.getUserProfile().getZip());


        String detail="Student: "+student.getStudentId() + " |  Group: "+ StudentService.getStudentGroup(student.getStudentId()).getGroupName()
                + " | Year: "+ StudentService.getYearOfStudyForStudent(student.getStudentId()).getYearName();
        textFieldOpen.setText(detail);


        ArrayList<SubjectsModel> subjects= StudentService.getAllSubjectsForStudent(student.getStudentId());
        subjectName.setCellValueFactory(new PropertyValueFactory<SubjectsModel,String>("subjectName"));
        description.setCellValueFactory(new PropertyValueFactory<SubjectsModel,String>("description"));
        subjectsTable.getItems().setAll(subjects);



    }

    @FXML
    private TextField nameText;

    @FXML
    private TextField midnameText;

    @FXML
    private TextField surnameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField nationalityText;

    @FXML
    private TextField CountryText;

    @FXML
    private TextField CountyText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField zipText;

    @FXML
    private TextField emailText;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;
    @FXML
    private Text textFieldOpen;
    @FXML
    private TableView<SubjectsModel> subjectsTable;

    @FXML
    private TableColumn<SubjectsModel, String> subjectName;

    @FXML
    private TableColumn<SubjectsModel, String> description;
    @FXML
    void onEdit(ActionEvent event) {
        saveButton.setDisable(false);
        editButton.setDisable(true);

        nameText.setDisable(false);
        surnameText.setDisable(false);
        phoneText.setDisable(false);
        nationalityText.setDisable(false);
        CountyText.setDisable(false);
        CountryText.setDisable(false);
        addressText.setDisable(false);
        zipText.setDisable(false);
        midnameText.setDisable(false);

    }

    @FXML
    void onSave(ActionEvent event) {
        try {
            NameValidator.validateLetters(nameText.getText());
            NameValidator.validateLetters(surnameText.getText());
            NameValidator.validateLetters(midnameText.getText());
            PhoneValidator.validate(phoneText.getText());
            NameValidator.validateLetters(nationalityText.getText());
            AddressValidator.validateAddress(addressText.getText());
            NameValidator.validateLetters(CountryText.getText());
            NameValidator.validateLetters(CountyText.getText());
            ZIPValidator.validate(zipText.getText());
            saveButton.setDisable(true);
            editButton.setDisable(false);


            nameText.setDisable(true);
            surnameText.setDisable(true);
            phoneText.setDisable(true);
            nationalityText.setDisable(true);
            CountyText.setDisable(true);
            CountryText.setDisable(true);
            addressText.setDisable(true);
            zipText.setDisable(true);
            midnameText.setDisable(true);

            AppUserService.EditUserProfile(user.getUserId(),
                    nameText.getText(),
                    surnameText.getText(),
                    midnameText.getText(),
                    phoneText.getText(),
                    nationalityText.getText(),
                    CountryText.getText(),
                    CountyText.getText(),
                    addressText.getText(),
                    zipText.getText());

            init();
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form not valid!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
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
subjectId=selected.getId();
        }
    }

    private int subjectId=0;
    @FXML
    private TextField subjectNName;

    @FXML
    private TextField gradeField;

    @FXML
    private Button setGradeButton;

    @FXML
    void onSetGradeButton(ActionEvent event) {
        try {
            if (subjectId > 0)

            {
                GradeValidator.validate(gradeField.getText());
                GradingService.updateGradeForSubject(student.getStudentId(), subjectId, gradeField.getText());
            }
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form not valid!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

    }


    @FXML
    private Button reportbtn;

    @FXML
    void onReport(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {

        AppReportModel report=new AppReportModel();
        report.setName(student.getStudentName());
        report.setSurname(user.getUserProfile().getSurname());
        report.setMidName(user.getUserProfile().getMidName());
        report.setGroup(StudentService.getStudentGroup(student.getStudentId()).getGroupName());
        report.setYear(StudentService.getYearOfStudyForStudent(student.getStudentId()).getYearName());
        report.setStudentId(String.valueOf(student.getStudentId()));


        ArrayList<Pair<SubjectsModel,Float>> grades=new ArrayList<>();

        ArrayList<SubjectsModel> subj=StudentService.getAllSubjectsForStudent(student.getStudentId());
        for (SubjectsModel s:subj
             ) {
            Pair<SubjectsModel,Float> gr=new Pair<SubjectsModel,Float>(s,
                    GradingService.getGradeforSubject(student.getStudentId(),s.getId()).equals("NOT SET")?Float.valueOf(0):
            Float.valueOf(GradingService.getGradeforSubject(student.getStudentId(),s.getId())));
            grades.add(gr);
        }
        report.setGrades(grades);

        PrintWriter writer = new PrintWriter("report-stud-"+student.getStudentId()+".txt", "UTF-8");
        writer.println(report.toString());
        writer.close();

        System.out.println(report.toString());

    }
}
