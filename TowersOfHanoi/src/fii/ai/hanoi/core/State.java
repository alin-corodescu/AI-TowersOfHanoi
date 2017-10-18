package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin on 10/18/17.
 * Class represinting a valid configuration of the system
 */
public class State {
    private static int rodCount = 3, discsCount = 3;
    private static State finalState;
    /**
     * To reconsider type of this member
     */
    private List<Integer> discsPositions;

    public State() {
        discsPositions = new ArrayList<>(discsCount);
    }

    /**
     * Static setter method used to initialize the configuration of the environment. Used to set the number of rods and
     * discs to be considered as input
     */
    public static void setEnvConfiguration(int rodCount, int discsCount) {

        State.rodCount = rodCount;
        State.discsCount = discsCount;
    }

    public static State getDefaultInitialState() {
        State initialState = new State();
        for (int i = 0; i < discsCount; i++) {
            initialState.discsPositions.set(i, 0);
        }
        return initialState;
    }

    public boolean isFinal() {
        return this.equals(finalState);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State comparedState = (State) obj;
            return comparedState.discsPositions.equals(this.discsPositions);
        } else return super.equals(obj);
    }
}
