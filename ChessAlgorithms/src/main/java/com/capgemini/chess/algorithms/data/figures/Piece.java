package com.capgemini.chess.algorithms.data.figures;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;

public abstract class Piece {

    private Color color;
    private Coordinate coordinate;


    public Piece(Color color, Coordinate coordinate) {
        this.color = color;
        this.coordinate = coordinate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
