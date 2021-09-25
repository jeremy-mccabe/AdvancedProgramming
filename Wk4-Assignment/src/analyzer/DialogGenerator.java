package analyzer;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.util.Pair;

class DialogGenerator {

    static void displayWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }

    static void displayDialog(String title, String header) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setGraphic(new ImageView(
                DialogGenerator.class.getClassLoader().getResource(
                        "./images/image.png").toString()
                )
        );
    }
}
