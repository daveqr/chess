package com.ebonyandirony.chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Board {

    public static final char FILE_LOWER_BOUND = 'a';
    public static final char FILE_UPPER_BOUND = 'h';
    public static final char RANK_LOWER_BOUND = '1';
    public static final char RANK_UPPER_BOUND = '8';
    private final Map<String, Long> bitboards = new HashMap<>();

    Board() {
        // @formatter:off
        bitboards.put("whitePawns",   0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L);
        bitboards.put("whiteRooks",   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L);
        bitboards.put("whiteKnights", 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L);
        bitboards.put("whiteBishops", 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L);
        bitboards.put("whiteQueen",   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L);
        bitboards.put("whiteKing",    0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L);

        bitboards.put("blackPawns",   0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L);
        bitboards.put("blackRooks",   0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        bitboards.put("blackKnights", 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        bitboards.put("blackBishops", 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        bitboards.put("blackQueen",   0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        bitboards.put("blackKing",    0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        // @formatter:on

        generateAllSquares().forEach(squareName -> {
            long bitboard = calculateBitboard(squareName);
            bitboards.put(squareName, bitboard);
        });
    }

    private static Stream<String> generateAllSquares() {
        List<String> squareNames = new ArrayList<>();

        for (char file = FILE_LOWER_BOUND; file <= FILE_UPPER_BOUND; file++) {
            for (char rank = RANK_LOWER_BOUND; rank <= RANK_UPPER_BOUND; rank++) {
                squareNames.add(String.valueOf(file) + rank);
            }
        }

        return squareNames.stream();
    }

    /**
     * Calculates the bitboard value for a square on an 8x8 chessboard.
     * <p>
     * A bitboard is a binary representation of a chessboard where all bits are initially set to 0
     * except for the bit corresponding to the given square, which is set to 1.
     * <p>
     * The formula used for calculation is: 1L << (fileIndex + rankIndex * 8),
     * where '8' represents the number of squares per side of the board.
     * <p>
     * - ((file - 'a') + (rank - 1) * 8) combines the file and rank indices into a linear index
     * for the square on an 8x8 chessboard.
     * - 1L << ... shifts the binary digit '1' left by the calculated linear index,
     * creating a bitboard with a single '1' at the square's position.
     *
     * @param squareName The square name in the format "a1" to "h8."
     * @return The bitboard value representing the square.
     */
    private static long calculateBitboard(String squareName) {
        // Calculate the file index (0 to 7) based on the file character ('a' to 'h')
        int fileIndex = squareName.charAt(0) - FILE_LOWER_BOUND;

        // Calculate the rank index (0 to 7) based on the rank character ('1' to '8')
        int rankIndex = squareName.charAt(1) - RANK_LOWER_BOUND;

        // Combine the file and rank indices into a linear index for the square on an 8x8 chessboard
        // Shift the binary digit '1' left by the linear index to create the bitboard
        return 1L << (fileIndex + rankIndex * 8);
    }

    public Map<String, Long> getBitboards() {
        return bitboards;
    }
}
