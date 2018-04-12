package PresentationLayer.Controllers;

import DataAccessLayer.Models.AppUsersModel;
import DataAccessLayer.Models.StudentsModel;
import BussinessLogicLayer.Services.AppUserService;
import BussinessLogicLayer.Services.StudentService;
import PresentationLayer.Validators.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    @FXML
    private TableView<StudentsModel> studentTable;

    @FXML
    private TableColumn<StudentsModel, Number> studentId;

    @FXML
    private TableColumn<StudentsModel, String> StudentName;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;


    @FXML
    private TextField welcomeText;

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


    private AppUsersModel user;

    private ArrayList<StudentsModel> students;

    public AppUsersModel getUser() {
        return user;
    }

    public void setUser(AppUsersModel user) {
        this.user = user;
    }

    public void init()
    {String role="";
        if(user.IsInRole("student"))
        {
             role=" student";
             gradesButton.setVisible(true);
        }
        else
        {
            role=" teacher";
            newStudButton.setVisible(true);
            refreshBtn.setVisible(true);

        }
        welcomeText.setText("Welcome" +role+
                ", "+ user.getUserName());
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

        if(user.IsInRole("student"))
        {
            studentTable.setVisible(false);
        }


            initStudentsTable();

    }

    private void initStudentsTable()
    {
        students= StudentService.GetAllStudents();
        studentId.setCellValueFactory(new PropertyValueFactory<StudentsModel,Number>("studentId"));
        StudentName.setCellValueFactory(new PropertyValueFactory<StudentsModel,String>("StudentName"));
        studentTable.getItems().setAll(students);
    }

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


        try{
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
        if (studentTable.getSelectionModel().getSelectedItem() != null) {
            StudentsModel selectedStudent = studentTable.getSelectionModel().getSelectedItem();
openStudentDetails(selectedStudent);
        }
    }

    private void openStudentDetails(StudentsModel student) throws IOException {
        Stage currentStage = (Stage) nameText.getScene().getWindow();
        StudentsModel newStudent=StudentService.getStudentById(student.getStudentId());
        AppUsersModel newUser=AppUserService.GetUserById(newStudent.getUserId());

        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StudentDetailController.class.getResource("/Views/StudentDetail.fxml"));
        Parent root = fxmlLoader.load();
        StudentDetailController controller = fxmlLoader.getController();
        controller.setUser(newUser);
        controller.setStudent(newStudent);
        controller.init();
        stage.setTitle("Student: "+student.getStudentName());
        Scene scene = new Scene(root,800,700);

        stage.setScene(scene);

        stage.show();


    }

    private void openGradeDetails(StudentsModel student)throws IOException
    {
        Stage currentStage = (Stage) nameText.getScene().getWindow();
        StudentsModel newStudent=StudentService.getStudentById(student.getStudentId());
        AppUsersModel newUser=AppUserService.GetUserById(newStudent.getUserId());

        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StudentGradesController.class.getResource("/Views/Grades.fxml"));
        Parent root = fxmlLoader.load();
        StudentGradesController controller = fxmlLoader.getController();
        controller.setUser(newUser);
        controller.setStudent(newStudent);
        controller.init();
        stage.setTitle("Student: "+student.getStudentName());
        Scene scene = new Scene(root,433,574);

        stage.setScene(scene);

        stage.show();
    }


    @FXML
    private Button gradesButton;


    @FXML
    void onGradesButtonPressed(ActionEvent event) throws IOException {

        StudentsModel found = null;
        for (StudentsModel p : StudentService.GetAllStudents()) {
            if (p.getUserId().equals(user.getUserId())) {
                found = p;
                break;
            }
        }
        StudentsModel student= found;

        if(student!=null)
        {
            openGradeDetails(student);
        }

    }

    @FXML
    private Button newStudButton;


    @FXML
    void onNewStudButton(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) nameText.getScene().getWindow();

        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StudentGradesController.class.getResource("/Views/AddStudent.fxml"));
        Parent root = fxmlLoader.load();
        AddStudentController controller = fxmlLoader.getController();
        controller.init();
        stage.setTitle("Add Student ");
        Scene scene = new Scene(root,1000,600);

        stage.setScene(scene);

        stage.show();


    }


    @FXML
    private Button refreshBtn;

    @FXML
    void onRefresh(ActionEvent event) {
        initStudentsTable();

    }

}
