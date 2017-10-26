package fii.ai.hanoi.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alin on 10/26/17.
 */
public class AStarBain implements Brain {

    State initialState, finalState;
    /**
     * Total cost of getting to a final node via a state starting from the initial node
     */
    Map<State, Integer> f = new HashMap<>();
    /**
     * Optimal cost of getting from the initial node to a current state
     */
    Map<State, Integer> g = new HashMap<>();
    /**
     * Optimal cost of getting from a state to a final note
     */
    Map<State, Integer> h = new HashMap<>();

    @Override
    public State computeNextState(State currentState) {
        if (g.isEmpty()) {
            computeG(currentState, null);
            computeH(State.getFinalState(), null);
            initialState = currentState;
            for (State st : g.keySet()) {
                f.put(st, g.get(st) +  h.get(st));
            }
        }

        List<State> accessibleStates = currentState.getAccessibleStates();
        return accessibleStates.stream().min((o1, o2) -> {
            if (f.get(o1).compareTo(f.get(o2)) == 0) {
                return h.get(o1).compareTo(h.get(o2));
            }
            return f.get(o1).compareTo(f.get(o2));
        }).get();
    }

    private void computeG(State currentState, State parentState) {
        if (parentState == null) {
            // We are in the initial node
            g.put(currentState, 0);
        } else {
            g.put(currentState, g.get(parentState) + 1);
        }
        // g is computed for the current node
        // get the accessible states
        List<State> accessibleStates = currentState.getAccessibleStates();
        for (State neighbour : accessibleStates) {
            // if f is not yet computed and node not yet visited
            if (!g.containsKey(neighbour)) {
                computeG(neighbour, currentState);
            } else {
                // if we can improve the path
                if (g.get(neighbour) > g.get(currentState) + 1) {
                    computeG(neighbour, currentState);
                }
            }
        }
    }

    private void computeH(State currentState, State parentState) {
        if (parentState == null) {
            // We are in the initial node
            h.put(currentState, 0);
        } else {
            h.put(currentState, h.get(parentState) + 1);
        }
        List<State> accessibleStates = currentState.getAccessibleStates();
        for (State neighbour : accessibleStates) {

            if (!h.containsKey(neighbour)) {
                computeH(neighbour, currentState);
            } else {

                if (h.get(neighbour) > h.get(currentState) + 1) {
                    computeH(neighbour, currentState);
                }
            }
        }

    }

//    private Integer computeF(State currentState, State parentState) {
//        int value;
//        if (parentState == null) {
//            // We are in the initial node
//            g.put(currentState, 0);
//        }
//        else {
//            g.put(currentState, g.get(parentState) + 1);
//        }
//
//        if (currentState.isFinal()) {
//            // h (starea curenta) = 0 pt ca starea e finala
//            h.put(currentState, 0);
//        }
//        else {
//            // g is computed for the current node
//            // get the accessible states
//
//            List<State> accessibleStates = currentState.getAccessibleStates();
//            for (State neighbour : accessibleStates) {
//                // if f is not yet computed and node not yet visited
//                if (!f.containsKey(neighbour)) {
//                    // compute the new f
//                    f.put(neighbour, computeF(neighbour, currentState));
//                } else {
//                    // if we can improve the path
//                    if (g.get(neighbour) > g.get(currentState) + 1) {
//                        // recompute the f function because we have a better path toward the neighbour
//                        // and it might change other f's as well
//                        f.put(neighbour, computeF(neighbour, currentState));
//                    }
//                }
//            }
//            // h(current) = min (h(vecini)) + 1
//            h.put(currentState,  accessibleStates.stream().mapToInt(s -> h.get(s)).min().getAsInt() + 1);
//        }
//        value = h.get(currentState) + g.get(currentState);
//        f.put(currentState, value);
//        return f.get(currentState);
//    }

    @Override
    public long getNumberOfConsideredStates() {
        return h.size();
    }

    @Override
    public void reset() {
        f = new HashMap<>();
        g = new HashMap<>();
        h = new HashMap<>();
    }
}
