package com.example.chess.piece;

public record Movement(MovementShape type, Direction direction, int number) {
    public static final Movement DIAGONAL_ALL = new Movement(MovementShape.DIAGONAL, Direction.ANY, Integer.MAX_VALUE);
    public static final Movement HORIZONTAL_ALL = new Movement(MovementShape.HORIZONTAL, Direction.ANY, Integer.MAX_VALUE);
    public static final Movement VERTICAL_ALL = new Movement(MovementShape.VERTICAL, Direction.ANY, Integer.MAX_VALUE);
    public static final Movement VERTICAL_FORWARD_ONE = new Movement(MovementShape.VERTICAL, Direction.FORWARD, 1);
    public static final Movement VERTICAL_ANY_ONE = new Movement(MovementShape.VERTICAL, Direction.ANY, 1);
    public static final Movement DIAGONAL_ANY_ONE = new Movement(MovementShape.DIAGONAL, Direction.ANY, 1);
    public static final Movement HORIZONTAL_ANY_ONE = new Movement(MovementShape.HORIZONTAL, Direction.ANY, 1);
    public static final Movement L_SHAPED = new Movement(MovementShape.L_SHAPED, Direction.ANY, 1);

}