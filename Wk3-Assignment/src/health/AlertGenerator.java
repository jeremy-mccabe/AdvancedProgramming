package health;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class AlertGenerator {

    private static Alert alert = new Alert(Alert.AlertType.NONE);

    static void displayInfoAlert(String title, String header, String content) {
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }

    static void displayWarningAlert(String title, String header, String content) {
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }

    static void displayErrorAlert(String title, String header, String content) {
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }

    static void displayBasicAlert(String title, String header, String content) {
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK); // throws illegal arg ex, ignore...
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.setAlertType(Alert.AlertType.NONE);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }

    static void displayConfirmationAlert(String title, String header, String content) {
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }
}
