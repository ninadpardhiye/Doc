/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.patient;

import docsys.animations.animationsBasic;
import docsys.home.homePanel;
import static docsys.patient.PatientManager.getRandomId;
import docsys.sql.SQLConnection;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Ninad
 */
public class AddPatientPanel {
    
    public static GridPane addPatientPane;
    public static TextField patientNameText;
    public static TextField patientSexText;
    public static TextField patientAgeText;
    public static TextArea patientAddressText;
    public static TextField patientContactText;
    public static TextArea patientPrevCondText;
    public static Button addPatientButton;
    
    public AddPatientPanel()
    {
     initialize();   
    }
    
    private void initialize()
    {
        addPatientPane = new GridPane();
        addPatientPane.setPadding(new Insets(12,0,0,12));
        addPatientPane.setHgap(10);
        addPatientPane.setVgap(10);
        addPatientPane.setOpacity(0);
        addPatientPane.setAlignment(Pos.CENTER);
//        addPatientPane.setGridLinesVisible(true);
        
        Label patientImage = new Label("Photo");
        patientImage.setTextFill(Color.web("#0076a3"));
        patientImage.setFont(Font.font("Cambria", 18));
        
        Image image = new Image("file:mainLogin.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(300);
        iv1.setFitHeight(400);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        
        HBox imageButtons = new HBox();
        imageButtons.setSpacing(20);
        imageButtons.setPadding(new Insets(12,12,12,12));
        imageButtons.setAlignment(Pos.CENTER);
        
        Button uploadImage = new Button("Upload");
        uploadImage.setPrefHeight(30);
        uploadImage.setPrefWidth(100);
        uploadImage.setCursor(Cursor.HAND);
        
        Button removeImage = new Button("Remove");
        removeImage.setPrefHeight(30);
        removeImage.setPrefWidth(100);
        removeImage.setCursor(Cursor.HAND);
        
        Label patientName = new Label("Name");
        patientName.setTextFill(Color.web("#0076a3"));
        patientName.setFont(Font.font("Cambria", 18));
        
        patientNameText = new TextField();
        patientNameText.setFont(Font.font("Cambria", 22));
        patientNameText.setPromptText("Name Of Patient");
        
        Label patientSex = new Label("Sex");
        patientSex.setTextFill(Color.web("#0076a3"));
        patientSex.setFont(Font.font("Cambria", 18));
        
        patientSexText = new TextField();
        patientSexText.setFont(Font.font("Cambria", 22));
        patientSexText.setPromptText("Male/Female");
        
        Label patientAge = new Label("Age");
        patientAge.setTextFill(Color.web("#0076a3"));
        patientAge.setFont(Font.font("Cambria", 18));
        
        patientAgeText = new TextField();
        patientAgeText.setFont(Font.font("Cambria", 22));
        patientAgeText.setPromptText("Age Of Patient");
        
        Label patientAddress = new Label("Address");
        patientAddress.setTextFill(Color.web("#0076a3"));
        patientAddress.setFont(Font.font("Cambria", 18));
        
        patientAddressText = new TextArea();
        patientAddressText.setFont(Font.font("Cambria", 22));
        patientAddressText.setPromptText("Address Of Patient");
        patientAddressText.setPrefColumnCount(2);
        patientAddressText.setPrefRowCount(5);
        
        Label patientContact = new Label("Contact");
        patientContact.setTextFill(Color.web("#0076a3"));
        patientContact.setFont(Font.font("Cambria", 18));
        
        patientContactText = new TextField();
        patientContactText.setFont(Font.font("Cambria", 22));
        patientContactText.setPromptText("Contact Of Patient");
        
        Label patientPrevCond = new Label("Previous Conditions or Info");
        patientPrevCond.setTextFill(Color.web("#0076a3"));
        patientPrevCond.setFont(Font.font("Cambria", 18));
        
        patientPrevCondText = new TextArea();
        patientPrevCondText.setFont(Font.font("Cambria", 22));
        patientPrevCondText.setPromptText("Previous Conditions or Info Of Patient");
        
        addPatientButton = new Button("Add Patient");
        addPatientButton.setPrefHeight(30);
        addPatientButton.setPrefWidth(100);
        addPatientButton.setCursor(Cursor.HAND);
        addPatientButton.setOnAction((ActionEvent e) -> {
            if(PatientManager.checkText())
                PatientManager.addPatient();
        });
        
        imageButtons.getChildren().addAll(uploadImage,removeImage);
        
        addPatientPane.add(patientImage, 0, 0);
        addPatientPane.add(iv1, 0, 1, 1, 5);
        addPatientPane.add(imageButtons, 0, 6);
        
        addPatientPane.add(patientName, 1, 1);
        addPatientPane.add(patientNameText, 2, 1);
        addPatientPane.add(patientAge, 1, 2);
        addPatientPane.add(patientAgeText, 2, 2);
        addPatientPane.add(patientSex, 1, 3);
        addPatientPane.add(patientSexText, 2, 3);
        addPatientPane.add(patientContact, 1, 4);
        addPatientPane.add(patientContactText, 2, 4);
        addPatientPane.add(patientAddress, 1, 5);
        addPatientPane.add(patientAddressText, 2, 5);
        
        addPatientPane.add(patientPrevCond, 3, 1, 2, 1);
        addPatientPane.add(patientPrevCondText, 3, 2, 2, 4);
        
        addPatientPane.add(addPatientButton, 4, 7);
        GridPane.setHalignment(addPatientButton, HPos.CENTER);
//        GridPane.setHalignment(imageButtons, HPos.CENTER);
    }
    
    public static void setPatientInfo(String id)
    {
        PatientBean p = PatientManager.getBean(id);
        patientNameText.setText(p.getP_name());
        patientAgeText.setText(p.getP_age());
        patientSexText.setText(p.getP_sex());
        patientContactText.setText(p.getP_contact());
        patientAddressText.setText(p.getP_addr());
        
        addPatientButton.setDisable(true);
        
        Button updatePatientButton = new Button("Update Patient");
        updatePatientButton.setPrefHeight(30);
        updatePatientButton.setPrefWidth(100);
        updatePatientButton.setCursor(Cursor.HAND);
        updatePatientButton.setOnAction((ActionEvent e) -> {
            if(PatientManager.checkText())
            {
                String val[] = new String[6];
            
            val[0] = AddPatientPanel.patientNameText.getText();
            val[1] = AddPatientPanel.patientSexText.getText();
            val[2] = AddPatientPanel.patientAgeText.getText();
            val[3] = AddPatientPanel.patientAddressText.getText();
            val[4] = AddPatientPanel.patientContactText.getText();
            val[5] = AddPatientPanel.patientPrevCondText.getText();
            String col[] = new String[6];
            
            col[0] = "p_name";
            col[1] = "p_sex";
            col[2] = "p_age";
            col[3] = "p_addr";
            col[4] = "p_contact";
            col[5] = "p_prevcond";
                
                
                System.out.println(val);
            SQLConnection.updateInTable("patient", col, val, "p_id", id);
            
            }
        });
        
        addPatientPane.add(updatePatientButton, 4, 7);
        GridPane.setHalignment(updatePatientButton, HPos.CENTER);
    }
    
}
