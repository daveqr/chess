package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.piece.Color;
import com.ebonyandirony.chess.piece.PieceType;
import org.junit.jupiter.api.Test;

import static com.ebonyandirony.chess.piece.Color.BLACK;
import static com.ebonyandirony.chess.piece.Color.WHITE;
import static com.ebonyandirony.chess.piece.PieceType.QUEEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoveTest {

    @Test
    public void shouldCreateAQueenCheckmateTarget() {
        Move move = Move.create("Qh8#", WHITE);
        assertThat(move.getType()).isEqualTo(QUEEN);
        assertThat(move.getTarget()).isEqualTo("Qh8#");
        assertThat(move.getFile()).isEqualTo('h');
        assertThat(move.getRank()).isEqualTo('8');

    }

    @Test
    public void shouldCreateAQueenTarget() {
        Move move = Move.create("Qd5", WHITE);
        assertThat(move.getType()).isEqualTo(QUEEN);
        assertThat(move.getTarget()).isEqualTo("Qd5");
        assertThat(move.getFile()).isEqualTo('d');
        assertThat(move.getRank()).isEqualTo('5');
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenFileTarget() {
        String target = "Qi4";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenRankTarget() {
        String target = "Qa0";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);

        assertThatThrownBy(() -> {
            Move.create("Qa9", WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: Qa9");
    }

    @Test
    public void shouldCreateAQueenCaptureTarget() {
        Move move = Move.create("Qxe5", WHITE);
        assertThat(move.getType()).isEqualTo(QUEEN);
        assertThat(move.getTarget()).isEqualTo("Qxe5");
        assertThat(move.getFile()).isEqualTo('e');
        assertThat(move.getRank()).isEqualTo('5');
    }

    @Test
    public void shouldCreateAPawnTarget() {
        Move move = Move.create("a4", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getTarget()).isEqualTo("a4");
        assertThat(move.getFile()).isEqualTo('a');
        assertThat(move.getRank()).isEqualTo('4');
    }

    @Test
    public void shouldCreateAPawnTargetAtWhiteUpperBound() {
        Move move = Move.create("a8", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getTarget()).isEqualTo("a8");
        assertThat(move.getFile()).isEqualTo('a');
        assertThat(move.getRank()).isEqualTo('8');
    }

    @Test
    public void shouldCreateAPawnTargetAtWhiteLowerBound() {
        Move move = Move.create("a2", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getTarget()).isEqualTo("a2");
        assertThat(move.getFile()).isEqualTo('a');
        assertThat(move.getRank()).isEqualTo('2');
    }

    @Test
    public void shouldCreateAPawnTargetAtBlackUpperBound() {
        Move move = Move.create("a1", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getTarget()).isEqualTo("a1");
        assertThat(move.getFile()).isEqualTo('a');
        assertThat(move.getRank()).isEqualTo('1');
    }

    @Test
    public void shouldCreateAPawnTargetAtBlackLowerBound() {
        Move move = Move.create("a7", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getTarget()).isEqualTo("a7");
        assertThat(move.getFile()).isEqualTo('a');
        assertThat(move.getRank()).isEqualTo('7');
    }

    @Test
    public void shouldCreateAPawnCaptureTarget() {
        Move move = Move.create("exd5", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getTarget()).isEqualTo("exd5");
        assertThat(move.getFile()).isEqualTo('d');
        assertThat(move.getRank()).isEqualTo('5');
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnCaptureTarget() {
        String target = "exx6";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnFileTarget() {
        String target = "i4";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnRankTarget() {
        String target = "a0";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);

        assertThatThrownBy(() -> {
            Move.create("a9", WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: a9");
    }

    @Test
    public void shouldThrowExceptionForAnInvalidTarget() {
        String target = "Z1";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: Z1");
    }

    @Test
    public void shouldThrowExceptionForNullTarget() {
        assertThatThrownBy(() -> {
            Move.create(null, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Move cannot be null.");
    }

    @Test
    public void shouldThrowExceptionForEmptyTarget() {
        assertThatThrownBy(() -> {
            Move.create(" ", WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Move cannot be empty.");
    }

    @Test
    public void shouldCreateAKingMove() {
        Move move = Move.create("Kf1", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.KING);
        assertThat(move.getTarget()).isEqualTo("Kf1");
        assertThat(move.getFile()).isEqualTo('f');
        assertThat(move.getRank()).isEqualTo('1');
    }

    @Test
    public void shouldThrowExceptionForInvalidFile() {
        String target = "Ka0";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);

        assertThatThrownBy(() -> {
            Move.create("Ka9", WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: Ka9");
    }

    @Test
    public void shouldThrowExceptionForInvalidRank() {
        String target = "Ki1";

        assertThatThrownBy(() -> {
            Move.create(target, WHITE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: " + target);
    }

    @Test
    public void shouldCreateAKingCaptureMove() {
        Move move = Move.create("Kxf1", WHITE);
        assertThat(move.getType()).isEqualTo(PieceType.KING);
        assertThat(move.getTarget()).isEqualTo("Kxf1");
        assertThat(move.getFile()).isEqualTo('f');
        assertThat(move.getRank()).isEqualTo('1');
    }

    @Test
    public void shouldReturnSimpleString() {
        Move move = Move.create("Kxf1", WHITE);
        assertThat(move.toSimpleString()).isEqualTo("f1");
    }

    @Test
    public void shouldBeWhiteMoveWhenCreatedAsWhite() {
        final boolean isWhite =  Move.create("Kxf1", WHITE).isWhiteMove();
        assertThat(isWhite).isTrue();
    }

    @Test
    public void shouldBeBlackMoveWhenCreatedAsBlack() {
        final boolean isWhite =  Move.create("Kxf1", BLACK).isWhiteMove();
        assertThat(isWhite).isFalse();
    }
}