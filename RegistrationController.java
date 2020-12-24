
package fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import fx.FX;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class RegistrationController implements Initializable {

    @FXML
    private TextField TextLog;
    @FXML
    private TextField TextPass;
    @FXML
    private Button ButReg;
    @FXML
    public void handleRegistrAction() throws IOException {
        String login = FX.user.getLogin();
        String pass = FX.user.getPass();
        if (TextLog.getText().equals(login)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Логин существует!");

            alert.showAndWait();
            Stage stage = (Stage) ButReg.getScene().getWindow();
            stage.close();
        } else {
            FX.user.setLogin(TextLog.getText());
            FX.user.setPass(TextPass.getText());
            Stage stage = (Stage) ButReg.getScene().getWindow();
            stage.close();
            
            Stage cat = new Stage();
            cat.setTitle("Регистрация");
            Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
            Scene scene = new Scene(root);
            cat.setScene(scene);
            cat.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
