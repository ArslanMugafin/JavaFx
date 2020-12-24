
package fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Scene;
import fx.FX;
import javafx.scene.control.Button;

public class ShopController implements Initializable {
   
    @FXML
    private TextField TextLog;
    @FXML
    private TextField TextPass;
    @FXML
    Button Button;
    
    @FXML
    private void handleButtonAction() throws IOException{
        String login = FX.user.getLogin();
        String pass = FX.user.getPass();
        String textL = TextLog.getText();
        String textP = TextPass.getText();
        if ((textL.equals(login)) && (textP.equals(pass))){
            Stage stage = (Stage) Button.getScene().getWindow();
            stage.close();
            
            Stage cat = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Catalog.fxml"));
            Scene scene = new Scene(root);
            cat.setScene(scene);
            cat.show();
    } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Неверные логин или пароль");

        alert.showAndWait();
        }
    }
    
   
    @FXML
    public void registrationAction() throws IOException{
        Stage stage = (Stage) Button.getScene().getWindow();
        stage.close();
        
        Stage cat = new Stage();
        cat.setTitle("Регистрация");
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        cat.setScene(scene);
        cat.show();
    }
    
    @FXML
    public void DeleteAction() throws IOException{
        TextLog.setText("");
        TextPass.setText("");
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    private Parent FXMLLoader(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
