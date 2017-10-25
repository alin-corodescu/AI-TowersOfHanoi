package fii.ai.hanoi.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alin on 10/18/17.
 * TODO rename this class
 */
public class Runnner
{
    private List<Results> resultsList = new ArrayList<>();
    private int iterations;

    private Runnner()
    {

    }

    public static void main(String[] args)
    {
        new Runnner().run();
    }

    private void run()
    {
        SolutionFinder solutionFinder = createSolutionFinderFromInputDataFile("input.txt");
        for (int i = 0; i < iterations; i++)
        {
            solutionFinder.reset();
            Results results = solutionFinder.findSolution();
            resultsList.add(results);
        }
        ResultsProcessorImpl resultsProcessor = new ResultsProcessorImpl();
        resultsProcessor.processResults(resultsList);
    }

    private void printSolutionDetailed(List<State> solution)
    {
        for (State state : solution)
        {
            System.out.println(state + "->");
        }
    }

    private SolutionFinder createSolutionFinderFromInputDataFile(String filename)
    {
        try
        {
            Brain brain;
            Scanner scanner = new Scanner(new File(filename));
            iterations = scanner.nextInt();
            int rodCount = scanner.nextInt();
            int disc = scanner.nextInt();
            int[] startingStatePositions = new int[rodCount];
            int[] targetStatePositions = new int[rodCount];
            for (int i = 0; i < rodCount; i++)
                startingStatePositions[i] = scanner.nextInt();
            for (int i = 0; i < rodCount; i++)
                targetStatePositions[i] = scanner.nextInt();
            String strategy = scanner.next();
            switch (strategy.toLowerCase())
            {
                case "random":
                    brain = new RandomBrain();
                    break;
                case "hillclimb":
                    brain = new HillClimbingBrain();
                    break;
                case "backtracking":
                    brain = new BacktrackingBrain();
                    break;
                default:
                    throw new RuntimeException("Strategy " + strategy + "is not recognized!");
            }
            System.out.println("Strategia " + strategy.toLowerCase() + " detectata!");
            return new SolutionFinder(brain, rodCount, disc, startingStatePositions, targetStatePositions);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
