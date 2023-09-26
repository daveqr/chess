package com.ebonyandirony.chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.ebonyandirony.chess.board.Board.Pieces.*;


public class Board {
    public static final char FILE_LOWER_BOUND = 'a';

    public static final char FILE_UPPER_BOUND = 'h';

    public static final char RANK_LOWER_BOUND = '1';

    public static final char RANK_UPPER_BOUND = '8';

    private final Map<Pieces, Long> pieceBitboards = new HashMap<>();

    private final Map<String, Long> squareBitboards = new HashMap<>();

    enum Pieces {
        WHITE_PAWNS,
        WHITE_ROOKS,
        WHITE_KNIGHTS,
        WHITE_BISHOPS,
        WHITE_QUEEN,
        WHITE_KING,
        BLACK_PAWNS,
        BLACK_ROOKS,
        BLACK_KNIGHTS,
        BLACK_BISHOPS,
        BLACK_QUEEN,
        BLACK_KING
    }

    Board() {
        // @formatter:off
        // A1 == LSB
        // H8 == MSB
        pieceBitboards.put(WHITE_PAWNS,   0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L);
        pieceBitboards.put(WHITE_ROOKS,   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L);
        pieceBitboards.put(WHITE_KNIGHTS, 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L);
        pieceBitboards.put(WHITE_BISHOPS, 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L);
        pieceBitboards.put(WHITE_QUEEN,   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L);
        pieceBitboards.put(WHITE_KING,    0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L);

        pieceBitboards.put(BLACK_PAWNS,   0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(BLACK_ROOKS,   0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(BLACK_KNIGHTS, 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(BLACK_BISHOPS, 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(BLACK_QUEEN,   0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(BLACK_KING,    0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        // @formatter:on

        generateAllSquares().forEach(squareName -> {
            long bitboard = calculateBitboard(squareName);
            squareBitboards.put(squareName, bitboard);
        });
    }

    public long getSquareBitboard(String square) {
        return squareBitboards.get(square);
    }

    public void setWhiteKingBoard(long board) {
        pieceBitboards.put(WHITE_KING, board);
    }

    public long getWhitePawnsBoard() {
        return pieceBitboards.get(WHITE_PAWNS);
    }

    public long getWhiteRooksBoard() {
        return pieceBitboards.get(WHITE_ROOKS);
    }

    public long getWhiteBishopsBoard() {
        return pieceBitboards.get(WHITE_BISHOPS);
    }

    public long getWhiteKnightsBoard() {
        return pieceBitboards.get(WHITE_KNIGHTS);
    }

    public long getWhiteKingBoard() {
        return pieceBitboards.get(WHITE_KING);
    }

    public long getBlackQueenBoard() {
        return pieceBitboards.get(BLACK_QUEEN);
    }

    public void setBlackKingBoard(long board) {
        pieceBitboards.put(BLACK_KING, board);
    }

    public long getBlackKingBoard() {
        return pieceBitboards.get(BLACK_KING);
    }

    public long getBlackPawnsBoard() {
        return pieceBitboards.get(BLACK_PAWNS);
    }

    public long getBlackRooksBoard() {
        return pieceBitboards.get(BLACK_ROOKS);
    }

    public long getBlackBishopsBoard() {
        return pieceBitboards.get(BLACK_BISHOPS);
    }

    public long getBlackKnightsBoard() {
        return pieceBitboards.get(BLACK_KNIGHTS);
    }

    public long getWhiteQueenBoard() {
        return pieceBitboards.get(WHITE_QUEEN);
    }

//    public boolean isPathBlocked(String sourceSquare, String destinationSquare) {
//        long occupancy = allPieces();
//
//        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00001000_00000000L
//        long sourceBit = BITBOARDS.get(sourceSquare);
//        long destinationBit = BITBOARDS.get(destinationSquare);
//
//        // e2: 7 is 'e' - 'a', 4 is '1' - '1', and 2 is 2
//        int squareIndex = 7 - 4 + (2 * 8);
//        long fileMask = 0x0101010101010101L << squareIndex % 8;
//        long rankMask = 0xFFL << (8 * (squareIndex / 8));
////        Long.numberOfTrailingZeros()
//
//        return false;
//    }

//    private long generateSquaresBetween(long sourceBit, long destinationBit) {
//        long fileMask = (1L << Long.numberOfTrailingZeros(sourceBit % 256)) - 1L;
//        long rankMask = (1L << (Long.numberOfTrailingZeros(sourceBit / 256))) - 1L;
//        return (fileMask & rankMask) & (sourceBit ^ destinationBit);
//    }

    long allPieces() {
        return allWhitePieces() | allBlackPieces();
    }

    long allWhitePieces() {
        return pieceBitboards.get(WHITE_PAWNS) | pieceBitboards.get(WHITE_ROOKS)
                | pieceBitboards.get(WHITE_KNIGHTS) | pieceBitboards.get(WHITE_BISHOPS)
                | pieceBitboards.get(WHITE_QUEEN) | pieceBitboards.get(WHITE_KING);
    }

    long allBlackPieces() {
        return pieceBitboards.get(BLACK_PAWNS) | pieceBitboards.get(BLACK_ROOKS)
                | pieceBitboards.get(BLACK_KNIGHTS) | pieceBitboards.get(BLACK_BISHOPS)
                | pieceBitboards.get(BLACK_QUEEN) | pieceBitboards.get(BLACK_KING);
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

        return 1L << (fileIndex + rankIndex * 8);
    }
}
