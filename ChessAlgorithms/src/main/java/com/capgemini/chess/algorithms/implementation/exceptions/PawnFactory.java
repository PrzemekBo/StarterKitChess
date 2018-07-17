package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import sun.rmi.server.InactiveGroupException;

public class PawnFactory implements Factory {
    @Override
    public boolean moveValidation(Piece piece, Coordinate fromPlace, Coordinate toPlace, MoveType moveType) throws InvalidMoveException {

        PieceType pieceType = piece.getType();

        int changeOfLocationX = Math.abs(fromPlace.getX() - toPlace.getX());
        int changeOfLocationY = Math.abs(fromPlace.getY() - toPlace.getY());
        switch (pieceType) {

            case PAWN:
                if (piece.getColor().equals(Color.WHITE)) {
                    if (moveType == MoveType.CAPTURE) {
                    /*    if (ChangeOfLocationX == -1 && ChangeOfLocationY == -1) {
                            return true;*/
                        if (changeOfLocationX == 1 && changeOfLocationY == -1) {
                            return true;

                        } else if (changeOfLocationX == 1 && changeOfLocationY == -1) {
                            return true;
                        } else
                            throw new InvalidMoveException();

                    } else if (moveType.equals(MoveType.ATTACK)) {
                        if (fromPlace.getY() == 1 && changeOfLocationX == 0 && (changeOfLocationY == -1 || changeOfLocationY == -2)) {
                            return true;

                        } else if (changeOfLocationX == 0 && changeOfLocationY == -1) {
                            return true;
                        } else throw new InvalidMoveException();
                    }

                }else {
                    if (moveType == MoveType.CAPTURE) {
                        if (fromPlace.getY() == 6 && changeOfLocationX == 0 && changeOfLocationY == 1 || changeOfLocationY == 2) {
                            return true;
                        } else if (changeOfLocationX == 1 && changeOfLocationY == 0) {
                            return true;
                        } else throw new InvalidMoveException();
                    }
                }
                        default:
                            throw new InvalidMoveException();
                    }

        }

    }



