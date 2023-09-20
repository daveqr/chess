package com.ebonyandirony.chess.piece;

import java.util.Set;

import static com.ebonyandirony.chess.piece.Movement.DIAGONAL_ALL;
import static com.ebonyandirony.chess.piece.Movement.DIAGONAL_ANY_ONE;
import static com.ebonyandirony.chess.piece.Movement.HORIZONTAL_ALL;
import static com.ebonyandirony.chess.piece.Movement.HORIZONTAL_ANY_ONE;
import static com.ebonyandirony.chess.piece.Movement.L_SHAPED;
import static com.ebonyandirony.chess.piece.Movement.VERTICAL_ALL;
import static com.ebonyandirony.chess.piece.Movement.VERTICAL_ANY_ONE;
import static com.ebonyandirony.chess.piece.Movement.VERTICAL_FORWARD_ONE;

enum Capture {
    DIAGONAL, ANY_VALID
}

public class PieceFactory {

    public static Piece createPawn() {
        return new Piece(PieceType.PAWN, Set.of(VERTICAL_FORWARD_ONE), Capture.DIAGONAL);
    }

    public static Piece createKing() {
        return new Piece(PieceType.KING, Set.of(VERTICAL_ANY_ONE, DIAGONAL_ANY_ONE, HORIZONTAL_ANY_ONE), Capture.ANY_VALID);
    }

    public static Piece createQueen() {
        return new Piece(PieceType.QUEEN, Set.of(VERTICAL_ALL, DIAGONAL_ALL, HORIZONTAL_ALL), Capture.ANY_VALID);
    }

    public static Piece createBishop() {
        return new Piece(PieceType.BISHOP, Set.of(DIAGONAL_ALL), Capture.ANY_VALID);
    }

    public static Piece createKnight() {
        return new Piece(PieceType.KNIGHT, Set.of(L_SHAPED), Capture.ANY_VALID);
    }

    public static Piece createRook() {
        return new Piece(PieceType.ROOK, Set.of(HORIZONTAL_ALL, VERTICAL_ALL), Capture.ANY_VALID);
    }
}
