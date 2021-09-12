package maintainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArrayModel {

    private Integer[] array;
    private final int arrayValueBound = 100;
    private Random rand;
    private boolean sorted;

    ArrayModel(int size) {
        rand = new Random();
        seedArray(size);
        sorted = false;
    }

    void seedArray(int size) {
        array = new Integer[size];
        // fill array:
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(arrayValueBound);
        }
    }

    int getSize() {
        return array.length;
    }

    void setIsSorted(boolean b) {
        sorted = b;
    }

    boolean isSorted() {
        return sorted;
    }

    void addElement(int elem, int idx) {

        Integer[] newArray = new Integer[array.length+1];

        for (int i = 0, j =0; i < newArray.length; i++, j++) {
            if (i == idx) {
                newArray[i] = elem;
                j--;
            } else {
                newArray[i] = array[j];
            }
        }

        array = newArray;
    }

    // type ArrayList<String> required as argument to observableArrayList
    // @ Controller.java (used to populate ListView)...
    ArrayList<String> getStringArrayList() {

        ArrayList<String> list;
        list = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add("Elem   @  ["+i+"]:   " + array[i]);
        }
        return list;
    }

    ArrayList<String> getCombinedCollatedStringArrayList() {

        ArrayList<String> oldList;
        oldList = new ArrayList<>(array.length);
        ArrayList<String> sortedList;
        sortedList = new ArrayList<>(array.length);
        ArrayList<String> combinedList;
        combinedList = new ArrayList<>(array.length * 2);

        // copy String elems into "old list"
        for (Integer elem : array) {
            oldList.add(""+elem);
        }

        // sort the array (destructive)
        Arrays.sort(array);

        // copy String elems into "sorted list"
        for (Integer elem : array) {
            sortedList.add(""+elem);
        }

        // join both String lists into "combined list" for return (collated results)
        for (int i = 0; i < array.length; i++) {
            combinedList.add("(Sorted)   Elem  @  ["+i+"]:   " + sortedList.get(i));
            combinedList.add("(Old)   Elem  @  ["+i+"]:   " + oldList.get(i));
        }

        return combinedList;
    }

    ArrayList<String> getCombinedUncollatedStringArrayList() {

        ArrayList<String> oldList;
        oldList = new ArrayList<>(array.length);
        ArrayList<String> sortedList;
        sortedList = new ArrayList<>(array.length);
        ArrayList<String> combinedList;
        combinedList = new ArrayList<>(array.length * 2);

        // copy String elems into "old list"
        for (Integer elem : array) {
            oldList.add(""+elem);
        }

        // sort the array (destructive)
        Arrays.sort(array);

        // copy String elems into "sorted list"
        for (Integer elem : array) {
            sortedList.add(""+elem);
        }

        // join both String lists into "combined list" for return (collated results)
        for (int i = 0; i < array.length; i++) {
            combinedList.add("(Sorted)   Elem  @  ["+i+"]:   " + sortedList.get(i));
        }
        for (int i = 0; i < array.length; i++) {
            combinedList.add("(Old)   Elem  @  ["+i+"]:   " + oldList.get(i));
        }

        return combinedList;
    }
}
