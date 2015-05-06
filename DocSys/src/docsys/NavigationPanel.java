/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys;

import docsys.animations.animationsBasic;
import docsys.home.homePanel;
import docsys.inventory.AddInventoryPanel;
import docsys.patient.AddPatientPanel;
import docsys.patient.SearchPatientPanel;
import docsys.prescription.AddPrescriptionPanel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author Ninad
 */
public class NavigationPanel {
    
    public static HBox navigateBox;
    
    public NavigationPanel()
    {
        initialize();
    }
    
    private void initialize()
    {
        
        navigateBox = new HBox();
        navigateBox.setStyle("-fx-background-color: #657CDA;");
        navigateBox.setPrefWidth(500);
        navigateBox.setPrefHeight(50);
        navigateBox.setMaxWidth(1000);
        navigateBox.setMaxHeight(50);
        navigateBox.setSpacing(10);
        navigateBox.setVisible(false);
        navigateBox.setAlignment(Pos.CENTER);
        
        
        
        Button homeButton = new Button("Home");
        homeButton.setCursor(Cursor.HAND);
        homeButton.setFont(Font.font("Cambria", 15));
        homeButton.setAlignment(Pos.CENTER);
        homeButton.setPrefSize(150, 30);
        homeButton.setOnAction((ActionEvent e) -> {
            if(animationsBasic.currentPane!=homePanel.homePanel)
            {
                animationsBasic.changePanel(animationsBasic.currentPane, homePanel.homePanel,homePanel.b);
                
            }
        });
        
        Button addPatientButton = new Button("Add Patient");
        addPatientButton.setCursor(Cursor.HAND);
        addPatientButton.setFont(Font.font("Cambria", 15));
        addPatientButton.setAlignment(Pos.CENTER);
        addPatientButton.setPrefSize(150, 30);
        addPatientButton.setOnAction((ActionEvent e) -> {
            if(animationsBasic.currentPane!=AddPatientPanel.addPatientPane)
            {
                animationsBasic.changePanel(animationsBasic.currentPane, AddPatientPanel.addPatientPane,AddPatientPanel.patientNameText);
                
            }
        });
        
        Button searchPatientButton = new Button("Search Patient");
        searchPatientButton.setCursor(Cursor.HAND);
        searchPatientButton.setFont(Font.font("Cambria", 15));
        searchPatientButton.setAlignment(Pos.CENTER);
        searchPatientButton.setPrefSize(150, 30);
        searchPatientButton.setOnAction((ActionEvent e) -> {
            if(animationsBasic.currentPane!=SearchPatientPanel.searchPatientPane)
            {
                animationsBasic.changePanel(animationsBasic.currentPane, SearchPatientPanel.searchPatientPane, SearchPatientPanel.normalSearchText);
                
            }
        });
        
        Button addInventoryButton = new Button("Add Inventory");
        addInventoryButton.setCursor(Cursor.HAND);
        addInventoryButton.setFont(Font.font("Cambria", 15));
        addInventoryButton.setAlignment(Pos.CENTER);
        addInventoryButton.setPrefSize(150, 30);
        addInventoryButton.setOnAction((ActionEvent e) -> {
            if(animationsBasic.currentPane!=AddInventoryPanel.addInventoryPane)
            {
                animationsBasic.changePanel(animationsBasic.currentPane, AddInventoryPanel.addInventoryPane);
                
            }
        });
        
        Button addPrescriptionButton = new Button("Add Prescription");
        addPrescriptionButton.setCursor(Cursor.HAND);
        addPrescriptionButton.setFont(Font.font("Cambria", 15));
        addPrescriptionButton.setAlignment(Pos.CENTER);
        addPrescriptionButton.setPrefSize(150, 30);
        addPrescriptionButton.setOnAction((ActionEvent e) -> {
            if(animationsBasic.currentPane!=AddPrescriptionPanel.addPrescriptionPane)
            {
                animationsBasic.changePanel(animationsBasic.currentPane, AddPrescriptionPanel.addPrescriptionPane);
                
            }
        });
        
        navigateBox.getChildren().addAll(homeButton, addPatientButton, searchPatientButton, addInventoryButton, addPrescriptionButton);
    }
    
}