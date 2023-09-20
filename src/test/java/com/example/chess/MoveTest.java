package com.example.chess;

import com.example.chess.piece.PieceType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoveTest {

    @Test
    public void shouldCreateAQueen() {
        Move move = new Move("Qd5");
        assertThat(move.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(move.getMove()).isEqualTo("Qd5");
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenFileMove() {
        String move = "Qi4";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen move: " + move);
    }

    @Test
    public void shouldThrowAnExceptionForInvalidQueenRankMove() {
        String move = "Qa0";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen move: " + move);

        assertThatThrownBy(() -> {
            new Move("Qa9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Queen move: Qa9");
    }

    @Test
    public void shouldCreateAQueenCapture() {
        Move move = new Move("Qxe5");
        assertThat(move.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(move.getMove()).isEqualTo("Qxe5");
    }

    @Test
    public void shouldCreateAPawn() {
        Move move = new Move("a4");
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("a4");
    }

    @Test
    public void shouldCreateAPawnCaptureMove() {
        Move move = new Move("ex6");
        assertThat(move.getType()).isEqualTo(PieceType.PAWN);
        assertThat(move.getMove()).isEqualTo("ex6");
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnCaptureMove() {
        String move = "exx6";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + move);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnFileMove() {
        String move = "i4";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + move);
    }

    @Test
    public void shouldThrowExceptionForInvalidPawnRankMove() {
        String move = "a0";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + move);

        assertThatThrownBy(() -> {
            new Move("a9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: a9");
    }

    @Test
    public void shouldThrowExceptionWhenMovingPawnFromRank1() {
        String move = "a1";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid pawn move: " + move);
    }

    @Test
    public void shouldThrowExceptionForAnInvalidPiece() {
        String move = "Z1";

        assertThatThrownBy(() -> {
            new Move(move);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid piece: Z1");
    }

    @Test
    public void shouldThrowExceptionForNullPiece() {
        assertThatThrownBy(() -> {
            new Move(null);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Move cannot be null.");
    }

    @Test
    public void shouldThrowExceptionForEmptyPiece() {
        assertThatThrownBy(() -> {
            new Move(" ");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Move cannot be empty.");
    }
}