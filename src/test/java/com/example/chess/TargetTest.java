package com.example.chess;

import com.example.chess.piece.PieceType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class TargetTest {

    @Test
    public void shouldCreateAQueenCheckmateTarget() {
        Target target = new Target("Qh8#");
        assertThat(target.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(target.getTarget()).isEqualTo("Qh8#");
    }

    @Test
    public void shouldCreateAQueenTarget() {
        Target target = new Target("Qd5");
        assertThat(target.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(target.getTarget()).isEqualTo("Qd5");
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenFileTarget() {
        String target = "Qi4";

        assertThatThrownBy(() -> {
            new Target(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen target: " + target);
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenRankTarget() {
        String target = "Qa0";

        assertThatThrownBy(() -> {
            new Target(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen target: " + target);

        assertThatThrownBy(() -> {
            new Target("Qa9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen target: Qa9");
    }

    @Test
    public void shouldCreateAQueenCaptureTarget() {
        Target target = new Target("Qxe5");
        assertThat(target.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(target.getTarget()).isEqualTo("Qxe5");
    }

    @Test
    public void shouldCreateAPawnTarget() {
        Target target = new Target("a4");
        assertThat(target.getType()).isEqualTo(PieceType.PAWN);
        assertThat(target.getTarget()).isEqualTo("a4");
    }

    @Test
    public void shouldCreateAPawnTargetAtWhiteUpperBound() {
        Target target = new Target("a8");
        assertThat(target.getType()).isEqualTo(PieceType.PAWN);
        assertThat(target.getTarget()).isEqualTo("a8");
    }

    @Test
    public void shouldCreateAPawnTargetAtWhiteLowerBound() {
        Target target = new Target("a2");
        assertThat(target.getType()).isEqualTo(PieceType.PAWN);
        assertThat(target.getTarget()).isEqualTo("a2");
    }

    @Test
    public void shouldCreateAPawnTargetAtBlackUpperBound() {
        Target target = new Target("a1");
        assertThat(target.getType()).isEqualTo(PieceType.PAWN);
        assertThat(target.getTarget()).isEqualTo("a1");
    }

    @Test
    public void shouldCreateAPawnTargetAtBlackLowerBound() {
        Target target = new Target("a7");
        assertThat(target.getType()).isEqualTo(PieceType.PAWN);
        assertThat(target.getTarget()).isEqualTo("a7");
    }

    @Test
    public void shouldCreateAPawnCaptureTarget() {
        Target target = new Target("ex6");
        assertThat(target.getType()).isEqualTo(PieceType.PAWN);
        assertThat(target.getTarget()).isEqualTo("ex6");
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnCaptureTarget() {
        String target = "exx6";

        assertThatThrownBy(() -> {
            new Target(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn target: " + target);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnFileTarget() {
        String target = "i4";

        assertThatThrownBy(() -> {
            new Target(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn target: " + target);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnRankTarget() {
        String target = "a0";

        assertThatThrownBy(() -> {
            new Target(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn target: " + target);

        assertThatThrownBy(() -> {
            new Target("a9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn target: a9");
    }

    @Test
    public void shouldThrowExceptionForAnInvalidTarget() {
        String target = "Z1";

        assertThatThrownBy(() -> {
            new Target(target);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid piece: Z1");
    }

    @Test
    public void shouldThrowExceptionForNullTarget() {
        assertThatThrownBy(() -> {
            new Target(null);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Target cannot be null.");
    }

    @Test
    public void shouldThrowExceptionForEmptyTarget() {
        assertThatThrownBy(() -> {
            new Target(" ");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Target cannot be empty.");
    }
}