package health;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML TextField bpTextField;
    @FXML TextField bmiTextField;
    @FXML TextField bgTextField;
    @FXML TextField hdlTextField;
    @FXML TextField ldlTextField;
    @FXML TextField tgTextField;
    @FXML TextField fileTextField;
    @FXML Button generateReportButton;
    @FXML Button generateAndSaveButton;
    @FXML Button loadFileButton;
    @FXML Button viewFilesButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void generateReport() {

    }

    @FXML
    private void generateAndSave() {

    }

    @FXML
    private void loadFile() {

    }

    @FXML
    private void viewFiles() {

    }
}
