package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class KnightFactory   {


    //TODO czy knight ma to implementowac
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) {

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());

                if (changeOfLocationX == 1 && changeOfLocationY == 2) {
                    return true;
                } else if (changeOfLocationX == 2 && changeOfLocationY == 1) {
                    return true;
                } else {
                    return true;
                }

    }



}
