package fii.ai.hanoi.core;

import java.util.*;

public class HillClimbingBrain implements Brain
{
    private List<State> visitedStates;

    private Stack<State> solution;

    private List<State> accessibleStates;

    private Random randomGenerator;

    public HillClimbingBrain()
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
            State nextState = this.findBestState(accessibleStates);
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

    private State findBestState(List<State> accessibleStates)
    {
        int bestMinim = 9999, minim;
        State state = new State();
        for (State accessibleState : accessibleStates) {
            minim = calculateHeuristics(accessibleState);
            if (bestMinim > minim) {
                state = accessibleState;
                bestMinim = minim;
            }
            else if (bestMinim == minim)
                if (randomGenerator.nextInt(10) > 5){
                    state = accessibleState;
                }
        }
        return state;
    }

    private int calculateHeuristics(State state)
    {
        int hillClimbHeuristic = 0, numberOfRods = State.getRodCount()-1;
        for(int i=0;i<state.getDiscsPositions().size();i++)
            hillClimbHeuristic += numberOfRods - state.getDiscsPositions().get(i)*(i+1);
        return hillClimbHeuristic;
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
