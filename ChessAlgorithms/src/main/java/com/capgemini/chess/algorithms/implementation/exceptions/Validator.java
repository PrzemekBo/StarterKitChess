package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;

public class Validator {


    //TODO nie jestem pewny tej metody
    public boolean checkIfIsOnBoard(Coordinate fromPlace, Coordinate toPlace) throws InvalidMoveException {
        if (fromPlace.getY() >= 8 || fromPlace.getX() >= 8 || toPlace.getY() >= 8 || toPlace.getX() >= 8 ||
                fromPlace.getY() < 0 || fromPlace.getX() < 0 || toPlace.getY() < 0 || toPlace.getX() < 0) {
            throw new InvalidMoveException();
        } else {
            return true;
        }
    }

    public boolean checkTheOccupationOfTheField(Coordinate coordinate) throws InvalidMoveException {
        BoardManager boardManager=new BoardManager();
        boardManager.getBoard();
        Board board= new Board();
        Piece piece = board.getPieceAt(coordinate);

        // sprawdzenie czy to nie jest puste pole
        if (piece == null) {
            throw new InvalidMoveException();
        }


        return true;
    }
}
