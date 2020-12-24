/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EditProductController implements Initializable {

    @FXML
    private TextField IdField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField SumField;
    @FXML
    private TextField CountField;
    
    private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            IdField.setText(product.getProdId() != null ? 
                    product.getProdId().toString() : "");
            NameField.setText(product.getProdName());
            SumField.setText(product.getProdSum() != null ?
                    product.getProdSum().toString() : "");
            CountField.setText(product.getProdCount() != null ?
                    product.getProdCount().toString() : "");
        }
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (IdField.getText() == null || IdField.getText().length() == 0) {
            errorMessage += "Нет доступного артикула\n";
        }
        if (NameField.getText() == null || NameField.getText().length() == 0) {
            errorMessage += "Нет доступного наименования товара\n";
        }
        if (SumField.getText() == null || SumField.getText().length() == 0) {
            errorMessage += "Нет доступной суммы\n";
        }
        if (CountField.getText() == null || CountField.getText().length() == 0) {
            errorMessage += "Нет доступного количества\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректный ввод");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            
            return false;
        }
    }
    
    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()) {
            product.setProdId(Integer.parseInt(IdField.getText()));
            product.setProdName(NameField.getText());
            product.setProdSum(Double.parseDouble(SumField.getText()));
            product.setProdCount(Integer.parseInt(CountField.getText()));
            
            if (getStage().getTitle().equals("Add new Product")) {
                Product.addProduct(product);  
            } else {
                Product.editProduct(product, Sub.getIndex());
            }
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private Stage getStage() {
        return (Stage) IdField.getScene().getWindow();
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
