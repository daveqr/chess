package com.ebonyandirony.chess.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoardFactoryTest {

    @Test
    public void shouldCreateANewBoard() {
        final Board board = Board.create();

        assertNotNull(board);
    }
}