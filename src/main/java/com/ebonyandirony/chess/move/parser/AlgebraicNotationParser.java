package com.ebonyandirony.chess.move.parser;

public final class AlgebraicNotationParser implements NotationParser {

    public AlgebraicNotationParser() {
    }

    public boolean parses(final String move) {
        String queenSideCastleRegex = "O-O-O";
        String chessMovePattern = "([KQRBN]?[a-h]?[1-8]?[x-]?[a-h][1-8](=[QRBN])?[+#]?)";

        return move.matches(queenSideCastleRegex) ||
                move.matches(chessMovePattern);
    }
}
