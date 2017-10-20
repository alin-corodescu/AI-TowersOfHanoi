package fii.ai.hanoi.core;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alin on 10/18/17.
 * TODO rename this class
 */
public class Runnner {
    private List<Results> resultsList = new ArrayList<>();

    public static void main(String[] args) {
        new Runnner().run();
    }

    private void run() {
        Brain brain = new BacktrackingBrain();
        int rodCount = 5;
        int discCount = 5;
        int iterations = 1;
        SolutionFinder solutionFinder = new SolutionFinder(brain, rodCount, discCount);
        for (int i = 0; i < iterations; i++) {
            solutionFinder.reset();
            Results results = solutionFinder.findSolution();
            printSolutionDetailed(results.solution);
            resultsList.add(results);
        }


    }

    private void printSolutionDetailed(List<State> solution) {
        for (State state : solution) {
            System.out.println(state + "->");
        }
    }

    private Runnner() {

    }
}
