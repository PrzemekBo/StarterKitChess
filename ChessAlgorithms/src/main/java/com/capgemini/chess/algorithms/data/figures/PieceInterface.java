package com.capgemini.chess.algorithms.data.figures;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

import java.util.List;

public interface PieceInterface {

    Color getColor();

    PieceType getType();

    Move validateMove(Piece[][] state, Coordinate from, Coordinate to) throws InvalidMoveException;


    //given

    //when

    //then



    //given

    //when

    //then


    //test


}

