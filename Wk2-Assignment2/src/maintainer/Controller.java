package maintainer;

import com.sun.org.apache.bcel.internal.classfile.Unknown;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ArrayModel arrayModel;
    private final int initialArraySize = 20;

    private Alert alert;

    @FXML private ListView<String> displayListView;
    @FXML private Label arraySizeLabel;
    @FXML private Spinner removeSpinner;
    @FXML private Button removeButton;
    @FXML private Button addButton1;
    @FXML private Button addButton2;
    @FXML private Button addButton3;
    @FXML private Button addButton4;
    @FXML private Button addButton5;
    @FXML private Button addAllButton;
    @FXML private Button displayArrayButton;
    @FXML private Button sortAndDisplayCollatedButton;
    @FXML private Button sortAndDisplayUncollatedButton;
    @FXML private Button reseedArrayButton;
    @FXML private Button displaySizeButton;
    @FXML private Button searchAndDisplayButton;
    @FXML private TextField searchAndDisplayTextField;
    @FXML private TextField valueTextField1;
    @FXML private TextField valueTextField2;
    @FXML private TextField valueTextField3;
    @FXML private TextField valueTextField4;
    @FXML private TextField valueTextField5;
    @FXML private TextField indexTextField1;
    @FXML private TextField indexTextField2;
    @FXML private TextField indexTextField3;
    @FXML private TextField indexTextField4;
    @FXML private TextField indexTextField5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arrayModel = new ArrayModel(initialArraySize);
        registerListeners();
        setupAlertDialog();
    }

    @FXML
    private void reseedArray() {
        arrayModel.seedArray(initialArraySize);
        arrayModel.setIsSorted(false);
    }

    @FXML
    private void displayArray() {
        ObservableList<String> elems = FXCollections.observableArrayList(
                arrayModel.getStringArrayList()
        );
        displayListView.setItems(elems);
    }

    @FXML
    private void sortAndDisplayCollated() {
        if (!arrayModel.isSorted()) {
            ObservableList<String> combinedElems = FXCollections.observableArrayList(
                    arrayModel.getCombinedCollatedStringArrayList()
            );
            displayListView.setItems(combinedElems);
            arrayModel.setIsSorted(true);
        } else {
            displayInfoAlert("Array is already sorted!  Reseed and try again.");
        }
    }

    @FXML
    private void sortAndDisplayUncollated() {
        if (!arrayModel.isSorted()) {
            ObservableList<String> combinedElems = FXCollections.observableArrayList(
                    arrayModel.getCombinedUncollatedStringArrayList()
            );
            displayListView.setItems(combinedElems);
            arrayModel.setIsSorted(true);
        } else {
            displayInfoAlert("Array is already sorted!  Reseed and try again.");
        }
    }

    @FXML
    private void displaySize() {
        arraySizeLabel.setText(""+arrayModel.getSize());
    }

    @FXML
    private void searchAndDisplay() {

    }

    @FXML
    private void remove() {

    }

    private void registerListeners() {
        // FX Control IDs == ((Control) event.getSource()).getId()
        addAllButton.setOnAction(event -> {
            arrayModel.setIsSorted(false);
            try {
                int a = Integer.parseInt(valueTextField1.getText());
                int b = Integer.parseInt(valueTextField2.getText());
                int c = Integer.parseInt(valueTextField3.getText());
                int d = Integer.parseInt(valueTextField4.getText());
                int e = Integer.parseInt(valueTextField5.getText());

                int idxA = Integer.parseInt(indexTextField1.getText());
                int idxB = Integer.parseInt(indexTextField2.getText());
                int idxC = Integer.parseInt(indexTextField3.getText());
                int idxD = Integer.parseInt(indexTextField4.getText());
                int idxE = Integer.parseInt(indexTextField5.getText());

                if (idxA < 0 || idxA > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else if (idxB < 0 || idxB > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else if (idxC < 0 || idxC > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else if (idxD < 0 || idxD > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else if (idxE < 0 || idxE > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else {
                    // success block
                    arrayModel.addElement(a, idxA);
                    arrayModel.addElement(b, idxB);
                    arrayModel.addElement(c, idxC);
                    arrayModel.addElement(d, idxD);
                    arrayModel.addElement(e, idxE);
                    // reset controls
                    valueTextField1.clear();
                    valueTextField2.clear();
                    valueTextField3.clear();
                    valueTextField4.clear();
                    valueTextField5.clear();
                    indexTextField1.clear();
                    indexTextField2.clear();
                    indexTextField3.clear();
                    indexTextField4.clear();
                    indexTextField5.clear();
                    // reset size label
                    arraySizeLabel.setText("Size:  Changed");
                }

            } catch (Exception e) {
                displayErrorAlert("Invalid number entered.  All values must be integers.");
            }
        });
        addButton1.setOnAction(event -> {
            arrayModel.setIsSorted(false);
            try {
                int a = Integer.parseInt(valueTextField1.getText());
                int idxA = Integer.parseInt(indexTextField1.getText());

                if (idxA < 0 || idxA > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else {
                    // success block
                    arrayModel.addElement(a, idxA);
                    // reset controls
                    valueTextField1.clear();
                    indexTextField1.clear();
                    // reset size label
                    arraySizeLabel.setText("Size:  Changed");
                }
            } catch (Exception e) {
                displayErrorAlert("Invalid number entered.  All values must be integers.");
            }
        });
        addButton2.setOnAction(event -> {
            arrayModel.setIsSorted(false);
            try {
                int b = Integer.parseInt(valueTextField2.getText());
                int idxB = Integer.parseInt(indexTextField2.getText());

                if (idxB < 0 || idxB > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else {
                    // success block
                    arrayModel.addElement(b, idxB);
                    // reset controls
                    valueTextField2.clear();
                    indexTextField2.clear();
                    // reset size label
                    arraySizeLabel.setText("Size:  Changed");
                }
            } catch (Exception e) {
                displayErrorAlert("Invalid number entered.  All values must be integers.");
            }
        });
        addButton3.setOnAction(event -> {
            arrayModel.setIsSorted(false);
            try {
                int c = Integer.parseInt(valueTextField3.getText());
                int idxC = Integer.parseInt(indexTextField3.getText());

                if (idxC < 0 || idxC > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else {
                    // success block
                    arrayModel.addElement(c, idxC);
                    // reset controls
                    valueTextField3.clear();
                    indexTextField3.clear();
                    // reset size label
                    arraySizeLabel.setText("Size:  Changed");
                }
            } catch (Exception e) {
                displayErrorAlert("Invalid number entered.  All values must be integers.");
            }
        });
        addButton4.setOnAction(event -> {
            arrayModel.setIsSorted(false);
            try {
                int d = Integer.parseInt(valueTextField4.getText());
                int idxD = Integer.parseInt(indexTextField4.getText());

                if (idxD < 0 || idxD > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else {
                    // success block
                    arrayModel.addElement(d, idxD);
                    // reset controls
                    valueTextField4.clear();
                    indexTextField4.clear();
                    // reset size label
                    arraySizeLabel.setText("Size:  Changed");
                }
            } catch (Exception e) {
                displayErrorAlert("Invalid number entered.  All values must be integers.");
            }
        });
        addButton5.setOnAction(event -> {
            arrayModel.setIsSorted(false);
            try {
                int e = Integer.parseInt(valueTextField5.getText());
                int idxE = Integer.parseInt(indexTextField5.getText());

                if (idxE < 0 || idxE > arrayModel.getSize()) {
                    displayErrorAlert("Invalid array index entered.  Double check array size.");
                } else {
                    // success block
                    arrayModel.addElement(e, idxE);
                    // reset controls
                    valueTextField5.clear();
                    indexTextField5.clear();
                    // reset size label
                    arraySizeLabel.setText("Size:  Changed");
                }
            } catch (Exception e) {
                System.out.println("Message: " + e.getMessage());
                displayErrorAlert("Invalid number entered.  All values must be integers.");
            }
        });
    }

    private void setupAlertDialog() {
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void displayInfoAlert(String s) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(s);
        alert.show();
    }

    private void displayWarningAlert(String s) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(s);
        alert.show();
    }

    private void displayErrorAlert(String s) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(s);
        alert.show();
    }
}
