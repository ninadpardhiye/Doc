/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.home;

import docsys.animations.animationsBasic;
import docsys.login.LoginPanel;
import static docsys.login.LoginPanel.loginPanel;
import docsys.patient.AddPatientPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ninad
 */
public class homePanel {
    
    public static VBox viewPatientInfo;
    public static VBox addPrescriptionInfo;
    public static VBox addPatientInfo;
    public static GridPane homePanel;
    public static Button b;
    
    public homePanel()
    {
        initialize();
    }
    
    private void initialize()
    {
        homePanel = new GridPane();
        homePanel.setPadding(new Insets(12,0,0,12));
        homePanel.setHgap(10);
        homePanel.setVgap(10);
        homePanel.setOpacity(0);
        homePanel.setAlignment(Pos.CENTER);
        
        addPatientInfo = new VBox();
        addPatientInfo.setStyle("-fx-background-color: #657CDA;");
        addPatientInfo.setPrefWidth(250);
        addPatientInfo.setPrefHeight(250);
        addPatientInfo.setMaxWidth(350);
        addPatientInfo.setMaxHeight(350);
        addPatientInfo.setSpacing(10);
        addPatientInfo.setOnMousePressed( new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                animationsBasic.homePaneonPress(addPatientInfo);
            }
    
    });
        addPatientInfo.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                animationsBasic.homePaneonRelease(addPatientInfo, AddPatientPanel.addPatientPane);
            }
        });
        b = new Button();
        b.onMousePressedProperty().bind(addPatientInfo.onMousePressedProperty());
        b.onMouseReleasedProperty().bind(addPatientInfo.onMouseReleasedProperty());
        
        b.setOnAction((ActionEvent e) -> {
            System.out.println("asdh");
        });
        
        addPrescriptionInfo = new VBox();
        addPrescriptionInfo.setStyle("-fx-background-color: #657CDA;");
        addPrescriptionInfo.setPrefWidth(250);
        addPrescriptionInfo.setPrefHeight(250);
        addPrescriptionInfo.setMaxWidth(350);
        addPrescriptionInfo.setMaxHeight(350);
        addPrescriptionInfo.setSpacing(10);
        addPrescriptionInfo.setOnMousePressed( new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                animationsBasic.homePaneonPress(addPrescriptionInfo);
            }
    
    });
        addPrescriptionInfo.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                animationsBasic.homePaneonRelease(addPrescriptionInfo,LoginPanel.loginPanel);
            }
        });
        
        addPatientInfo.getChildren().addAll(b);
        
        homePanel.add(addPatientInfo, 0, 0);
        homePanel.add(addPrescriptionInfo, 1, 0);
        
    }
    
}
