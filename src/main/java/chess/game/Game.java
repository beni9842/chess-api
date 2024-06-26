package chess.game;

import chess.moves.LegalMoves;
import chess.moves.Move;
import chess.moves.PawnDouble;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Game implements GameInterface {
    private final int id;
    private Board board;
    private PieceColor activeColor;
    private int fullmoveCounter;
    private int halfmoveCounter;
    private boolean K;
    private boolean Q;
    private boolean k;
    private boolean q;
    private final List<Move> moveRecord;

    public Game(int gameID, Board gameBoard, PieceColor currColor, int nHalfmoves, int nFullmoves, ArrayList<Move> movesMade) {
        id = gameID;
        board = gameBoard;
        activeColor = currColor;
        fullmoveCounter = nFullmoves;
        halfmoveCounter = nHalfmoves;
        moveRecord = movesMade;
        K = true;
        Q = true;
        k = true;
        q = true;
    }
    public static Game NewGame() {
        return new Game(GameID.GetNewID(), Board.NewBoard(), PieceColor.White, 0, 1, new ArrayList<>());
    }
    public static Game BuildFromMoveRecord(List<Move> moves) {
        Game g = NewGame();
        for (Move m : moves) {
            g.executeMove(m);
        }
        return g;
    }
    public static Game BuildFromFEN(String fenString) {
        //TODO
        return null;
    }
    public void toggleActiveColor() {
        if (activeColor == PieceColor.White) {
            activeColor = PieceColor.Black;
        } else if (activeColor == PieceColor.Black) {
            activeColor = PieceColor.White;
        }
    }
    public PieceColor getActiveColor() {
        return activeColor;
    }
    public String activeColorString() {
        return switch (activeColor) {
            case White -> "w";
            case Black -> "b";
            default -> "-";
        };
    }
    public String castleString() {
        StringBuilder castle = new StringBuilder();
        if (K) {
            castle.append("K");
        }
        if (Q) {
            castle.append("Q");
        }
        if (k) {
            castle.append("k");
        }
        if (q) {
            castle.append("q");
        }
        if (castle.isEmpty()) {
            castle.append("-");
        }
        return castle.toString();
    }
    public String enPassantString() {
        if (moveRecord.isEmpty()) {
            return "-";
        } else {
            Move lastMove = moveRecord.get(moveRecord.size() - 1);
            return lastMove.epTargetString();
        }

    }
    @Override
    public String getFEN() {
        return (
                board.toString() + " " +
                activeColorString() + " " +
                castleString() + " " +
                enPassantString() + " " +
                halfmoveCounter + " " +
                fullmoveCounter
        );
    }
    public String executeMove(Move legalMove) {
        StringBuilder message = new StringBuilder();
        Piece movingPiece = legalMove.getMovingPiece();
        Piece capturedPiece = legalMove.getCapturedPiece();
        if (movingPiece.getType() == PieceType.King) {
            if (movingPiece.getColor() == PieceColor.White) {
                K = false;
                Q = false;
            } else {
                k = false;
                q = false;
            }
        }
        if (movingPiece.getType() == PieceType.Rook) {
            if (Objects.equals(legalMove.getMoveVector().subList(0,2), Arrays.asList(0,0))) {
                q = false;
            } else if ((Objects.equals(legalMove.getMoveVector().subList(0,2), Arrays.asList(0,7)))) {
                Q = false;
            } else if (Objects.equals(legalMove.getMoveVector().subList(0,2), Arrays.asList(7,0))) {
                k = false;
            } else if ((Objects.equals(legalMove.getMoveVector().subList(0,2), Arrays.asList(7,7)))) {
                K = false;
            }
        }
        // add color and UCI to message
        message.append(activeColor).append(" made move ").append(legalMove.toSAN());
        // execute move
        board = legalMove.execute();
        // add move to record
        moveRecord.add(legalMove);
        // toggle the turn
        toggleActiveColor();
        // if the turn after toggling is white, both players have made a move and fullmove can be incremented
        if (activeColor == PieceColor.White) {
            fullmoveCounter += 1;
        }
        // if the moving piece was a pawn or a piece was captured by the moving player, reset halfmove
        if (movingPiece.getType() == PieceType.Pawn || !capturedPiece.equals(Pieces.NoPiece())) {
            halfmoveCounter = 0;
        } else { // otherwise increment halfmove
            halfmoveCounter += 1;
        }
        // if the current color has no available moves, they are in checkmate
        boolean checkmate = GameObserver.InCheckmate(activeColor, board, enPassantString(), castleString());
        if (checkmate) {
            toggleActiveColor();
            message.append("\nCheckmate! ").append(activeColor).append(" wins!");
            toggleActiveColor();
        } else if (GameObserver.InCheck(activeColor, board, enPassantString(), castleString())) {
            message.append("\nCheck!");
        }
        return message.toString();
    }
    @Override
    public String postMoveUCI(String uciString) {
        List<Move> legalMoves = LegalMoves.GetLegalMoves(board, activeColor, enPassantString(), castleString());
        for (Move legalMove : legalMoves) {
            if (legalMove.toUCI().equals(uciString)) {
                return executeMove(legalMove);
            }
        }
        return "Error: illegal move";
    }

    @Override
    public String postMoveSAN(String sanString) {
        List<Move> legalMoves = LegalMoves.GetLegalMoves(board, activeColor, enPassantString(), castleString());
        for (Move legalMove : legalMoves) {
            if (legalMove.toSAN().equals(sanString)) {
                return executeMove(legalMove);
            }
        }
        return "Error: illegal move";
    }

    @Override
    public int getID() {
        return id;
    }
}
