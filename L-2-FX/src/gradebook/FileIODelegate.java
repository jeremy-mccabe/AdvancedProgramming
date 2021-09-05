package gradebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class FileIODelegate {

    private static BufferedReader reader = null;
    private static BufferedWriter writer = null;
    private Path filePath = null;
    private String dir = "./";

    static void writeGrade(String grade) throws Exception {
        try {
            String delimiter = "\n";
            writer.write(grade + delimiter);
            writer.flush();
            System.out.println(grade + " written to file.");
        } catch (Exception e) {
            throw new Exception("Error occurred while attempting grade write operation.");
        }
    }

    void transactIO() {
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

    boolean checkFilePresence(String file) throws Exception {

        filePath = Paths.get(dir + file);

        if (Files.exists(filePath)) {
            System.out.println("File '" + file + "' exists.");
            try {
                reader = new BufferedReader(new FileReader(filePath.getFileName().toString()));
                writer = new BufferedWriter(new FileWriter(filePath.getFileName().toString()));
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

    List<String> generateReport() throws Exception {

        List<String> inputList = new ArrayList<>();
        List<String> outputList = new ArrayList<>();

        try {
            if (reader != null) {
                String curr;
                while ((curr = reader.readLine()) != null) {
                    inputList.add(curr);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception();
        }

        int a = 0, aMinus = 0, bPlus = 0, b = 0, bMinus = 0, cPlus = 0,
            c = 0, cMinus = 0, dPlus = 0, d = 0, dMinus = 0, f = 0;
        double aggregate = 0.0;

        // A=4.0, A-=3.7, B+=3.3, B=3.0, B-=2.7, C+=2.3,
        // C= 2.0, C-=1.7, D+=1.3, D=1.0, D-=0.7, F=0.0
        for (String s : inputList) {
            switch (s) {
                case "A":
                case "a":
                    a++;
                    aggregate += 4.0;
                    break;
                case "A-":
                case "a-":
                    aMinus++;
                    aggregate += 3.7;
                    break;
                case "B+":
                case "b+":
                    bPlus++;
                    aggregate += 3.3;
                    break;
                case "B":
                case "b":
                    b++;
                    aggregate += 3.0;
                    break;
                case "B-":
                case "b-":
                    bMinus++;
                    aggregate += 2.7;
                    break;
                case "C+":
                case "c+":
                    cPlus++;
                    aggregate += 2.3;
                    break;
                case "C":
                case "c":
                    c++;
                    aggregate += 2.0;
                    break;
                case "C-":
                case "c-":
                    cMinus++;
                    aggregate += 1.7;
                    break;
                case "D+":
                case "d+":
                    dPlus++;
                    aggregate += 1.3;
                    break;
                case "D":
                case "d":
                    d++;
                    aggregate += 1.0;
                    break;
                case "D-":
                case "d-":
                    dMinus++;
                    aggregate += 0.7;
                    break;
                case "F":
                case "f":
                    f++;
                    break;
                default:
                    break;
            }
        }

        DecimalFormat df = new DecimalFormat("#.###");

        // GPA (@ elem/idx 0):
        outputList.add("" + df.format(aggregate / inputList.size()));
        // Grade tallies (@ elem/idx 1- 12):
        outputList.add("" + a);
        outputList.add("" + aMinus);
        outputList.add("" + bPlus);
        outputList.add("" + b);
        outputList.add("" + bMinus);
        outputList.add("" + cPlus);
        outputList.add("" + c);
        outputList.add("" + cMinus);
        outputList.add("" + dPlus);
        outputList.add("" + d);
        outputList.add("" + dMinus);
        outputList.add("" + f);

        return outputList;
    }
}
