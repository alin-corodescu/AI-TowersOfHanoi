package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by alin on 10/18/17.
 * {@link Brain} implementation using a random algorithm to find the road to the final state. Does not guarantee finding
 * the optimal stateStack
 */
public class RandomBrain implements Brain
{
    private List<State> visitedStates;

    private Stack<State> solution;

    private List<State> accessibleStates;

    private Random randomGenerator;

    public RandomBrain()
    {
        visitedStates = new ArrayList<>();
        solution = new Stack<>();
        accessibleStates = new ArrayList<>();
        randomGenerator = new Random();
    }

    @Override
    public State computeNextState(State currentState)
    {
        accessibleStates.clear();
        accessibleStates = currentState.getAccessibleStates();
        while (!accessibleStates.isEmpty())
        {
            int nextStateIndex = randomGenerator.nextInt(accessibleStates.size());
            State nextState = accessibleStates.get(nextStateIndex);
            if (!visitedStates.contains(nextState))
            {
                visitedStates.add(currentState);
                solution.add(currentState);
                return nextState;
            } else
            {
                accessibleStates.remove(nextState);
            }
        }
        visitedStates.add(currentState);
        return solution.pop();
    }

    @Override
    public long getNumberOfConsideredStates()
    {
        return visitedStates.size();
    }

    @Override
    public void reset()
    {
        visitedStates.clear();
        solution.clear();
        accessibleStates.clear();
    }
}
