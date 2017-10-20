package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.Arrays;
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

    public State(Integer... args)
    {
        if (args.length != discsCount)
            throw new RuntimeException("Invalid state configuration");
        discsPositions = Arrays.asList(args);
    }


    public static void setFinalState(State finalState) {
           State.finalState = finalState;
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

    public static void setDefaultFinalState()
    {
        finalState = new State();
        for (int i = 0; i < discsCount; i++)
        {
            finalState.discsPositions.set(i, rodCount - 1);
        }
    }


    public static int getRodCount(){
        return rodCount;
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

    /**
     * Todo consider making this private
     * To generate new states starting from this, one should use {@link #move(int, int)} or {@link #getAccessibleStates()}
     *
     * @param which piece to move
     * @param where rod onto which to move the piece
     * @return a new state, the result of moving the piece which onto the rod where
     */
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

    /**
     * Todo consider public vs private access to this method
     * Tests if moving the piece which onto the rod where is a valid move
     *
     * @param which the piece to be moved
     * @param where to rod to be placed on
     */
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

    /**
     * To generate new states starting from this, one should use {@link #move(int, int)} or {@link #getAccessibleStates()}
     *
     * @return List of accessible states from the current state
     */
    public List<State> getAccessibleStates()
    {
        List<State> accessibleStates = new ArrayList<>();
        // Compute the available states from the current one
        // Not the most elegant way of doing things, but it's a start
        for (int which = 0; which < discsCount; which++)
            for (int where = 0; where < rodCount; where++)
                if (canMove(which, where))
                    accessibleStates.add(this.move(which, where));
        return accessibleStates;
    }

    @Override
    public int hashCode() {
        return discsPositions.hashCode();
    }
}
