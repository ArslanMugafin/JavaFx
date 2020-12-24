/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Product {
    String ProdName;
    Integer ProdId,ProdCount;
    Double ProdSum;
    
    public Product(Integer ProdId, String ProdName, Double ProdSum, Integer ProdCount){
        this.ProdId = ProdId;
        this.ProdName = ProdName;
        this.ProdSum = ProdSum;
        this.ProdCount = ProdCount;
    }
    
    public Product(){
        
    }
    
    public String getProdName(){
        return ProdName;
    }
    public void setProdName(String ProdName){
        this.ProdName = ProdName;
    }
    
    public Integer getProdId(){
        return ProdId;
    }
    public void setProdId(Integer ProdId){
        this.ProdId = ProdId;
    }
    
    public Double getProdSum(){
        return ProdSum;
    }
    public void setProdSum(Double ProdSum){
        this.ProdSum = ProdSum;
    }
    
    public Integer getProdCount(){
        return ProdCount;
    }
    public void setProdCount(Integer ProdCount){
        this.ProdCount = ProdCount;
    }
    
     public static void deleteProduct(Integer index) throws FileNotFoundException, IOException {
        File sourceFile = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\FX\\src\\fx\\file.txt");
        File outputFile = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\FX\\src\\fx\\file2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        String line;
        int n = -1;
        line = reader.readLine();
        while (line != null) {
            n += 1;
            if (n != index) {
                writer.write(line);
                
                if ((line = reader.readLine()) != null)  {
                    writer.newLine();
                }    
            } else {
                if ((line = reader.readLine()) != null) {
                    writer.newLine();
                }
            }
        }
        reader.close();
        writer.close();
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }
     
     public static void addProduct(Product product) throws IOException {
        FileWriter witer = new FileWriter("C:\\Users\\User\\Documents\\NetBeansProjects\\FX\\src\\fx\\file.txt", true);
        try (BufferedWriter bw = new BufferedWriter(witer)) {
            bw.newLine();
            bw.write(product.ProdId.toString() + "," + product.ProdName + "," + product.ProdSum.toString() + "," + product.ProdCount.toString());
        }
    }
    
     public static void editProduct(Product product, Integer index) throws IOException {
        File sourceFile = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\FX\\src\\fx\\file.txt");
        File outputFile = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\FX\\src\\fx\\file2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        String line;
        int n = -1;
        line = reader.readLine();
        while (line != null) {
            n += 1;
            if (n != index) {
                writer.write(line);
                if ((line = reader.readLine()) != null) {
                    writer.newLine();
                }  
            } else {
                writer.write(product.ProdId.toString() + "," + product.ProdName + "," + product.ProdSum.toString() + "," + product.ProdCount.toString());
                if ((line = reader.readLine()) != null) {
                    writer.newLine();
                }
            }
        }
        reader.close();
        writer.close();
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }
     
}
