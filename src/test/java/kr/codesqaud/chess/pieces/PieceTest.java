package kr.codesqaud.chess.pieces;


import static kr.codesqaud.chessgame.pieces.Position.createPosition;
import static kr.codesqaud.chessgame.pieces.Position.emptyPosition;
import static kr.codesqaud.chessgame.pieces.Type.BISHOP;
import static kr.codesqaud.chessgame.pieces.Type.KING;
import static kr.codesqaud.chessgame.pieces.Type.KNIGHT;
import static kr.codesqaud.chessgame.pieces.Type.NO_PIECE;
import static kr.codesqaud.chessgame.pieces.Type.PAWN;
import static kr.codesqaud.chessgame.pieces.Type.QUEEN;
import static kr.codesqaud.chessgame.pieces.Type.ROOK;

import kr.codesqaud.chessgame.pieces.Color;
import kr.codesqaud.chessgame.pieces.Piece;
import kr.codesqaud.chessgame.pieces.PieceFactory;
import kr.codesqaud.chessgame.pieces.Type;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    private PieceFactory pieceFactory;

    @BeforeEach
    public void setup() {
        pieceFactory = PieceFactory.getInstance();
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(pieceFactory.createWhitePawn(createPosition("a2")), Color.WHITE, PAWN.getWhiteRepresentation());
        verifyPawn(pieceFactory.createBlackPawn(createPosition("a7")), Color.BLACK, PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("폰의 색깔이 백색이어야 한다.")
    public void isWhite() {
        // given
        final Piece whitePawn = pieceFactory.createWhitePawn(createPosition("a2"));
        // when
        final boolean white = whitePawn.isWhite();
        // then
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(white).isTrue();
        assertions.assertAll();
    }

    @Test
    @DisplayName("폰의 색깔이 흑색이어야 한다.")
    public void isBlack() {
        // given
        final Piece whitePawn = pieceFactory.createBlackPawn(createPosition("a7"));
        // when
        final boolean black = whitePawn.isBlack();
        // then
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(black).isTrue();
        assertions.assertAll();
    }


    @Test
    @DisplayName("백색폰은 소문자 p이고 흑색폰은 대문자 P여야 한다")
    public void getRepresentationPerPiece() {
        // given
        Type pawn = PAWN;
        // when
        String whiteRepresentation = pawn.getWhiteRepresentation();
        String blackRepresentation = pawn.getBlackRepresentation();
        // then
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(whiteRepresentation).isEqualTo("p");
        assertions.assertThat(blackRepresentation).isEqualTo("P");
        assertions.assertAll();
    }

    @Test
    @DisplayName("팩토리 메소드로 생성한 기물 객체는 색상과 타입이 일치하여야 한다")
    public void create_piece() {
        // when
        Piece whitePawn = pieceFactory.createWhitePawn(createPosition("a2"));
        Piece blackPawn = pieceFactory.createBlackPawn(createPosition("a7"));
        Piece whiteRook = pieceFactory.createWhiteRook(createPosition("a1"));
        Piece blackRook = pieceFactory.createBlackRook(createPosition("a8"));
        Piece whiteKnight = pieceFactory.createWhiteKnight(createPosition("b1"));
        Piece blackKnight = pieceFactory.createBlackKnight(createPosition("b8"));
        Piece whiteBishop = pieceFactory.createWhiteBishop(createPosition("c1"));
        Piece blackBishop = pieceFactory.createBlackBishop(createPosition("c8"));
        Piece whiteQueen = pieceFactory.createWhiteQueen(createPosition("d1"));
        Piece blackQueen = pieceFactory.createBlackQueen(createPosition("d8"));
        Piece whiteKing = pieceFactory.createWhiteKing(createPosition("e1"));
        Piece blackKing = pieceFactory.createBlackKing(createPosition("e8"));
        Piece blank = pieceFactory.createBlank(emptyPosition());
        // then
        verifyPiece(whitePawn, blackPawn, PAWN);
        verifyPiece(whiteRook, blackRook, ROOK);
        verifyPiece(whiteKnight, blackKnight, KNIGHT);
        verifyPiece(whiteBishop, blackBishop, BISHOP);
        verifyPiece(whiteQueen, blackQueen, QUEEN);
        verifyPiece(whiteKing, blackKing, KING);
        verifyPiecePosition(whitePawn, "a2");
        verifyPiecePosition(blackPawn, "a7");
        verifyPiecePosition(whiteRook, "a1");
        verifyPiecePosition(blackRook, "a8");
        verifyPiecePosition(whiteKnight, "b1");
        verifyPiecePosition(blackKnight, "b8");
        verifyPiecePosition(whiteBishop, "c1");
        verifyPiecePosition(blackBishop, "c8");
        verifyPiecePosition(whiteQueen, "d1");
        verifyPiecePosition(blackQueen, "d8");
        verifyPiecePosition(whiteKing, "e1");
        verifyPiecePosition(blackKing, "e8");

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(blank.isWhite()).isFalse();
        assertions.assertThat(blank.isBlack()).isFalse();
        assertions.assertThat(blank.getType()).isEqualTo(NO_PIECE);
        assertions.assertThat(blank.getPosition().toString()).isEqualTo("00");
        assertions.assertAll();
    }

    private void verifyPawn(final Piece piece, final Color color, final String representation) {
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(piece.getColor()).isEqualTo(color);
        assertions.assertThat(piece.getRepresentation()).isEqualTo(representation);
        assertions.assertAll();
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(whitePiece.isWhite()).isTrue();
        assertions.assertThat(whitePiece.getType()).isEqualTo(type);
        assertions.assertThat(blackPiece.isBlack()).isTrue();
        assertions.assertThat(blackPiece.getType()).isEqualTo(type);
        assertions.assertAll();
    }


    private void verifyPiecePosition(final Piece piece, final String position) {
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(piece.getPosition()).isEqualTo(createPosition(position));
        assertions.assertAll();
    }

}
