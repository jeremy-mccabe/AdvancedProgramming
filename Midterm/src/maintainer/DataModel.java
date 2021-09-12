package maintainer;

import java.util.ArrayList;
import java.util.Arrays;

public class DataModel {

    private final String[] NAMES = {
            "Amin Bob",
            "Jill Holder",
            "Lucia Crowther",
            "Billy Reese",
            "Jaya Sheldon",
            "Richie Storey",
    };

    private String[] names;

    DataModel() {
        names = NAMES;
    }

    void resetModel() {
        names = NAMES;
        AlertGenerator.displayInfoAlert("Names were set to original order.");
    }

    void addElement(String elem) {
        if (elem.equals("")) {
            AlertGenerator.displayErrorAlert("Invalid name, try again!");
            return;
        }
        String[] newArray = new String[names.length+1];
        System.arraycopy(names, 0, newArray, 0, names.length);
        newArray[names.length] = elem;
        names = newArray;
        AlertGenerator.displayInfoAlert("Name '" + elem + "' was added to database.");
    }

    void removeName(String elem) {
        // check for existence
        int idx = -1;
        for (int i = 0; i < names.length; i++) {
            if (elem.equals(names[i])) {
                idx = i;
            }
        }
        // modify
        if (idx >= 0) {
            String[] newArray = new String[names.length-1];
            for (int i = 0, j =0; i < newArray.length; i++, j++) {
                if (j == idx) {
                    j++;
                }
                newArray[i] = names[j];
            }
            names = newArray;
            AlertGenerator.displayInfoAlert("Name(s) removed from database.");
        } else {
            AlertGenerator.displayWarningAlert("Name '" + elem + "' does not exist in database.");
        }
    }

    // type ArrayList<String> required as argument to observableArrayList
    // @ Controller.java (used to populate ListView)...
    ArrayList<String> getStringArrayList() {
        return new ArrayList<>(Arrays.asList(names));
    }
}
