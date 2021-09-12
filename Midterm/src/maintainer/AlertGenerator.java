package maintainer;

import javafx.scene.control.Alert;

public class AlertGenerator {

    private static Alert alert = new Alert(Alert.AlertType.NONE);

    static void displayInfoAlert(String s) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(s);
        alert.show();
    }

    static void displayWarningAlert(String s) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(s);
        alert.show();
    }

    static void displayErrorAlert(String s) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(s);
        alert.show();
    }
}
