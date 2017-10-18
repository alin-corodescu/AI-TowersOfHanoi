package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin on 10/18/17.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        List<Integer> test1 = new ArrayList<>();

        test.add(1);
        test.add(2);
        test1.add(1);

        System.out.println(test1.equals(test));
    }
}
