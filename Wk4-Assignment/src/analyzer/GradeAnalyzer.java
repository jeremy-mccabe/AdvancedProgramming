package analyzer;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

class GradeAnalyzer {

    private FileIODelegate delegate;
    final private DoubleBinaryOperator sum = (x, y) -> x + y;
    final private BinaryOperator<Double> sum_t = (x, y) -> x + y;

    GradeAnalyzer() {
        delegate = new FileIODelegate();
    }

    private Double mu(List<Integer> list) {

        if (list.stream()
                .mapToDouble(n->(double)n)
                .reduce(sum)
                .isPresent()
        ) {
            return list.stream()
                    .mapToDouble(n->(double)n)
                    .reduce(sum)
                    .getAsDouble()
                    /
                    (double) list.stream()
                    .mapToDouble(n->(double)n)
                    .count();
        } else {
            return null;
        }
    }

    private Double sigma(List<Integer> list) {
        Double mu = mu(list);
        if (mu != null) {
            Double intermediate = list.stream()
                    .map(x -> x - mu) //diff
                    .map(x -> x * x) //square
                    .reduce(sum_t).get(); //sum
            return Math.pow(intermediate / (list.size() - 1), 0.5); //sqrt(x/n-1)
        } else {
            return null;
        }
    }

    List<String> analyze() {

        List<Integer> gradeList = delegate.readGrades();
        Double mu = mu(gradeList);
        Double sigma = sigma(gradeList);

        List<String> stringList = gradeList.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        if (mu != null && sigma != null) {
            for (int i = 0; i < gradeList.size(); i++) {
                Integer ES = gradeList.get(i); //exam score (ES)
                if (ES >= mu + 1.5 * sigma) {
                    stringList.set(i, stringList.get(i) + "\t\tA");
                } else if (mu + 0.5 * sigma <= ES && ES < mu + 1.5 * sigma) {
                    stringList.set(i, stringList.get(i) + "\t\tB");
                } else if (mu - 0.5 * sigma <= ES && ES < mu + 0.5 * sigma) {
                    stringList.set(i, stringList.get(i) + "\t\tC");
                } else if (mu - 1.5 * sigma <= ES && ES <= mu - 0.5 * sigma) {
                    stringList.set(i, stringList.get(i) + "\t\tD");
                } else if (ES < mu - 1.5 * sigma) {
                    stringList.set(i, stringList.get(i) + "\t\tF");
                } else {
                    return null; //error
                }
            }
        } else {
            return null; //error
        }

        List<String> statistics = Arrays.asList(
                "There were " + gradeList.size() + " exams.",
                "Mean:  " + String.format("%.2f", mu),
                "Std. Deviation:  " + String.format("%.2f", sigma),
                "\n",
                "Score\tGrade"
        );

        stringList.addAll(0, statistics);

        return stringList;
    }
}
