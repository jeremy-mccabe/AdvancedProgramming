
public class UnitTest {

    private Analyzer analyzer;

    UnitTest(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    void run() {
        try {
            System.out.println("UnitTest::run -> Submitting input... (140000, 8.00, 360)");
            analyzer.receiveInput(""+140000, ""+8.00, ""+360, Analyzer.Mode.CONSOLE);
            System.out.println("UnitTest::run -> PASSED input submission.");
            System.out.println("UnitTest::run -> Validating input...");
            analyzer.validateInput();
            System.out.println("UnitTest::run -> PASSED input validation.");
            System.out.println("UnitTest::run -> Printing hardcoded input values:");
            analyzer.printInputValues();
            System.out.println("UnitTest::run -> Printing calculated values:");
            analyzer.printCalculatedValues();
            System.out.println("UnitTest::run -> SUCCESSFUL");
        } catch (NumberFormatException nfe) {
            System.out.println("UnitTest::run -> FAILED, Error with input:" +
                    (nfe.getMessage() != null ? nfe.getMessage() : ""));
        } catch (ArithmeticException ae) {
            System.out.println("UnitTest::run -> FAILED, Error with math operations:" +
                    (ae.getMessage() != null ? ae.getMessage() : ""));
        }
    }
}
