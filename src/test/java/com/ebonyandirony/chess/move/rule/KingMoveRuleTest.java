package com.ebonyandirony.chess.move.rule;

import com.ebonyandirony.chess.board.Board;
import com.ebonyandirony.chess.move.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KingMoveRuleTest {
    private static final long WHITE_KING_AT_D4 = 0b00000000_00000000_00000000_00000000_00001000_00000000_00000000_00000000L;
    private static final long WHITE_KING_AT_B1 = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000010L;

    private static final String[] KING_MOVES = {
            "Kc4", "Kd5", "Kc5", "Ke5", "Ke4", "Ke3", "Kd3", "Kc3"
    };

    private static final String[] OCCUPIED_KING_MOVES = {
            "Ka1", "Kc1", "Ka2", "Kb2", "Kc2"
    };

    private static Stream<String> kingMovesProvider() {
        return Arrays.stream(KING_MOVES);
    }

    private static Stream<String> occupiedKingMovesProvider() {
        return Arrays.stream(OCCUPIED_KING_MOVES);
    }

    @ParameterizedTest
    @MethodSource("kingMovesProvider")
    public void shouldAllowKingToMoveLeftWhenUnoccupied(final String move) {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_D4);

        final KingMoveRule rule = new KingMoveRule(board, Move.create(move));

        assertThat(rule.isLegalMove()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("occupiedKingMovesProvider")
    public void shouldDisallowKingToMoveLeftWhenOccupied(final String move) {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_B1);

        final KingMoveRule rule = new KingMoveRule(board, Move.create(move));

        assertThat(rule.isLegalMove()).isFalse();
    }

    @Test
    public void shouldDisallowKingToMoveLeftWhenMoreThanOne() {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_D4);

        final Move move = Move.create("Ka4");
        final KingMoveRule rule = new KingMoveRule(board, move);

        assertThat(rule.isLegalMove()).isFalse();
    }
}