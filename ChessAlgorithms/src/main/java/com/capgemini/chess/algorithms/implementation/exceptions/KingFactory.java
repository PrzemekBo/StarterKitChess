package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;

public class KingFactory implements Factory {
    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) throws InvalidMoveException {

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());

                if (changeOfLocationX == 1 && changeOfLocationY == 0) {
                    return true;

                } else if (changeOfLocationX == 0 && changeOfLocationY == 1) {
                    return true;
                } else if (changeOfLocationX == 1 && changeOfLocationY == 0) {
                    return true;
                } else if (changeOfLocationX == 1 && changeOfLocationY == 1) {
                    return true;
                } else throw new
                        InvalidMoveException();
        }




        }





}




