package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin on 10/18/17.
 */
public class SolutionFinder
{

    private Brain brain;
    private List<State> solution = new ArrayList<>();

    private int[] initialDiscPositions;

    public SolutionFinder(Brain brain, int rodCount, int discCount, int[] initialDiscPositions, int[] finalDiscPositions)
    {
        this.brain = brain;
        this.initialDiscPositions = initialDiscPositions;
        State.setEnvConfiguration(rodCount, discCount);
        State.setFinalState(new State(finalDiscPositions));
    }

    public Results findSolution()
    {
        Results results = new Results();

        long startTime = System.nanoTime();

        State state = new State(initialDiscPositions);

        solution.add(state);
        while (!state.isFinal())
        {
            state = brain.computeNextState(state);
            solution.add(state);
        }

        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        results.consideredStates = brain.getNumberOfConsideredStates();
        results.duration = duration;
        results.solution = solution;
        results.setBestSolution(SolutionUtils.compactSolution(solution));

        // add duration and consideredStates to the result set, together with the states
        return results;
    }

    public void reset()
    {
        brain.reset();
        solution = new ArrayList<>();
    }
}
