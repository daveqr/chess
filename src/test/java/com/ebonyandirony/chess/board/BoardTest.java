package com.ebonyandirony.chess.board;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardTest {

    public static Stream<Arguments> bitboardTestData() {
        return generateAllSquares().map(squareName -> {
            long bitboard = calculateBitboard(squareName);
            return Arguments.of(squareName, bitboard);
        });
    }

    public static Stream<String> generateAllSquares() {
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
    public void testBitboardsForAllSquares(String squareName, long expectedBitboard) {
        Map<String, Long> bitboards = new Board().getBitboards();

        assertThat(bitboards.get(squareName)).isEqualTo(expectedBitboard);
    }
}