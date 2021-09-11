package maintainer;

import java.lang.reflect.Array;
import java.util.Random;

public class ArrayModel {

    private Integer[] array;

    ArrayModel() {
        array = (Integer[]) Array.newInstance(Integer.class, 20);
        // fill array:
        Random rand = new Random();
        for (Integer elem : array) {
            elem = rand.nextInt();
        }
        for (Integer elem : array) {
            System.out.println(elem);
        }
    }
}
