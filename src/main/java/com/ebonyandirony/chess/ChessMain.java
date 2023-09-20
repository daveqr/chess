package com.ebonyandirony.chess;

import com.google.inject.AbstractModule;

public class ChessMain extends AbstractModule {

    public static void main(String[] args) {
        Chess chess = new Chess();
        chess.run();
    }
}
