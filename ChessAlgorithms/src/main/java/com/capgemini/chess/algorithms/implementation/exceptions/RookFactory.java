package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;

public class RookFactory implements Factory {
    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) throws InvalidMoveException {
        PieceType pieceType = piece.getType();

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());



        switch (pieceType) {
            case ROOK:
                if (changeOfLocationX > 0 && changeOfLocationY == 0) {
                    return true;
                } else if (changeOfLocationX == 0 && changeOfLocationY > 0) {
                    return true;
                } else {
                    throw new InvalidMoveException();
                }
        }



        throw new InvalidMoveException();
    }





}
