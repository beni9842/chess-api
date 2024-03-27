package chess.game;

import chess.api.GameID;
import chess.api.GamePool;
import chess.events.MovePieceEvent;
import chess.observers.CheckObserver;
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
    private final int halfmove;
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
        g.attach(new CheckObserver());
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
    @Override
    public void attach(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void broadcast(Event e) {
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
        Move move = new Move(moveString);
        try {
            move.execute(board);
        } catch (Exception e) {
            return "Move unsuccessful";
        }
        broadcast(new MovePieceEvent(moveString, turn));
        return "Move successful";
    }
}
