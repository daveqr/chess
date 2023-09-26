package com.ebonyandirony.chess.move;

public class QueenMoveVerifier implements MoveVerifier {
    public boolean isAllowed(final String move) {
        String moveRegex = "(Qx?[a-h][1-8])|(Q[a-h][1-8]#)";

        return move.matches(moveRegex);
    }
}
