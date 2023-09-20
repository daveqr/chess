package com.ebonyandirony.chess.square;

import com.ebonyandirony.chess.board.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//private static final String[][] CHESSBOARD = {
//        {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"},
//        {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8"},
//        {"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8"},
//        {"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8"},
//        {"e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"},
//        {"f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8"},
//        {"g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8"},
//        {"h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8"}
//        };

class SquareAddressTest {
    @Test
    public void shouldGetTheCorrectAtSquare() {
        final SquareAddress square = SquareAddress.fromString("b6");

        assertThat(square.getFile()).isEqualTo(1);
        assertThat(square.getRank()).isEqualTo(5);
    }

    @Test
    public void shouldGetSquareForValidSquares() {
        for (char letter = Board.FILE_LOWER_BOUND; letter <= Board.FILE_UPPER_BOUND; letter++) {
            for (int i = Board.RANK_LOWER_BOUND; i <= Board.RANK_UPPER_BOUND; i++) {
                final String squareLabel = String.valueOf(letter) + i;
                final SquareAddress square = SquareAddress.fromString(squareLabel);
                assertNotNull(square);
            }
        }
    }

    @Test
    public void shouldThrowExceptionForIncorrectLength() {
        assertThatThrownBy(() -> {
            SquareAddress.fromString("b66");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: b66");

    }

    @Test
    public void shouldThrowExceptionOutsideOfFileBound() {
        for (char letter = 'i'; letter <= 'z'; letter++) {
            final String finalLetter = letter + "6";
            assertThatThrownBy(() -> {
                SquareAddress.fromString(finalLetter);
            }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: " + finalLetter);
        }
    }

    @Test
    public void shouldThrowExceptionUnderRankLowerBound() {
        assertThatThrownBy(() -> {
            SquareAddress.fromString("a0");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: a0");
    }

    @Test
    public void shouldThrowExceptionOverRankUpperBound() {
        assertThatThrownBy(() -> {
            SquareAddress.fromString("a9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: a9");
    }
}