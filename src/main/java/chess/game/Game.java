package chess.game;

import chess.api.GameID;
import chess.events.Event;
import chess.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameInterface {
    private final int id;
    private final Board board;
    private final List<Observer> observers;
    private PieceColor turn;
    private int fullmove;
    private int halfmove;
    private Game(Board gameBoard, PieceColor currTurn, int halfmoveClock, int fullmoveCounter) {
        id = GameID.GetNewID();
        board = gameBoard;
        turn = currTurn;
        fullmove = fullmoveCounter;
        halfmove = halfmoveClock;
        observers = new ArrayList<>();
    }

    public static Game NewGame() {
        Game g = new Game(Board.NewBoard(), PieceColor.White, 0, 1);
        return g;
    }
    public String castleOptions() {
        /*
        TODO: implement
         */
        return "-";
    }
    public String enPassantTargets() {
        /*
        TODO: implement
         */
        return "-";
    }
    public String activeColor() {
        return switch (turn) {
            case White -> "w";
            case Black -> "b";
            default -> "-";
        };
    }

    public void attach(Observer newObserver) {
        /*
        attaches a new Observer
        */
        observers.add(newObserver);
    }


    public void broadcast(Event e) {
        /*
        Broadcasts an event to the observers that are attached to the Game
        */
        for (Observer o : observers) {
            o.update(e);
        }
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getState() {
        return board.toString() + " "
                + activeColor() + " "
                + castleOptions() + " "
                + enPassantTargets() + " "
                + halfmove + " "
                + fullmove;

    }
    void toggleTurn() {
        if (turn == PieceColor.White) {
            turn = PieceColor.Black;
        } else if (turn == PieceColor.Black) {
            turn = PieceColor.White;
        } else {
            throw new RuntimeException("Illegal turn color encountered.");
        }
    }
    @Override
    public String postMove(String moveString) { // moveString in Universal Chess Interface
        for (Move legalMove : LegalMoves.GetAll(board, turn)) {
            if (legalMove.toUCI().equals(moveString)) {
                Piece capturedPiece, movingPiece;
                try {
                    capturedPiece = legalMove.capturedPiece(board);
                    movingPiece = legalMove.movingPiece(board);
                    legalMove.execute(board);
                } catch (Exception e) {
                    return "Move unsuccessful";
                }
                Board moveResult = board.copy();
                if (movingPiece.getType() == PieceType.Pawn || !capturedPiece.equals(Pieces.NoPiece())) {
                    halfmove = 0;
                } else {
                    halfmove++;
                }
                if (turn == PieceColor.Black) {
                    fullmove++;
                }
                toggleTurn();
                // broadcast(new MovePieceEvent(moveString, turn, moveResult));
                return "Move successful";
            }
        }
        return "Illegal move";
    }
}
