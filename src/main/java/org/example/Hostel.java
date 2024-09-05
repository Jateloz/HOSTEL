package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;

import java.text.NumberFormat;

public class Hostel extends Application {
    //the attributes
    private int noOfRooms;
    private TenantList list;

    //width and height of GUI stored as constants
    private final int WIDTH = 800;
    private final int HEIGHT = 500;

    //visual components
    private final Label headingLabel = new Label("Hostel Application");
    private final Label roomLabel1 = new Label("Room");
    private final TextField roomField1 = new TextField();
    private final Label nameLabel = new Label("Name");
    private final TextField nameField = new TextField();
    private final Button addButton = new Button("Add Tenant");
    private final Button displayButton =  new Button("Display Tenants");
    private final Button removeButton = new Button("Remove Tenant");
    private final Button saveAndQuitButton = new Button("Save and Quit");
    private final Button searchButton = new Button("Search");
    private final TextArea displayArea1 = new TextArea();
    private final Label roomLabel2 = new Label("Room");
    private final TextField roomField2 = new TextField();
    private final Label monthLabel = new Label("Month");
    private final TextField monthField =  new TextField();
    private final Label amountLabel = new Label("Amount");
    private final TextField amountField = new TextField();
    private final Button paymentButton = new Button("Make Payment");
    private final Button listButton = new Button("List Payments");
    private final TextArea displayArea2 = new TextArea();

    @Override
    public void start(Stage stage) throws Exception {
        noOfRooms = getNumberOfRooms(); //call private method

        //initialise tenant list
        list = new TenantList(noOfRooms);

        //create 4 hBoxes
        HBox roomDetails = new HBox(10);
        HBox tenantButtons =  new HBox(10);
        HBox searchButtonHBox = new HBox(10);
        HBox paymentDetails  = new HBox(10);
        HBox paymentButtons = new HBox(10);

        //add components to the HBoxes
        roomDetails.getChildren().addAll(roomLabel1,roomField1,nameLabel,nameField);
        tenantButtons.getChildren().addAll(addButton,displayButton,removeButton,saveAndQuitButton);
        searchButtonHBox.getChildren().add(searchButton);
        paymentDetails.getChildren().addAll(roomLabel2,roomField2,monthLabel,monthField,amountLabel,amountField);
        paymentButtons.getChildren().addAll(paymentButton,listButton);

        //create a VBox
        VBox root = new VBox(10);

        //add all components to VBox
        root.getChildren().addAll(headingLabel,roomDetails,tenantButtons,searchButtonHBox,displayArea1,paymentDetails,paymentButtons,displayArea2);

        //create the scene
        Scene scene = new Scene(root, Color.LIGHTBLUE);

        //set the font of the heading
        Font font = new Font("Calibri",40);
        headingLabel.setFont(font);

        //set alignment of HBoxes
        roomDetails.setAlignment(Pos.CENTER);
        tenantButtons.setAlignment(Pos.CENTER);
        paymentDetails.setAlignment(Pos.CENTER);
        paymentButtons.setAlignment(Pos.CENTER);
        searchButtonHBox.setAlignment(Pos.CENTER);

        //set alignment of VBox
        root.setAlignment(Pos.CENTER);

        //set min and max width of the components
        roomField1.setMinWidth(50);
        roomField2.setMaxWidth(50);

        roomDetails.setMinWidth(WIDTH);
        roomDetails.setMaxWidth(WIDTH);

        tenantButtons.setMinWidth(WIDTH);
        tenantButtons.setMaxWidth(WIDTH);

        paymentDetails.setMinWidth(WIDTH);
        paymentDetails.setMaxWidth(WIDTH);

        paymentButtons.setMinWidth(WIDTH);
        paymentButtons.setMaxWidth(WIDTH);

        root.setMinSize(WIDTH,HEIGHT);
        root.setMaxSize(WIDTH,HEIGHT);

        displayArea1.setMaxSize(WIDTH-80,HEIGHT/5);
        displayArea2.setMaxSize(WIDTH-80,HEIGHT/5);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        //customise the visual components
        displayArea1.setEditable(false);
        displayArea2.setEditable(false);

        //customise the VBox border and background
        BorderStroke style = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(0),new BorderWidths(2));
        root.setBorder(new Border(style));
        root.setBackground(Background.EMPTY);

        //customise the buttons
        addButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,new CornerRadii(10),Insets.EMPTY)));
        displayButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,new CornerRadii(10),Insets.EMPTY)));
        removeButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,new CornerRadii(10),Insets.EMPTY)));
        saveAndQuitButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,new CornerRadii(10),Insets.EMPTY)));
        paymentButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,new CornerRadii(10),Insets.EMPTY)));
        listButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,new CornerRadii(10),Insets.EMPTY)));
        searchButton.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN,new CornerRadii(10),Insets.EMPTY)));

        //calls private methods for the button event handlers
        addButton.setOnAction(e -> addHandler());
        displayButton.setOnAction(e -> displayHandler());
        removeButton.setOnAction(e -> removeHandler());
        paymentButton.setOnAction(e -> paymentHandler());
        listButton.setOnAction(e -> listHandler());
        saveAndQuitButton.setOnAction(e -> saveAndQuitHandler());
        searchButton.setOnAction(e -> searchHandler());

        //configure the stage and make  it visible
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Hostel Application");
        stage.show();
    }


    /**method to request number of hostel rooms from the user
     * return number of  rooms
     *
     */

    private int getNumberOfRooms(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("How many rooms?");
        dialog.setTitle("Information Request");

        String response = dialog.showAndWait().get();
        return Integer.parseInt(response);
    }

    //event handler methods
    private void addHandler() {

        String roomEntered = roomField1.getText();
        String nameEntered = nameField.getText().toUpperCase();

        try {
            //check for errors
            if (roomEntered.length() == 0 || nameEntered.length() == 0) {

                displayArea1.setText("Room number and name must be entered");
            } else if (Integer.parseInt(roomEntered) < 1 || Integer.parseInt(roomEntered) > noOfRooms) {
                displayArea1.setText("There are only " + noOfRooms + " rooms available");

            } else if (list.search(Integer.parseInt(roomEntered)) != null) {

                displayArea1.setText("Room number " + Integer.parseInt(roomEntered) + " is occupied");
            } else {//ok add the tenant
                Tenant t = new Tenant(nameEntered, Integer.parseInt(roomEntered));
                list.addTenant(t);
                roomField1.setText("");
                nameField.setText("");
                displayArea1.setText("New tenant in room " + roomEntered + " successfully added");

            }
        }catch (NumberFormatException e){
            displayArea1.setText("Invalid room number "+ e.getMessage()+"\n Enter whole numbers only");
        }
    }
    private void saveAndQuitHandler() {
        Platform.exit();

    }

    private void listHandler() {
        int i;
        String roomEntered = roomField2.getText();
        //checks for errors
        if (roomEntered.length() == 0){

            displayArea2.setText("The room number must bee filled");
        }
        else if (Integer.parseInt(roomEntered)<1 || Integer.parseInt(roomEntered)>noOfRooms){

            displayArea2.setText("Invalid room number");
        }
        else if (list.search(Integer.parseInt(roomEntered)) == null){

            displayArea2.setText("Room number "+Integer.parseInt(roomEntered)+" is empty");
        }
        else {//ok to list payments
            Tenant t = list.search(Integer.parseInt(roomEntered));
            PaymentList p = t.getPayments();
            if(t.getPayments().getTotal() == 0){

                displayArea2.setText("No payments made for this tenant");
            }
            else{
                /**the numberFormat class is similar to decimalFormat class
                 * the currency instance creates the value to find the values out to which country we are from
                 */
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String s;
                displayArea2.setText("Month "+"\t\t\t\t"+" Amount"+"\n");
                for (i=1;i<=p.getTotal();i++){
                    s = nf.format(p.getPayment(i).getAmount());
                    displayArea2.appendText(""+p.getPayment(i).getMonth()+"\t\t\t\t\t"+s+"\n");
                }

                displayArea2.appendText("\n"+"Total paid so far: "+nf.format(p.calculateTotalPaid()));
                monthField.setText("");
                amountField.setText("");
            }
        }
    }

    private void paymentHandler() {
        String roomEntered = roomField1.getText();
        String monthEntered = monthField.getText();
        String amountEntered = amountField.getText();

        //check for errors
        if (roomEntered.length() == 0 || monthEntered.length() == 0 || amountEntered.length() == 0) {

            displayArea2.setText("Room number,month and amount must all be entered");
        } else if (Integer.parseInt(roomEntered) < 1 || Integer.parseInt(roomEntered) > noOfRooms) {

            displayArea2.setText("Invalid room number");
        } else if (list.search(Integer.parseInt(roomEntered)) == null) {

            displayArea2.setText("Room " + roomEntered + " is empty");
        } else {//ok to process payment
            Payment p = new Payment(monthEntered, Double.parseDouble(amountEntered));
            list.search(Integer.parseInt(roomEntered)).makePayment(p);
            displayArea2.setText("Payment added successfully");
        }
    }
    private void removeHandler() {
        String roomEntered = roomField1.getText();
        //checks for errors
        if (roomEntered.length() == 0){

            displayArea1.setText("Room number must be entered");
        }
        else if(Integer.parseInt(roomEntered)<1 || (Integer.parseInt(roomEntered)>noOfRooms)){

            displayArea1.setText("Invalid room number");
        }
        else if(list.search(Integer.parseInt(roomEntered)) == null){

            displayArea1.setText("Room number "+roomEntered+" is empty");
        }
        else {//ok to remove tenant

            list.removeTenant(Integer.parseInt(roomEntered));
            displayArea1.setText("Tenant removed from room " + Integer.parseInt(roomEntered) + " successfully");
        }
    }

    private void displayHandler() {
        int i;
        if (list.isEmpty()) {//no rooms to display

            displayArea1.setText("All rooms are empty");
        } else {//display rooms
            displayArea1.setText("Room " + "\t" + "Name " + "\n");
            for (i = 1; i <= list.getTotal(); i++) {

                displayArea1.appendText(list.getTenant(i).getRoom() + "\t\t" + list.getTenant(i).getName() + "\n");
            }
        }
    }

    private void searchHandler() {
        String enteredRoom = roomField1.getText();

        //look for errors
        if (enteredRoom.length() == 0){

            displayArea1.setText("Enter a room number to get the tenant's name");
        }
        else if (Integer.parseInt(enteredRoom)<1 || Integer.parseInt(enteredRoom)>noOfRooms){

            displayArea1.setText("Invalid room number");
        }
        else if (list.search(Integer.parseInt(enteredRoom)) != null){
            for (int i=1;i <= Integer.parseInt(enteredRoom);i++){

                displayArea1.setText("The tenant at room "+enteredRoom+" is "+list.getTenant(i).getName());
            }
        }
        else if (list.isEmpty()){

            displayArea1.setText("The room is empty");
        }
    }


    public static void main(String[] args){

        launch(args);
    }
}
