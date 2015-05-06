/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.login;

import docsys.NavigationPanel;
import docsys.animations.animationsBasic;
import docsys.home.homePanel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Ninad
 */
public class LoginPanel {
    
    public static VBox loginPanel;
    public static TextField usernameText ;
    public static PasswordField passwordText ;
    public static Button loginButton;
    
    public LoginPanel(){
        initialize();
    }
    
    private void initialize(){
        
        loginPanel = new VBox();
        loginPanel.setPrefWidth(350);
        loginPanel.setPrefHeight(600);
        loginPanel.setMaxWidth(350);
        loginPanel.setMaxHeight(600);
        loginPanel.setSpacing(10);
        loginPanel.setAlignment(Pos.CENTER);
        loginPanel.setStyle("-fx-background-color: \n" +
"        #000000,\n" +
"        linear-gradient(#7ebcea, #2f4b8f),\n" +
"        linear-gradient(#426ab7, #263e75),\n" +
"        linear-gradient(#395cab, #223768);"
                + "  -fx-background-insets: 0,1,2,3;\n" +
"    -fx-background-radius: 3,2,2,2;\n" +
"    -fx-padding: 12 30 12 30;\n" +
"    -fx-text-fill: white;\n" +
"    -fx-font-size: 12px;");
        
        
//        Image image = new Image(getClass().getResourceAsStream("mainLogin.jpg"));
        Image image = new Image("file:mainLogin.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(300);
        iv1.setFitHeight(400);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        
        usernameText = new TextField("");
        usernameText.setAlignment(Pos.CENTER);
        usernameText.setFont(Font.font("Cambria", 15));
        usernameText.maxWidthProperty().bind(loginPanel.widthProperty().subtract(20));
        usernameText.setPromptText("Enter UserName Here");
        
        passwordText = new PasswordField();
        passwordText.setAlignment(Pos.CENTER);
        passwordText.setFont(Font.font("Cambria", 15));
        passwordText.maxWidthProperty().bind(loginPanel.widthProperty().subtract(20));
        passwordText.setPromptText("Enter Password Here");
        
        loginButton = new Button("Login");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setFont(Font.font("Cambria", 15));
        loginButton.setAlignment(Pos.CENTER);
        loginButton.setPrefSize(150, 30);
        DropShadow shadow = new DropShadow();
        loginButton.setStyle("-fx-background-color: \n" +
"        #090a0c,\n" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
"        linear-gradient(#20262b, #191d22),\n" +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
"    -fx-background-radius: 5,4,3,5;\n" +
"    -fx-background-insets: 0,1,2,0;\n" +
"    -fx-text-fill: white;\n" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
"    -fx-font-family: \"Arial\";\n" +
"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
"    -fx-font-size: 12px;\n" +
"    -fx-padding: 10 20 10 20;");
        loginButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                loginButton.setEffect(shadow); 
        }); 
        
        loginButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                loginButton.setEffect(null); 
        });
        loginButton.setOnAction((ActionEvent e) -> {
            if(loginController.login(usernameText.getText(),passwordText.getText()))
            {
                NavigationPanel.navigateBox.setVisible(true);
                animationsBasic.changePanel(loginPanel, homePanel.homePanel);
                
            }
        });
        
        loginPanel.getChildren().addAll(iv1,usernameText,passwordText,loginButton);
        
    }
    
}