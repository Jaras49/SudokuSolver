package com.sudoku.input.reader;

import java.util.Scanner;

public class InputReader {

    private final Scanner sc;

    public InputReader() {

        sc = new Scanner(System.in);
    }

    public String getInput() {

        return sc.nextLine();

    }
}
