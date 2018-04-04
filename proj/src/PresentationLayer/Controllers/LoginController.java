package PresentationLayer.Controllers;

import BussinessLogicLayer.Models.AppUsersModel;
import BussinessLogicLayer.Services.AppUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    @FXML
    private TextField usernameTextBox;

    @FXML
    private Button loginButton;

    @FXML
    private Text errorTextField;

    @FXML
    private PasswordField passWordTextBoxpassWordTextBox;
    @FXML
    void onLoginButtonClicked(ActionEvent event) throws IOException {

        AppUsersModel user= AppUserService.GetUserLogin(usernameTextBox.getText(),passWordTextBoxpassWordTextBox.getText());

        if(user==null)
        {
            errorTextField.setVisible(true);
        }
        else
        {
            usernameTextBox.setText("success");
            Stage currentStage = (Stage) loginButton.getScene().getWindow();


            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/Views/MainPage.fxml"));
            Parent root = fxmlLoader.load();
            MainController controller = fxmlLoader.getController();
            controller.setUser(user);
            controller.init();
            stage.setTitle("SINU - Homepage");
            Scene scene = new Scene(root,800,700);

            stage.setScene(scene);

            stage.show();


            currentStage.close();



        }

    }

}
