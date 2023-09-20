package com.ebonyandirony.chess.board;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Board create() {
        return new Board();
    }
}