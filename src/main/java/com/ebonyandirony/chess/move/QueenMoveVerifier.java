package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.board.Board;

import static com.ebonyandirony.chess.move.Move.CAPTURE_MODIFIER;
import static com.ebonyandirony.chess.move.Move.CHECKMATE_MODIFIER;

public class QueenMoveVerifier implements MoveVerifier {
    private static boolean isOnBoard(char[] target) {
        // @formatter:off
        char[] cleanedMove = new String(target)
                .replace(Character.toString(CAPTURE_MODIFIER), "")
                .replace(Character.toString(CHECKMATE_MODIFIER), "")
                .toCharArray();
        // @formatter:on

        final char rank = cleanedMove[cleanedMove.length - 1];
        final char file = cleanedMove[cleanedMove.length - 2];

        // @formatter:off
        return  file >= Board.FILE_LOWER_BOUND &&
                file <= Board.FILE_UPPER_BOUND &&
                rank >= Board.RANK_LOWER_BOUND &&
                rank <= Board.RANK_UPPER_BOUND;
        // @formatter:on
    }

    @Override
    public boolean isAllowed(final char[] move) {
        final boolean isOnBoard = isOnBoard(move);

        // Qd5
        final boolean isStandard = move.length == 3;

        // Qxb7
        final boolean isCapture = move.length == 4 && move[1] == CAPTURE_MODIFIER;

        // Qh8#
        final boolean isCheckmate = move.length == 4 && move[move.length - 1] == CHECKMATE_MODIFIER;

        return isOnBoard && (isStandard || isCapture || isCheckmate);
    }
}
