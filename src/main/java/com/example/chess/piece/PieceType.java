package com.example.chess.piece;

public enum PieceType {
    PAWN("P", "pawn"), KING("K", "King"), QUEEN("Q", "Queen"), BISHOP("B", "Bishop"), ROOK("R", "Rook"), KNIGHT("N",
            "Knight");

    private final String symbol;
    private final String label;

    PieceType(final String symbol, final String label) {
        this.symbol = symbol;
        this.label = label;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLabel() {
        return label;
    }
}
