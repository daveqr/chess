package com.example.chess.piece;

public enum PieceType {
    PAWN("p", "pawn", '♙', '♟'), KING("K", "King", '♔', '♚'), QUEEN("Q", "Queen", '♕', '♛'), BISHOP("B", "Bishop", '♗',
            '♝'), ROOK("R", "Rook", '♖', '♜'), KNIGHT("N", "Knight", '♘', '♞');

    private final String symbol;
    private final String label;

    private final char whiteAsciiCharacter;
    private final char blackAsciiCharacter;

    PieceType(final String symbol, final String label, char whiteAsciiCharacter, char blackAsciiCharacter) {
        this.symbol = symbol;
        this.label = label;
        this.whiteAsciiCharacter = whiteAsciiCharacter;
        this.blackAsciiCharacter = blackAsciiCharacter;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLabel() {
        return label;
    }

    public char getWhiteAsciiCharacter() {
        return whiteAsciiCharacter;
    }

    public char getBlackAsciiCharacter() {
        return blackAsciiCharacter;
    }
}
