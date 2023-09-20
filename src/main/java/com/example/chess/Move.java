package com.example.chess;

import com.example.chess.piece.PieceType;

import java.util.HashMap;
import java.util.Map;

interface MoveValidator {
    char FILE_A = 'a'; // Lower bound for file
    char FILE_H = 'h'; // Upper bound for file
    char RANK_1 = '1'; // Lower bound for rank
    char RANK_2 = '2'; // Lower bound for rank (for pawns)
    char RANK_8 = '8'; // Upper bound for rank

    boolean isValidMove(final char[] move);
}

class PawnMoveValidator implements MoveValidator {
    @Override
    public boolean isValidMove(final char[] move) {
        final char pieceSymbol = move[0];
        final char endRank = move[move.length - 1];
        boolean isCapture = move.length == 3 && move[1] == 'x';

        return (pieceSymbol >= FILE_A && pieceSymbol <= FILE_H && endRank >= RANK_2 && endRank <= RANK_8 && (move.length == 2
                || isCapture));
    }
}

class QueenMoveValidator implements MoveValidator {
    @Override
    public boolean isValidMove(final char[] move) {
        final char endRank = move[move.length - 1];
        boolean isOnBoard =
                move[move.length - 2] >= FILE_A && move[move.length - 2] <= FILE_H && endRank >= RANK_1 && endRank <= RANK_8;
        boolean isValidLength = move.length == 3 || (move.length == 4 && move[1] == 'x');

        return isOnBoard && isValidLength;
    }
}

public class Move {
    private final PieceType type;
    private final String move;

    // TODO inject these
    private final Map<PieceType, MoveValidator> pieceTypeToValidatorMap = new HashMap<>();

    public Move(final String move) {
        // TODO inject these using guice
        pieceTypeToValidatorMap.put(PieceType.PAWN, new PawnMoveValidator());
        pieceTypeToValidatorMap.put(PieceType.QUEEN, new QueenMoveValidator());

        assertMove(move);

        final char[] moveCharArray = move.toCharArray();
        final char pieceSymbol = moveCharArray[0];

        if (Character.isLowerCase(pieceSymbol)) {
            this.type = PieceType.PAWN;
            this.move = validateAndNormalize(moveCharArray, PieceType.PAWN);
        } else if (String.valueOf(pieceSymbol).equals(PieceType.QUEEN.getSymbol())) {
            this.type = PieceType.QUEEN;
            this.move = validateAndNormalize(moveCharArray, PieceType.QUEEN);
        } else {
            throw new IllegalArgumentException("Invalid piece: " + move);
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
        final MoveValidator validator = getValidatorForPiece(pieceType);
        final String move = String.valueOf(moveChars);
        if (validator.isValidMove(moveChars)) {
            return move;
        } else {
            String errorMessage = "Invalid " + pieceType.getName() + " move: " + move;
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private MoveValidator getValidatorForPiece(final PieceType pieceType) {
        final MoveValidator validator = pieceTypeToValidatorMap.get(pieceType);

        if (validator == null) {
            throw new IllegalArgumentException("No validator found for piece type: " + pieceType);
        }

        return validator;
    }

    public PieceType getType() {
        return type;
    }

    public String getMove() {
        return move;
    }
}
