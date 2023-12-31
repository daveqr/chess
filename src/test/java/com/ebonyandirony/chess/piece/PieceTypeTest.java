package com.ebonyandirony.chess.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PieceTypeTest {

    @Test
    public void shouldBeAValidKing() {
        assertThat(PieceType.KING.getSymbol()).isEqualTo('K');
        assertThat(PieceType.KING.getLabel()).isEqualTo("King");
        assertThat(PieceType.KING.getBlackAsciiCharacter()).isEqualTo('♚');
    }

    @Test
    public void shouldBeAValidQueen() {
        assertThat(PieceType.QUEEN.getSymbol()).isEqualTo('Q');
        assertThat(PieceType.QUEEN.getLabel()).isEqualTo("Queen");
        assertThat(PieceType.QUEEN.getBlackAsciiCharacter()).isEqualTo('♛');
    }

    @Test
    public void shouldBeAValidRook() {
        assertThat(PieceType.ROOK.getSymbol()).isEqualTo('R');
        assertThat(PieceType.ROOK.getLabel()).isEqualTo("Rook");
        assertThat(PieceType.ROOK.getBlackAsciiCharacter()).isEqualTo('♜');
    }

    @Test
    public void shouldBeAValidBishop() {
        assertThat(PieceType.BISHOP.getSymbol()).isEqualTo('B');
        assertThat(PieceType.BISHOP.getLabel()).isEqualTo("Bishop");
        assertThat(PieceType.BISHOP.getBlackAsciiCharacter()).isEqualTo('♝');
    }

    @Test
    public void shouldBeAValidKnight() {
        assertThat(PieceType.KNIGHT.getSymbol()).isEqualTo('N');
        assertThat(PieceType.KNIGHT.getLabel()).isEqualTo("Knight");
        assertThat(PieceType.KNIGHT.getBlackAsciiCharacter()).isEqualTo('♞');
    }

    @Test
    public void shouldBeAValidPawn() {
        assertThat(PieceType.PAWN.getSymbol()).isEqualTo('p');
        assertThat(PieceType.PAWN.getLabel()).isEqualTo("pawn");
        assertThat(PieceType.PAWN.getBlackAsciiCharacter()).isEqualTo('♟');
    }

    @Test
    public void shouldFindExpectedSymbol() {
        char[] symbols = {'p' , 'K' , 'Q' , 'B' , 'R' , 'N'};
        PieceType[] expectedTypes = {PieceType.PAWN, PieceType.KING, PieceType.QUEEN, PieceType.BISHOP, PieceType.ROOK, PieceType.KNIGHT};

        for (int i = 0; i < symbols.length; i++) {
            PieceType result = PieceType.fromSymbol(symbols[i]);
            assertEquals(expectedTypes[i], result, "Expected type does not match for symbol " + symbols[i]);
        }
    }

    @Test
    public void shouldThrowExceptionForUnexpectedSymbols() {
        char[] invalidSymbols = {'a' , 'C' , 'X' , 'z' , '1' , '?'};

        for (char symbol : invalidSymbols) {
            assertThrows(IllegalArgumentException.class, () -> PieceType.fromSymbol(symbol));
        }
    }
}