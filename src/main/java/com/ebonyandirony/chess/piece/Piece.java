package com.ebonyandirony.chess.piece;

import java.util.Set;

public record Piece(PieceType type, Set<Movement> movement, Capture capture) {}
