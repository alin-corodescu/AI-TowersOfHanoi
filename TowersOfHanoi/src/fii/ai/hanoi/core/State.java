package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alin on 10/18/17.
 * Class represinting a valid configuration of the system
 */
public class State
{
    private static int rodCount = 3, discsCount = 3;
    private static State finalState;
    /**
     * To reconsider type of this member
     */
    private List<Integer> discsPositions;

    public State()
    {
        discsPositions = new ArrayList<>(Collections.nCopies(discsCount, 0));
    }

    /**
     * Static setter method used to initialize the configuration of the environment. Used to set the number of rods and
     * discs to be considered as input
     */
    public static void setEnvConfiguration(int rodCount, int discsCount)
    {

        State.rodCount = rodCount;
        State.discsCount = discsCount;
    }

    public static State getDefaultInitialState()
    {
        State initialState = new State();
        for (int i = 0; i < discsCount; i++)
        {
            initialState.discsPositions.set(i, 0);
        }
        return initialState;
    }

    public List<Integer> getDiscsPositions()
    {
        return new ArrayList<>(discsPositions);
    }

    public boolean isFinal()
    {
        return this.equals(finalState);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof State)
        {
            State comparedState = (State) obj;
            return comparedState.discsPositions.equals(this.discsPositions);
        } else return super.equals(obj);
    }

    public State move(int which, int where)
    {
        if (canMove(which, where))
        {
            State nextState = this.cloneState();
            nextState.discsPositions.set(which, where);
            return nextState;
        } else
            /* TODO think about another exception */
            throw new RuntimeException("Ilegal move!");
    }

    public boolean canMove(int which, int where)
    {
        if (this.discsPositions.get(which) == where)
            return false;
        for (int i = 0; i < which; i++)
            if (discsPositions.get(i) == discsPositions.get(which) ||
                    discsPositions.get(i) == where)
                return false;
        return true;
    }

    private State cloneState()
    {
        State clonedState = new State();
        for (int i = 0; i < discsPositions.size(); i++)
            clonedState.discsPositions.set(i, discsPositions.get(i));
        return clonedState;
    }

    @Override
    public String toString()
    {
        return discsPositions.toString();
    }
}
