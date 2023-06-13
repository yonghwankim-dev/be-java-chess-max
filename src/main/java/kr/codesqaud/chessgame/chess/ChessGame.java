package kr.codesqaud.chessgame.chess;

import kr.codesqaud.chessgame.exception.InvalidTurnException;
import kr.codesqaud.chessgame.pieces.config.Color;

public class ChessGame {

    private final Board board;
    private Color currentTurn;

    public ChessGame(Board board) {
        this.board = board;
        this.currentTurn = Color.WHITE;
    }

    public void initialize() {
        board.initialize();
    }

    public void move(final String sourcePosition, final String targetPosition) {
        verifySameColor(sourcePosition);
        board.move(sourcePosition, targetPosition);
    }

    private void verifySameColor(final String sourcePosition) {
        if (currentTurn != board.getColorByPosition(sourcePosition)) {
            throw new InvalidTurnException(currentTurn.name() + "색 기물을 선택해주세요.");
        }
    }

    public String showBoard() {
        return board.showBoard();
    }

    public void nextTurn() {
        if (currentTurn == Color.WHITE) {
            currentTurn = Color.BLACK;
        } else {
            currentTurn = Color.WHITE;
        }
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
