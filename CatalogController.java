/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kourse.AllInfoController;


public class CatalogController implements Initializable {
    
    private final ObservableList <Product> productData = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Product> catalogTable;
    @FXML
    private TableColumn<Product, String> ProdId;
    @FXML
    private TableColumn<Product, String> ProdName;
    @FXML
    private TableColumn<Product, String> ProdSum;
    @FXML
    private TableColumn<Product, String> ProdCount;
    
    @FXML
    private Label IdLabel;
     @FXML
    private Label NameLabel;
      @FXML
    private Label SumLabel;
       @FXML
    private Label CountLabel;
       
    private void AddProducts(){
        File info = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\FX\\src\\fx\\file.txt");
        Scanner scan;
        String[] free;
        try {
            free = new String[4];
            int i=0;
            scan = new Scanner(info);
            while(scan.hasNext()){
                free=scan.nextLine().split(" ");
                
                productData.add(new Product(Integer.parseInt(free[0]), free[1], Double.parseDouble(free[2]), Integer.parseInt(free[3])));
                    ProdId.setCellValueFactory(new PropertyValueFactory<>("productId"));
                    ProdName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                    ProdSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
                    ProdCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
            }
            catalogTable.setItems(productData);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AllInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showProductDetails(Product product){
        if(product!=null){
            IdLabel.setText(product.getProdId().toString());
            NameLabel.setText(product.getProdName());
            SumLabel.setText(product.getProdSum().toString());
            CountLabel.setText(product.getProdCount().toString());
        }else{
            IdLabel.setText("");
            NameLabel.setText("");
            SumLabel.setText("");
            CountLabel.setText("");
        }
    }   
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddProducts();
        showProductDetails(null);
        
        productData.add(new Product(1, "Chair",1500.00,2));
        ProdId.setCellValueFactory(new PropertyValueFactory<>("ProdId"));
        ProdName.setCellValueFactory(new PropertyValueFactory<>("ProdName"));
        ProdSum.setCellValueFactory(new PropertyValueFactory<>("ProdSum"));
        ProdCount.setCellValueFactory(new PropertyValueFactory<>("ProdCount"));
        catalogTable.setItems(productData);
        
        
        catalogTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue)->showProductDetails(newValue));
    }
    
    public boolean showProductEditDialog(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditProductController.class.getResource("EditProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
        
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
        
            EditProductController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
        
            dialogStage.showAndWait();
        
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showProductAddDialog(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditProductController.class.getResource("EditProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
        
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add new Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
        
            EditProductController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
        
            dialogStage.showAndWait();
        
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @FXML
    private void handleNewProduct() throws IOException {
        
        Product tempProduct = new Product();
        
        boolean okClicked = showProductAddDialog(tempProduct);
        if (okClicked) {
            productData.add(tempProduct);
        } 
        
    }
    
    @FXML
    private void handleEditProduct(){
        Product selectedProduct = catalogTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
            Sub.setIndex(selectedIndex);
            boolean okClicked = showProductEditDialog(selectedProduct);
            if (okClicked) {
                productData.set(selectedIndex, selectedProduct);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран продукт");
            alert.setContentText("Выберите продукт в таблице");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteProduct() throws IOException{
        int selectedIndex=catalogTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
        catalogTable.getItems().remove(selectedIndex);
        Product.deleteProduct(selectedIndex);
        }
        else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Ошибка");
        alert.setHeaderText("Не выбран товар");
        alert.setContentText("Выберите товар для удаления");

        alert.showAndWait();
        }
    }
    
}
