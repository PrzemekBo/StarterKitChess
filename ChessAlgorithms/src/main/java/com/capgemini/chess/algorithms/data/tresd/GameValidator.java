package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;

public abstract class GameValidator {

    private Board board;
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private int changeX;
    private int changeY;
    //private Board board;

    public abstract boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType);

    public boolean validateMovePath(Coordinate from, Coordinate to, Board board)
            throws InvalidMoveException {
        this.fromX = from.getX();
        this.fromY = from.getY();
        this.toX = to.getX();
        this.toY = to.getY();
        this.changeX = fromX - toX;
        this.changeY = fromY - toY;
        this.board = board;

        if (fromX != toX && fromY != toY) {
            diagonalMove();
        }

        else if (changeX != 0 && changeY == 0) {
            horizontalMove();
        }

        else if (changeX == 0 && changeY != 0) {
            verticalMove();
        }

        return true;
    }

    private void horizontalMove() throws InvalidMoveException {
        int change = changeX > 0 ? -1 : 1;

        for (int x = fromX + change; x != toX; x = x + change) {
            Coordinate coordinate = new Coordinate(x, toY);
            if (this.board.getPieceAt(coordinate) != null) {
                throw new InvalidMoveException();
            }
        }
    }

    private void verticalMove() throws InvalidMoveException {
        int change = changeY > 0 ? -1 : 1;

        for (int y = fromY + change; y != toY; y = y + change) {
            Coordinate coordinate = new Coordinate(toX, y);
            if (this.board.getPieceAt(coordinate) != null) {
                throw new InvalidMoveException();
            }
        }
    }

    private void diagonalMove() throws InvalidMoveException {
        changeX = changeX > 0 ? -1 : 1;
        changeY = changeY > 0 ? -1 : 1;
        for (int x = fromX + changeX, y = fromY + changeY; x != toX && y != toY; x = x + changeX, y = y + changeY) {
            Coordinate coordinate = new Coordinate(x, y);
            if(this.board.getPieceAt(coordinate) != null) {
                throw new InvalidMoveException();
            }
        }
    }
}


    /*public boolean validateMovePath(Coordinate fromPlace, Coordinate toPlace, Board board) throws InvalidMoveException {

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

        private boolean noClshLeftMovement() throws InvalidMoveException {
            for (int i = fromPlace.getX() - 1; i > toPlace.getX(); i--) {
                Coordinate coordinate = new Coordinate(i, fromPlace.getY());
                if (board.getPieceAt(coordinate) != null) {
                    return false;
                }
            }



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
*/