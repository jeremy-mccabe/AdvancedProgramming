package analyzer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.pow;

public class Analyzer {

    private int principal;
    private double APR;
    private int duration;
    private double monthlyPayment;
    private double totalInterest;
    private Mode mode;
    private BigDecimal bd;
    private int decimalPlaces;

    Analyzer() {
        decimalPlaces = 2;
    }

    enum Mode {
        CONSOLE,
        JAVA_FX
    }

    //    p: principal amount of the loan
    //    r: monthly interest rate (APR / 12) given as a number between 0 (0%) and 1 (100%)
    //    n: duration of the loan

    double calculateMonthlyPayment()
            throws ArithmeticException {
        // payment = p * r / [1 - (1 + r)^(-n)]
        try {
            bd = BigDecimal.valueOf((principal * (APR/12)) / (1 - pow((1+(APR/12)), (-1*duration))));
            bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
            monthlyPayment = bd.doubleValue();
        } catch (Exception e) {
            System.out.println(e.getMessage() != null ? e.getMessage() : "");
            throw new ArithmeticException();
        }

        return monthlyPayment;
    }

    double calculateTotalInterest()
            throws ArithmeticException {
        // total interest = n * payment – p
        try {
            bd = BigDecimal.valueOf(duration * monthlyPayment - principal);
            bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
            totalInterest = bd.doubleValue();
        } catch (Exception e) {
            System.out.println(e.getMessage() != null ? e.getMessage() : "");
            throw new ArithmeticException();
        }

        return totalInterest;
    }

    // returns data type validation predicate
    boolean receiveInput(String principal, String APR, String duration, Mode mode)
            throws NumberFormatException {
        try {
            this.principal = Integer.parseInt(principal);
            this.APR = Double.parseDouble(APR)/100;
            this.duration = Integer.parseInt(duration);
            this.mode = mode;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage() != null ? e.getMessage() : "");
            throw new NumberFormatException();
        }
    }

    // returns valid numerical data predicate
    boolean validateInput()
            throws ArithmeticException {
        try {
            calculateMonthlyPayment();
            calculateTotalInterest();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage() != null ? e.getMessage() : "");
            throw new ArithmeticException();
        }
    }

    void printPrincipal() {
        System.out.println("\tAmount of Loan - $" + principal + "\n");
    }

    void printAPR() {
        System.out.println("\tAnnual Interest Rate - " + APR*100 + "%" + "\n");
    }

    void printDuration() {
        System.out.println("\tDuration of loan in months - " + duration + "\n");
    }

    void printMonthlyPayment() {
        System.out.println("\tMonthly payment - $" + String.format("%.2f", monthlyPayment) + "\n");
    }

    void printTotalInterest() {
        System.out.println("\tTotal interest paid - $" + String.format("%.2f", totalInterest) + "\n");
    }

    void printInputValues() {
        System.out.println(
                "\tAmount of Loan\t\t\t\t- $" + principal +
                "\n\tAnnual Interest Rate\t\t- " + APR*100 + "%" +
                "\n\tDuration of loan in months\t- " + duration +
                "\n"
        );
    }

    void printCalculatedValues() {
        System.out.println(
                "\tMonthly payment \t\t- $" + String.format("%.2f", monthlyPayment) +
                "\n\tTotal interest paid \t- $" + String.format("%.2f", totalInterest) +
                "\n"
        );
    }

    void printFullReport() {
        System.out.println(
                "\tAmount of Loan\t\t\t\t- $" + principal +
                        "\n\tAnnual Interest Rate\t\t- " + APR*100 + "%" +
                        "\n\tDuration of loan in months\t- " + duration +
                        "\n\tMonthly payment \t\t\t- $" + String.format("%.2f", monthlyPayment) +
                        "\n\tTotal interest paid \t\t- $" + String.format("%.2f", totalInterest) +
                        "\n"
        );
    }
}
