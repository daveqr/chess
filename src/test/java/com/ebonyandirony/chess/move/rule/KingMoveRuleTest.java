package com.ebonyandirony.chess.move.rule;

import com.ebonyandirony.chess.board.Board;
import com.ebonyandirony.chess.move.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.ebonyandirony.chess.piece.Color.WHITE;
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
    public void shouldAllowKingToMoveWhenTargetUnoccupied(final String target) {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_D4);

        final KingMoveRule rule = new KingMoveRule(board, Move.create(target, WHITE));

        assertThat(rule.isLegalMove()).as("Move is legal: d4 to " + target).isTrue();
    }

    @ParameterizedTest
    @MethodSource("occupiedKingMovesProvider")
    public void shouldDisallowKingToMoveWhenTargetOccupied(final String move) {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_B1);

        final KingMoveRule rule = new KingMoveRule(board, Move.create(move, WHITE));

        assertThat(rule.isLegalMove()).isFalse();
    }

    @Test
    public void shouldDisallowKingToMoveUpWhenMoreThanOne() {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_D4);

        final Move move = Move.create("Kd7", WHITE);
        final KingMoveRule rule = new KingMoveRule(board, move);

        assertThat(rule.isLegalMove()).isFalse();
    }

    @Test
    public void shouldDisallowKingToMoveDownWhenMoreThanOne() {
        final Board board = Board.create();
        board.setWhiteKingBoard(WHITE_KING_AT_D4);

        final Move move = Move.create("Kd1", WHITE);
        final KingMoveRule rule = new KingMoveRule(board, move);

        assertThat(rule.isLegalMove()).isFalse();
    }
}
