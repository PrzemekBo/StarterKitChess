package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;


public class PawnFactory implements Factory  {

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

    @Override
    public boolean validateMovePath(Coordinate fromPlace, Coordinate toPlace, Board board) throws InvalidMoveException {
        return false;
    }
}


/*
    @Override
    public boolean validateMovePath(Coordinate fromPlace, Coordinate toPlace, Board board) throws InvalidMoveException {


        if (fromPlace.getX()>toPlace.getX()&&fromPlace.getY()>toPlace.getY()) {
            for (int x = fromPlace.getX() + 1, y = fromPlace.getY() + 1; x > toPlace.getX() && y > toPlace.getY(); x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }
        }
        if (fromPlace.getX()>toPlace.getX()&&fromPlace.getY()<toPlace.getY()) {
            for (int x = fromPlace.getX() + 1, y = fromPlace.getY() + 1; x > toPlace.getX() && y < toPlace.getY(); x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }
        }


        if (fromPlace.getX()<toPlace.getX()&&fromPlace.getY()<toPlace.getY()){
            for (int x=fromPlace.getX()+1,y=fromPlace.getY()+1;x<toPlace.getX()&&y<toPlace.getY();x++,y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw  new InvalidMoveException();
                }
            }

        }
        if (fromPlace.getX()<toPlace.getX()&&fromPlace.getY()>toPlace.getY()) {
            for (int x = fromPlace.getX() + 1, y = fromPlace.getY() + 1; x < toPlace.getX() && y > toPlace.getY(); x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }
        }



        return true;
    }
*/

