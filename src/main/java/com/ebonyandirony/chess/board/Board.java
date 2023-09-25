package com.ebonyandirony.chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    public static final char FILE_LOWER_BOUND = 'a';
    public static final char FILE_UPPER_BOUND = 'h';
    public static final char RANK_LOWER_BOUND = '1';
    public static final char RANK_UPPER_BOUND = '8';
    final static Map<String, Long> BITBOARDS = new HashMap<>();

    static {
        // @formatter:off
        // Initial white state is from the pov of the black player
        // A1== bit 0
        BITBOARDS.put("whitePawns",   0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L);
        BITBOARDS.put("whiteRooks",   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L);
        BITBOARDS.put("whiteKnights", 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L);
        BITBOARDS.put("whiteBishops", 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L);
        BITBOARDS.put("whiteQueen",   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L);
        BITBOARDS.put("whiteKing",    0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L);

        // Initial black state is also from the pov of the black player
        BITBOARDS.put("blackPawns",   0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L);
        BITBOARDS.put("blackRooks",   0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        BITBOARDS.put("blackKnights", 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        BITBOARDS.put("blackBishops", 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        BITBOARDS.put("blackQueen",   0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        BITBOARDS.put("blackKing",    0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        // @formatter:on

        generateAllSquares().forEach(squareName -> {
            long bitboard = calculateBitboard(squareName);
            BITBOARDS.put(squareName, bitboard);
        });
    }

    Board() {
    }

    public boolean isPathBlocked(String sourceSquare, String destinationSquare) {
        long occupancy = allPieces();

        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00001000_00000000L
        long sourceBit = BITBOARDS.get(sourceSquare);
        long destinationBit = BITBOARDS.get(destinationSquare);

        // e2: 7 is 'e' - 'a', 4 is '1' - '1', and 2 is 2
        int squareIndex = 7 - 4 + (2 * 8);
        long fileMask = 0x0101010101010101L << squareIndex % 8;
        long rankMask = 0xFFL << (8 * (squareIndex / 8));
//        Long.numberOfTrailingZeros()

        return false;
    }

    private long generateSquaresBetween(long sourceBit, long destinationBit) {
        long fileMask = (1L << Long.numberOfTrailingZeros(sourceBit % 256)) - 1L;
        long rankMask = (1L << (Long.numberOfTrailingZeros(sourceBit / 256))) - 1L;
        return (fileMask & rankMask) & (sourceBit ^ destinationBit);
    }

    private long allPieces() {
        long allWhitePieces = BITBOARDS.get("whitePawns") | BITBOARDS.get("whiteRooks")
                | BITBOARDS.get("whiteKnights") | BITBOARDS.get("whiteBishops")
                | BITBOARDS.get("whiteQueen") | BITBOARDS.get("whiteKing");

        long allBlackPieces = BITBOARDS.get("blackPawns") | BITBOARDS.get("blackRooks")
                | BITBOARDS.get("blackKnights") | BITBOARDS.get("blackBishops")
                | BITBOARDS.get("blackQueen") | BITBOARDS.get("blackKing");

        return allWhitePieces | allBlackPieces;
    }

    private static Stream<String> generateAllSquares() {
        // @formatter:off
        return IntStream.rangeClosed(FILE_LOWER_BOUND, FILE_UPPER_BOUND).mapToObj(
                file -> IntStream.rangeClosed(RANK_LOWER_BOUND, RANK_UPPER_BOUND).mapToObj(
                        rank -> String.format("%c%c", file, rank)
                )
        ).flatMap(Function.identity());
        // @formatter:on
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
}
