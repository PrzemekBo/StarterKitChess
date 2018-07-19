package com.capgemini.chess.algorithms.implementation.figures;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.implementation.GameValidator;

public class Queen extends GameValidator {
    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) {


        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());

        if (fromPlace.getX() - toPlace.getX() > 0 && changeOfLocationY == 0) {
            return true;

        } else if (fromPlace.getX() - toPlace.getX() == 0 && changeOfLocationY > 0) {
            return true;
        } else if (changeOfLocationX == changeOfLocationX) {
            return true;
        } else {
            return false;
        }
    }
}
