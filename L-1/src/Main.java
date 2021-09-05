import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int weightInPounds = -1;
        int heightInInches = -1;

        while (weightInPounds <= 0 || heightInInches <= 0) {
            try {
                if (weightInPounds < 0) {
                    System.out.println("Enter your weight in pounds: ");
                    weightInPounds = input.nextInt();
                }
                if (heightInInches < 0) {
                    System.out.println("Enter your height in inches: ");
                    heightInInches = input.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again.");
                input.nextLine(); // clear buffer
            }
        }

        System.out.println(
                "BMI Values:\n" +
                        "\tSerious underweight:\tbelow 18\n" +
                        "\tUnderweight:\t\t\tless than 16 - 18\n" +
                        "\tNormal weight:\t\t\tbetween 18 - 24\n" +
                        "\tOverweight:\t\t\t\tbetween 24 – 29\n" +
                        "\tSeriously overweight:\tbetween 29 - 35\n" +
                        "\tObese:\t\t\t\t\t35 or greater\n"
        );

        int decimalPlaces = 2;
        double BMI = (weightInPounds * 705) / pow(heightInInches, 2);
        BigDecimal formattedBMI = new BigDecimal(Double.toString(BMI));
        formattedBMI = formattedBMI.setScale(decimalPlaces, RoundingMode.HALF_UP);
        System.out.println("Your BMI is: " + formattedBMI);

    }
}
