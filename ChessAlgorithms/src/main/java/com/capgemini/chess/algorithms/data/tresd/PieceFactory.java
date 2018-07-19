package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class PieceFactory {

    public Factory getPieceForm(Coordinate cord, Board board) throws InvalidMoveException {
        if (board.getPieceAt(cord).getType() == PieceType.KNIGHT) {
            Factory pieceFormOnThisPlace = new KnightFactory();
            return pieceFormOnThisPlace;
        } else if (board.getPieceAt(cord).getType() == PieceType.BISHOP) {
            Factory pieceFormOnThisPlace = new BishopFactory();
            return pieceFormOnThisPlace;

        } else if (board.getPieceAt(cord).getType() == PieceType.ROOK) {
            Factory pieceFormOnThisPlace = new RookFactory();
            return pieceFormOnThisPlace;

        } else if (board.getPieceAt(cord).getType() == PieceType.QUEEN) {
            Factory pieceFormOnThisPlace = new QueenFactory();
            return pieceFormOnThisPlace;

        } else if (board.getPieceAt(cord).getType() == PieceType.KING) {
            Factory pieceFormOnThisPlace = new KingFactory();
            return pieceFormOnThisPlace;

        } else if (board.getPieceAt(cord).getType() == PieceType.PAWN) {
            if (board.getPieceAt(cord).getColor() == Color.BLACK) {
                Factory pieceFormOnThisPlace = new PawnFactory();
                return pieceFormOnThisPlace;

            } else {// (colorFrom == Color.WHITE)
                Factory pieceFormOnThisPlace = new PawnFactory();
                return pieceFormOnThisPlace;

            }

        }
        return null;
    }
}
