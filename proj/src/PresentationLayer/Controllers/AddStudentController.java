package PresentationLayer.Controllers;

import BussinessLogicLayer.Models.AppUsersModel;
import BussinessLogicLayer.Models.GroupModel;
import BussinessLogicLayer.Models.SubjectsModel;
import BussinessLogicLayer.Services.AppUserService;
import BussinessLogicLayer.Services.GradingService;
import BussinessLogicLayer.Services.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddStudentController
{
    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField surnameTxt;

    @FXML
    private TextField midnameTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField nationalityTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private TextField countyTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField zipTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button nextBotton;
    @FXML
    private TableView<GroupModel> groupTable;
    @FXML
    private TableColumn<GroupModel, String> groupName;

    @FXML
    private TableColumn<GroupModel, String> description;

    @FXML
    private TextField emailTxt;
    @FXML
    private Button saveButton;
    @FXML
    private Pane paneOne;
    @FXML
    private Pane paneTwo;
    private String UserId="";
    private int StudentId=0;
    @FXML
    void onNextButton(ActionEvent event) {

        paneOne.setDisable(true);


        UserId=AppUserService.AddUser(usernameTxt.getText(),passwordTxt.getText(),emailTxt.getText());

        AppUserService.AddUserProfile(UserId,nameTxt.getText(),
                surnameTxt.getText(),
                midnameTxt.getText(),
                phoneTxt.getText(),
                nationalityTxt.getText(),
                countryTxt.getText(),
                countyTxt.getText(),
                addressTxt.getText(),
                zipTxt.getText());


        StudentId= StudentService.MakeUserStudent(UserId);

init();
        paneTwo.setDisable(false);
    }

    @FXML
    void onSaveButton(ActionEvent event) {

    StudentService.AddStudentToGroup(StudentId,GroupiD);

        Stage currentStage = (Stage) addressTxt.getScene().getWindow();

        currentStage.close();

    }

    public void init()
    {
        ArrayList<GroupModel> groups= StudentService.getAllGroups();
        groupName.setCellValueFactory(new PropertyValueFactory<GroupModel,String>("groupName"));
        description.setCellValueFactory(new PropertyValueFactory<GroupModel,String>("description"));
        groupTable.getItems().setAll(groups);

    }

    int GroupiD=0;
    @FXML
    void onClick(MouseEvent event) throws IOException {
        if (event.getClickCount() > 1) {
            onEdit();
        }
    }


    public void onEdit() throws IOException {
        // check the table's selected item and get selected item
        if (groupTable.getSelectionModel().getSelectedItem() != null) {
            GroupModel selected = groupTable.getSelectionModel().getSelectedItem();
            GroupiD=selected.getId();
saveButton.setDisable(false);

        }
    }


}
