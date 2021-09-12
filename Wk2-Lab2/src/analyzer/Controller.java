package analyzer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Analyzer analyzer;
    private Status status;
    private Alert alert;
    private int decimalPlaces;

    @FXML private TextField principalTextField;
    @FXML private TextField interestRateTextField;
    @FXML private TextField durationTextField;
    @FXML private Button verifyInputButton;
    @FXML private Label statusLabel;
    @FXML private Button generateReportButton;
    @FXML private Label principalResultLabel;
    @FXML private Label interestRateResultLabel;
    @FXML private Label durationResultLabel;
    @FXML private Label monthlyPaymentResultLabel;
    @FXML private Label totalInterestResultLabel;
    @FXML private Button clearButton;
    @FXML private Button exitButton;

    private enum Status {
        VALID,
        INVALID
    }

    @Override
    public void initialize(URL location, ResourceBundle arg1) {
        analyzer = new Analyzer();
        decimalPlaces = 2;
        setupAlertDialog();
        registerListeners();
    }

    @FXML
    private void submitAndVerifyInput() {

        try {
            if (analyzer.receiveInput(
                    principalTextField.getText(),
                    interestRateTextField.getText(),
                    durationTextField.getText(),
                    Analyzer.Mode.JAVA_FX
            )) {
                // input was entered correctly and can move fwd to validation
                statusLabel.setText(Strings.statusValidString);
                verifyInputButton.setDefaultButton(true);
                status = Status.VALID;
                System.out.println("Valid input received.");
            } else {
                // alert user
                verifyInputButton.setDefaultButton(false);
                status = Status.INVALID;
                statusLabel.setText(Strings.statusInvalidString);
                displayWarningAlert("Input type/entry was invalid, needs to be reentered");
                principalTextField.clear();
                interestRateTextField.clear();
                durationTextField.clear();
                System.out.println("Invalid input received.");
            }

            if (analyzer.validateInput()) {
                // input passed validation, enable ability to generate report
                status = Status.VALID;
            } else {
                status = Status.INVALID;
            }

        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            // alert user
            displayWarningAlert("Input type/entry was invalid, needs to be reentered");
            statusLabel.setText(Strings.statusInvalidString);
            status = Status.INVALID;
            principalTextField.clear();
            interestRateTextField.clear();
            durationTextField.clear();
        } catch (ArithmeticException ae) {
            System.out.println(ae.getMessage());
            // alert user
            displayWarningAlert("Numerical calculations were invalid, needs to be reentered");
            statusLabel.setText(Strings.statusInvalidString);
            status = Status.INVALID;
            principalTextField.clear();
            interestRateTextField.clear();
            durationTextField.clear();
        }
    }

    @FXML
    private void generateReport() {

        try {
            if (status == Status.VALID) {

                // do report:
                principalResultLabel.setText(Strings.principalString + " = $" + principalTextField.getText());
                interestRateResultLabel.setText(Strings.aprString + " = " + interestRateTextField.getText() + "%");
                durationResultLabel.setText(Strings.durationString + " = " + durationTextField.getText() + " months");
                monthlyPaymentResultLabel.setText(
                        Strings.monthlyPaymentString + " = $" + String.format("%.2f",
                                analyzer.calculateMonthlyPayment())
                );
                totalInterestResultLabel.setText(
                        Strings.totalInterestString + " = $" + String.format("%.2f",
                                analyzer.calculateTotalInterest())
                );

                // zeroize text input fields:
                principalTextField.clear();
                interestRateTextField.clear();
                durationTextField.clear();
                statusLabel.setText(Strings.statusPendingString);

            } else {
                // inform user to validate info first, or enter new valid info:
                displayInfoAlert("Ensure all input was entered and validated");
            }
        } catch (ArithmeticException ae) {
            // inform user to validate info first, or enter new valid info:
            displayInfoAlert("Ensure all input was entered and validated");
        }
    }

    @FXML
    private void clear() {
        principalTextField.clear();
        interestRateTextField.clear();
        durationTextField.clear();
        statusLabel.setText(Strings.statusPendingString);
        principalResultLabel.setText(Strings.principalString);
        interestRateResultLabel.setText(Strings.aprString);
        durationResultLabel.setText(Strings.durationString);
        monthlyPaymentResultLabel.setText(Strings.monthlyPaymentString);
        totalInterestResultLabel.setText(Strings.totalInterestString);
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void setupAlertDialog() {
        status = Status.INVALID;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void displayWarningAlert(String s) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(s);
        alert.show();
    }

    private void displayInfoAlert(String s) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(s);
        alert.show();
    }

    private void registerListeners() {

        principalTextField.textProperty().addListener(
                (observable, oldVal, newVal) -> {
                    System.out.println("principalTextField changed: " + oldVal + " => " + newVal);
                    verifyInputButton.setDefaultButton(false);
                    statusLabel.setText(Strings.statusInvalidString);
                    status = Status.INVALID;
                }
        );
        interestRateTextField.textProperty().addListener(
                (observable, oldVal, newVal) -> {
                    System.out.println("interestRateTextField changed: " + oldVal + " => " + newVal);
                    verifyInputButton.setDefaultButton(false);
                    statusLabel.setText(Strings.statusInvalidString);
                    status = Status.INVALID;
                }
        );
        durationTextField.textProperty().addListener(
                (observable, oldVal, newVal) -> {
                    System.out.println("durationTextField changed: " + oldVal + " => " + newVal);
                    verifyInputButton.setDefaultButton(false);
                    statusLabel.setText(Strings.statusInvalidString);
                    status = Status.INVALID;
                }
        );
    }
}
