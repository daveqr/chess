package com.ebonyandirony.chess.square;

import com.ebonyandirony.chess.board.Board;
import lombok.Getter;

@Getter
public final class SquareAddress {

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
        final int rankChar = square.charAt(1);

        final boolean isValidFile = (fileChar >= Board.FILE_LOWER_BOUND && fileChar <= Board.FILE_UPPER_BOUND);
        final boolean isValidRank = (rankChar >= Board.RANK_LOWER_BOUND && rankChar <= Board.RANK_UPPER_BOUND);

        if (isValidFile && isValidRank) {
            final int file = fileChar - Board.FILE_LOWER_BOUND;
            final int rank = rankChar - Board.RANK_LOWER_BOUND;
            return new SquareAddress(square, file, rank);
        }

        throw new IllegalArgumentException("Invalid square label: " + square);
    }

}
