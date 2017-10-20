package fii.ai.hanoi.core;

import java.util.ArrayList;
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

    private void run()
    {
        Brain brain = new RandomBrain();
        int rodCount = 3;
        int discCount = 3;
        int iterations = 1;
        SolutionFinder solutionFinder = new SolutionFinder(brain, rodCount, discCount);
        for (int i = 0; i < iterations; i++)
        {
            solutionFinder.reset();
            Results results = solutionFinder.findSolution();
            printSolutionDetailed(results.solution);
            resultsList.add(results);
        }
        ResultsProcessorImpl resultsProcessor = new ResultsProcessorImpl();
        resultsProcessor.processResults(resultsList);
    }
        private void printSolutionDetailed(List<State> solution){
            for (State state:solution){
                System.out.println(state);
            }
    }


    private Runnner() {

    }
}
