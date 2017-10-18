package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin on 10/18/17.
 */
public class SolutionFinder {

    private Brain brain;
    private List<State> solution = new ArrayList<>();

    public SolutionFinder(Brain brain, int rodCount, int discCount) {
        this.brain = brain;
        State.setEnvConfiguration(rodCount, discCount);
    }

    public Results findSolution() {
        Results results = new Results();

        long startTime = System.nanoTime();
        // Todo maybe later change from default
        State state = State.getDefaultInitialState();
        solution.add(state);
        while (!state.isFinal()) {
            state = brain.computeNextState(state);
            solution.add(state);
        }

        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        results.consideredStates = brain.getNumberOfConsideredStates();
        results.duration = duration;
        results.solution = solution;

        // add duration and consideredStates to the result set, together with the states
        return results;
    }

    public void reset() {
        brain.reset();
        solution.clear();
    }
}