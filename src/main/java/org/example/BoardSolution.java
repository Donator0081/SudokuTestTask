package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BoardSolution {
    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int BOARD_START_INDEX = 0;

    public boolean isValid(char[][] board) {
        for (int i = BOARD_START_INDEX; i < BOARD_SIZE; i++) {
            if (!(isValidRowAndColumn(board, i) && isValidSubsection3x3(board, i))) {
                return false;
            }
        }
        return true;
    }


    private boolean isValidRowAndColumn(char[][] board, int value) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        boolean checkRow = IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> containsValidCharacters(board[value][column], constraint));
        if (!checkRow) {
            return false;
        }
        Arrays.fill(constraint, false);
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> containsValidCharacters(board[row][value], constraint));
    }

    private boolean isValidSubsection3x3(char[][] board, int value) {
        if (value % SUBSECTION_SIZE > 0) {
            return true;
        }
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (value / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = BOARD_START_INDEX;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int i = BOARD_START_INDEX; i < SUBSECTION_SIZE; i++) {
            for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
                for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                    if (!containsValidCharacters(board[r][c], constraint)) {
                        return false;
                    }
                }
            }
            subsectionColumnStart = subsectionColumnEnd;
            subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;
            Arrays.fill(constraint, false);
        }

        return true;
    }

    private boolean containsValidCharacters(char value, boolean[] constraint) {
        char noValue = '.';
        if (!Character.isDigit(value) && value != noValue) {
            throw new WrongCharException("Wrong character exception");
        }
        if (value == noValue) {
            return true;
        }
        int intValue = Character.getNumericValue(value);
        if (intValue > constraint.length || intValue < 1) {
            throw new WrongDigitException("Wrong digit exception");
        }
        if (!constraint[intValue - 1]) {
            constraint[intValue - 1] = true;
        } else {
            return false;
        }
        return true;
    }


}
