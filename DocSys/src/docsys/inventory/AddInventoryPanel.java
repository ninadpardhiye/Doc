/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.inventory;


import docsys.sql.SQLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;


/**
 *
 * @author Ninad
 */
public class AddInventoryPanel {

    public static GridPane addInventoryPane;
    public static CustomTextField searchText;
    public static TextField qtyText;
    public static ComboBox prodType;
    
    private final TableView<InventoryBean> table = new TableView<>();  
    private final ObservableList<InventoryBean> data =            FXCollections.observableArrayList();
    final HBox hb = new HBox();
    
    
    
    public AddInventoryPanel()
    {
        initialize();
    }
    
    private void initialize()
    {
        addInventoryPane = new GridPane();
        addInventoryPane.setPadding(new Insets(12,0,0,12));
        addInventoryPane.setHgap(10);
        addInventoryPane.setVgap(10);
        addInventoryPane.setOpacity(0);
        addInventoryPane.setAlignment(Pos.CENTER);
        
        final Label normalSearchLabel = new Label("Product Name");
        normalSearchLabel.setTextFill(Color.web("#0076a3"));
        normalSearchLabel.setFont(Font.font("Cambria", 22));
        
        searchText = new CustomTextField();
//        normalSearchText.setPrefWidth(100);
        searchText.setFont(Font.font("Cambria", 22));
        searchText.setPromptText("Enter name of inventory");
        
        
        final Label qtyLabel = new Label("Quantity");
        qtyLabel.setTextFill(Color.web("#0076a3"));
        qtyLabel.setFont(Font.font("Cambria", 22));
        
        qtyText = new TextField();
        qtyText.setFont(Font.font("Cambria", 22));
        qtyText.setPromptText(" Quantity of inventory");
        
        prodType = new ComboBox();
        ObservableList<String> cats =FXCollections.observableArrayList ();
        cats.addAll("Tablet","Ointment","Syrup");
        prodType.setItems(cats);
        prodType.setPrefHeight(42);
        
//        ObservableList data =FXCollections.observableArrayList ();
        
        Button addButton = new Button("Add To List");
        addButton.setCursor(Cursor.HAND);
        addButton.setPrefHeight(28);
        addButton.setPrefWidth(100);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                      data.add(new InventoryBean(searchText.getText(),qtyText.getText(),prodType.getSelectionModel().getSelectedItem().toString()));
            }
        });
        
        TextFields.bindAutoCompletion(searchText, "hye", "ada", "ads a");
        
        Object[] a = data.toArray();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(a[i].toString());
        }
        
        table.setMinWidth(750);
        table.setMaxWidth(750);
        
        table.setEditable(true); 
        TableColumn<InventoryBean, String> nameCol =         
                new TableColumn<>("Product Name");        
        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.65));
        nameCol.setCellValueFactory(            
                new PropertyValueFactory<>("prodName"));                
        nameCol.setCellFactory(TextFieldTableCell.<InventoryBean>forTableColumn());        
        nameCol.setOnEditCommit(            
                
                (TableColumn.CellEditEvent<InventoryBean, String> t) -> {
                TablePosition focusedCell = table.getFocusModel().getFocusedCell();    
                    ((InventoryBean) t.getTableView().getItems().get(                        
                            t.getTablePosition().getRow())                        
                            ).setProdName(t.getNewValue());     
                     Platform.runLater(new Runnable(){

            @Override
            public void run() {
                
                table.getFocusModel().focus(focusedCell);
                         System.out.println(table.getFocusModel().getFocusedCell());
                                 
            }
            
        });
           
                }
        
        );
        
        TableColumn<InventoryBean, String> typeCol =         
                new TableColumn<>("Product Type");        
        typeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        typeCol.setCellValueFactory(            
                new PropertyValueFactory<>("prodType"));                
        typeCol.setCellFactory(TextFieldTableCell.<InventoryBean>forTableColumn());        
        typeCol.setOnEditCommit(            
                
                (TableColumn.CellEditEvent<InventoryBean, String> t) -> {
                TablePosition focusedCell = table.getFocusModel().getFocusedCell();    
                    ((InventoryBean) t.getTableView().getItems().get(                        
                            t.getTablePosition().getRow())                        
                            ).setProdName(t.getNewValue());     
                     Platform.runLater(new Runnable(){

            @Override
            public void run() {
                
                table.getFocusModel().focus(focusedCell);
                         System.out.println(table.getFocusModel().getFocusedCell());
                                 
            }
            
        });
           
                }
        
        );
        
        
        TableColumn<InventoryBean, String> qtyCol =             new TableColumn<>("Quantity");        qtyCol.setMinWidth(100);        qtyCol.setCellValueFactory(            new PropertyValueFactory<>("prodQty"));       qtyCol.setCellFactory(TextFieldTableCell.<InventoryBean>forTableColumn());       qtyCol.setOnEditCommit(            (TableColumn.CellEditEvent<InventoryBean, String> t) -> {((InventoryBean) t.getTableView().getItems().get(                        t.getTablePosition().getRow())                        ).setProdQty(t.getNewValue());     }); 
        qtyCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        
        table.getSelectionModel().setCellSelectionEnabled(true);
        
        table.setItems(data);        table.getColumns().addAll(nameCol, typeCol, qtyCol); 
            
        
        Button addSubButton = new Button("Submit");
        addSubButton.setCursor(Cursor.HAND);
        addSubButton.setPrefHeight(28);
        addSubButton.setPrefWidth(100);
        addSubButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String col[] = {"i_id","i_prodname","i_prodqty","i_type","i_date"};
                String val[] = new String[5];
                String type = prodType.getSelectionModel().getSelectedItem().toString();
                switch(type)
                {
                    case "Tablet":
                        type = "t";
                        break;
                    case "Ointment":
                        type = "o";
                        break;
                    case "Syrup":
                        type = "s";
                        break;
                }
                      for (int i = 0; i < data.size(); i++) {
                          InventoryBean a = data.get(i);
                          val[0] = String.valueOf(getRandomId());
                          val[1] = a.getProdName();
                          val[2] = a.getProdQty();
                          GregorianCalendar t = new GregorianCalendar();
                          Date time = t.getTime();
                          val[3] = type;
                          val[4] = time.toString();
                          
                          SQLConnection.insertInTable("inventory", col, val);
                }
            }
        });
        
        
        
        
        
        
        addInventoryPane.add(normalSearchLabel, 1, 0);
        addInventoryPane.add(searchText, 1, 1);
        addInventoryPane.add(qtyLabel, 3, 0);
        addInventoryPane.add(qtyText, 3, 1);
        addInventoryPane.add(prodType, 4, 1);
        
        addInventoryPane.add(addButton, 5, 1);
        addInventoryPane.add(table, 0, 2, 6, 1);
        addInventoryPane.add(addSubButton, 4, 3, 6, 1);
        GridPane.setHalignment(addSubButton, HPos.CENTER);
        
        
        
        
    }
    
    public static int getRandomId()
    {
        int p_id;
        ResultSet rs = SQLConnection.getFromTable("inventory");
        boolean found = false;
        while(!found)
        {
            try {
                Random n = new Random();
                p_id = n.nextInt((999999-100000)+1)+100000;
                boolean exists = false;
                while(rs.next())
                {
                    exists = true;
                        if(p_id == Integer.parseInt(rs.getString("i_id")))
                        {
                            found = true;
                            return p_id;
                        }
                    }
                if(!exists)
                    return p_id;
            } catch (SQLException ex) {
                Logger.getLogger(AddInventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
}
