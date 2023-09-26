package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.piece.PieceType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoveTest {

    @Test
    public void shouldCreateAQueenCheckmateTarget() {
        Move move = new Move("Qh8#");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(move.getMove()).isEqualTo("Qh8#");
    }

    @Test
    public void shouldCreateAQueenTarget() {
        Move move = new Move("Qd5");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(move.getMove()).isEqualTo("Qd5");
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenFileTarget() {
        String target = "Qi4";

        assertThatThrownBy(() -> {
            new Move(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen move: " + target);
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenRankTarget() {
        String target = "Qa0";

        assertThatThrownBy(() -> {
            new Move(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen move: " + target);

        assertThatThrownBy(() -> {
            new Move("Qa9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen move: Qa9");
    }

    @Test
    public void shouldCreateAQueenCaptureTarget() {
        Move move = new Move("Qxe5");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(move.getMove()).isEqualTo("Qxe5");
    }

    @Test
    public void shouldCreateAPawnTarget() {
        Move move = new Move("a4");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("a4");
    }

    @Test
    public void shouldCreateAPawnTargetAtWhiteUpperBound() {
        Move move = new Move("a8");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("a8");
    }

    @Test
    public void shouldCreateAPawnTargetAtWhiteLowerBound() {
        Move move = new Move("a2");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("a2");
    }

    @Test
    public void shouldCreateAPawnTargetAtBlackUpperBound() {
        Move move = new Move("a1");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("a1");
    }

    @Test
    public void shouldCreateAPawnTargetAtBlackLowerBound() {
        Move move = new Move("a7");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("a7");
    }

    @Test
    public void shouldCreateAPawnCaptureTarget() {
        Move move = new Move("ex6");
        AssertionsForClassTypes.assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("ex6");
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnCaptureTarget() {
        String target = "exx6";

        assertThatThrownBy(() -> {
            new Move(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + target);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnFileTarget() {
        String target = "i4";

        assertThatThrownBy(() -> {
            new Move(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + target);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnRankTarget() {
        String target = "a0";

        assertThatThrownBy(() -> {
            new Move(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + target);

        assertThatThrownBy(() -> {
            new Move("a9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: a9");
    }

    @Test
    public void shouldThrowExceptionForAnInvalidTarget() {
        String target = "Z1";

        assertThatThrownBy(() -> {
            new Move(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid move: Z1");
    }

    @Test
    public void shouldThrowExceptionForNullTarget() {
        assertThatThrownBy(() -> {
            new Move(null);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Move cannot be null.");
    }

    @Test
    public void shouldThrowExceptionForEmptyTarget() {
        assertThatThrownBy(() -> {
            new Move(" ");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Move cannot be empty.");
    }
}