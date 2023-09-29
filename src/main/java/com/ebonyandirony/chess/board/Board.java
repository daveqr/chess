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

    private final Map<Pieces, Long> pieceBitboards = new HashMap<>();

    private final Map<String, Long> squareBitboards = new HashMap<>();

    public static Board create() {
        return new Board();
    }

    private enum Pieces {
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

    private Board() {
        // @formatter:off
        // A1 == LSB
        // H8 == MSB
        pieceBitboards.put(Pieces.WHITE_PAWNS,   0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L);
        pieceBitboards.put(Pieces.WHITE_ROOKS,   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L);
        pieceBitboards.put(Pieces.WHITE_KNIGHTS, 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L);
        pieceBitboards.put(Pieces.WHITE_BISHOPS, 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L);
        pieceBitboards.put(Pieces.WHITE_QUEEN,   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L);
        pieceBitboards.put(Pieces.WHITE_KING,    0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L);

        pieceBitboards.put(Pieces.BLACK_PAWNS,   0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(Pieces.BLACK_ROOKS,   0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(Pieces.BLACK_KNIGHTS, 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(Pieces.BLACK_BISHOPS, 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(Pieces.BLACK_QUEEN,   0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        pieceBitboards.put(Pieces.BLACK_KING,    0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L);
        // @formatter:on

        generateAllSquares().forEach(squareName -> {
            final long bitboard = calculateBitboard(squareName);
            squareBitboards.put(squareName, bitboard);
        });
    }

    public long getSquareBitboard(String square) {
        return squareBitboards.get(square);
    }

    public void setWhiteKingBoard(long board) {
        pieceBitboards.put(Pieces.WHITE_KING, board);
    }

    public long getWhitePawnsBoard() {
        return pieceBitboards.get(Pieces.WHITE_PAWNS);
    }

    public long getWhiteRooksBoard() {
        return pieceBitboards.get(Pieces.WHITE_ROOKS);
    }

    public long getWhiteBishopsBoard() {
        return pieceBitboards.get(Pieces.WHITE_BISHOPS);
    }

    public long getWhiteKnightsBoard() {
        return pieceBitboards.get(Pieces.WHITE_KNIGHTS);
    }

    public long getWhiteKingBoard() {
        return pieceBitboards.get(Pieces.WHITE_KING);
    }

    public long getBlackQueenBoard() {
        return pieceBitboards.get(Pieces.BLACK_QUEEN);
    }

    public long getBlackKingBoard() {
        return pieceBitboards.get(Pieces.BLACK_KING);
    }

    public long getBlackPawnsBoard() {
        return pieceBitboards.get(Pieces.BLACK_PAWNS);
    }

    public long getBlackRooksBoard() {
        return pieceBitboards.get(Pieces.BLACK_ROOKS);
    }

    public long getBlackBishopsBoard() {
        return pieceBitboards.get(Pieces.BLACK_BISHOPS);
    }

    public long getBlackKnightsBoard() {
        return pieceBitboards.get(Pieces.BLACK_KNIGHTS);
    }

    public long getWhiteQueenBoard() {
        return pieceBitboards.get(Pieces.WHITE_QUEEN);
    }

    long allPieces() {
        return allWhitePieces() | allBlackPieces();
    }

    public long allWhitePieces() {
        return pieceBitboards.get(Pieces.WHITE_PAWNS) | pieceBitboards.get(Pieces.WHITE_ROOKS)
                | pieceBitboards.get(Pieces.WHITE_KNIGHTS) | pieceBitboards.get(Pieces.WHITE_BISHOPS)
                | pieceBitboards.get(Pieces.WHITE_QUEEN) | pieceBitboards.get(Pieces.WHITE_KING);
    }

    public long allBlackPieces() {
        return pieceBitboards.get(Pieces.BLACK_PAWNS) | pieceBitboards.get(Pieces.BLACK_ROOKS)
                | pieceBitboards.get(Pieces.BLACK_KNIGHTS) | pieceBitboards.get(Pieces.BLACK_BISHOPS)
                | pieceBitboards.get(Pieces.BLACK_QUEEN) | pieceBitboards.get(Pieces.BLACK_KING);
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
     * @param square The square name in the format "a1" to "h8."
     * @return The bitboard value representing the square.
     */
    public static long calculateBitboard(String square) {
        final int fileIndex = square.charAt(0) - FILE_LOWER_BOUND;
        final int rankIndex = square.charAt(1) - RANK_LOWER_BOUND;

        return 1L << (fileIndex + rankIndex * 8);
    }

    public boolean isOccupied(String square) {
        final long currentBoard = allBlackPieces() | allWhitePieces();
        final long proposedBoard = calculateBitboard(square);

        return (currentBoard & proposedBoard) != 0;
    }
}
