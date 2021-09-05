package calculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // JavaFX scene containers:
    @FXML private GridPane gridPane;
    // JavaFX scene controls:
    @FXML private TextField valTextField1;
    @FXML private TextField valTextField2;
    @FXML private Label resLabel;
    @FXML private Button addButton;
    @FXML private Button subButton;
    @FXML private Button mulButton;
    @FXML private Button divButton;
    @FXML private Button clrButton;
    @FXML private Button extButton;

    @Override
    public void initialize(URL location, ResourceBundle arg1) {

        addButton.setMaxWidth(Double.MAX_VALUE);
        subButton.setMaxWidth(Double.MAX_VALUE);
        mulButton.setMaxWidth(Double.MAX_VALUE);
        divButton.setMaxWidth(Double.MAX_VALUE);
        clrButton.setMaxWidth(Double.MAX_VALUE);
        extButton.setMaxWidth(Double.MAX_VALUE);
    }

    // Arithmetic operations do NOT account for overflow...
    
    @FXML
    public void add() {
        try {
            resLabel.setText("" + (Long.parseLong(valTextField1.getText()) + Long.parseLong(valTextField2.getText())));
        } catch (Exception e) {
            resLabel.setText("Invalid Input!");
        }
    }

    @FXML
    public void sub() {
        try {
            resLabel.setText("" + (Long.parseLong(valTextField1.getText()) - Long.parseLong(valTextField2.getText())));
        } catch (Exception e) {
            resLabel.setText("Invalid Input!");
        }
    }

    @FXML
    public void mul() {
        try {
            resLabel.setText("" + (Long.parseLong(valTextField1.getText()) * Long.parseLong(valTextField2.getText())));
        } catch (Exception e) {
            resLabel.setText("Invalid Input!");
        }
    }

    @FXML
    public void div() {
        try {
            resLabel.setText("" + (Double.parseDouble(valTextField1.getText()) / Double.parseDouble(valTextField2.getText())));
        } catch (Exception e) {
            resLabel.setText("Invalid Input!");
        }
    }

    @FXML
    public void clr() {
        valTextField1.clear();
        valTextField2.clear();
    }

    @FXML
    public void ext() {
        Stage stage = (Stage) extButton.getScene().getWindow();
        stage.close();
    }

}
