package PresentationLayer.Controllers;

import BussinessLogicLayer.Models.AppUsersModel;
import BussinessLogicLayer.Models.StudentsModel;
import BussinessLogicLayer.Services.AppUserService;
import BussinessLogicLayer.Services.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.util.ArrayList;

public class MainController {
    @FXML
    private TableView<StudentsModel> studentTable;

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
        }
        else
        {
            role=" teacher";
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

        studentTable = new TableView<>();
        students= StudentService.GetAllStudents();
        final ObservableList<StudentsModel> studentsList= FXCollections.observableArrayList(students);
        studentTable.setItems(studentsList);


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




}
