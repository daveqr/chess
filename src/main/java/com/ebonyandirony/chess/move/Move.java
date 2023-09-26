package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.board.Board;
import com.ebonyandirony.chess.piece.PieceType;

import java.util.EnumMap;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ebonyandirony.chess.piece.PieceType.*;

public class Move {
    private final PieceType type;
    private final String move;

    private final char file;

    private final char rank;

    private static final EnumMap<PieceType, MoveVerifier> VERIFIERS = new EnumMap<>(PieceType.class);

    // TODO inject these using guice
    static {
        VERIFIERS.put(PAWN, new PawnMoveVerifier());
        VERIFIERS.put(QUEEN, new QueenMoveVerifier());
        VERIFIERS.put(KING, new KingMoveVerifier());
    }

    public Move(final String move) {
        assertMove(move);

        final char symbol = move.toCharArray()[0];

        if (Character.isLowerCase(symbol) && verify(move, PAWN)) {
            this.type = PAWN;
            this.move = move;
            this.rank = findRank(move);
            this.file = findFile(move);
        } else if (symbol == QUEEN.getSymbol() && verify(move, QUEEN)) {
            this.type = QUEEN;
            this.move = move;
            this.rank = findRank(move);
            this.file = findFile(move);
        } else if (symbol == KING.getSymbol() && verify(move, KING)) {
            this.type = KING;
            this.move = move;
            this.rank = findRank(move);
            this.file = findFile(move);
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

        if (verifier.isAllowed(move)) {
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

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    private char findRank(String move) {
        return findRankOrFile(move, true);
    }

    private char findFile(String move) {
        return findRankOrFile(move, false);
    }

    private char findRankOrFile(String move, boolean isRank) {
        char firstChar = move.charAt(0);

        if (isPawnMove(firstChar)) {
            return extractFileOrRankForPawnMove(move, isRank);
        } else if (isNamedPieceMove(firstChar)) {
            return extractFileOrRankForNamedPieceMove(move, isRank);
        } else {
            throw new IllegalArgumentException("Invalid file in move: " + move);
        }
    }

    private boolean isPawnMove(char firstChar) {
        return firstChar >= Board.FILE_LOWER_BOUND && firstChar <= Board.FILE_UPPER_BOUND;
    }

    private boolean isNamedPieceMove(char firstChar) {
        Set<Character> namedPieceSymbols = PieceType.getNamedPieces().stream()
                .map(PieceType::getSymbol)
                .collect(Collectors.toSet());
        return Character.isLetter(firstChar) && namedPieceSymbols.contains(firstChar);
    }

    private char extractFileOrRankForPawnMove(String move, boolean isRank) {
        if (move.charAt(1) == 'x') {
            return move.charAt(isRank ? move.length() - 1 : 2);
        } else {
            return move.charAt(isRank ? move.length() - 1 : 0);
        }
    }

    private char extractFileOrRankForNamedPieceMove(String move, boolean isRank) {
        if (move.length() == 3) {
            return move.charAt(isRank ? move.length() - 1 : 1);
        } else if (move.length() > 3 && move.charAt(1) == 'x') {
            return move.charAt(isRank ? move.length() - 1 : 2);
        } else {
            return move.charAt(isRank ? move.length() - 2 : 1);
        }
    }
}
