package com.sudoku.input.handler;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private static final String VALIDATOR_PATTERN = "(\\s*\\d\\s*,\\s*\\d\\s*,\\s*\\d\\s*){1,9}";
    private static final String REPLACE_REGEX = " ";
    private static final String REPLACEMENT = "";

    public String handleInput(String input) throws InvalidInputException {

        Matcher m = Pattern.compile(VALIDATOR_PATTERN).matcher(input);

        StringJoiner sj = new StringJoiner(",");
        boolean foundFlag = false;
        while (m.find()) {
            sj.add(m.group(1));
            foundFlag = true;
        }
        if (!foundFlag) {
            throw new InvalidInputException();
        }
        return sj.toString().replaceAll(REPLACE_REGEX, REPLACEMENT);
    }
}
