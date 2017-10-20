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

    private long consideredStates;

    public RandomBrain()
    {
        visitedStates = new ArrayList<>();
        solution = new Stack<>();
        accessibleStates = new ArrayList<>();
        randomGenerator = new Random();
        consideredStates = 0;
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
                consideredStates++;
                visitedStates.add(currentState);
                solution.add(currentState);
                return nextState;
            } else {
                accessibleStates.remove(nextState);
            }
        }
        if( solution.size() > 2) {
            int stepsBack = randomGenerator.nextInt(solution.size()-1)+1;
            consideredStates++;
            visitedStates.add(currentState);
            for (int i = 0; i < stepsBack - 1; i++){
                visitedStates.remove(solution.pop());
            }
            return solution.pop();
        }
        else {
            visitedStates.clear();
            return solution.pop();
        }
    }

    @Override
    public long getNumberOfConsideredStates()
    {
        return consideredStates;
    }

    @Override
    public void reset()
    {
        accessibleStates.clear();
        visitedStates.clear();
        consideredStates = 0;
        solution.clear();
        randomGenerator = new Random();
    }
}
