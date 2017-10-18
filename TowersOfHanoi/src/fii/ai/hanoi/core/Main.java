package fii.ai.hanoi.core;

import java.util.Set;

/**
 * Created by alin on 10/18/17.
 */
public class Main
{
    public static void main(String[] args)
    {
        State.setEnvConfiguration(3, 3);
        State initialState = State.getDefaultInitialState();

        System.out.println("Initial State: " + initialState);

        State nextState = initialState.move(0, 1);

        System.out.println("NextState: " + nextState);

        int x = 1;
        System.out.printf("This is a %d formatted string \n", x);
    }
}
