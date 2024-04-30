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
    public PieceColor turn;
    private int fullmove;
    private int halfmove;

    private Game(GameBuilder builder) {
        id = builder.id;
        board = builder.board;
        observers = builder.observers;
        turn = builder.turn;
        fullmove = builder.fullmove;
        halfmove = builder.halfmove;
    }

    public static Game NewGame() {
        return new GameBuilder().build();
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
        observers.add(newObserver);
    }

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

    public void toggleTurn() {
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

    public static class GameBuilder {
        private int id;
        private Board board;
        private List<Observer> observers = new ArrayList<>();
        private PieceColor turn;
        private int fullmove;
        private int halfmove;

        public GameBuilder() {
            id = GameID.GetNewID();
            board = Board.NewBoard();
            turn = PieceColor.White;
            fullmove = 1;
            halfmove = 0;
        }

        public GameBuilder withBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameBuilder withTurn(PieceColor turn) {
            this.turn = turn;
            return this;
        }

        // Add other setter methods as needed

        public Game build() {
            return new Game(this);
        }
    }
}
