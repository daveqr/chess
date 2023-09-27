package com.ebonyandirony.chess.move.verify;

public final class AlgebraicNotationVerifier implements NotationVerifier {

    public AlgebraicNotationVerifier() {
    }

    public boolean verify(final String move) {
        String queenSideCastleRegex = "O-O-O";
        String chessMovePattern = "([KQRBN]?[a-h]?[1-8]?[x-]?[a-h][1-8](=[QRBN])?[+#]?)";

        return move.matches(queenSideCastleRegex) ||
                move.matches(chessMovePattern);
    }
}
