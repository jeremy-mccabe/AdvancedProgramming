package analyzer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private GradeAnalyzer analyzer;

    @FXML private Button button;
    @FXML private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analyzer = new GradeAnalyzer();
    }

    @FXML
    private void analyzeGrades() {
        ObservableList<String> list = FXCollections.observableArrayList(analyzer.analyze());
        listView.setItems(list);
        listView.refresh();
    }
}
