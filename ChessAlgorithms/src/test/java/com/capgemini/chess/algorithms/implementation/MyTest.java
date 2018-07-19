package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class MyTest {

    Board board = new Board();

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseWhitePawnGoBack() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 4));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(3, 4), new Coordinate(3, 3));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }


    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseRookGoDownLeft() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(2, 2));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(1, 1), new Coordinate(2, 2));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseRookGoOutsideBoard() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(2, 2));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(12, 2), new Coordinate(2, 2));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldReturnTrueIfKiightHaveSomtingOnPath()  {
        // given
        board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(2, 2));
        board.setPieceAt(Piece.BLACK_ROOK,new Coordinate(4,2));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(3, 3), new Coordinate(4, 1));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionForWhiteQueenLeftUpMove() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
        board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(3, 5));

        // when
        BoardManager boardManager = new BoardManager(board);
        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(4, 4), new Coordinate(2, 6));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseWhitePawnIsNotInStratingPosition() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 2));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(2, 2), new Coordinate(2, 4));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseNotYourPiece() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(1, 4));
        board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(2, 4));
        board.setPieceAt(Piece.WHITE_KING, new Coordinate(5, 4));

        // when
        BoardManager boardManager = new BoardManager(board);
        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(1, 4), new Coordinate(5, 4));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseWhitePawnCanCaptureInFront() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 2));
        board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(2, 3));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(2, 2), new Coordinate(2, 3));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionForWhiteBishopRightDownMove() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(3, 4));
        board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(4, 3));

        // when
        BoardManager boardManager = new BoardManager(board);
        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(3, 4), new Coordinate(5, 3));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }

    @org.junit.Test
    public void shouldThrowInvalidMoveExceptionBecauseWhitePawnCanNotGoBack() throws InvalidMoveException {
        // given
        board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 3));

        // when
        BoardManager boardManager = new BoardManager(board);

        boolean exceptionThrown = false;
        try {
            boardManager.performMove(new Coordinate(3, 3), new Coordinate(3, 2));
        } catch (InvalidMoveException e) {
            exceptionThrown = true;
        }

        // then
        assertTrue(exceptionThrown);
    }






}
