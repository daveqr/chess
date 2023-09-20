package com.ebonyandirony.chess.square;

import com.ebonyandirony.chess.board.Board;

public class SquareAddress {

    private final int file;
    private final int rank;
    private final String square;

    private SquareAddress(final String square, final int file, final int rank) {
        this.square = square;
        this.file = file;
        this.rank = rank;
    }

    public static SquareAddress fromString(final String squareIn) {
        final String square = squareIn.toLowerCase();

        if (square.length() != 2) {
            throw new IllegalArgumentException("Invalid square label: " + square);
        }

        final char fileChar = square.charAt(0);
        final int rankInt = Character.getNumericValue(square.charAt(1));

        final boolean isValidFile = (fileChar >= Board.FILE_LOWER_BOUND && fileChar <= Board.FILE_UPPER_BOUND);
        final boolean isValidRank = (rankInt >= Board.RANK_LOWER_BOUND && rankInt <= Board.RANK_UPPER_BOUND);

        if (isValidFile && isValidRank) {
            final int file = Math.abs('a' - fileChar);
            final int rank = rankInt - 1;
            return new SquareAddress(square, file, rank);
        }

        throw new IllegalArgumentException("Invalid square label: " + square);
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public String getSquare() {
        return square;
    }
}
