package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.move.verify.AlgebraicNotationVerifier;
import com.ebonyandirony.chess.move.verify.NotationVerifier;
import com.ebonyandirony.chess.piece.Color;
import com.ebonyandirony.chess.piece.PieceType;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ebonyandirony.chess.board.Board.FILE_LOWER_BOUND;
import static com.ebonyandirony.chess.board.Board.FILE_UPPER_BOUND;
import static com.ebonyandirony.chess.piece.PieceType.PAWN;

public class Move {
    private final NotationVerifier notationVerifier = new AlgebraicNotationVerifier();

    @Getter
    private final PieceType type;

    @Getter
    @ToString.Include
    private final String move;

    @Getter
    private final char file;

    @Getter
    private final int rank;

    @ToString.Include
    private final Color color;

    private Move(final String move, final Color color) {
        assertMove(move);
        verify(move);

        this.move = move;
        this.rank = findRank(move);
        this.file = findFile(move);
        this.color = color;

        final char symbol = move.charAt(0);
        final boolean isPawn = Character.isLowerCase(symbol) &&
                symbol >= FILE_LOWER_BOUND && symbol <= FILE_UPPER_BOUND;

        this.type = isPawn ? PAWN : PieceType.fromSymbol(symbol);
    }

    public static Move create(final String move, final Color color) {
        return new Move(move, color);
    }

    private void assertMove(final String target) {
        if (target == null) {
            throw new IllegalArgumentException("Move cannot be null.");
        }

        if (target.trim().isEmpty()) {
            throw new IllegalArgumentException("Move cannot be empty.");
        }
    }

    private void verify(final String move) {
        if (!notationVerifier.verify(move)) {
            throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    public boolean isWhiteMove() {
        return color == Color.WHITE;
    }

    private int findRank(final String move) {
        final char rank = findRankOrFile(move, true);

        return Character.getNumericValue(rank);
    }

    private char findFile(final String move) {
        return findRankOrFile(move, false);
    }

    private char findRankOrFile(final String move, boolean isRank) {
        char firstChar = move.charAt(0);

        if (isPawnMove(firstChar)) {
            return extractFileOrRankForPawnMove(move, isRank);
        } else if (isNamedPieceMove(firstChar)) {
            return extractFileOrRankForNamedPieceMove(move, isRank);
        } else {
            throw new IllegalArgumentException("Invalid file in move: " + move);
        }
    }

    private boolean isPawnMove(final char firstChar) {
        return firstChar >= FILE_LOWER_BOUND && firstChar <= FILE_UPPER_BOUND;
    }

    private boolean isNamedPieceMove(final char firstChar) {
        final Set<Character> namedPieceSymbols = PieceType.getNamedPieces().stream()
                .map(PieceType::getSymbol)
                .collect(Collectors.toSet());
        return Character.isLetter(firstChar) && namedPieceSymbols.contains(firstChar);
    }

    private char extractFileOrRankForPawnMove(final String move, final boolean isRank) {
        char targetChar;

        if (move.charAt(1) == 'x') {
            targetChar = move.charAt(isRank ? move.length() - 1 : 2);
        } else {
            targetChar = move.charAt(isRank ? move.length() - 1 : 0);
        }

        return targetChar;
    }

    private char extractFileOrRankForNamedPieceMove(String move, boolean isRank) {
        int length = move.length();

        if (length == 3) {
            return move.charAt(isRank ? length - 1 : 1);
        } else if (length > 3 && move.charAt(1) == 'x') {
            return move.charAt(isRank ? length - 1 : 2);
        } else {
            return move.charAt(isRank ? length - 2 : 1);
        }
    }

    public String toSimpleString() {
        return file + Integer.toString(rank);
    }
}
