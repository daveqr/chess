package com.ebonyandirony.chess.move;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AlgebraicNotationParserTest {

    // Garry Kasparov vs Veselin Topalov
    private static final String[] moves = {
            "e4", "d6", "d4", "Nf6", "Nc3", "g6", "Be3", "Bg7", "Qd2", "c6", "f3", "b5",
            "Nge2", "Nbd7", "Bh6", "Bxh6", "Qxh6", "Bb7", "a3", "e5", "O-O-O", "Qe7",
            "Kb1", "a6", "Nc1", "O-O-O", "Nb3", "exd4", "Rxd4", "c5", "Rd1", "Nb6",
            "g3", "Kb8", "Na5", "Ba8", "Bh3", "d5", "Qf4+", "Ka7", "Rhe1", "d4", "Nd5",
            "Nbxg5", "exd5", "Qd6", "Rxd4", "cxd4", "Re7+", "Kb6", "Qxd4+", "Ka5",
            "b4+", "Ka4", "Qc3", "Qxd5", "Ra7", "Bb7", "Rxb7", "Qc4", "Qxf6", "Kxa3",
            "Qa1+", "Kxb4", "Qb2+", "Kc5", "Qa3+", "Kd1", "Bf1", "Rd2", "Rd7", "Rxd7",
            "Bxc4", "bxc4", "Qxh8", "Rd3", "Qa1"
    };

    private static Stream<String> movesProvider() {
        return Arrays.stream(moves);
    }

    @ParameterizedTest
    @MethodSource("movesProvider")
    public void shouldParseMoves(String move) {
        NotationParser parser = new AlgebraicNotationParser();
        assertThat(parser.parses(move)).isTrue();
    }
}

