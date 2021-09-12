package maintainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    DataModel model;

    @FXML TextField textField;
    @FXML ListView<String> listView;
    @FXML GridPane gridPane;
    @FXML Button loadButton;
    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML Button selectButton;
    @FXML Button resetButton;
    @FXML Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        model = new DataModel();
        registerCallbacks();
        configureGridPane();
    }

    @FXML
    private void displayArray() {
        ObservableList<String> elems = FXCollections.observableArrayList(
                model.getStringArrayList());
        listView.setItems(elems);
    }

    private void registerCallbacks() {

        loadButton.setOnAction(event -> displayArray());
        resetButton.setOnAction(event -> model.resetModel());
        addButton.setOnAction(event -> model.addElement(textField.getText()));

        removeButton.setOnAction(event -> {
            ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty()) {
                AlertGenerator.displayErrorAlert("Must selected name(s) from the list for removal.");
            } else {
                for (String s : selectedItems) {
                    model.removeName(s);
                }
            }
        });

        selectButton.setOnAction(event -> {
            ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
            StringBuilder selectedNames = new StringBuilder("Selected Names:\n");
            int i = 1;
            for (String s : selectedItems) {
                selectedNames.append("\t"+ i++ +".  "+ s +"\n");
            }
            AlertGenerator.displayInfoAlert(selectedNames.toString());
        });

        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }

    private void configureGridPane() {
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(loadButton, HPos.CENTER);
        GridPane.setValignment(loadButton, VPos.CENTER);
        GridPane.setHalignment(addButton, HPos.CENTER);
        GridPane.setValignment(addButton, VPos.CENTER);
        GridPane.setHalignment(removeButton, HPos.CENTER);
        GridPane.setValignment(removeButton, VPos.CENTER);
        GridPane.setHalignment(selectButton, HPos.CENTER);
        GridPane.setValignment(selectButton, VPos.CENTER);
        GridPane.setHalignment(resetButton, HPos.CENTER);
        GridPane.setValignment(resetButton, VPos.CENTER);
        GridPane.setHalignment(exitButton, HPos.CENTER);
        GridPane.setValignment(exitButton, VPos.CENTER);
    }
}
