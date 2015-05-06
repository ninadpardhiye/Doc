/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys;

import docsys.animations.animationsBasic;
import docsys.home.homePanel;
import docsys.inventory.AddInventoryPanel;
import docsys.login.LoginPanel;
import docsys.patient.AddPatientPanel;
import docsys.patient.SearchPatientPanel;
import docsys.prescription.AddPrescriptionPanel;
import docsys.sql.SQLConnection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ninad
 */
public class DocSys extends Application {
    
    public static BorderPane root;
    
    @Override
    public void start(Stage primaryStage) {
        
        new LoginPanel();
        new NavigationPanel();
        new SQLConnection();
        new animationsBasic();
        new homePanel();
        new SearchPatientPanel();
        new AddPatientPanel();
        new AddPrescriptionPanel();
        
        new AddInventoryPanel();
        
        root = new BorderPane();
        root.setStyle("-fx-background-color: #D4ECF4;");
        root.setPadding(Insets.EMPTY);
        BorderPane.setAlignment(NavigationPanel.navigateBox, Pos.CENTER);
//        root.setCenter(homePanel.homePanel);
        root.setTop(NavigationPanel.navigateBox);
        root.setCenter(LoginPanel.loginPanel);
//        root.getChildren().addAll(homePanel.homePanel,LoginPanel.loginPanel);
//        javax.swing.SwingUtilities.invokeLater(new Runnable(){
//
//            @Override
//            public void run() {
//                try {
//                    java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
//                    Image image = ImageIO.read(new File("mainLogin.png"));
//                    java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(image);
//                    trayIcon.addActionListener(event -> {
//                        Platform.exit();
//                        tray.remove(trayIcon);
//                    });
//                    tray.add(trayIcon);
//                } catch (IOException ex) {
//                    Logger.getLogger(DocSys.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (AWTException ex) {
//                    Logger.getLogger(DocSys.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            
//        });
        
        
        Scene scene = new Scene(root, 350, 600);
        
        primaryStage.setTitle("DocSys");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
