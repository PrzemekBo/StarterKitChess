package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;


public interface Factory {

     boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType)
            throws InvalidMoveException ;


}
