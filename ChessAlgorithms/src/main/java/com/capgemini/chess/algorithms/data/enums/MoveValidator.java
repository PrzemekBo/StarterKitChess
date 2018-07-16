package com.capgemini.chess.algorithms.data.enums;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class MoveValidator {


    public boolean valideteMove(Piece piece, Coordinate fromPlace, Coordinate toPlace) throws InvalidMoveException {

        PieceType pieceType = piece.getType();


        switch (pieceType) {
            case PAWN:
                if (piece.getColor().equals(Color.WHITE)) {
                    if (Math.abs(fromPlace.getX() - toPlace.getX()) == 0 && fromPlace.getY() - toPlace.getY() == -1) {
                        return true;
                    } else if (fromPlace.getY() == 1 && fromPlace.getX() - toPlace.getX() == 0 && (fromPlace.getY() - toPlace.getY() == -1 || fromPlace.getY() - toPlace.getY() == -2)) {
                        return true;
                    } else {
                        throw new InvalidMoveException();
                    }
                } else {
                    if (Math.abs(fromPlace.getX() - toPlace.getX()) == 0 && fromPlace.getY() - toPlace.getY() == 1) {
                        return true;
                    } else if (fromPlace.getY() == 6 && fromPlace.getX() - toPlace.getX() == 0 && (fromPlace.getY() - toPlace.getY() == 1 || fromPlace.getY() - toPlace.getY() == 2)) {
                        return true;
                    } else {
                        throw new InvalidMoveException();
                    }
                }

            case KING:
                //if (Math.abs(fromPlace.getX()-toPlace.getY()==1&&fromPlace.getX()-fromPlace.getX()==0){
                if (Math.abs(fromPlace.getX() - toPlace.getX()) == 0 && Math.abs(fromPlace.getY() - toPlace.getY()) == 1) {
                    return true;

                } else if (Math.abs(fromPlace.getX() - toPlace.getX()) == 1 && Math.abs(fromPlace.getY() - toPlace.getY()) == 0) {
                    return true;
                } else if (Math.abs(fromPlace.getX() - toPlace.getX()) == 1 && Math.abs(fromPlace.getY() - toPlace.getY()) == 1) {
                    return true;
                } else {
                    throw new InvalidMoveException();
                }
            case QUEEN:
                if (Math.abs(fromPlace.getX() - toPlace.getX()) == 0 && Math.abs(fromPlace.getY() - toPlace.getY()) >= 1) {
                    return true;

                } else if (Math.abs(fromPlace.getX() - toPlace.getX()) >= 1 && Math.abs(fromPlace.getY() - toPlace.getY()) == 0) {
                    return true;
                } else if (Math.abs(fromPlace.getX() - toPlace.getX()) >= 1 && Math.abs(fromPlace.getY() - toPlace.getY()) >= 1) {
                    return true;
                } else {
                    throw new InvalidMoveException();
                }
            case KNIGHT:
                if (Math.abs(fromPlace.getX() - toPlace.getX()) >= 1 && Math.abs(fromPlace.getY() - toPlace.getY()) >= 1){
                    return true;
                }else {
                    throw new InvalidMoveException();
                }
            case ROOK:
                if ( Math.abs(fromPlace.getX() - toPlace.getX()) == 0 && Math.abs(fromPlace.getY() - toPlace.getY()) >= 1) {
                return true;

            } else if (Math.abs(fromPlace.getX() - toPlace.getX()) >= 1 && Math.abs(fromPlace.getY() - toPlace.getY()) == 0) {
                return true;
            }else {
                    throw new InvalidMoveException();

                }
            case BISHOP:
                if (Math.abs(fromPlace.getX()-toPlace.getX())==-1&&Math.abs(fromPlace.getY()-toPlace.getY())==-2){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==-2&&Math.abs(fromPlace.getY()-toPlace.getY())==1){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==2&&Math.abs(fromPlace.getY()-toPlace.getY())==1){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==-2&&Math.abs(fromPlace.getY()-toPlace.getY())==-1){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==-1&&Math.abs(fromPlace.getY()-toPlace.getY())==2){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==-2&&Math.abs(fromPlace.getY()-toPlace.getY())==1){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==-2&&Math.abs(fromPlace.getY()-toPlace.getY())==1){
                    return true;
                }else if (Math.abs(fromPlace.getX()-toPlace.getX())==-2&&Math.abs(fromPlace.getY()-toPlace.getY())==1){
                    return true;

                }

        }
        throw new InvalidMoveException();

        }


}












