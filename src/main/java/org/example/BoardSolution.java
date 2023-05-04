package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class BoardSolution {

    private static final Set<Character> validNumbers = new HashSet<>();
    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int BOARD_START_INDEX = 0;

    static {
        validNumbers.add('1');
        validNumbers.add('2');
        validNumbers.add('3');
        validNumbers.add('4');
        validNumbers.add('5');
        validNumbers.add('6');
        validNumbers.add('7');
        validNumbers.add('8');
        validNumbers.add('9');
    }

    public boolean isValid(char[][] board) {
        boolean result = false;
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (validation(board, row, column)) {
                    result = true;
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    private boolean validation(char[][] board, int row, int column) {
        return isValidRow(board, row) &&
                isValidColumn(board, column) &&
                isValidSubsection3x3(board, row, column);
    }

    private boolean isValidRow(char[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> containsValidCharacters(board[row][column], constraint));
    }

    private boolean isValidColumn(char[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> containsValidCharacters(board[row][column], constraint));
    }

    private boolean isValidSubsection3x3(char[][] board, int row, int column) {
        if ((row % SUBSECTION_SIZE > 0) || (column % SUBSECTION_SIZE > 0)) {
            return true;
        }
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!containsValidCharacters(board[r][c], constraint)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean containsValidCharacters(char value, boolean[] constraint) {
        char noValue = '.';
        if (!validNumbers.contains(value) && value != noValue) {
            throw new WrongCharException("Wrong character exception");
        }
        if (value != noValue) {
            int intValue = Character.getNumericValue(value);
            if (!constraint[intValue - 1]) {
                constraint[intValue - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }


}
