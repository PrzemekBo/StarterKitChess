package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;

public class KnightFactory implements Factory {


    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) throws InvalidMoveException {

        PieceType pieceType = piece.getType();

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());


        //TODO upewnij sie mcz tyle metod i czy dobre zakresy

        switch (pieceType) {
            case KNIGHT:
                if (changeOfLocationX == 1 && changeOfLocationY == 2) {
                    return true;
                } else if (changeOfLocationX == 2 && changeOfLocationY == 1) {
                    return true;
                } else {
                    throw new InvalidMoveException();
                }
        }



        throw new InvalidMoveException();
    }





}
