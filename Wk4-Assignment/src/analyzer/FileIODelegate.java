package analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileIODelegate {

    private BufferedReader reader;

    FileIODelegate() {
        reader = null;
    }

    List<Integer> readGrades() {

        List<Integer> list = new ArrayList<>();

        final String gradesDir = "./";
        final String gradesFile = "grades";
        final String fileExt = ".txt";
        Path filePath = Paths.get(gradesDir + gradesFile + fileExt);

        if (Files.exists(filePath)) {
            try {

                reader = new BufferedReader(new FileReader(filePath.toString()));

                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(Integer.parseInt(line));
                }

                return list;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            System.out.println("File '" + filePath.getFileName() + "' does not exist.");
            return null;
        }
    }
}
