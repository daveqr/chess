package com.example.chess.piece;

public record Movement(MovementDirection type, Direction direction, int number) {
    public static final Movement DIAGONAL_ALL = new Movement(MovementDirection.DIAGONAL, Direction.ANY, Integer.MAX_VALUE);
    public static final Movement HORIZONTAL_ALL = new Movement(MovementDirection.HORIZONTAL, Direction.ANY, Integer.MAX_VALUE);
    public static final Movement VERTICAL_ALL = new Movement(MovementDirection.VERTICAL, Direction.ANY, Integer.MAX_VALUE);
    public static final Movement VERTICAL_FORWARD_ONE = new Movement(MovementDirection.VERTICAL, Direction.FORWARD, 1);
    public static final Movement VERTICAL_ANY_ONE = new Movement(MovementDirection.VERTICAL, Direction.ANY, 1);
    public static final Movement DIAGONAL_ANY_ONE = new Movement(MovementDirection.DIAGONAL, Direction.ANY, 1);
    public static final Movement HORIZONTAL_ANY_ONE = new Movement(MovementDirection.HORIZONTAL, Direction.ANY, 1);
    public static final Movement L_SHAPED = new Movement(MovementDirection.L_SHAPED, Direction.ANY, 1);

}