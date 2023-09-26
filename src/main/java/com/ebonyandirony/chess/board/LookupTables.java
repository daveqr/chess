package com.ebonyandirony.chess.board;

public class LookupTables {
    // RANK_8 <- RANK_1

    // CLEAR RANKS
    static final long CLEAR_RANK_1 = 0b11111111_11111111_11111111_11111111_11111111_11111111_11111111_00000000L;
    static final long CLEAR_RANK_2 = 0b11111111_11111111_11111111_11111111_11111111_11111111_00000000_11111111L;
    static final long CLEAR_RANK_3 = 0b11111111_11111111_11111111_11111111_11111111_00000000_11111111_11111111L;
    static final long CLEAR_RANK_4 = 0b11111111_11111111_11111111_11111111_00000000_11111111_11111111_11111111L;
    static final long CLEAR_RANK_5 = 0b11111111_11111111_11111111_00000000_11111111_11111111_11111111_11111111L;
    static final long CLEAR_RANK_6 = 0b11111111_11111111_00000000_11111111_11111111_11111111_11111111_11111111L;
    static final long CLEAR_RANK_7 = 0b11111111_00000000_11111111_11111111_11111111_11111111_11111111_11111111L;
    static final long CLEAR_RANK_8 = 0b00000000_11111111_11111111_11111111_11111111_11111111_11111111_11111111L;

    // MASK RANKS
    static final long MASK_RANK_1 = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_11111111L;
    static final long MASK_RANK_2 = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
    static final long MASK_RANK_3 = 0b00000000_00000000_00000000_00000000_00000000_11111111_00000000_00000000L;
    static final long MASK_RANK_4 = 0b00000000_00000000_00000000_00000000_11111111_00000000_00000000_00000000L;
    static final long MASK_RANK_5 = 0b00000000_00000000_00000000_11111111_00000000_00000000_00000000_00000000L;
    static final long MASK_RANK_6 = 0b00000000_00000000_11111111_00000000_00000000_00000000_00000000_00000000L;
    static final long MASK_RANK_7 = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
    static final long MASK_RANK_8 = 0b11111111_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;

    // FILE_H <- FILE_A
    // CLEAR FILES
    static final long CLEAR_FILE_A = 0b11111110_11111110_11111110_11111110_11111110_11111110_11111110_11111110L;
    static final long CLEAR_FILE_B = 0b11111101_11111101_11111101_11111101_11111101_11111101_11111101_11111101L;
    static final long CLEAR_FILE_C = 0b11111011_11111011_11111011_11111011_11111011_11111011_11111011_11111011L;
    static final long CLEAR_FILE_D = 0b11110111_11110111_11110111_11110111_11110111_11110111_11110111_11110111L;
    static final long CLEAR_FILE_E = 0b11101111_11101111_11101111_11101111_11101111_11101111_11101111_11101111L;
    static final long CLEAR_FILE_F = 0b11011111_11011111_11011111_11011111_11011111_11011111_11011111_11011111L;
    static final long CLEAR_FILE_G = 0b10111111_10111111_10111111_10111111_10111111_10111111_10111111_10111111L;
    static final long CLEAR_FILE_H = 0b01111111_01111111_01111111_01111111_01111111_01111111_01111111_01111111L;

    // MASK FILES
    static final long MASK_FILE_A = 0b00000001_00000001_00000001_00000001_00000001_00000001_00000001_00000001L;
    static final long MASK_FILE_B = 0b00000010_00000010_00000010_00000010_00000010_00000010_00000010_00000010L;
    static final long MASK_FILE_C = 0b00000100_00000100_00000100_00000100_00000100_00000100_00000100_00000100L;
    static final long MASK_FILE_D = 0b00001000_00001000_00001000_00001000_00001000_00001000_00001000_00001000L;
    static final long MASK_FILE_E = 0b00010000_00010000_00010000_00010000_00010000_00010000_00010000_00010000L;
    static final long MASK_FILE_F = 0b00100000_00100000_00100000_00100000_00100000_00100000_00100000_00100000L;
    static final long MASK_FILE_G = 0b01000000_01000000_01000000_01000000_01000000_01000000_01000000_01000000L;
    static final long MASK_FILE_H = 0b10000000_10000000_10000000_10000000_10000000_10000000_10000000_10000000L;

}