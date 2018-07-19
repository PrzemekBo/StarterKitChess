package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class RookFactory extends GameValidator {


    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) {


        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());


        if (changeOfLocationX > 0 && changeOfLocationY == 0) {
            return true;
        } else if (changeOfLocationX == 0 && changeOfLocationY > 0) {
            return true;
        } else {
            return false;
        }
    }

/*    @Override
    public boolean validateMovePath(Coordinate fromPlace, Coordinate toPlace, Board board) throws InvalidMoveException {
        boolean forwardMovement = toPlace.getY() > fromPlace.getY();
        boolean backwardMovement = toPlace.getY() < fromPlace.getY();
        boolean rightMovement = toPlace.getX() > fromPlace.getX();
        boolean leftMovement = toPlace.getX() < fromPlace.getX();

        if (Math.abs(toPlace.getX() - fromPlace.getX()) == 0 && Math.abs(toPlace.getY() - fromPlace.getY()) != 0) {
            if (forwardMovement && this.noClashForwardMovement()) {
                return true;
            } else if (backwardMovement && this.noClashBackwardMovement()) {
                return true;
            } else
                return false;

        } else {
            if (Math.abs(toPlace.getY() - fromPlace.getY()) == 0 && Math.abs(toPlace.getX() - fromPlace.getX()) != 0) {
                if (rightMovement && this.noClashRightMovement()) {
                    return true;
                } else if (leftMovement && this.noClshLeftMovement()) {
                    return true;
                } else
                    return false;
            }

            throw new InvalidMoveException();
        }
    }
    private boolean noClashForwardMovement() {
        for (int j = fromPlace.getY() + 1; j < toPlace.getY(); j++) {
            Coordinate coordinate = new Coordinate(fromPlace.getX(), j);
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noClashBackwardMovement() {
        for (int j = fromPlace.getY() - 1; j > toPlace.getY(); j--) {
            Coordinate coordinate = new Coordinate(fromPlace.getX(), j);
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noClashRightMovement() {
        for (int i = fromPlace.getX() + 1; i < toPlace.getX(); i++) {
            Coordinate coordinate = new Coordinate(i, fromPlace.getY());
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noClshLeftMovement() {
        for (int i = fromPlace.getX() - 1; i > toPlace.getX(); i--) {
            Coordinate coordinate = new Coordinate(i, fromPlace.getY());
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }*/
}
