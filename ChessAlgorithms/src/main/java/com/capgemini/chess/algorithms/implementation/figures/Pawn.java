package com.capgemini.chess.algorithms.implementation.figures;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.implementation.GameValidator;


public class Pawn extends GameValidator {

    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) {

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = fromPlace.getY() - toPlace.getY();

        int startingYPositionOfWhitePawn = 1;
        int startingYPostitionOfBlackPawn = 6;

        if (piece.getColor().equals(Color.WHITE)) {
            if (moveType == MoveType.ATTACK) {
                if (changeOfLocationX == 0 && changeOfLocationY == -1) {
                    return true;
                } else if (fromPlace.getY() == startingYPositionOfWhitePawn && changeOfLocationX == 0
                        && (changeOfLocationY == -1 || changeOfLocationY == -2)) {
                    return true;
                }
                return false;

            } else if (moveType == MoveType.CAPTURE) {
                if (changeOfLocationX == 1 && changeOfLocationY == -1) {
                    return true;
                }
                return false;
            }
        } else {
            if (moveType == MoveType.ATTACK) {
                if (changeOfLocationX == 0 && changeOfLocationY == 1) {
                    return true;
                } else if (fromPlace.getY() == startingYPostitionOfBlackPawn && changeOfLocationX == 0
                        && (changeOfLocationY == 1 || changeOfLocationY == 2)) {
                    return true;
                }
                return false;

            } else if (moveType == MoveType.CAPTURE) {
                if (changeOfLocationX == 1 && changeOfLocationY == 1) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}



