package com.ebonyandirony.chess.move;

public class PawnMoveVerifier implements MoveVerifier {
    @Override
    public boolean isAllowed(final String move) {
        // TODO handle capture case where files are not adjacent, eg exg6
        String moveRegex = "([a-h]x?[a-h]?[1-8])";

        return move.matches(moveRegex);
    }
}
