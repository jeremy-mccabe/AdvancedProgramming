package gradebook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // JavaFX scene containers:
    @FXML private BorderPane borderPane;
    // JavaFX scene controls:
    // Left pane:
    @FXML private VBox writeVbox;
    @FXML private Label gradeEntryLabel;
    @FXML private TextField gradeEntryTextField;
    @FXML private Button enterGradeButton;
    @FXML private Button doneButton;
    // Right pane:
    @FXML private VBox readVbox;
    @FXML private TextField fileSelectionTextField;
    @FXML private Button fileStatusButton;
    @FXML private Button exitButton;
    // Bottom pane:
    @FXML private VBox reportVbox;
    @FXML private Label gradeCountLabel;
    @FXML private Label gpaLabel;

    // File I/O object:
    private FileIODelegate delegate = new FileIODelegate();
    /* API methods:
    LEFT pane:
        enterGrade
        done/write/close - 'transactIO'
    RIGHT pane:
        check file: assign bool 'fileExists'
        exit
    BOTTOM pane:
        generateReport
    */

    private boolean fileExists = false;
    private boolean isTransactionComplete = false;
    private boolean isInitialClick = true;
    private final String gradeEntryString = "Grade Entry:\t";
    private final String fileStatusString = "File Status:\t";

    private enum WriteStatus {
        READY       ("Ready"),
        IN_PROGRESS ("In Progress"),
        COMPLETE    ("Complete")
        ;
        private final String status;
        WriteStatus(String status) {
            this.status = status;
        }
        @Override
        public String toString() {
            return status;
        }
    }
    private enum ButtonColor {
        RED     ("#ff0000"),
        YELLOW  ("#ffff00"),
        GREEN   ("#00ff00"),
        DEFAULT ("#F4F162"),
        BLUE    ("#006EE6"),
        ;
        private final String color;
        ButtonColor(String color) {
            this.color = color;
        }
        @Override
        public String toString() {
            return color;
        }
    }
    private enum FileStatus {
        INVALID ("Invalid"),
        UNKNOWN ("Unknown"),
        VALID   ("Valid")
        ;
        private final String status;
        FileStatus(String status) {
            this.status = status;
        }
        @Override
        public String toString() {
            return status;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle arg1) {

        fileStatusButton.setStyle("-fx-background-color: " + ButtonColor.YELLOW.toString() + "; ");
        fileStatusButton.setText(fileStatusString + FileStatus.UNKNOWN.toString());

        gradeEntryLabel.setText(gradeEntryString + WriteStatus.READY.toString());
    }

    @FXML
    private void parseGradeInput() {

        if (!fileExists) {
            gradeEntryTextField.setPromptText("ERROR: Check 'File Status'");
            gradeEntryTextField.clear();
            return;
        } else if (isTransactionComplete) {
            return;
        }

        doneButton.setDefaultButton(false);

        String grade = gradeEntryTextField.getText();
        System.out.println("Grade entered: " + grade);

        gradeEntryLabel.setText(gradeEntryString + WriteStatus.IN_PROGRESS.toString());

        if (isValidGrade(grade)) {
            gradeEntryTextField.setPromptText("Added: " + grade.toUpperCase());
            try {
                if (fileExists) {
                    FileIODelegate.writeGrade(grade);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            gradeEntryTextField.setPromptText("(Invalid grade entry)");
            System.out.println("Invalid input, please try again.");
        }
        gradeEntryTextField.clear();
    }

    @FXML
    private void finalizeTransaction() {

        if (!fileExists) {
            gradeEntryTextField.setPromptText("ERROR: Check 'File Status'");
            gradeEntryTextField.clear();
            return;
        }

        delegate.transactIO();
        gradeEntryLabel.setText(gradeEntryString + WriteStatus.COMPLETE.toString());
        gradeEntryTextField.setPromptText("All grades submitted");
        gradeEntryTextField.clear();

        doneButton.setDefaultButton(true);
        isTransactionComplete = true;
    }

    @FXML
    private void checkFileStatus() {

        if (fileSelectionTextField.getText().isEmpty()) {
            fileSelectionTextField.setPromptText("ERROR: Enter file name");
            fileSelectionTextField.clear();
        }

        try {
            fileExists = delegate.checkFilePresence(fileSelectionTextField.getText());
            if (fileExists) {
                fileStatusButton.setStyle("-fx-background-color: " + ButtonColor.GREEN.toString() + "; ");
                fileStatusButton.setText(fileStatusString + FileStatus.VALID.toString());
            } else {
                fileStatusButton.setStyle("-fx-background-color: " + ButtonColor.RED.toString() + "; ");
                fileStatusButton.setText(fileStatusString + FileStatus.INVALID.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void generateReport() {

        if (!fileExists) {
            gradeEntryTextField.setPromptText("Verify file exists (press 'File Status')");
            gradeEntryTextField.clear();
            return;
        } else if (!isTransactionComplete) {
            gradeEntryTextField.setPromptText("Finalize grades ('DONE')");
            return;
        }

        List<String> list = null;

        try {
            list = delegate.generateReport();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (list != null) {
            gradeCountLabel.setText(
                "A="+list.get(1) +
                ",  A-="+list.get(2) +
                ",  B+="+list.get(3) +
                ",  B="+list.get(4) +
                ",  B-="+list.get(5) +
                ",  C+="+list.get(6) +
                ",  C="+list.get(7) +
                ",  C-="+list.get(8) +
                ",  D+="+list.get(9) +
                ",  D="+list.get(10) +
                ",  D-="+list.get(11) +
                ",  F="+list.get(12)
            );
            gpaLabel.setText("Class GPA: " + list.get(0));
        } else {
            System.out.println("GPA/Grade tally List is null.");
        }
    }

    private boolean isValidGrade(String grade) {
        switch (grade) {
            case "A":
            case "a":
            case "A-":
            case "a-":
            case "B+":
            case "b+":
            case "B":
            case "b":
            case "B-":
            case "b-":
            case "C+":
            case "c+":
            case "C":
            case "c":
            case "C-":
            case "c-":
            case "D+":
            case "d+":
            case "D":
            case "d":
            case "D-":
            case "d-":
            case "F":
            case "f":
                return true;
            default:
                return false;
        }
    }

    @FXML
    private void addFileNameOnClick() {
        if (isInitialClick) {
            fileSelectionTextField.setText("gradebook.txt");
            isInitialClick = false;
        }
    }

    @FXML
    private void exit() {
        delegate.transactIO();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}