package com.sudoku.input.handler;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class InputHandlerTestSuite {

    private static final InputHandler inputHandler = new InputHandler();

    @RunWith(Parameterized.class)
    public static class ValidInput {

        @Parameters
        public static Collection testData() {
            return Arrays.asList(new Object[][]{
                    {"1,2,3,4,5,6", "1,2,3,4,5,6"},
                    {"   1   ,  2,3", "1,2,3"},
                    {"  1,   2 , 3  , 4 ", "1,2,3"},
                    {"1,2,3,4,5,    ,6,7,8    ,    9", "1,2,3,6,7,8"},
                    {"1,2,3,4,5,    6,7,8    ,    9", "1,2,3,4,5,6,7,8,9"}
            });
        }

        @Parameter
        public String input;

        @Parameter(1)
        public String expected;

        @Test
        public void shouldHandleInput() throws InvalidInputException {

            assertEquals(expected, inputHandler.handleInput(input));
        }
    }

    @RunWith(Parameterized.class)
    public static class InvalidInput {

        @Parameters
        public static Collection testData() {
            return Arrays.asList(new Object[][]{
                    {"11111", true},
                    {"  1,  22, 33 , 4 ,5 ", false},
                    {"1,2", true},
                    {"1,2,3", false}
            });
        }

        @Parameter
        public String input;

        @Parameter(1)
        public boolean expected;

        @Test
        public void shouldThrowException() {

            boolean isThrown = false;
            try {
                inputHandler.handleInput(input);
            } catch (InvalidInputException e) {
                isThrown = true;
            } finally {
                assertEquals(expected, isThrown);
            }
        }
    }
}