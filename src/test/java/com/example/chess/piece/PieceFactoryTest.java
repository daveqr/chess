package com.example.chess.piece;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.chess.piece.Movement.VERTICAL_FORWARD_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceFactoryTest {

    @Test
    public void shouldCreatePawn() {
        var pawn = PieceFactory.createPawn();
        assertThat(pawn.type()).isEqualTo(PieceType.PAWN);
        assertThat(pawn.movement()).isEqualTo(Set.of(VERTICAL_FORWARD_ONE));

        //        Set.of(VERTICAL_FORWARD_ONE), Capture.DIAGONAL);
    }

}