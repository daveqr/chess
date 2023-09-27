package com.ebonyandirony.chess.move;

import com.ebonyandirony.chess.move.verify.AlgebraicNotationVerifier;
import com.ebonyandirony.chess.move.verify.NotationVerifier;
import com.ebonyandirony.chess.piece.PieceType;
import com.google.inject.Inject;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ebonyandirony.chess.board.Board.FILE_LOWER_BOUND;
import static com.ebonyandirony.chess.board.Board.FILE_UPPER_BOUND;
import static com.ebonyandirony.chess.piece.PieceType.PAWN;


public class Move {
    private final NotationVerifier notationVerifier = new AlgebraicNotationVerifier();

    private final PieceType type;

    private final String move;

    private final char file;

    private final char rank;

    @Inject
    private Move(String move) {
        assertMove(move);
        verify(move);

        this.move = move;
        this.rank = findRank(move);
        this.file = findFile(move);

        final char symbol = move.charAt(0);
        final boolean isPawn = Character.isLowerCase(symbol) && symbol >= 'a' && symbol <= 'h';

        this.type = isPawn ? PAWN : PieceType.fromSymbol(symbol);
    }

    public static Move create(final String move) {
        return new Move(move);
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
        return firstChar >= FILE_LOWER_BOUND && firstChar <= FILE_UPPER_BOUND;
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
