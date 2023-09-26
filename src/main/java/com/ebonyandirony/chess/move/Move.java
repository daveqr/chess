package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.piece.PieceType;

import java.util.EnumMap;

import static com.ebonyandirony.chess.piece.PieceType.PAWN;
import static com.ebonyandirony.chess.piece.PieceType.QUEEN;

public class Move {
    static final char CAPTURE_MODIFIER = 'x';

    static final char CHECKMATE_MODIFIER = '#';

    private final PieceType type;
    private final String move;

    // TODO inject these using guice
    private static final EnumMap<PieceType, MoveVerifier> VERIFIERS = new EnumMap<>(PieceType.class);

    static {
        VERIFIERS.put(PAWN, new PawnMoveVerifier());
        VERIFIERS.put(QUEEN, new QueenMoveVerifier());
    }

    public Move(final String move) {
        assertMove(move);

        final char symbol = move.toCharArray()[0];

        if (Character.isLowerCase(symbol) && verify(move, PAWN)) {
            this.type = PAWN;
            this.move = move;
        } else if (symbol == QUEEN.getSymbol() && verify(move, QUEEN)) {
            this.type = QUEEN;
            this.move = move;
        } else {
            throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    private void assertMove(final String target) {
        if (target == null) {
            throw new IllegalArgumentException("Move cannot be null.");
        }

        if (target.trim().isEmpty()) {
            throw new IllegalArgumentException("Move cannot be empty.");
        }
    }

    private boolean verify(final String move, final PieceType type) {
        final MoveVerifier verifier = VERIFIERS.get(type);

        if (verifier.isAllowed(move.toCharArray())) {
            return true;
        } else {
            final String errorMessage = "Invalid " + type.getLabel() + " move: " + move;
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public PieceType getType() {
        return type;
    }

    public String getMove() {
        return move;
    }
}
