package fii.ai.hanoi.core;

import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * Created by alin on 10/18/17.
 * TODO rename this class
 */
public class Runnner {
    private List<Results> resultsList;

    public static void main(String[] args) {
        new Runnner().run();
    }

    private void run() {
        Brain brain = null;
        int rodCount = 0;
        int discCount = 0;
        int iterations = 1;
        SolutionFinder solutionFinder = new SolutionFinder(brain, rodCount, discCount);
        for (int i = 0; i < iterations; i++) {
            solutionFinder.reset();
            Results results = solutionFinder.findSolution();
            resultsList.add(results);
        }


    }

    private Runnner() {

    }
}
