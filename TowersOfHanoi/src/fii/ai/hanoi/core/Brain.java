package fii.ai.hanoi.core;

/**
 * Created by alin on 10/18/17.
 */
public interface Brain {
    /**
     * Computes the next state based on a heuristic
     * @param currentState state from which to go
     * @return next state
     */
    State computeNextState(State currentState);

    /**
     * @return the number of states considered when making decisions during calls to {@see fii.ai.hanoi.core.Brain#computeNextState}
     */
    long getNumberOfConsideredStates();

    /**
     * Resets any internal members
     */
    void reset();
}
