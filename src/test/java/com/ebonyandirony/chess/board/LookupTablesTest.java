package com.ebonyandirony.chess.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LookupTablesTest {

    @Test
    public void shouldClearRank1() {
        long expected = 0b11111111_11111111_11111111_11111111_11111111_11111111_11111111_00000000L;
        assertThat(LookupTables.CLEAR_RANK_1).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank2() {
        long expected = 0b11111111_11111111_11111111_11111111_11111111_11111111_00000000_11111111L;
        assertThat(LookupTables.CLEAR_RANK_2).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank3() {
        long expected = 0b11111111_11111111_11111111_11111111_11111111_00000000_11111111_11111111L;
        assertThat(LookupTables.CLEAR_RANK_3).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank4() {
        long expected = 0b11111111_11111111_11111111_11111111_00000000_11111111_11111111_11111111L;
        assertThat(LookupTables.CLEAR_RANK_4).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank5() {
        long expected = 0b11111111_11111111_11111111_00000000_11111111_11111111_11111111_11111111L;
        assertThat(LookupTables.CLEAR_RANK_5).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank6() {
        long expected = 0b11111111_11111111_00000000_11111111_11111111_11111111_11111111_11111111L;
        assertThat(LookupTables.CLEAR_RANK_6).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank7() {
        long expected = 0b11111111_00000000_11111111_11111111_11111111_11111111_11111111_11111111L;
        assertThat(LookupTables.CLEAR_RANK_7).isEqualTo(expected);
    }

    @Test
    public void shouldClearRank8() {
        long expected = 0b00000000_11111111_11111111_11111111_11111111_11111111_11111111_11111111L;
        assertThat(LookupTables.CLEAR_RANK_8).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank1() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_11111111L;
        assertThat(LookupTables.MASK_RANK_1).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank2() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
        assertThat(LookupTables.MASK_RANK_2).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank3() {
        long expected = 0b00000000_00000000_00000000_00000000_00000000_11111111_00000000_00000000L;
        assertThat(LookupTables.MASK_RANK_3).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank4() {
        long expected = 0b00000000_00000000_00000000_00000000_11111111_00000000_00000000_00000000L;
        assertThat(LookupTables.MASK_RANK_4).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank5() {
        long expected = 0b00000000_00000000_00000000_11111111_00000000_00000000_00000000_00000000L;
        assertThat(LookupTables.MASK_RANK_5).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank6() {
        long expected = 0b00000000_00000000_11111111_00000000_00000000_00000000_00000000_00000000L;
        assertThat(LookupTables.MASK_RANK_6).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank7() {
        long expected = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(LookupTables.MASK_RANK_7).isEqualTo(expected);
    }

    @Test
    public void shouldMaskRank8() {
        long expected = 0b11111111_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        assertThat(LookupTables.MASK_RANK_8).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileA() {
        long expected = 0b11111110_11111110_11111110_11111110_11111110_11111110_11111110_11111110L;
        assertThat(LookupTables.CLEAR_FILE_A).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileB() {
        long expected = 0b11111101_11111101_11111101_11111101_11111101_11111101_11111101_11111101L;
        assertThat(LookupTables.CLEAR_FILE_B).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileC() {
        long expected = 0b11111011_11111011_11111011_11111011_11111011_11111011_11111011_11111011L;
        assertThat(LookupTables.CLEAR_FILE_C).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileD() {
        long expected = 0b11110111_11110111_11110111_11110111_11110111_11110111_11110111_11110111L;
        assertThat(LookupTables.CLEAR_FILE_D).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileE() {
        long expected = 0b11101111_11101111_11101111_11101111_11101111_11101111_11101111_11101111L;
        assertThat(LookupTables.CLEAR_FILE_E).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileF() {
        long expected = 0b11011111_11011111_11011111_11011111_11011111_11011111_11011111_11011111L;
        assertThat(LookupTables.CLEAR_FILE_F).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileG() {
        long expected = 0b10111111_10111111_10111111_10111111_10111111_10111111_10111111_10111111L;
        assertThat(LookupTables.CLEAR_FILE_G).isEqualTo(expected);
    }

    @Test
    public void shouldClearFileH() {
        long expected = 0b01111111_01111111_01111111_01111111_01111111_01111111_01111111_01111111L;
        assertThat(LookupTables.CLEAR_FILE_H).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileA() {
        long expected = 0b00000001_00000001_00000001_00000001_00000001_00000001_00000001_00000001L;
        assertThat(LookupTables.MASK_FILE_A).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileB() {
        long expected = 0b00000010_00000010_00000010_00000010_00000010_00000010_00000010_00000010L;
        assertThat(LookupTables.MASK_FILE_B).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileC() {
        long expected = 0b00000100_00000100_00000100_00000100_00000100_00000100_00000100_00000100L;
        assertThat(LookupTables.MASK_FILE_C).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileD() {
        long expected = 0b00001000_00001000_00001000_00001000_00001000_00001000_00001000_00001000L;
        assertThat(LookupTables.MASK_FILE_D).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileE() {
        long expected = 0b00010000_00010000_00010000_00010000_00010000_00010000_00010000_00010000L;
        assertThat(LookupTables.MASK_FILE_E).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileF() {
        long expected = 0b00100000_00100000_00100000_00100000_00100000_00100000_00100000_00100000L;
        assertThat(LookupTables.MASK_FILE_F).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileG() {
        long expected = 0b01000000_01000000_01000000_01000000_01000000_01000000_01000000_01000000L;
        assertThat(LookupTables.MASK_FILE_G).isEqualTo(expected);
    }

    @Test
    public void shouldMaskFileH() {
        long expected = 0b10000000_10000000_10000000_10000000_10000000_10000000_10000000_10000000L;
        assertThat(LookupTables.MASK_FILE_H).isEqualTo(expected);
    }

}
