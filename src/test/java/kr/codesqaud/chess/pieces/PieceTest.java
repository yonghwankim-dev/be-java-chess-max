package kr.codesqaud.chess.pieces;


import static kr.codesqaud.chessgame.chess.pieces.Position.createPosition;
import static kr.codesqaud.chessgame.chess.pieces.Position.emptyPosition;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.BISHOP;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.KING;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.KNIGHT;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.NO_PIECE;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.PAWN;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.QUEEN;
import static kr.codesqaud.chessgame.chess.pieces.config.Type.ROOK;

import kr.codesqaud.chessgame.chess.pieces.Bishop;
import kr.codesqaud.chessgame.chess.pieces.Blank;
import kr.codesqaud.chessgame.chess.pieces.King;
import kr.codesqaud.chessgame.chess.pieces.Knight;
import kr.codesqaud.chessgame.chess.pieces.Pawn;
import kr.codesqaud.chessgame.chess.pieces.Piece;
import kr.codesqaud.chessgame.chess.pieces.Queen;
import kr.codesqaud.chessgame.chess.pieces.Rook;
import kr.codesqaud.chessgame.chess.pieces.config.Color;
import kr.codesqaud.chessgame.chess.pieces.config.Type;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("위치 정보가 주어지고 백폰과 흑폰 생성 요청할때 백폰과 흑폰이 생성된다")
    public void create() {
        verifyPawn(Pawn.createWhite(createPosition("a2")), Color.WHITE, PAWN.getWhiteRepresentation());
        verifyPawn(Pawn.createBlack(createPosition("a7")), Color.BLACK, PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("위치 정보가 주이지고 백폰 생성 요청할때 생성된 백폰은 하얀색입니다")
    public void isWhite() {
        // given
        final Piece whitePawn = Pawn.createWhite(createPosition("a2"));
        // when
        final boolean white = whitePawn.isWhite();
        // then
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(white).isTrue();
        assertions.assertAll();
    }

    @Test
    @DisplayName("위치 정보가 주어지고 흑폰 생성 요청할때 생성된 흑폰은 검정색입니다.")
    public void isBlack() {
        // given
        final Piece whitePawn = Pawn.createBlack(createPosition("a7"));
        // when
        final boolean black = whitePawn.isBlack();
        // then
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(black).isTrue();
        assertions.assertAll();
    }


    @Test
    @DisplayName("폰 타입이 주어지고 색상별 표현문자 요청시 백폰은 소문자 p이고 흑폰은 대문자 P이다")
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
    @DisplayName("위치정보가 주어지고 기물별 팩토리 메소드로 기물 객체 생성할때 기물의 정보와 색상이 일치한다")
    public void create_piece() {
        // when
        Piece whitePawn = Pawn.createWhite(createPosition("a2"));
        Piece blackPawn = Pawn.createBlack(createPosition("a7"));
        Piece whiteRook = Rook.createWhite(createPosition("a1"));
        Piece blackRook = Rook.createBlack(createPosition("a8"));
        Piece whiteKnight = Knight.createWhite(createPosition("b1"));
        Piece blackKnight = Knight.createBlack(createPosition("b8"));
        Piece whiteBishop = Bishop.createWhite(createPosition("c1"));
        Piece blackBishop = Bishop.createBlack(createPosition("c8"));
        Piece whiteQueen = Queen.createWhite(createPosition("d1"));
        Piece blackQueen = Queen.createBlack(createPosition("d8"));
        Piece whiteKing = King.createWhite(createPosition("e1"));
        Piece blackKing = King.createBlack(createPosition("e8"));
        Piece blank = Blank.create(emptyPosition());
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
        assertions.assertThat(blank.getPosition().toString()).isEqualTo("`0");
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
