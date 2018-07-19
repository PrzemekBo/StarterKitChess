package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class BishopFactory  extends GameValidator {
    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType)  {

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());

                if (changeOfLocationX == changeOfLocationY) {
                    return true;
                }else
                    return false;

    }

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


}

