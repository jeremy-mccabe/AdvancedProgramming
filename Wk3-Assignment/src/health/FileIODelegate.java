package health;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class FileIODelegate {

    private final String recordsDir = "./patient-records/";
    private BufferedReader reader;
    private BufferedWriter writer;

    private enum Mode {
        READ,
        WRITE
    }

    FileIODelegate() {
        reader = null;
        writer = null;
    }

    void searchAndListFileNames() {

        StringBuilder builder = new StringBuilder();
        String[] pathNames;
        File file = new File(recordsDir);
        pathNames = file.list();

        final String alertTitle = "Record Manager";
        final String alertHeader1 = "No patient records exist.";
        final String alertHeader2 = "Patient Record Files:\n";

        if (pathNames != null && pathNames.length == 0) {

            AlertGenerator.displayInfoAlert(alertTitle, alertHeader1, "");

        } else if (pathNames != null) {

            builder.append("");
            for (String pathName : pathNames) {
                if (pathName.startsWith(".")) {
                    continue;
                }
                builder.append(pathName);
                builder.append("\n");
            }

            AlertGenerator.displayInfoAlert(alertTitle, alertHeader2, builder.toString());

        } else {
            System.out.println("Something went wrong with file I/O...");
        }
    }

    String readRecordFile(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            if (checkFilePresence(fileName, Mode.READ)) {
                // read from file:
                if (reader != null) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }
                }
                // return string:
                return stringBuilder.toString();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    void writeRecordFile(String record, String fileName) throws Exception {

        try {
            if (checkFilePresence(fileName, Mode.WRITE)) {
                writer.write(record);
                writer.flush();
                System.out.println(record + " written to file.");
            } else {
                System.out.println("File '" + fileName + "' is not present on disk.");
                throw new FileNotFoundException();
            }
        } catch (Exception e) {
            throw new Exception("Error occurred while attempting record write operation.");
        }
    }

    void transactIO(String file) {

        Path filePath = Paths.get(recordsDir + file);

        try {
            if (Files.exists(filePath)) {
                if (writer != null) {
                    writer.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkFilePresence(String file, Mode mode) throws Exception {

        Path filePath = Paths.get(recordsDir + file);

        System.out.println("FILE (full path): " + filePath);

        if (Files.exists(filePath)) {
            System.out.println("File '" + file + "' exists.");
            try {
                if (mode == Mode.READ) {
                    reader = new BufferedReader(new FileReader(filePath.toString()));
                } else if (mode == Mode.WRITE) {
                    writer = new BufferedWriter(new FileWriter(filePath.toString()));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new Exception();
            }
            return true;
        } else {
            System.out.println("File '" + file + "' does not exist.");
            return false;
        }
    }


    private String getDateTimeString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
