package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public abstract class GameValidator {

    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private int changeMoveX;
    private int changeMoveY;
    private Board board;


    public abstract boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType);

    public boolean validateMovePath(Coordinate fromPlace, Coordinate toPlace, Board board) throws InvalidMoveException {
        boolean forwardMovement = toPlace.getY() > fromPlace.getY();
        boolean backwardMovement = toPlace.getY() < fromPlace.getY();
        boolean rightMovement = toPlace.getX() > fromPlace.getX();
        boolean leftMovement = toPlace.getX() < fromPlace.getX();


        this.fromX = fromPlace.getX();
        this.fromY = fromPlace.getY();
        this.toX = toPlace.getX();
        this.toY = toPlace.getY();
        this.changeMoveX = fromX - toX;
        this.changeMoveY = fromY - toY;
        this.board = board;


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
        for (int j = fromY + 1; j < toX; j++) {
            Coordinate coordinate = new Coordinate(fromX, j);
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noClashBackwardMovement() {
        for (int j = fromY - 1; j > toY; j--) {
            Coordinate coordinate = new Coordinate(fromX, j);
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noClashRightMovement() {
        for (int i = fromX + 1; i < toX; i++) {
            Coordinate coordinate = new Coordinate(i, fromY);
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noClshLeftMovement() throws InvalidMoveException {
        for (int i = fromX - 1; i > fromX; i--) {
            Coordinate coordinate = new Coordinate(i, fromX);
            if (board.getPieceAt(coordinate) != null) {
                return false;
            }
        }


        if (fromX > toX && fromY > toY) {
            for (int x = fromX + 1, y = fromY + 1; x > toX && y > toY; x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }
        }
        if (fromX > toX && fromY < toY) {
            for (int x = fromX + 1, y = fromY + 1; x > toX && y < toY; x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }
        }


        if (fromX < toX && fromY < toY) {
            for (int x = fromX + 1, y = fromY + 1; x < toX && y < toY; x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }

        }
        if (fromX < toX && fromY > toY) {
            for (int x = fromX + 1, y = fromY + 1; x < fromX && y > toY; x++, y++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (board.getPieceAt(coordinate) != null) {
                    throw new InvalidMoveException();
                }
            }
        }


        return true;
    }


}