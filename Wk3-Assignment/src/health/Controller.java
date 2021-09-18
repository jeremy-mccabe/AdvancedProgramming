package health;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private PatientDataModel patient;
    private FileIODelegate delegate;

    @FXML TextField bpTextField;
    @FXML TextField weightTextField;
    @FXML TextField heightTextField;
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
        patient = new PatientDataModel();
        delegate = new FileIODelegate();
    }

    @FXML
    void generateReport(ActionEvent event) {

        final String alertTitle = "Patient Report";
        final String alertHeaderSaveSuccess = "Patient data successfully SAVED to file record\n";
        final String alertHeaderSaveFailure = "Patient data FAILED to save to file record, ONLY PRINTING report\n";
        final String alertHeaderMessage = "Patient data reflects health status and biometrics gathered";
        String alertHeader;

        try {
            patient.setBloodPressure(Integer.parseInt(bpTextField.getText()));
            patient.setBodyMassIndex(
                    Double.parseDouble(weightTextField.getText()) /
                    Math.pow(Double.parseDouble(heightTextField.getText()) , 2)
                    * 703
            );
            patient.setBloodGlucose(Integer.parseInt(bgTextField.getText()));
            patient.setHdlCholesterol(Integer.parseInt(hdlTextField.getText()));
            patient.setLdlCholesterol(Integer.parseInt(ldlTextField.getText()));
            patient.setTriglycerides(Integer.parseInt(tgTextField.getText()));
            patient.setTotalCholesterol(
                    patient.getHdlCholesterol() +
                    patient.getLdlCholesterol() +
                    (int) Math.round(patient.getTriglycerides() * 0.2)
            );

            // output string (for save/print):
            String reportString = patient.generateReportString();

            // check for file I/O option:
            if (generateAndSaveButton.getId().equals(((Button) event.getSource()).getId())) {

                try {
                    delegate.writeRecordFile(reportString, fileTextField.getText());
                    delegate.transactIO(fileTextField.getText());
                    alertHeader = alertHeaderSaveSuccess + alertHeaderMessage;
                } catch (Exception ex) {
                    alertHeader = alertHeaderSaveFailure + alertHeaderMessage;
                    System.out.println(ex.getMessage());
                }

            } else {
                alertHeader = alertHeaderMessage;
            }

            // display report:
            if (patient.getStatus() == PatientDataModel.Levels.SAFE) {
                alertHeader = "Patient Levels = SAFE\n" + alertHeader;
                AlertGenerator.displayInfoAlert(alertTitle, alertHeader, reportString);
            } else if (patient.getStatus() == PatientDataModel.Levels.AT_RISK) {
                alertHeader = "Patient Levels = AT RISK\n" + alertHeader;
                AlertGenerator.displayWarningAlert(alertTitle, alertHeader, reportString);
            } else if (patient.getStatus() == PatientDataModel.Levels.DANGEROUS) {
                alertHeader = "Patient Levels = DANGEROUS (Seek professional medical advice)\n" + alertHeader;
                AlertGenerator.displayErrorAlert(alertTitle, alertHeader, reportString);
            } else {
                AlertGenerator.displayErrorAlert("", "UNKNOWN ERROR","Something went wrong...");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            patient.resetValues();
            AlertGenerator.displayErrorAlert("", "Input Error",
                    "All values must be entered as a 'whole numbers'."
            );
        }
    }

    @FXML
    void loadFile() {
        final String input = fileTextField.getText();
        final String record = delegate.readRecordFile(input);
        final String alertTitle = "Report File Viewer";
        final String alertHeaderSuccess = "Patient record successfully loaded from file '" +
                fileTextField.getText() + "'";
        final String alertHeaderFailure =
                input.equals("") ? "Failed to read record" : "Failed to read record: '" + input + "'";
        final String alertContentFailure = "" +
                "Patient record could not be retrieved from disk.\n" +
                "Click 'View Files' to ensure that the file exists in the appropriate folder ('./patient-records').";
        final String alertHeaderEmptyRecord = "Patient record was completely empty";

        if (record != null && !record.equals("")) {
            AlertGenerator.displayInfoAlert(alertTitle, alertHeaderSuccess, record);
        } else if (record != null) {
            AlertGenerator.displayWarningAlert(alertTitle, alertHeaderEmptyRecord, "");
        } else {
            AlertGenerator.displayWarningAlert(alertTitle, alertHeaderFailure, alertContentFailure);
        }
    }

    @FXML
    void viewFiles() {
        delegate.searchAndListFileNames();
    }
}
