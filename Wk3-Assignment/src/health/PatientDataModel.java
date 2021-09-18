package health;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PatientDataModel {

    private Integer bloodPressure;
    private Double bodyMassIndex;
    private Integer bloodGlucose;
    private Integer hdlCholesterol;
    private Integer ldlCholesterol;
    private Integer triglycerides;
    private Integer totalCholesterol;

    private StringBuilder stringBuilder;

    enum Levels {
        SAFE,
        AT_RISK,
        DANGEROUS
    }

    PatientDataModel() {
        stringBuilder = new StringBuilder("");
    }

    String generateReportString() {

        // reset builder:
        stringBuilder.setLength(0);

        // blood pressure:
        stringBuilder.append("Blood Pressure (Diastolic):\t\t\t\t\t\t(");
        stringBuilder.append(
                bloodPressure < 90 ? "Low" :
                        bloodPressure < 140 ? "Normal" :
                                bloodPressure < 160 ? "Moderate" :
                                        bloodPressure < 210 ? "HIGH" :
                                                bloodPressure > 140 ? "SEVERE" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(bloodPressure);
        stringBuilder.append(" mm/Hg\n");

        // bmi:
        stringBuilder.append("Body Mass Index (BMI):\t\t\t\t\t\t(");
        stringBuilder.append(
                bodyMassIndex < 18.5 ? "Underweight" :
                        bodyMassIndex < 24.9 ? "Normal" :
                                bodyMassIndex < 30 ? "OVERWEIGHT" :
                                        bodyMassIndex >= 30 ? "OBESE" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(bodyMassIndex);
        stringBuilder.append("\n");

        // blood sugar:
        stringBuilder.append("Blood Glucose (Hemoglobin A/1c):\t\t\t\t(");
        stringBuilder.append(
                bloodGlucose > 340 ? "VERY POOR" :
                        bloodGlucose > 270 ? "POOR" :
                                bloodGlucose > 210 ? "Marginal" :
                                        bloodGlucose > 150 ? "Good" :
                                                bloodGlucose > 60 ? "Excellent" :
                                                        bloodGlucose <= 60 ? "VERY LOW" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(bloodGlucose);
        stringBuilder.append(" g/dL\n");

        // hdl:
        stringBuilder.append("HDL Cholesterol ('Good'):\t\t\t\t\t\t(");
        stringBuilder.append(
                hdlCholesterol > 90 ? "TOO HIGH" :
                        hdlCholesterol > 40 ? "Optimal" :
                                hdlCholesterol > 30 ? "Normal" :
                                        hdlCholesterol > 20 ? "Low" :
                                                hdlCholesterol <= 20 ? "VERY LOW" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(hdlCholesterol);
        stringBuilder.append(" mg/dL\n");

        // ldl:
        stringBuilder.append("LDL Cholesterol ('Bad'):\t\t\t\t\t\t(");
        stringBuilder.append(
                ldlCholesterol < 40 ? "TOO LOW" :
                        ldlCholesterol < 100 ? "Optimal" :
                                ldlCholesterol < 130 ? "Normal" :
                                        ldlCholesterol < 160 ? "HIGH" :
                                                ldlCholesterol >= 160 ? "VERY HIGH" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(ldlCholesterol);
        stringBuilder.append(" mg/dL\n");

        // triglycerides:
        stringBuilder.append("Triglycerides ('3 Fatty Acids'):\t\t\t\t\t(");
        stringBuilder.append(
                triglycerides < 50 ? "TOO LOW" :
                        triglycerides < 100 ? "Optimal" :
                                triglycerides < 150 ? "Normal" :
                                        triglycerides < 500 ? "HIGH" :
                                                triglycerides >= 500 ? "VERY HIGH" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(triglycerides);
        stringBuilder.append(" mg/dL\n");

        // total cholesterol:
        stringBuilder.append("Total Cholesterol (HDL, LDL, Triglycerides):\t\t(");
        stringBuilder.append(
                totalCholesterol < 50 ? "TOO LOW" :
                        totalCholesterol < 200 ? "Excellent" :
                                totalCholesterol < 240 ? "Moderate" :
                                        totalCholesterol >= 240 ? "HIGH" : "<<error>>"
        );
        stringBuilder.append(")   =   ");
        stringBuilder.append(totalCholesterol);
        stringBuilder.append(" mm/Hg\n");

        // return string:
        return stringBuilder.toString();
    }

    public Levels getStatus() {
        if (
            bloodPressure > 210 || bloodPressure < 50 ||
            bodyMassIndex > 130 || bodyMassIndex < 5 ||
            bloodGlucose < 40 || bloodGlucose > 340 ||
            ldlCholesterol > 160 || ldlCholesterol < 40 ||
            hdlCholesterol < 20 || hdlCholesterol > 90 ||
            triglycerides > 500 || triglycerides < 50 ||
            totalCholesterol > 240
            ) {
            // return extreme
            return Levels.DANGEROUS;

        } else if (
            bloodPressure > 180 || bloodPressure < 90 ||
            bodyMassIndex > 25 || bodyMassIndex < 15 ||
            bloodGlucose < 70 || bloodGlucose > 270 ||
            ldlCholesterol > 130 || ldlCholesterol < 60 ||
            hdlCholesterol < 30 || hdlCholesterol > 70 ||
            triglycerides > 200 || triglycerides < 50 ||
            totalCholesterol > 220
            ) {
            // return risky
            return Levels.AT_RISK;

        } else {
            //return normal
            return Levels.SAFE;
        }
    }

    public void resetValues() {
        bloodGlucose = null;
        bodyMassIndex = null;
        bloodGlucose = null;
        hdlCholesterol = null;
        ldlCholesterol = null;
        triglycerides = null;
        totalCholesterol = null;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        BigDecimal bd = new BigDecimal(bodyMassIndex);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.bodyMassIndex = bd.doubleValue();
    }

    public int getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(int bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public int getHdlCholesterol() {
        return hdlCholesterol;
    }

    public void setHdlCholesterol(int hdlCholesterol) {
        this.hdlCholesterol = hdlCholesterol;
    }

    public int getLdlCholesterol() {
        return ldlCholesterol;
    }

    public void setLdlCholesterol(int ldlCholesterol) {
        this.ldlCholesterol = ldlCholesterol;
    }

    public int getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(int triglycerides) {
        this.triglycerides = triglycerides;
    }

    public int getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(int totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }
}
