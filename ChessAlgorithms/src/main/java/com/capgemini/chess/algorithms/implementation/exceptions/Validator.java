package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;

public class Validator {

   // Board board= new Board();

    BoardManager boardManager;

    public Validator(BoardManager boardManager){
        this.boardManager=boardManager;
    }


    //TODO nie jestem pewny tej metody
    public static void checkIfIsOnBoard(Coordinate fromPlace, Coordinate toPlace) throws InvalidMoveException {
     /*   if (fromPlace.getY() >= 8 || fromPlace.getX() >= 8 || toPlace.getY() >= 8 || toPlace.getX() >= 8 ||
                fromPlace.getY() < 0 || fromPlace.getX() < 0 || toPlace.getY() < 0 || toPlace.getX() < 0) {
            throw new InvalidMoveException();
        } else {
            return true;
        }*/
        if (fromPlace.getY() >= Board.SIZE || fromPlace.getY() < 0 || fromPlace.getX() >= Board.SIZE || fromPlace.getX() < 0)
            throw new InvalidMoveException();

    }


    public boolean checkTheOccupationOfTheField(Coordinate coordinate) throws InvalidMoveException {


        Board board=  boardManager.getBoard();
        Piece piece = board.getPieceAt(coordinate);

        // sprawdzenie czy to nie jest puste pole
        if (piece == null) {
            throw new InvalidMoveException();
        }


        return true;
    }
}
