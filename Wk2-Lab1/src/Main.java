import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean runUnitTest;
        Scanner in = new Scanner(System.in);
        Analyzer analyzer = new Analyzer();

        while (true) {
            System.out.println("Enter (1) to run unit test, enter (2) to enter numbers manually:");
            try {
                int choice = in.nextInt();
                if (choice == 1 || choice == 2) {
                    runUnitTest = choice == 1;
                    break;
                } else {
                    System.out.println("Please select either (1) or (2), try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() != null ? e.getMessage() : "");
                System.out.println("Invalid input, try again.");
                in.nextLine(); // flush
            }
        }

        if (runUnitTest) {
            UnitTest unitTest = new UnitTest(analyzer);
            unitTest.run();
        } else {
            /*
                STATE MACHINE:
                null  -> received  -> verify  -> valid    -> output report
                                              -> invalid  -> notify  -> reentry  -> (received)...
            */
            // main input loop
            char sentinel = 'y';
            while (sentinel == 'y' || sentinel == 'Y') {
                try {
                    System.out.println("Enter Amount of Loan (principal):");
                    int principal = in.nextInt();
                    System.out.println("Enter Annual Interest Rate % (eg: 8.0):");
                    double APR = in.nextDouble();
                    System.out.println("Enter Duration of loan in months:");
                    int duration = in.nextInt();

                    analyzer.receiveInput(""+principal, ""+APR, ""+duration, Analyzer.Mode.CONSOLE);

                    // validation confirmation loop
                    while (true) {
                        System.out.println("Enter 'V' or 'v' to validate:");
                        String confirmValidate = in.next();
                        if (confirmValidate.equals("v") || confirmValidate.equals("V")) {
                            System.out.println("Input " +
                                    (analyzer.validateInput()
                                            ? "was SUCCESSFULLY validated."
                                            : "FAILED to validate, needs re-entry.")
                            );
                            break;
                        } else {
                            System.out.println("Must attempt input validation, try again.");
                            in.nextLine(); // flush
                        }
                    }

                    // print selection loop
                    while (true) {
                        System.out.println(
                                "Select print output:" +
                                "\n\t(1) Principal" +
                                "\n\t(2) APR" +
                                "\n\t(3) Duration" +
                                "\n\t(4) Monthly Payment" +
                                "\n\t(5) Total Interest" +
                                "\n\t(6) Print all"
                        );

                        try {
                            int choice = Integer.parseInt(in.next());
                            switch (choice) {
                                case 1:
                                    analyzer.printPrincipal();
                                    break;
                                case 2:
                                    analyzer.printAPR();
                                    break;
                                case 3:
                                    analyzer.printDuration();
                                    break;
                                case 4:
                                    analyzer.printMonthlyPayment();
                                    break;
                                case 5:
                                    analyzer.printTotalInterest();
                                    break;
                                case 6:
                                    analyzer.printFullReport();
                                    break;
                            }

                            if (choice < 1 || choice > 6) {
                                System.out.println("Invalid choice, try again.");
                                in.nextLine(); // flush
                            } else {
                                break;
                            }

                        } catch (Exception e) {
                            System.out.println("Must select a valid print option.");
                            in.nextLine(); // flush
                        }
                    }

                    // return loop
                    while (true) {
                        System.out.println("Do you wish to continue ('y' or 'n')?:");
                        try {
                            String choice = in.next();
                            if (choice.equals("n") || choice.equals("N")) {
                                break;
                            } else if (choice.equals("y") || choice.equals("Y")) {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Must select Yes ('Y','y') or No ('N','n'), try again.");
                            in.nextLine(); // flush
                        }
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println(nfe.getMessage() != null ? nfe.getMessage() : "");
                    System.out.println("Invalid input types, try again.");
                    in.nextLine(); // flush
                } catch (ArithmeticException ae) {
                    System.out.println(ae.getMessage() != null ? ae.getMessage() : "");
                    System.out.println("Invalid calculation was performed, need new values, try again.");
                    in.nextLine(); // flush
                } catch (Exception e) {
                    System.out.println("Invalid selection, try again.");
                    in.nextLine(); // flush
                }
            }
        }
    }
}
