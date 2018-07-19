package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class PicesTypeFactory {



    public class PieceTypeFactory {

        public Factory getFactoryvalidation(Board board, Coordinate from) {

            PieceType pieceType = board.getPieceAt(from).getType();

            Factory result = null;


            if (pieceType == PieceType.PAWN) {
                result = new PawnFactory();
            } else if (pieceType == PieceType.ROOK) {
                result = new RookFactory();
            } else if (pieceType == PieceType.KNIGHT) {
                result = new KnightFactory();
            } else if (pieceType == PieceType.BISHOP) {
                result = new BishopFactory();
            } else if (pieceType == PieceType.QUEEN) {
                result = new QueenFactory();
            } else if (pieceType == PieceType.KING) {
                result = new KnightFactory();
            }

            return result;
        }
    }

}
