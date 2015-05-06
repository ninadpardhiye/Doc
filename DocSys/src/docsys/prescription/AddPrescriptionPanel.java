/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.prescription;

import docsys.animations.animationsBasic;
import docsys.patient.PatientBean;
import docsys.patient.PatientManager;
import docsys.patient.SearchPatientPanel;
import docsys.sql.SQLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Ninad
 */
public class AddPrescriptionPanel {
    
    public static GridPane addPrescriptionPane;
    public static VBox prescPane;
    public static GridPane prescTabPane;
    public static GridPane prescOintPane;
    public static GridPane prescSyrupPane;
    public static ListView prescTabListView;
    public static ListView prescOintListView;
    public static ListView prescSyrupListView;
    public static ObservableList prescTabList;
    public static ObservableList prescOintList;
    public static ObservableList prescSyrupList;
    public static CustomTextField prescTabName;
    public static TextField prescOintName;
    public static TextField prescSyrupName;
    public static VBox patientBox;
    public static CustomTextField patientNameText;
    public static TextField patientAgeText;
    public static TextField patientSexText;
    public static TextField patientPrevCondText;
    public static TextArea prescComment;
    public static PatientBean p = null;
    public static Button homeButton;
    public static HBox mainBox;
    
    public AddPrescriptionPanel()
    {
        try {
            initialize();
            ResultSet rs = SQLConnection.getFromTable("tablet");
            Vector a = new Vector();
            while(rs.next())
                a.add(rs.getString("t_name"));
            TextFields.bindAutoCompletion(prescTabName, a);
            rs = SQLConnection.getFromTable("ointment");
            a = new Vector();
            while(rs.next())
                a.add(rs.getString("o_name"));
            TextFields.bindAutoCompletion(prescOintName, a);
            rs = SQLConnection.getFromTable("syrup");
            a = new Vector();
            while(rs.next())
                a.add(rs.getString("s_name"));
            TextFields.bindAutoCompletion(prescSyrupName, a);
        } catch (SQLException ex) {
            Logger.getLogger(AddPrescriptionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialize()
    {
        
        
        
        addPrescriptionPane = new GridPane();
        addPrescriptionPane.setPadding(new Insets(12,0,0,12));
        addPrescriptionPane.setHgap(10);
        addPrescriptionPane.setVgap(10);
        addPrescriptionPane.setOpacity(0);
        addPrescriptionPane.setAlignment(Pos.CENTER);
        addPrescriptionPane.setGridLinesVisible(true);
        
        StackPane patientInfo = new StackPane();
        
        VBox patientInfoBox = new VBox();
        patientInfoBox.setPrefWidth(350);
        patientInfoBox.setPrefHeight(600);
        patientInfoBox.setMaxWidth(350);
        patientInfoBox.setMaxHeight(600);
        patientInfoBox.setSpacing(10);
        
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
        
        VBox imageBox = new VBox();
        imageBox.setSpacing(10);
        imageBox.getChildren().addAll(patientImage,iv1);
        
        Label patientName = new Label("Name");
        patientName.setTextFill(Color.web("#0076a3"));
        patientName.setFont(Font.font("Cambria", 18));
        patientName.setAlignment(Pos.CENTER_LEFT);
        
        patientNameText = new CustomTextField();
        patientNameText.setFont(Font.font("Cambria", 22));
        patientNameText.setPromptText("Name Of Patient");
        
        Label patientSex = new Label("Sex");
        patientSex.setTextFill(Color.web("#0076a3"));
        patientSex.setFont(Font.font("Cambria", 18));
        patientSex.setAlignment(Pos.CENTER_LEFT);
        
        patientSexText = new TextField();
        patientSexText.setFont(Font.font("Cambria", 22));
        patientSexText.setPromptText("Male/Female");
        
        Label patientAge = new Label("Age");
        patientAge.setTextFill(Color.web("#0076a3"));
        patientAge.setFont(Font.font("Cambria", 18));
        patientAge.setAlignment(Pos.CENTER_LEFT);
        
        patientAgeText = new TextField();
        patientAgeText.setFont(Font.font("Cambria", 22));
        patientAgeText.setPromptText("Age Of Patient");
        
        Label patientPrevCond = new Label("Previous Conditions or Info");
        patientPrevCond.setTextFill(Color.web("#0076a3"));
        patientPrevCond.setFont(Font.font("Cambria", 18));
        patientPrevCond.setAlignment(Pos.CENTER_LEFT);
        
        patientPrevCondText = new TextField();
        patientPrevCondText.setFont(Font.font("Cambria", 22));
        patientPrevCondText.setPromptText("Previous Conditions or Info Of Patient");
        
        patientInfoBox.getChildren().addAll(patientName,patientNameText,patientSex,patientSexText,patientAge,patientAgeText,patientPrevCond,patientPrevCondText);
        
        mainBox = new HBox();
        mainBox.setSpacing(10);
        mainBox.setDisable(true);
        mainBox.getChildren().addAll(imageBox,patientInfoBox);
        
        homeButton = new Button("Select Patient");
        homeButton.setCursor(Cursor.HAND);
        homeButton.setFont(Font.font("Cambria", 15));
        homeButton.setAlignment(Pos.CENTER);
        homeButton.setPrefSize(150, 30);
        homeButton.setOnAction((ActionEvent e) -> {
            
                animationsBasic.changePanel(animationsBasic.currentPane, SearchPatientPanel.searchPatientPane,SearchPatientPanel.normalSearchText);
                
            
        });
        
        patientInfo.getChildren().addAll(mainBox,homeButton);
        
        prescPane = new VBox();
        prescPane.setDisable(true);
        
            prescTabPane = new GridPane();
            prescTabPane.setPadding(new Insets(12,0,0,12));
            prescTabPane.setAlignment(Pos.CENTER);
            
                Label tabLabel = new Label("Tablet");
                tabLabel.setTextFill(Color.web("#0076a3"));
                tabLabel.setFont(Font.font("Cambria", 22));
            
                prescTabName = new CustomTextField();
            
                prescTabList = FXCollections.observableArrayList();
                
                
                prescTabListView = new ListView();
                prescTabListView.setMaxHeight(176);
                prescTabListView.setItems(prescTabList);
                
                Button prescTabAddButton = new Button("Add");
                prescTabAddButton.setCursor(Cursor.HAND);
                prescTabAddButton.setAlignment(Pos.CENTER);
                prescTabAddButton.setOnAction((ActionEvent e) -> {
                        {
                            prescTabList.add(prescTabName.getText());
                            prescTabName.setText("");
                        }
                    });
                
                Button prescTabRemButton = new Button("Remove");
                prescTabRemButton.setCursor(Cursor.HAND);
                prescTabRemButton.setAlignment(Pos.CENTER);
                prescTabRemButton.setOnAction((ActionEvent e) -> {
                    if(prescTabListView.getSelectionModel().getSelectedIndex()!=-1)
                            prescTabList.remove(prescTabListView.getSelectionModel().getSelectedIndex());
                    });

                prescTabPane.add(tabLabel, 0, 0, 2, 1);
                prescTabPane.add(prescTabName, 0, 1);
                prescTabPane.add(prescTabAddButton, 1, 1);
                prescTabPane.add(prescTabListView, 0, 2, 1, 3);
                prescTabPane.add(prescTabRemButton, 1, 2);
                GridPane.setHalignment(prescTabPane, HPos.CENTER);
                
            prescSyrupPane = new GridPane();
            prescSyrupPane.setPadding(new Insets(12,0,0,12));
            prescSyrupPane.setAlignment(Pos.CENTER);
            
                Label syrupLabel = new Label("Syrup");
                syrupLabel.setTextFill(Color.web("#0076a3"));
                syrupLabel.setFont(Font.font("Cambria", 22));
            
                prescSyrupName = new CustomTextField();
            
                prescSyrupList = FXCollections.observableArrayList();
                
                
                prescSyrupListView = new ListView();
                prescSyrupListView.setMaxHeight(176);
                prescSyrupListView.setItems(prescSyrupList);
                
                Button prescSyrupAddButton = new Button("Add");
                prescSyrupAddButton.setCursor(Cursor.HAND);
                prescSyrupAddButton.setAlignment(Pos.CENTER);
                prescSyrupAddButton.setOnAction((ActionEvent e) -> {
                        {
                            prescSyrupList.add(prescSyrupName.getText());
                            prescSyrupName.setText("");
                        }
                    });
                
                Button prescSyrupRemButton = new Button("Remove");
                prescSyrupRemButton.setCursor(Cursor.HAND);
                prescSyrupRemButton.setAlignment(Pos.CENTER);
                prescSyrupRemButton.setOnAction((ActionEvent e) -> {
                    if(prescSyrupListView.getSelectionModel().getSelectedIndex()!=-1)
                            prescSyrupList.remove(prescSyrupListView.getSelectionModel().getSelectedIndex());
                    });

                prescSyrupPane.add(syrupLabel, 0, 0, 2, 1);
                prescSyrupPane.add(prescSyrupName, 0, 1);
                prescSyrupPane.add(prescSyrupAddButton, 1, 1);
                prescSyrupPane.add(prescSyrupListView, 0, 2, 1, 3);
                prescSyrupPane.add(prescSyrupRemButton, 1, 2);
                GridPane.setHalignment(prescSyrupPane, HPos.CENTER);
                
            prescOintPane = new GridPane();
            prescOintPane.setPadding(new Insets(12,0,0,12));
            prescOintPane.setAlignment(Pos.CENTER);
            
                Label ointLabel = new Label("Ointment");
                ointLabel.setTextFill(Color.web("#0076a3"));
                ointLabel.setFont(Font.font("Cambria", 22));
                
                prescOintName = new CustomTextField();
            
                prescOintList = FXCollections.observableArrayList();
                
                
                prescOintListView = new ListView();
                prescOintListView.setMaxHeight(176);
                prescOintListView.setItems(prescOintList);
                
                Button prescOintAddButton = new Button("Add");
                prescOintAddButton.setCursor(Cursor.HAND);
                prescOintAddButton.setAlignment(Pos.CENTER);
                prescOintAddButton.setOnAction((ActionEvent e) -> {
                        {
                            prescOintList.add(prescOintName.getText());
                            prescOintName.setText("");
                        }
                    });
                
                Button prescOintRemButton = new Button("Remove");
                prescOintRemButton.setCursor(Cursor.HAND);
                prescOintRemButton.setAlignment(Pos.CENTER);
                prescOintRemButton.setOnAction((ActionEvent e) -> {
                    if(prescOintListView.getSelectionModel().getSelectedIndex()!=-1)
                            prescOintList.remove(prescOintListView.getSelectionModel().getSelectedIndex());
                    });

                prescOintPane.add(ointLabel, 0, 0, 2, 1);
                prescOintPane.add(prescOintName, 0, 1);
                prescOintPane.add(prescOintAddButton, 1, 1);
                prescOintPane.add(prescOintListView, 0, 2, 1, 3);
                prescOintPane.add(prescOintRemButton, 1, 2);
                GridPane.setHalignment(prescOintPane, HPos.CENTER);
        
                prescPane.getChildren().addAll(prescTabPane,prescSyrupPane,prescOintPane);
                
            prescComment = new TextArea();
            
            prescComment.setDisable(true);
                
        addPrescriptionPane.add(patientInfo, 0, 1, 1, 3);
        addPrescriptionPane.add(prescPane, 1, 1, 1, 5);
        addPrescriptionPane.add(prescComment, 0, 5);
        
    }
    
    public static void setPatientInfo(String id)
    {
        PatientBean p = PatientManager.getBean(id);
        patientNameText.setText(p.getP_name());
        patientAgeText.setText(p.getP_age());
        patientSexText.setText(p.getP_sex());
        
        homeButton.setDisable(true);
        homeButton.setVisible(false);
        mainBox.setDisable(false);
        prescPane.setDisable(false);
        prescComment.setDisable(false);
    }
    
    public static void removePatientInfo(String id)
    {
        
        patientNameText.clear();
        patientAgeText.clear();
        patientSexText.clear();
        
        homeButton.setDisable(false);
        homeButton.setVisible(true);
        mainBox.setDisable(true);
        prescPane.setDisable(true);
        prescComment.setDisable(true);
    }
    
}
