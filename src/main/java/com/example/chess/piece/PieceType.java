package com.example.chess.piece;

public enum PieceType {
    PAWN("P", "pawn"), KING("K", "King"), QUEEN("Q", "Queen"), BISHOP("B", "Bishop"), ROOK("R", "Rook"), KNIGHT("N",
            "Knight");

    private final String symbol;
    private final String customName;

    PieceType(String symbol, String customName) {
        this.symbol = symbol;
        this.customName = customName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return customName;
    }
}
