package fii.ai.hanoi.core;

import java.util.List;

/**
 * Created by alin on 10/18/17.
 */
public class Results {
    List<State> solution;
    List<State> bestSolution;
    long duration;
    long consideredStates;

    public List<State> getBestSolution() {
        return bestSolution;
    }

    public void setBestSolution(List<State> bestSolution) {
        this.bestSolution = bestSolution;
    }

    public List<State> getSolution() {
        return solution;
    }

    public void setSolution(List<State> solution) {
        this.solution = solution;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getConsideredStates() {
        return consideredStates;
    }

    public void setConsideredStates(long consideredStates) {
        this.consideredStates = consideredStates;
    }
}
