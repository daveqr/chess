package com.ebonyandirony.chess.piece;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceFactoryTest {

    @Test
    public void shouldCreatePawn() {
        val pawn = PieceFactory.createPawn();
        assertThat(pawn.type()).isEqualTo(PieceType.PAWN);
        assertThat(pawn.movement()).isEqualTo(Set.of(Movement.VERTICAL_FORWARD_ONE));
    }

}