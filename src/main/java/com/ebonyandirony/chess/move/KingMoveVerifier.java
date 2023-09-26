package com.ebonyandirony.chess.move;

public class KingMoveVerifier implements MoveVerifier {
    public boolean isAllowed(final String move) {
        String moveRegex = "(Kx?[a-h][1-8])";

        return move.matches(moveRegex);
    }
}
