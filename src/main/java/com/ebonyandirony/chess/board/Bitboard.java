package com.ebonyandirony.chess.board;

public class Bitboard {
    Bitboard() {
        // @formatter:off
        long whitePawns =   0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
        long whiteRooks =   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L;
        long whiteKnights = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L;
        long whiteBishops = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L;
        long whiteQueen =   0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L;
        long whiteKing =    0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L;

        long blackPawns =   0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        long blackRooks =   0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long blackKnights = 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long blackBishops = 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long blackQueen =   0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long blackKing =    0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        // @formatter:off

        // Example:
        // long bitboardForD3 = 1L << (3 * 8 + 4);
        // (row * numSquares + col)
        // d = 3 (4th row 0-based)
        // 8 = num squares per row
        // 4 = 3 (4th col 0-based)
        // (3 * 8 + 4): calculates the index of square d3 in a linear 0-based index for an 8x8 chessboard
        //      3 corresponds to the row (rank) of the square, with rows numbered from 0 to 7 (0-based).
        //      8 represents the total number of squares in each row (8 columns on the board).
        //      4 represents the column (file) of the square, with columns also numbered from 0 to 7 (0-based).

        //      When you multiply 3 by 8 and add 4, you get 3 * 8 + 4 = 28.
        //      This is the index of square d3 in a linear 0-based index for an 8x8 board.

        long bitboardForA1 = 1L;
        long bitboardForA2 = 1L << (    8);
        long bitboardForA3 = 1L << (2 * 8);
        long bitboardForA4 = 1L << (3 * 8);
        long bitboardForA5 = 1L << (4 * 8);
        long bitboardForA6 = 1L << (5 * 8);
        long bitboardForA7 = 1L << (6 * 8);
        long bitboardForA8 = 1L << (7 * 8);

        // Bitboards for squares in the 'b' file
        long bitboardForB1 = 1L << (        1);
        long bitboardForB2 = 1L << (    8 + 1);
        long bitboardForB3 = 1L << (2 * 8 + 1);
        long bitboardForB4 = 1L << (3 * 8 + 1);
        long bitboardForB5 = 1L << (4 * 8 + 1);
        long bitboardForB6 = 1L << (5 * 8 + 1);
        long bitboardForB7 = 1L << (6 * 8 + 1);
        long bitboardForB8 = 1L << (7 * 8 + 1);

        // Bitboards for squares in the 'c' file
        long bitboardForC1 = 1L << (        2);
        long bitboardForC2 = 1L << (    8 + 2);
        long bitboardForC3 = 1L << (2 * 8 + 2);
        long bitboardForC4 = 1L << (3 * 8 + 2);
        long bitboardForC5 = 1L << (4 * 8 + 2);
        long bitboardForC6 = 1L << (5 * 8 + 2);
        long bitboardForC7 = 1L << (6 * 8 + 2);
        long bitboardForC8 = 1L << (7 * 8 + 2);

        // Bitboards for squares in the 'd' file
        long bitboardForD1 = 1L << (        3);
        long bitboardForD2 = 1L << (    8 + 3);
        long bitboardForD3 = 1L << (2 * 8 + 3);
        long bitboardForD4 = 1L << (3 * 8 + 3);
        long bitboardForD5 = 1L << (4 * 8 + 3);
        long bitboardForD6 = 1L << (5 * 8 + 3);
        long bitboardForD7 = 1L << (6 * 8 + 3);
        long bitboardForD8 = 1L << (7 * 8 + 3);

        // Bitboards for squares in the 'e' file
        long bitboardForE1 = 1L << (        4);
        long bitboardForE2 = 1L << (    8 + 4);
        long bitboardForE3 = 1L << (2 * 8 + 4);
        long bitboardForE4 = 1L << (3 * 8 + 4);
        long bitboardForE5 = 1L << (4 * 8 + 4);
        long bitboardForE6 = 1L << (5 * 8 + 4);
        long bitboardForE7 = 1L << (6 * 8 + 4);
        long bitboardForE8 = 1L << (7 * 8 + 4);

        // Bitboards for squares in the 'f' file
        long bitboardForF1 = 1L << (        5);
        long bitboardForF2 = 1L << (    8 + 5);
        long bitboardForF3 = 1L << (2 * 8 + 5);
        long bitboardForF4 = 1L << (3 * 8 + 5);
        long bitboardForF5 = 1L << (4 * 8 + 5);
        long bitboardForF6 = 1L << (5 * 8 + 5);
        long bitboardForF7 = 1L << (6 * 8 + 5);
        long bitboardForF8 = 1L << (7 * 8 + 5);

        // Bitboards for squares in the 'g' file
        long bitboardForG1 = 1L << (        6);
        long bitboardForG2 = 1L << (    8 + 6);
        long bitboardForG3 = 1L << (2 * 8 + 6);
        long bitboardForG4 = 1L << (3 * 8 + 6);
        long bitboardForG5 = 1L << (4 * 8 + 6);
        long bitboardForG6 = 1L << (5 * 8 + 6);
        long bitboardForG7 = 1L << (6 * 8 + 6);
        long bitboardForG8 = 1L << (7 * 8 + 6);

        // Bitboards for squares in the 'h' file
        long bitboardForH1 = 1L << (        7);
        long bitboardForH2 = 1L << (    8 + 7);
        long bitboardForH3 = 1L << (2 * 8 + 7);
        long bitboardForH4 = 1L << (3 * 8 + 7);
        long bitboardForH5 = 1L << (4 * 8 + 7);
        long bitboardForH6 = 1L << (5 * 8 + 7);
        long bitboardForH7 = 1L << (6 * 8 + 7);
        long bitboardForH8 = 1L << (7 * 8 + 7);
    }
}
