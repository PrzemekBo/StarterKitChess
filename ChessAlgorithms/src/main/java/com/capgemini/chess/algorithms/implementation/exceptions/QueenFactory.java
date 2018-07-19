package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class QueenFactory extends GameValidator {
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
/*

    @Override
    public boolean validateMovePath(Coordinate fromPlace, Coordinate toPlace, Board board) throws InvalidMoveException {


        RookFactory rookFactory =new RookFactory();
        BishopFactory bishopFactory= new BishopFactory();

        rookFactory.validateMovePath(fromPlace,toPlace,board);
        try {
            rookFactory.validateMovePath(fromPlace,toPlace,board);

        }catch (InvalidMoveException a){
            bishopFactory.validateMovePath(fromPlace,toPlace,board);


        }


        return true;
    }

}
*/
