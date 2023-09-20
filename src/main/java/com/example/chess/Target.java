package com.example.chess;

import com.example.chess.piece.PieceType;

import java.util.HashMap;
import java.util.Map;

interface TargetDestinationValidator {
    char FILE_LOWER_BOUND = 'a';
    char FILE_UPPER_BOUND = 'h';
    char RANK_LOWER_BOUND = '1';
    char RANK_UPPER_BOUND = '8';

    boolean isValidTarget(final char[] target);
}

class PawnTargetValidator implements TargetDestinationValidator {
    @Override
    public boolean isValidTarget(final char[] target) {
        final char pieceSymbol = target[0];
        final char endRank = target[target.length - 1];
        boolean isCapture = target.length == 3 && target[1] == Target.CAPTURE_MODIFIER;

        // @formatter:off
        return pieceSymbol >= FILE_LOWER_BOUND &&
               pieceSymbol <= FILE_UPPER_BOUND &&
               endRank >= RANK_LOWER_BOUND &&
               endRank <= RANK_UPPER_BOUND &&
               (target.length == 2 || isCapture);
        // @formatter:on
    }
}

class QueenTargetValidator implements TargetDestinationValidator {
    private static boolean isOnBoard(char[] target) {
        // @formatter:off
        char[] cleanedMove = new String(target)
                .replace(Character.toString(Target.CAPTURE_MODIFIER), "")
                .replace(Character.toString(Target.CHECKMATE_MODIFIER), "")
                .toCharArray();

        return  cleanedMove[cleanedMove.length - 2] >= FILE_LOWER_BOUND &&
                cleanedMove[cleanedMove.length - 2] <= FILE_UPPER_BOUND &&
                cleanedMove[cleanedMove.length - 1] >= RANK_LOWER_BOUND &&
                cleanedMove[cleanedMove.length - 1] <= RANK_UPPER_BOUND;
        // @formatter:on
    }

    @Override
    public boolean isValidTarget(final char[] target) {
        final boolean isOnBoard = isOnBoard(target);

        // Qd5
        final boolean isStandard = target.length == 3;

        // Qxb7
        final boolean isCapture = target.length == 4 && target[1] == Target.CAPTURE_MODIFIER;

        // Qh8#
        final boolean isCheckmate = target.length == 4 && target[target.length - 1] == Target.CHECKMATE_MODIFIER;

        return isOnBoard && (isStandard || isCapture || isCheckmate);
    }
}

public class Target {
    static final char CAPTURE_MODIFIER = 'x';

    static final char CHECKMATE_MODIFIER = '#';

    private final PieceType type;
    private final String target;

    // TODO inject these using guice
    private final Map<PieceType, TargetDestinationValidator> pieceTypeToValidatorMap = new HashMap<>();

    public Target(final String target) {
        pieceTypeToValidatorMap.put(PieceType.PAWN, new PawnTargetValidator());
        pieceTypeToValidatorMap.put(PieceType.QUEEN, new QueenTargetValidator());

        assertTarget(target);

        final char[] targetCharArray = target.toCharArray();
        final char pieceSymbol = targetCharArray[0];

        if (Character.isLowerCase(pieceSymbol)) {
            this.type = PieceType.PAWN;
            this.target = validateAndNormalize(targetCharArray, PieceType.PAWN);
        } else if (String.valueOf(pieceSymbol).equals(PieceType.QUEEN.getSymbol())) {
            this.type = PieceType.QUEEN;
            this.target = validateAndNormalize(targetCharArray, PieceType.QUEEN);
        } else {
            throw new IllegalArgumentException("Invalid piece: " + target);
        }
    }

    private void assertTarget(final String target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null.");
        }

        if (target.trim().isEmpty()) {
            throw new IllegalArgumentException("Target cannot be empty.");
        }
    }

    private String validateAndNormalize(final char[] targetChars, final PieceType pieceType) {
        final TargetDestinationValidator validator = getValidatorForPiece(pieceType);
        final String target = String.valueOf(targetChars);
        if (validator.isValidTarget(targetChars)) {
            return target;
        } else {
            String errorMessage = "Invalid " + pieceType.getLabel() + " target: " + target;
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
        return target;
    }
}
