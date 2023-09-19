package com.example.chess;

public class Square {

    // @formatter:off
    private static final String[][] CHESSBOARD = {
            {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
            {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
            {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
            {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
            {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
            {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
            {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
            {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"}
    };
    // @formatter:on

    private final int x;
    private final int y;
    private final String square;

    private Square(String square) {
        this.x = 8 - Character.getNumericValue(square.charAt(1));
        this.y = square.charAt(0) - 'a';
        this.square = square;
    }

    public static Square get(String square) {
        square = square.toLowerCase();

        if (square.length() == 2 && square.charAt(0) >= 'a' && square.charAt(0) <= 'h' && square.charAt(1) >= '1'
                && square.charAt(1) <= '8') {
            return new Square(square);
        }

        throw new IllegalArgumentException("Invalid square label: " + square);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSquare() {
        return square;
    }
}
