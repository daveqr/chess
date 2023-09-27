package com.ebonyandirony.chess.move.rule;

import com.ebonyandirony.chess.board.Board;
import com.ebonyandirony.chess.move.Move;

public final class KingMoveRule implements MoveRule {

    private final Board board;

    private final Move move;

    KingMoveRule(final Board board, final Move move) {

        // TODO verify this is a King move

        this.board = board;
        this.move = move;
    }

    public boolean isValidMove() {
        // Check if the move is valid given the current board state
        return false;
    }

    public boolean isLegalMove() {
        // Check if the move is legal regardless of the state of the board

        final long currentPosition = move.isWhiteMove() ? this.board.getWhiteKingBoard() : this.board.getBlackKingBoard();
        final long targetPosition = Board.calculateBitboard(move.toSimpleString());

        // "numberOfTrailingZeros" gives the index of the rightmost set bit
        // "% 8" normalizes the result to a value between 0 and 7, irrespective of rank (8 bits per rank).
        // This means it calculates the horizontal distance within a single rank, disregarding the vertical position.
        // Taking the result modulo 8 ensures that the value stays within the range [0, 7].
        final int currentFileOffset = Long.numberOfTrailingZeros(currentPosition) % 8;
        final int targetFileOffset = Long.numberOfTrailingZeros(targetPosition) % 8;

        final int currentRankOffset = Long.numberOfTrailingZeros(currentPosition) / 8;
        final int targetRankOffset = Long.numberOfTrailingZeros(targetPosition) / 8;

        final boolean isTargetFileWithinRange = Math.abs(targetFileOffset - currentFileOffset) <= 1;
        final boolean isTargetRankWithinRange = Math.abs(currentRankOffset - targetRankOffset) <= 1;

        final long allPieces = move.isWhiteMove() ? board.allWhitePieces() : board.allBlackPieces();
        final boolean noFriendsInTarget = (targetPosition & allPieces) == 0;

        return isTargetFileWithinRange && isTargetRankWithinRange && noFriendsInTarget;
    }
}
