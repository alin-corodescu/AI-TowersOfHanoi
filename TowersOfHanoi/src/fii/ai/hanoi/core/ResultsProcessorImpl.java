package fii.ai.hanoi.core;

import com.sun.org.apache.regexp.internal.RE;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * Created by alin on 10/18/17.
 */
public class ResultsProcessorImpl implements ResultsProcessor {
    @Override
    public void processResults(List<Results> results) {
        double durationAvg = results.stream().mapToLong(Results::getDuration).average().getAsDouble();
        System.out.println("Durata medie de executie este : " + durationAvg);

        double testedStatesAvg = results.stream().mapToLong(Results::getConsideredStates).average().getAsDouble();
        System.out.println("Numarul mediu de stari testate este: " + testedStatesAvg);

        for (int i = 1; i < results.size(); i++) {
            System.out.printf("Solutia %d are %d stari", i, results.get(i).getSolution().size());
        }
    }
}
