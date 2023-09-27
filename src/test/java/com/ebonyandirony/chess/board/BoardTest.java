package com.ebonyandirony.chess.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.ebonyandirony.chess.board.Board.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardTest {

    private static Stream<Arguments> bitboardTestData() {
        return generateAllSquares().map(squareName -> {
            long bitboard = calculateBitboard(squareName);
            return Arguments.of(squareName, bitboard);
        });
    }

    private static Stream<String> generateAllSquares() {
        List<String> squareNames = new ArrayList<>();

        for (char file = FILE_LOWER_BOUND; file <= FILE_UPPER_BOUND; file++) {
            for (char rank = RANK_LOWER_BOUND; rank <= Board.RANK_UPPER_BOUND; rank++) {
                squareNames.add(String.valueOf(file) + rank);
            }
        }

        return squareNames.stream();
    }

    private static long calculateBitboard(String squareName) {
        int fileIndex = squareName.charAt(0) - FILE_LOWER_BOUND;
        int rankIndex = squareName.charAt(1) - RANK_LOWER_BOUND;
        return 1L << (fileIndex + rankIndex * 8);
    }

    @ParameterizedTest
    @MethodSource("bitboardTestData")
    public void shouldHaveBitboardsForAllSquares(String squareName, long expectedBitboard) {
        Board board = create();
        assertThat(board.getSquareBitboard(squareName)).isEqualTo(expectedBitboard);
    }

    @Test
    public void shouldCreateWhitePawnBitboard() {

        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
        assertThat(create().getWhitePawnsBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteRookBitboard() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L;
        assertThat(create().getWhiteRooksBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteKnightBitboard() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L;
        assertThat(create().getWhiteKnightsBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteBishopBitboard() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L;
        assertThat(create().getWhiteBishopsBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteQueenBitboard() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L;
        assertThat(create().getWhiteQueenBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteKingBitboard() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L;
        assertThat(create().getWhiteKingBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackPawnBitboard() {
        long expected = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().getBlackPawnsBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackRookBitboard() {
        long expected = 0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().getBlackRooksBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackKnightBitboard() {
        long expected = 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().getBlackKnightsBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackBishopBitboard() {
        long expected = 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().getBlackBishopsBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackQueenBitboard() {
        long expected = 0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().getBlackQueenBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackKingBitboard() {
        long expected = 0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().getBlackKingBoard()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateAllWhitePieces() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_11111111L;
        assertThat(create().allWhitePieces()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateAllBlackPieces() {
        long expected = 0b11111111_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(create().allBlackPieces()).isEqualTo(expected);
    }

    @Test
    public void shouldCreateAllPieces() {
        long expected = 0b11111111_11111111_00000000_00000000_00000000_00000000_11111111_11111111L;
        assertThat(create().allPieces()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("occupiedSquareProvider")
    public void shouldReturnTrueForOccupiedSquare(String square) {
        Board board = Board.create();
        assertThat(board.isOccupied(square))
                .as("Square " + square + " should be occupied.")
                .isTrue();
    }

    @ParameterizedTest
    @MethodSource("unoccupiedSquareProvider")
    public void shouldReturnFalseForUnoccupiedSquare(String square) {
        Board board = Board.create();
        assertThat(board.isOccupied(square))
                .as("Square " + square + " should not be occupied.")
                .isFalse();
    }

    private static Stream<String> occupiedSquareProvider() {
        List<String> squares = new ArrayList<>();

        for (char file = 'a'; file <= 'h'; file++) {
            for (int rank = 1; rank <= 2; rank++) {
                squares.add(String.format("%c%d", file, rank));
            }

            for (int rank = 8; rank >= 7; rank--) {
                squares.add(String.format("%c%d", file, rank));
            }
        }

        return squares.stream();
    }

    private static Stream<String> unoccupiedSquareProvider() {
        List<String> squares = new ArrayList<>();

        for (char file = 'a'; file <= 'h'; file++) {
            for (int rank = 3; rank <= 6; rank++) {
                squares.add(String.format("%c%d", file, rank));
            }
        }

        return squares.stream();
    }
}