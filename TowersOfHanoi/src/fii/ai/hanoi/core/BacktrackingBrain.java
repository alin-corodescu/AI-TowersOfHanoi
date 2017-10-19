package fii.ai.hanoi.core;

import java.util.*;

/**
 * Created by alin on 10/18/17.
 * {@link Brain} implementation using a backtracking algorithm to compute a stateStack. Does not guarantee finding
 * the optimal stateStack
 */
public class BacktrackingBrain implements Brain {
    // I need a set of states
    private Set<State> visitedStates = new HashSet<>();

    private Stack<State> stateStack = new Stack<>();

    private List<State> solution = new ArrayList<>();

    /**
     * Variable hinting at which index will be the {@link State} for  the subsequent call to {@link #computeNextState(State)}
     */
    private int indexHint = 0;

    private boolean hasFoundSolution = false;

    @Override
    public State computeNextState(State currentState) {
        if (solution.isEmpty()) {
            findSolution(currentState);
        }
        // If the currentState is not at the indexHint
        if (!solution.get(indexHint).equals(currentState)) {
            // Look for the current state
            indexHint = solution.indexOf(currentState);
            if (indexHint == -1) {
                // Should never happen if the algorithm is followed
                throw new RuntimeException("Algorithm not following the solution");
            }
        }
        return solution.get(indexHint +1);
    }

    private void findSolution(State startingState) {
        backtrack(startingState);
        solution = new ArrayList<>(stateStack);

    }

    private void backtrack(State state) {
        visitedStates.add(state);
        stateStack.push(state);
        if (state.isFinal()) {
            hasFoundSolution = true;
            return;
        }

        List<State> accessibleStates = state.getAccessibleStates();
        for (State neighbour : accessibleStates) {
            // If we haven't visited this neighbour before
            if (!visitedStates.contains(neighbour) && !hasFoundSolution)
                backtrack(neighbour);
        }

        if (!hasFoundSolution) {
            stateStack.pop();
        }
    }

    @Override
    public long getNumberOfConsideredStates() {
        return visitedStates.size();
    }

    @Override
    public void reset() {
        visitedStates.clear();
        stateStack.clear();
        hasFoundSolution = false;
        indexHint = 0;

    }
}
