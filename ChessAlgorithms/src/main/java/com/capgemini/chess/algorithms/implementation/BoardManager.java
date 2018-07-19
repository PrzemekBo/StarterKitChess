package com.capgemini.chess.algorithms.implementation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.*;


/**
 * Class for managing of basic operations on the Chess Board.
 *
 * @author Michal Bejm
 */
public class BoardManager {

    private Board board = new Board();



    public BoardManager() {
        initBoard();
    }

    public BoardManager(List<Move> moves) {
        initBoard();
        for (Move move : moves) {
            addMove(move);
        }
    }

    public BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Getter for generated board
     *
     * @return board
     */


    public Board getBoard() {
        return this.board;
    }

    /**
     * Performs move of the chess piece on the chess board from one field to
     * another.
     *
     * @param from coordinates of 'from' field
     * @param to   coordinates of 'to' field
     * @return move object which includes moved piece and move type
     * @throws InvalidMoveException in case move is not valid
     */
    public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {

        Move move = validateMove(from, to);

        addMove(move);

        return move;
    }

    /**
     * Calculates state of the chess board.
     *
     * @return state of the chess board
     */
    public BoardState updateBoardState() {

        Color nextMoveColor = calculateNextMoveColor();

        boolean isKingInCheck = isKingInCheck(nextMoveColor);
        boolean isAnyMoveValid = isAnyMoveValid(nextMoveColor);

        BoardState boardState;
        if (isKingInCheck) {
            if (isAnyMoveValid) {
                boardState = BoardState.CHECK;
            } else {
                boardState = BoardState.CHECK_MATE;
            }
        } else {
            if (isAnyMoveValid) {
                boardState = BoardState.REGULAR;
            } else {
                boardState = BoardState.STALE_MATE;
            }
        }
        this.board.setState(boardState);
        return boardState;

    }
    /**
     * Checks threefold repetition rule (one of the conditions to end the chess
     * game with a draw).
     *
     * @return true if current state repeated at list two times, false otherwise
     */
    public boolean checkThreefoldRepetitionRule() {

        // there is no need to check moves that where before last capture/en
        // passant/castling
        int lastNonAttackMoveIndex = findLastNonAttackMoveIndex();
        List<Move> omittedMoves = this.board.getMoveHistory().subList(0, lastNonAttackMoveIndex);
        BoardManager simulatedBoardManager = new BoardManager(omittedMoves);

        int counter = 0;
        for (int i = lastNonAttackMoveIndex; i < this.board.getMoveHistory().size(); i++) {
            Move moveToAdd = this.board.getMoveHistory().get(i);
            simulatedBoardManager.addMove(moveToAdd);
            boolean areBoardsEqual = Arrays.deepEquals(this.board.getPieces(),
                    simulatedBoardManager.getBoard().getPieces());
            if (areBoardsEqual) {
                counter++;
            }
        }

        return counter >= 2;
    }

    /**
     * Checks 50-move rule (one of the conditions to end the chess game with a
     * draw).
     *
     * @return true if no pawn was moved or not capture was performed during
     * last 50 moves, false otherwise
     */
    public boolean checkFiftyMoveRule() {

        // for this purpose a "move" consists of a player completing his turn
        // followed by his opponent completing his turn
        if (this.board.getMoveHistory().size() < 100) {
            return false;
        }

        for (int i = this.board.getMoveHistory().size() - 1; i >= this.board.getMoveHistory().size() - 100; i--) {
            Move currentMove = this.board.getMoveHistory().get(i);
            PieceType currentPieceType = currentMove.getMovedPiece().getType();
            if (currentMove.getType() != MoveType.ATTACK || currentPieceType == PieceType.PAWN) {
                return false;
            }
        }

        return true;
    }

    // PRIVATE

    private void initBoard() {

        this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
        this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
        this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
        this.board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
        this.board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
        this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
        this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
        this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

        for (int x = 0; x < Board.SIZE; x++) {
            this.board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
        }

        this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
        this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
        this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
        this.board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
        this.board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
        this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
        this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
        this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

        for (int x = 0; x < Board.SIZE; x++) {
            this.board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
        }
    }

    private void addMove(Move move) {

        addRegularMove(move);

        if (move.getType() == MoveType.CASTLING) {
            addCastling(move);
        } else if (move.getType() == MoveType.EN_PASSANT) {
            addEnPassant(move);
        }

        this.board.getMoveHistory().add(move);
    }

    private void addRegularMove(Move move) {
        Piece movedPiece = this.board.getPieceAt(move.getFrom());
        this.board.setPieceAt(null, move.getFrom());
        this.board.setPieceAt(movedPiece, move.getTo());

        performPromotion(move, movedPiece);
    }

    private void performPromotion(Move move, Piece movedPiece) {
        if (movedPiece == Piece.WHITE_PAWN && move.getTo().getY() == (Board.SIZE - 1)) {
            this.board.setPieceAt(Piece.WHITE_QUEEN, move.getTo());
        }
        if (movedPiece == Piece.BLACK_PAWN && move.getTo().getY() == 0) {
            this.board.setPieceAt(Piece.BLACK_QUEEN, move.getTo());
        }
    }

    private void addCastling(Move move) {
        if (move.getFrom().getX() > move.getTo().getX()) {
            Piece rook = this.board.getPieceAt(new Coordinate(0, move.getFrom().getY()));
            this.board.setPieceAt(null, new Coordinate(0, move.getFrom().getY()));
            this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() + 1, move.getTo().getY()));
        } else {
            Piece rook = this.board.getPieceAt(new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
            this.board.setPieceAt(null, new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
            this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() - 1, move.getTo().getY()));
        }
    }

    private void addEnPassant(Move move) {
        Move lastMove = this.board.getMoveHistory().get(this.board.getMoveHistory().size() - 1);
        this.board.setPieceAt(null, lastMove.getTo());
    }

    private Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException, KingInCheckException {

      //  Move performedMove = new Move();
        valideteIfPieceIsOnBoard(from, to);
        validateIfSpotIsNotNull(from);
        Piece piece = validateIfThePawnIsMoving(from, to);
        Color nextMoveColor = calculateNextMoveColor();

        MoveType moveType = setMoveType(to, nextMoveColor);
        Validator validator = movedTypeValidator(piece);



        validateIfPieceCanMoveTo(piece, from, to, moveType);

        Move move = makeAMove(piece, from, to, moveType);

        validateMovePath(piece, from, to, validator);

        Move myMove = new Move();
        myMove.setFrom(from);
        myMove.setTo(to);
        myMove.setMovedPiece(getBoard().getPieceAt(from));
        if (calculateNextMoveColor() != board.getPieceAt(from).getColor()) {
            throw new InvalidMoveException();
        }


      //  Validator vali = new Validator();





/*
        PieceTypeFactory pieceTypeFactory = new PieceTypeFactory();
        Factory validator = pieceTypeFactory.getFactoryvalidation(this.board, from);
        MoveType moveType = setMoveType(performedMove, from, to);
        validator.moveValidation(piece, from, to, moveType);
        validator.validateMovePath(from, to,this.board);
*/

       // MoveType moveType = setMoveType(to, colorOfNextPlayer);




        return null;
    }

    private boolean isKingInCheck(Color kingColor) {
        Coordinate cordOfTheKing = null;
        try {
            cordOfTheKing = checkWhereIsMyKing(kingColor);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        this.board.getMoveHistory().add(null);
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                Coordinate cord = new Coordinate(x, y);
                if (this.board.getPieceAt(cord) != null && this.board.getPieceAt(cord).getColor() != kingColor) {
                    try {
                        if (validateMove(cord, cordOfTheKing).getType() == MoveType.CAPTURE) {
                            this.board.getMoveHistory().remove(this.board.getMoveHistory().size() - 1);
                            return true;
                        }
                    } catch (InvalidMoveException e) {
                    }
                }
            }
        }
       // this.board.getMoveHistory().remove(this.board.getMoveHistory().size() - 1);
        return false;
    }


    private boolean isAnyMoveValid(Color nextMoveColor){
        return false;
    }

    private Coordinate checkWhereIsMyKing(Color kingColor) throws InvalidMoveException {

        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                Coordinate cord = new Coordinate(x, y);
                if (this.board.getPieceAt(cord) != null && this.board.getPieceAt(cord).getType() == PieceType.KING
                        && this.board.getPieceAt(cord).getColor() == kingColor) {
                    return cord;
                }
            }
        }
        throw new InvalidMoveException();
    }


    public static void valideteIfPieceIsOnBoard(Coordinate fromPlace, Coordinate toPlace) throws InvalidMoveException {

        if (toPlace.getY() >= Board.SIZE || toPlace.getY() < 0 || fromPlace.getX() >= Board.SIZE || fromPlace.getX() < 0)
            throw new InvalidMoveException();
    }



    private Piece validateIfThePawnIsMoving(Coordinate from, Coordinate to)throws InvalidMoveException {
        Piece piece = board.getPieceAt(from);
        if (from.getX() != to.getX() || from.getY() != to.getY()) {
            return piece;
        } else {
            throw new InvalidMoveException();
        }
    }



    private MoveType setMoveType(Coordinate to, Color nextMoveColor) {
        if (board.getPieceAt(to) != null && board.getPieceAt(to).getColor() != nextMoveColor) {
            return MoveType.CAPTURE;
        }

        return MoveType.ATTACK;
    }




    private void validateIfSpotIsNotNull(Coordinate from) throws InvalidMoveException {
        if (this.board.getPieceAt(from)==null)
            throw new InvalidMoveException();
    }



    private Validator movedTypeValidator(Piece piece) {
        if (piece.getType()==PieceType.BISHOP) {
            return new Validator(new BishopFactory());
        } else if (piece.getType()==PieceType.ROOK) {
            return new Validator(new RookFactory());
        } else if (piece.getType()==PieceType.QUEEN) {
            return new Validator(new QueenFactory());
        } else if (piece.getType()==PieceType.KNIGHT) {
            return new Validator(new KnightFactory());
        } else if (piece.getType()==PieceType.KING) {
            return new Validator(new KingFactory());
        } else {
            return new Validator(new PawnFactory());
        }
    }

    private void validateIfPieceCanMoveTo(Piece piece, Coordinate from, Coordinate to, MoveType moveType)throws InvalidMoveException {
        Validator validator = movedTypeValidator(piece);
        if (!validator.moveValidation(piece, from, to, moveType)) {
            throw new InvalidMoveException();
        }
    }

    private Move makeAMove(Piece piece, Coordinate from, Coordinate to, MoveType moveType) {
        Move move = new Move();
        move.setFrom(from);
        move.setTo(to);
        move.setMovedPiece(piece);
        move.setType(moveType);
        return move;
    }





    private void validateMovePath(Piece piece, Coordinate from, Coordinate to, Validator context)
            throws InvalidMoveException {
        if (!piece.getType().equals(PieceType.KNIGHT)) {
            context.validateMovePath(from, to, board);
        }
    }

    private Color calculateNextMoveColor() {
        if (this.board.getMoveHistory().size() % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    private int findLastNonAttackMoveIndex() {
        int counter = 0;
        int lastNonAttackMoveIndex = 0;

        for (Move move : this.board.getMoveHistory()) {
            if (move.getType() != MoveType.ATTACK) {
                lastNonAttackMoveIndex = counter;
            }
            counter++;
        }

        return lastNonAttackMoveIndex;
    }

}
