package com.example.chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SquareTest {
    @Test
    public void shouldGetTheCorrectSquare() {
        final Square b6 = Square.get("b6");

        assertThat(b6.getX()).isEqualTo(2);
        assertThat(b6.getY()).isEqualTo(1);
    }

    @Test
    public void shouldThrowExceptionWithIncorrectLetter() {
        for (char letter = 'i'; letter <= 'z'; letter++) {
            final String finalLetter = letter + "6";
            assertThatThrownBy(() -> {
                Square.get(finalLetter);
            }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: " + finalLetter);
        }
    }

    @Test
    public void shouldThrowExceptionWith0AsNumber() {
        assertThatThrownBy(() -> {
            Square.get("a0");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: a0");
    }

    @Test
    public void shouldThrowExceptionWith9AsNumber() {
        assertThatThrownBy(() -> {
            Square.get("a9");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid square label: a9");
    }
}