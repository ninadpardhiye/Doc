/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.patient;

import docsys.animations.animationsBasic;
import docsys.prescription.AddPrescriptionPanel;
import docsys.sql.SQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 *
 * @author Ninad
 */
public class SearchPatientPanel {

    public static GridPane searchPatientPane;
    public static ObservableList data;
    public static TextField normalSearchText;
    public static ComboBox searchCategories;
    public static String selectedPatient;
    
    public SearchPatientPanel()
    {
        initialize();
        fillTable();
    }
    
    private void initialize()
    {
        
        searchPatientPane = new GridPane();
        searchPatientPane.setPadding(new Insets(12,0,0,12));
        searchPatientPane.setHgap(10);
        searchPatientPane.setVgap(10);
        searchPatientPane.setOpacity(0);
        searchPatientPane.setAlignment(Pos.CENTER);
        
        final Label normalSearchLabel = new Label("Search");
        normalSearchLabel.setTextFill(Color.web("#0076a3"));
        normalSearchLabel.setFont(Font.font("Cambria", 22));
        
        normalSearchText = new TextField("");
//        normalSearchText.setPrefWidth(100);
        normalSearchText.setFont(Font.font("Cambria", 22));
        
        searchCategories = new ComboBox();
        ObservableList<String> cats =FXCollections.observableArrayList ();
        cats.addAll("ID","Name","Age","Sex","Address","Contact");
        searchCategories.setItems(cats);
        searchCategories.setPrefHeight(42);
        
        Button normalSearchButton = new Button("Search");
        normalSearchButton.setCursor(Cursor.HAND);
        normalSearchButton.setPrefHeight(28);
        normalSearchButton.setPrefWidth(100);
        normalSearchButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                searchPatient();
                
            }
        });
        
        TableView table = new TableView();
        
        Callback<TableColumn, TableCell> cellFactory =
        new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<PatientBean, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setGraphic(null);
                    }

                    private String getString() {
                        return getItem() == null ? "" : getItem().toString();
                    }
                };
               
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
//                        if (event.getClickCount() > 1) {
//                            System.out.println("double clicked!");
                            PatientBean person = (PatientBean) table.getItems().get(((TableCell)event.getSource()).getIndex());
//                            System.out.println(person.getIsbn());
                            selectedPatient = person.getP_id();
//                            TableRow c = (TableRow) event.getSource();
//                            System.out.println("Cell text: " + c.getText());
                            if(event.getClickCount() > 2)
                            {
                                AddPrescriptionPanel.setPatientInfo(selectedPatient);
                                animationsBasic.changePanel(searchPatientPane, AddPrescriptionPanel.addPrescriptionPane);
                            }
//                        }
                    }
                });
                
                return cell;
            }
        };
        
        TableColumn idCol = new TableColumn("Patient ID");
        idCol.setCellFactory(cellFactory);
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellFactory(cellFactory);
        TableColumn sexCol = new TableColumn("Sex");
        sexCol.setCellFactory(cellFactory);
        TableColumn ageCol = new TableColumn("Age");
        ageCol.setCellFactory(cellFactory);
        TableColumn addrCol = new TableColumn("Address");
        addrCol.setCellFactory(cellFactory);
        TableColumn contactCol = new TableColumn("Contact");
        contactCol.setCellFactory(cellFactory);
        
        data =FXCollections.observableArrayList ();
//        PatientBean b = PatientManager.getBean("4356346457");
//        data.add(b);
        
        
        
        idCol.setCellValueFactory(new PropertyValueFactory<PatientBean,String>("p_id"));
        idCol.minWidthProperty().bind(table.widthProperty().divide(12));
        nameCol.setCellValueFactory(new PropertyValueFactory<PatientBean,String>("p_name"));
        nameCol.minWidthProperty().bind(table.widthProperty().divide(12));
        sexCol.setCellValueFactory(new PropertyValueFactory<PatientBean,String>("p_sex"));
        sexCol.minWidthProperty().bind(table.widthProperty().divide(6));
        ageCol.setCellValueFactory(new PropertyValueFactory<PatientBean,String>("p_age"));
        ageCol.minWidthProperty().bind(table.widthProperty().divide(6));
        addrCol.setCellValueFactory(new PropertyValueFactory<PatientBean,String>("p_addr"));
        addrCol.minWidthProperty().bind(table.widthProperty().divide(12));
        contactCol.setCellValueFactory(new PropertyValueFactory<PatientBean,String>("p_contact"));
        contactCol.minWidthProperty().bind(table.widthProperty().divide(6));
        table.getColumns().addAll(idCol,nameCol,sexCol,ageCol,addrCol,contactCol);
        table.setItems(data);
        
        searchPatientPane.add(normalSearchLabel, 1, 1);
        searchPatientPane.add(normalSearchText, 2, 1);
        searchPatientPane.add(searchCategories, 3, 1);
        searchPatientPane.add(normalSearchButton, 4, 1);
        searchPatientPane.add(table, 2, 2);
        
    }
    
    public static void fillTable()
    {
        try {
            ResultSet rs = SQLConnection.getFromTable("patient");
            while(rs.next())
            {
                data.add(PatientManager.getBean(rs.getString("p_id")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SearchPatientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchPatient()
    {
        if(normalSearchText.getText().equalsIgnoreCase("")||searchCategories.getSelectionModel().getSelectedIndex()==-1)
        {
            
//            Notifications.create().title("Search Patient").text("Please Fill Criteria To Search...").hideAfter(new Duration(1500)).showInformation();
        }
        else
        {
            try {
                String crit="";
                switch(searchCategories.getSelectionModel().getSelectedItem().toString())
                {
                    case "ID":
                        crit="p_id";
                        break;
                    case "Name":
                        crit="p_name";
                        break;
                    case "Age":
                        crit="p_age";
                        break;
                    case "Sex":
                        crit="p_sex";
                        break;
                    case "Address":
                        crit="p_addr";
                        break;
                    case "Contact":
                        crit="p_contact";
                        break;
                }
                PreparedStatement stmt = SQLConnection.con.prepareStatement("select * from patient where "+crit+" like '%"+normalSearchText.getText()+"%'");
//                    stmt.setString(1, normalSearchText.getText());
                    ResultSet rs = stmt.executeQuery();
                    boolean exists = false;
                    while(rs.next())
                    {
                        exists = true;
                        data.clear();
                            data.add(PatientManager.getBean(rs.getString("p_id")));
                        
                    }
                    if(!exists)
                    {
                        data.clear();
//                        Notifications.create().title("Search Patient").text("No Entries Found For Given Criteria...").hideAfter(new Duration(1500)).showInformation();
                    }
            } catch (SQLException ex) {
                Logger.getLogger(SearchPatientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
