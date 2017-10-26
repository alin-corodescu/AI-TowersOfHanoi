package fii.ai.hanoi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin on 10/26/17.
 */
public class SolutionUtils {
    /**
     * Given a series of states, this function returns the minimal path of states that is contained within the series
     * by removing cycles in the list. for example : abcbd becomes abd, because bcb is a cycle.
     * @param expandedSoltion series of states to be compacted
     * @return compacted soltion
     */
    public static List<State> compactSolution(List<State> expandedSoltion) {
        List<State> compactSolution = new ArrayList<>();
        for (int  i = 0; i < expandedSoltion.size(); i++) {
            compactSolution.add(expandedSoltion.get(i));
            int lastIdx = expandedSoltion.lastIndexOf(expandedSoltion.get(i));
            if (lastIdx > i) {
                i = lastIdx;
            }
        }
        return compactSolution;
    }
}
