package com.example.chess;

import com.example.chess.piece.PieceType;

import java.util.HashMap;
import java.util.Map;

interface TargetDestinationValidator {
    char FILE_LOWER_BOUND = 'a';
    char FILE_UPPER_BOUND = 'h';
    char RANK_LOWER_BOUND = '1';
    char RANK_UPPER_BOUND = '8';

    boolean isValidMove(final char[] move);
}

class PawnTargetValidator implements TargetDestinationValidator {
    @Override
    public boolean isValidMove(final char[] move) {
        final char pieceSymbol = move[0];
        final char endRank = move[move.length - 1];
        boolean isCapture = move.length == 3 && move[1] == 'x';

        return (pieceSymbol >= FILE_LOWER_BOUND && pieceSymbol <= FILE_UPPER_BOUND && endRank >= RANK_LOWER_BOUND
                && endRank <= RANK_UPPER_BOUND && (move.length == 2
                || isCapture));
    }
}

class QueenTargetValidator implements TargetDestinationValidator {
    @Override
    public boolean isValidMove(final char[] move) {
        final char endRank = move[move.length - 1];
        boolean isOnBoard =
                move[move.length - 2] >= FILE_LOWER_BOUND
                        && move[move.length - 2] <= FILE_UPPER_BOUND && endRank >= RANK_LOWER_BOUND && endRank <= RANK_UPPER_BOUND;
        boolean isValidLength = move.length == 3 || (move.length == 4 && move[1] == 'x');

        return isOnBoard && isValidLength;
    }
}

public class Target {
    private final PieceType type;
    private final String move;

    // TODO inject these
    private final Map<PieceType, TargetDestinationValidator> pieceTypeToValidatorMap = new HashMap<>();

    public Target(final String target) {
        // TODO inject these using guice
        pieceTypeToValidatorMap.put(PieceType.PAWN, new PawnTargetValidator());
        pieceTypeToValidatorMap.put(PieceType.QUEEN, new QueenTargetValidator());

        assertMove(target);

        final char[] moveCharArray = target.toCharArray();
        final char pieceSymbol = moveCharArray[0];

        if (Character.isLowerCase(pieceSymbol)) {
            this.type = PieceType.PAWN;
            this.move = validateAndNormalize(moveCharArray, PieceType.PAWN);
        } else if (String.valueOf(pieceSymbol).equals(PieceType.QUEEN.getSymbol())) {
            this.type = PieceType.QUEEN;
            this.move = validateAndNormalize(moveCharArray, PieceType.QUEEN);
        } else {
            throw new IllegalArgumentException("Invalid piece: " + target);
        }
    }

    private void assertMove(final String move) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null.");
        }

        if (move.trim().isEmpty()) {
            throw new IllegalArgumentException("Move cannot be empty.");
        }
    }

    private String validateAndNormalize(final char[] moveChars, final PieceType pieceType) {
        final TargetDestinationValidator validator = getValidatorForPiece(pieceType);
        final String move = String.valueOf(moveChars);
        if (validator.isValidMove(moveChars)) {
            return move;
        } else {
            String errorMessage = "Invalid " + pieceType.getLabel() + " move: " + move;
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private TargetDestinationValidator getValidatorForPiece(final PieceType pieceType) {
        final TargetDestinationValidator validator = pieceTypeToValidatorMap.get(pieceType);

        if (validator == null) {
            throw new IllegalArgumentException("No validator found for piece type: " + pieceType);
        }

        return validator;
    }

    public PieceType getType() {
        return type;
    }

    public String getTarget() {
        return move;
    }
}
