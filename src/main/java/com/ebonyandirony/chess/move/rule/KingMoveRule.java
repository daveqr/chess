package com.ebonyandirony.chess.move.rule;

import com.ebonyandirony.chess.board.Board;
import com.ebonyandirony.chess.board.LookupTables;
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

        final long currentKingPosition = this.board.getWhiteKingBoard();
        final long proposedKingPosition = Board.calculateBitboard(move.toSimpleString());

        final long allWhitePieces = board.allWhitePieces();

        long moveDiff = Math.abs(Long.numberOfTrailingZeros(proposedKingPosition) % 8 - Long.numberOfTrailingZeros(currentKingPosition) % 8);

        if (moveDiff <= 1) {
            return (proposedKingPosition & allWhitePieces) == 0;
        }

        return false;
    }
}
