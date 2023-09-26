package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.board.Board;

import static com.ebonyandirony.chess.move.Move.CAPTURE_MODIFIER;

public class PawnMoveVerifier implements MoveVerifier {
    @Override
    public boolean isAllowed(final char[] move) {
        final char file = move[0];
        final char rank = move[move.length - 1];
        final boolean isCapture = move.length == 3 && move[1] == CAPTURE_MODIFIER;

        // @formatter:off
        return file >= Board.FILE_LOWER_BOUND &&
               file <= Board.FILE_UPPER_BOUND &&
               rank >= Board.RANK_LOWER_BOUND &&
               rank <= Board.RANK_UPPER_BOUND &&
               (move.length == 2 || isCapture);
        // @formatter:on
    }
}
