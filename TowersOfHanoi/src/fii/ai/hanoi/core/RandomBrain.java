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

    private int numberOfSafeStates ;
    private int indexOfLastSafeState;

    public RandomBrain()
    {
        visitedStates = new ArrayList<>();
        solution = new Stack<>();
        accessibleStates = new ArrayList<>();
        randomGenerator = new Random();
        consideredStates = 0;
        indexOfLastSafeState = 0;
        numberOfSafeStates = 1;
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
                checkIfSafeState(currentState);
                return nextState;
            } else {
                accessibleStates.remove(nextState);
            }
        }
        if( solution.size() > 2) {
            int stepsBack;
            if (numberOfSafeStates > 0 && solution.size() > indexOfLastSafeState)
                stepsBack = randomGenerator.nextInt( solution.size() - indexOfLastSafeState) + 1;

            else
                stepsBack = randomGenerator.nextInt(solution.size()-1)+1;
            consideredStates++;
            visitedStates.add(currentState);
            for (int i = 0; i < stepsBack - 1; i++){
                visitedStates.remove(solution.pop());
            }
            return solution.pop();
        }
        else {
            visitedStates.clear();
            indexOfLastSafeState = 0;
            numberOfSafeStates = 1;
            return solution.pop();
        }
    }

    public void checkIfSafeState(State currentState){
        int copy = numberOfSafeStates;
        for(int i=currentState.getDiscsPositions().size()-1;i>=0 && copy > 0;i--){
            if( currentState.getDiscsPositions().get(i) == State.getFinalState().getDiscsPositions().get(i) )
            {
                indexOfLastSafeState = solution.size() - 1;
                numberOfSafeStates++;
                copy --;
            }
        }
    }

    @Override
    public long getNumberOfConsideredStates()
    {
        return consideredStates+1;
    }

    @Override
    public void reset()
    {
        accessibleStates.clear();
        visitedStates.clear();
        consideredStates = 0;
        solution.clear();
        randomGenerator = new Random();
        indexOfLastSafeState = 0;
        numberOfSafeStates = 1;
    }
}
