package crypto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private GridPane gridPane;
    @FXML private TextField textField;
    @FXML private Button encryptButton;
    @FXML private Button decryptButton;
    @FXML private Button clearButton;
    @FXML private Button exitButton;
    @FXML private TextArea plainTextArea;
    @FXML private TextArea cypherTextArea;
    @FXML private TextArea resultTextArea;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureGridPane();
        plainTextArea.setEditable(false);
        cypherTextArea.setEditable(false);
        resultTextArea.setEditable(false);
        textField.setStyle("-fx-text-box-border: cornflowerblue;");
        plainTextArea.setStyle("-fx-background-color: firebrick;");
        cypherTextArea.setStyle("-fx-background-color: firebrick;");
        resultTextArea.setStyle("-fx-background-color: cornflowerblue;");
    }

    @FXML
    private void encrypt() {
        if (!textField.getText().equals("")) {
            plainTextArea.setText(CryptoModule.encrypt(textField.getText()));
        }
    }

    @FXML
    private void decrypt() {
        cypherTextArea.setText(plainTextArea.getText());
        resultTextArea.setText(CryptoModule.decrypt(cypherTextArea.getText()));
    }

    @FXML
    private void clear() {
        textField.clear();
        plainTextArea.clear();
        cypherTextArea.clear();
        resultTextArea.clear();
        plainTextArea.setPromptText(Strings.input);
        cypherTextArea.setPromptText(Strings.cypherText);
        resultTextArea.setPromptText(Strings.result);
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void configureGridPane() {
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(textField, HPos.CENTER);
        GridPane.setValignment(textField, VPos.CENTER);
        GridPane.setHalignment(label1, HPos.CENTER);
        GridPane.setValignment(label1, VPos.CENTER);
        GridPane.setHalignment(label2, HPos.CENTER);
        GridPane.setValignment(label2, VPos.CENTER);
        GridPane.setHalignment(label3, HPos.CENTER);
        GridPane.setValignment(label3, VPos.CENTER);
        GridPane.setHalignment(label4, HPos.CENTER);
        GridPane.setValignment(label4, VPos.CENTER);
        GridPane.setHalignment(cypherTextArea, HPos.CENTER);
        GridPane.setValignment(cypherTextArea, VPos.CENTER);
        GridPane.setHalignment(plainTextArea, HPos.CENTER);
        GridPane.setValignment(plainTextArea, VPos.CENTER);
        GridPane.setHalignment(resultTextArea, HPos.CENTER);
        GridPane.setValignment(resultTextArea, VPos.CENTER);
        GridPane.setHalignment(encryptButton, HPos.CENTER);
        GridPane.setValignment(encryptButton, VPos.CENTER);
        GridPane.setHalignment(decryptButton, HPos.CENTER);
        GridPane.setValignment(decryptButton, VPos.CENTER);
        GridPane.setHalignment(clearButton, HPos.CENTER);
        GridPane.setValignment(clearButton, VPos.CENTER);
        GridPane.setHalignment(exitButton, HPos.CENTER);
        GridPane.setValignment(exitButton, VPos.CENTER);
    }
}
