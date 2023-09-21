package com.ebonyandirony.chess.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

        for (char file = Board.FILE_LOWER_BOUND; file <= Board.FILE_UPPER_BOUND; file++) {
            for (char rank = Board.RANK_LOWER_BOUND; rank <= Board.RANK_UPPER_BOUND; rank++) {
                squareNames.add(String.valueOf(file) + rank);
            }
        }

        return squareNames.stream();
    }

    private static long calculateBitboard(String squareName) {
        int fileIndex = squareName.charAt(0) - Board.FILE_LOWER_BOUND;
        int rankIndex = squareName.charAt(1) - Board.RANK_LOWER_BOUND;
        return 1L << (fileIndex + rankIndex * 8);
    }

    @ParameterizedTest
    @MethodSource("bitboardTestData")
    public void shouldHaveABitboardsForAllSquares(String squareName, long expectedBitboard) {
        Map<String, Long> bitboards = new Board().getBitboards();

        assertThat(bitboards.get(squareName)).isEqualTo(expectedBitboard);
    }

    @Test
    public void shouldCreate76Bitboards() {
        Map<String, Long> bitboards = new Board().getBitboards();
        assertThat(bitboards.size()).isEqualTo(76);
    }

    @Test
    public void shouldCreateWhitePawnBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();

        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
        assertThat(bitboards.get("whitePawns")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteRookBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L;
        assertThat(bitboards.get("whiteRooks")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteKnightBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L;
        assertThat(bitboards.get("whiteKnights")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteBishopBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L;
        assertThat(bitboards.get("whiteBishops")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteQueenBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L;
        assertThat(bitboards.get("whiteQueen")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateWhiteKingBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L;
        assertThat(bitboards.get("whiteKing")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackPawnBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();

        long expected = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(bitboards.get("blackPawns")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackRookBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(bitboards.get("blackRooks")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackKnightBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(bitboards.get("blackKnights")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackBishopBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(bitboards.get("blackBishops")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackQueenBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(bitboards.get("blackQueen")).isEqualTo(expected);
    }

    @Test
    public void shouldCreateBlackKingBitboard() {
        Map<String, Long> bitboards = new Board().getBitboards();
        long expected = 0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(bitboards.get("blackKing")).isEqualTo(expected);
    }
}