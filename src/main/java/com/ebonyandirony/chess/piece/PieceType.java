package com.ebonyandirony.chess.piece;

import java.util.HashSet;
import java.util.Set;

public enum PieceType {


    PAWN('p' , "pawn", '♙' , '♟'),
    KING('K' , "King", '♔' , '♚'),
    QUEEN('Q' , "Queen", '♕' , '♛'),
    BISHOP('B' , "Bishop", '♗' ,
            '♝'),
    ROOK('R' , "Rook", '♖' , '♜'),
    KNIGHT('N' , "Knight", '♘' , '♞');


    private final char symbol;
    private final String label;

    private final char whiteAsciiCharacter;
    private final char blackAsciiCharacter;

    PieceType(final char symbol, final String label, char whiteAsciiCharacter, char blackAsciiCharacter) {
        this.symbol = symbol;
        this.label = label;
        this.whiteAsciiCharacter = whiteAsciiCharacter;
        this.blackAsciiCharacter = blackAsciiCharacter;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getLabel() {
        return label;
    }

    private static final Set<PieceType> NAMED_PIECES = new HashSet<>();

    static {
        NAMED_PIECES.add(BISHOP);
        NAMED_PIECES.add(ROOK);
        NAMED_PIECES.add(KNIGHT);
        NAMED_PIECES.add(QUEEN);
        NAMED_PIECES.add(KING);
    }

    public static Set<PieceType> getNamedPieces() {
        return NAMED_PIECES;
    }

    public char getWhiteAsciiCharacter() {
        return whiteAsciiCharacter;
    }

    public char getBlackAsciiCharacter() {
        return blackAsciiCharacter;
    }

    public static PieceType fromSymbol(char symbol) {
        for (PieceType pieceType : values()) {
            if (pieceType.getSymbol() == symbol) {
                return pieceType;
            }
        }
        throw new IllegalArgumentException("Invalid symbol: " + symbol);
    }
}
