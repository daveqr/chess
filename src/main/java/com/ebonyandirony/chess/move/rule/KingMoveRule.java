package com.ebonyandirony.chess.move.rule;

import com.ebonyandirony.chess.board.Board;
import com.ebonyandirony.chess.move.Move;

public final class KingMoveRule implements MoveRule {

    private final Board board;

    private final Move move;

    public KingMoveRule(final Board board, final Move move) {

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

        final long currentKingBoard = move.isWhiteMove() ? this.board.getWhiteKingBoard() : this.board.getBlackKingBoard();
        final long targetKingBoard = Board.calculateBitboard(move.toSimpleString());

        // Calculate the horizontal distance within a single rank (8 bits per rank).
        // Ensure the value stays in the range [0, 7].
        final int currentFileIndex = Long.numberOfTrailingZeros(currentKingBoard) % 8;
        final int targetFileIndex = Long.numberOfTrailingZeros(targetKingBoard) % 8;

        // Calculate the vertical distance within a single file (8 bits per file).
        final int currentRankIndex = Long.numberOfTrailingZeros(currentKingBoard) / 8;
        final int targetRankIndex = Long.numberOfTrailingZeros(targetKingBoard) / 8;

        final boolean isTargetFileWithinRange = Math.abs(targetFileIndex - currentFileIndex) <= 1;
        final boolean isTargetRankWithinRange = Math.abs(currentRankIndex - targetRankIndex) <= 1;

        final long allPieces = move.isWhiteMove() ? board.allWhitePieces() : board.allBlackPieces();
        final boolean noFriendsInTarget = (targetKingBoard & allPieces) == 0;

        return isTargetFileWithinRange && isTargetRankWithinRange && noFriendsInTarget;
    }
}
