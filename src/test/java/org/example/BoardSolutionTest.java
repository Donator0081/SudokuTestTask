package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardSolutionTest {

    private final BoardSolution boardSolution = new BoardSolution();

    @Test
    void isValid_validBoard_shouldReturnTrue() {
        assertTrue(boardSolution.isValid(BoardSolutionTestUtil.VALID_BOARD));
    }

    @Test
    void isValid_invalidBoardSameNumberAtRow_shouldReturnFalse() {
        assertFalse(boardSolution.isValid(BoardSolutionTestUtil.INVALID_BOARD_SAME_NUMBER_AT_ROW));
    }

    @Test
    void isValid_invalidBoardSameNumberAtColumn_shouldReturnFalse() {
        assertFalse(boardSolution.isValid(BoardSolutionTestUtil.INVALID_BOARD_SAME_NUMBER_AT_COLUMN));
    }

    @Test
    void isValid_invalidBoardSameNumberAtSubsection_shouldReturnFalse() {
        assertFalse(boardSolution.isValid(BoardSolutionTestUtil.INVALID_BOARD_SAME_NUMBER_AT_SUBSECTION));
    }

    @Test
    void isValid_invalidBoardIncorrectCharacter_shouldReturnFalse() {
        assertThrows(WrongCharException.class, () -> boardSolution.isValid(BoardSolutionTestUtil.INVALID_BOARD_INCORRECT_CHARACTER));
    }

    @Test
    void isValid_invalidBoardIncorrectDigit_shouldReturnFalse() {
        assertThrows(WrongDigitException.class, () -> boardSolution.isValid(BoardSolutionTestUtil.INVALID_BOARD_INCORRECT_DIGIT));
    }


}
