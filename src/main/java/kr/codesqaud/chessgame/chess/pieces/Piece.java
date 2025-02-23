package kr.codesqaud.chessgame.chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kr.codesqaud.chessgame.chess.pieces.config.Color;
import kr.codesqaud.chessgame.chess.pieces.config.Direction;
import kr.codesqaud.chessgame.chess.pieces.config.Type;
import kr.codesqaud.chessgame.exception.InvalidMovingPieceException;

public abstract class Piece {

    private final Color color;
    private final Type type;
    private final List<Direction> directions;
    private Position position;

    public Piece(final Color color, final Type type, final Position position) {
        this(color, type, position, new ArrayList<>());
    }

    public Piece(final Color color, final Type type, final Position position, final List<Direction> directions) {
        this.color = color;
        this.type = type;
        this.position = position;
        this.directions = directions;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public String getRepresentation() {
        return isWhite() ? type.getWhiteRepresentation() : type.getBlackRepresentation();
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public void move(Piece target) {
        verifyMovePosition(target);
        this.position = target.position;
    }

    public void verifyMovePosition(final Piece target) {
        verifySameTeam(target);
        verifyDirection(target);
    }

    public void verifySameTeam(final Piece target) {
        if (isSameTeam(target)) {
            throw new InvalidMovingPieceException(target.getPosition() + "로 이동할 수 없습니다. 같은 색상의 기물입니다.");
        }
    }

    public void verifyDirection(final Piece target) {
        if (!directions.contains(direction(target))) {
            throw new InvalidMovingPieceException(target.getPosition() + "로 이동할 수 없습니다.");
        }
    }

    public void verifyDirectionMatch(final Piece target) {
        int y = target.getPosition().getRank() - getPosition().getRank();
        int x = target.getPosition().getFile() - getPosition().getFile();
        Direction direction = direction(target);
        if (!direction.match(y, x)) {
            throw new InvalidMovingPieceException(target.getPosition() + "로 이동할 수 없습니다.");
        }
    }

    public Direction direction(final Piece target) {
        return position.direction(target.position);
    }

    public Degree degree(Piece target) {
        return position.degree(target.getPosition());
    }

    public boolean isSameTeam(Piece target) {
        return Objects.equals(color, target.getColor());
    }

    public boolean isMoving(final Piece target) {
        try {
            verifyMovePosition(target);
            return true;
        } catch (InvalidMovingPieceException e) {
            return false;
        }
    }

    public boolean matchType(Type type) {
        return this.type == type;
    }

    public boolean matchColor(Color color) {
        return this.color == color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getType(), getPosition());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return getColor() == piece.getColor() && getType() == piece.getType() && Objects.equals(getPosition(),
            piece.getPosition());
    }

    @Override
    public String toString() {
        return "Piece{" +
            "color=" + color +
            ", type=" + type +
            ", position=" + position +
            '}';
    }


}
