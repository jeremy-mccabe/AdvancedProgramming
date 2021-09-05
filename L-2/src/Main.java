import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static void writeGrade(String grade, BufferedWriter writer) throws Exception {
        try {
            String delimiter = "\n";
            writer.write(grade + delimiter);
            writer.flush();
            System.out.println(grade + " written to file.");
        } catch (Exception e) {
            throw new Exception("Error occurred while attempting grade write operation.");
        }
    }

    public static void main(String[] args) {

        String file = "gradebook.txt";
        String dir = "./";
        Path path = Paths.get(dir + file);

        if (Files.exists(path)) {

            System.out.println("File '" + file + "' exists.");
            String input = "";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a grade (A, A-, B+, B, B-, C+, C, C-, D+, D, D-, F) or 'Q' to quit.");

            BufferedReader reader = null;
            BufferedWriter writer = null;

            try {
                reader = new BufferedReader(new FileReader(path.getFileName().toString()));
                writer =  new BufferedWriter(new FileWriter(path.getFileName().toString()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            while (!input.toLowerCase().equals("q")) {
                try {
                    input = scanner.next();
                    switch (input) {
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
                            writeGrade(input, writer);
                            break;
                        case "Q":
                        case "q":
                            System.out.println("Exiting program.");
                            break;
                        default:
                            System.out.println("Invalid input, please try again.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
            }

            try {
                if (Files.exists(path)) {
                    if (writer != null ) {
                        writer.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            List<String> list = new ArrayList<>();
            try {
                if (reader != null) {
                    String curr;
                    while ((curr = reader.readLine()) != null) {
                        list.add(curr);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            int a=0, aMinus=0, bPlus=0, b=0, bMinus=0, cPlus=0, c=0, cMinus=0, dPlus=0, d=0, dMinus=0, f=0;
            double aggregate = 0.0;
            /*
            A=4.0, A-=3.7, B+=3.3, B=3.0, B-=2.7, C+=2.3,
            C= 2.0, C-=1.7, D+=1.3, D=1.0, D-=0.7, F=0.0
            */
            for (String s : list) {
                switch (s) {
                    case "A":
                    case "a":
                        a++;
                        aggregate+=4.0;
                        break;
                    case "A-":
                    case "a-":
                        aMinus++;
                        aggregate+=3.7;
                        break;
                    case "B+":
                    case "b+":
                        bPlus++;
                        aggregate+=3.3;
                        break;
                    case "B":
                    case "b":
                        b++;
                        aggregate+=3.0;
                        break;
                    case "B-":
                    case "b-":
                        bMinus++;
                        aggregate+=2.7;
                        break;
                    case "C+":
                    case "c+":
                        cPlus++;
                        aggregate+=2.3;
                        break;
                    case "C":
                    case "c":
                        c++;
                        aggregate+=2.0;
                        break;
                    case "C-":
                    case "c-":
                        cMinus++;
                        aggregate+=1.7;
                        break;
                    case "D+":
                    case "d+":
                        dPlus++;
                        aggregate+=1.3;
                        break;
                    case "D":
                    case "d":
                        d++;
                        aggregate+=1.0;
                        break;
                    case "D-":
                    case "d-":
                        dMinus++;
                        aggregate+=0.7;
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

            System.out.println(
                    "\nThe following are total number of grades by letter:\n" +
                    "A="+a+", A-="+aMinus+", B+="+bPlus+", B="+b+", B-="+bMinus+", C+="+cPlus+
                    ", C="+c+", C-="+cMinus+", D+="+dPlus+", D="+d+", D-="+dMinus+", F="+f+"\n"+
                    "Class GPA: " + df.format(aggregate / list.size())
            );
        } else {
            System.out.println(
                    "ERROR:\n" +
                    "File " + file + " does not exist on disk.\n" +
                    "Place file in root level directory."
            );
        }
    }
}
