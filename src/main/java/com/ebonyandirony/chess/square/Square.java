package com.ebonyandirony.chess.square;

import com.ebonyandirony.chess.piece.Piece;

public class Square {

    private final SquareAddress square;
    private final Piece piece;

    public Square(final SquareAddress square, final Piece piece) {
        this.square = square;
        this.piece = piece;
    }

    public SquareAddress getSquare() {
        return square;
    }

    public Piece getPiece() {
        return piece;
    }
}