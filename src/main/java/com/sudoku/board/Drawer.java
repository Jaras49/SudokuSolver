package com.sudoku.board;

import com.sudoku.board.elements.SudokuBoard;

public class Drawer {

    public String draw(SudokuBoard board) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.getSudokuRows().size(); i++) {

            sb.append(drawHorizontalLine(board.getSudokuRows().get(i).getSudokuElements().size()));

            for (int k = 0; k < board.getSudokuRows().get(0).getSudokuElements().size(); k++) {

                if (k == 0) {
                    sb.append("|");
                }

                if (board.getSudokuRows().get(i).getSudokuElements().get(k).getValue() == -1) {
                    sb.append("   |");

                } else {
                    sb.append(" ");
                    sb.append(board.getSudokuRows().get(i).getSudokuElements().get(k).getValue());
                    sb.append(" |");
                }
            }
            sb.append("\n");

            if(i == board.getSudokuRows().size() - 1) {
                sb.append(drawHorizontalLine(board.getSudokuRows().get(i).getSudokuElements().size()));
            }
        }
        return sb.toString();
    }

    private StringBuilder drawHorizontalLine(int elements) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < elements; i++) {

            if(i == 0) {
                sb.append("+---+");
            }
            else {
                sb.append("---+");
            }
        }
        return sb.append("\n");
    }
}
