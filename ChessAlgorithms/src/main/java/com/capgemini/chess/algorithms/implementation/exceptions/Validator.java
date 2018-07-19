package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;

public class Validator {

        private GameValidator gameValidator;

        public Validator(GameValidator gameValidator){
            this.gameValidator = gameValidator;
        }

        public boolean moveValidation(Piece piece, Coordinate from, Coordinate to, MoveType moveType){
            return gameValidator.moveValidation(piece, from, to, moveType);
        }

        public boolean validateMovePath(Coordinate from, Coordinate to, Board board) throws InvalidMoveException{
            return gameValidator.validateMovePath(from, to, board);
        }
}
