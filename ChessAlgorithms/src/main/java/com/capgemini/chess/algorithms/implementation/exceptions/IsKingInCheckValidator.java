package com.capgemini.chess.algorithms.implementation.exceptions;

import com.capgemini.chess.algorithms.data.Coordinate;

import java.util.Map;

public class IsKingInCheckValidator {

    //TODO nie jestem pewnz co do metodz w usunietych jets dobra,chyba
    public static <T, E> Coordinate checkWhereIsMyKing(Map<T, E> myMap, E valueKing) {

        for (Map.Entry<T, E> entry : myMap.entrySet()) {
            if (entry.getValue().equals(valueKing)) {
                return (Coordinate) entry.getKey();
            }
        }
        return null;
    }



}
