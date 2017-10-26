package fii.ai.hanoi.core;

import java.util.List;

/**
 * Created by alin on 10/18/17.
 */
public class ResultsProcessorImpl implements ResultsProcessor
{
    @Override
    public void processResults(List<Results> results)
    {
        double durationAvg = results.stream().mapToLong(Results::getDuration).average().getAsDouble();
        System.out.println("Durata medie de executie este : " + String.format("%.7f", durationAvg / 1000000000) + " secunde");

        double testedStatesAvg = results.stream().mapToLong(Results::getConsideredStates).average().getAsDouble();
        System.out.println("Numarul mediu de stari testate este: " + testedStatesAvg);

        double compactSolAverage = results.stream().mapToLong(r -> r.getBestSolution().size()).average().getAsDouble();
        System.out.println("Numarul mediu de stari ale solutiei (compactate) este : " + compactSolAverage);

        double solutionSizeAvg = results.stream().mapToLong(r -> r.getSolution().size()).average().getAsDouble();
        System.out.println("Numarul mediu de stari ale solutiei (NEcompactate) este : " + solutionSizeAvg);

        for (int i = 0; i < results.size(); i++)
        {
            System.out.printf("Solutia necompactata %d are %d stari", i, results.get(i).getSolution().size());
            System.out.println();
            System.out.printf("Solutia compactata %d are %d stari %s", i, results.get(i).getBestSolution().size(), System.lineSeparator());
        }

    }
}
