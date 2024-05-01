package chess.game;

import chess.events.Event;
import chess.events.MovePieceEvent;
import chess.observers.CheckObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class LegalMoves {
    public static List<Move> GetAllLegalMoves(Board b, PieceColor turn) {
        List<Move> allMoves = GetAll(b, turn);
        List<Move> legalMoves = new ArrayList<>();
        CheckObserver checkObserver = new CheckObserver();
        for (Move m : allMoves) {
            Board copy = b.copy();
            m.execute(copy);
            Event moveEvent = new MovePieceEvent(m.toUCI(), turn, copy);
            checkObserver.update(moveEvent);
            if (checkObserver.inCheck() != turn) {
                legalMoves.add(m);
            }
        }
        return legalMoves;
    }
    public static List<Move> GetAll(Board b, PieceColor turn) {
        List<Move> moveList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = b.getPiece(i, j);
                if (p.getColor() == turn) {
                    moveList.addAll(GetMovesForPiece(b, i, j));
                }
            }
        }
        return moveList;
    }
    public static List<Move> GetMovesForPiece(Board b, int rank, int file) {
        Piece p = b.getPiece(rank, file);
        PieceType type = p.getType();
        PieceColor color = p.getColor();
        return switch (type) {
            case Pawn -> GetPawnMoves(b, rank, file, color);
            case Rook -> GetRookMoves(b, rank, file, color);
            case Knight -> GetKnightMoves(b, rank, file, color);
            case Bishop -> GetBishopMoves(b, rank, file, color);
            case Queen -> GetQueenMoves(b, rank, file, color);
            case King -> GetKingMoves(b, rank, file, color);
            case NoType -> new ArrayList<>();
        };
    }

    public static List<Move> GetKingMoves(Board b, int rank, int file, PieceColor color) {
        List<Move> moves = new ArrayList<>();
        Piece p = b.getPiece(rank, file);
        if (p.getColor() == color) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int dstRank = rank + i;
                    int dstFile = file + j;
                    if (dstRank >= 0 && dstRank < 8 && dstFile >= 0 && dstFile < 8 && !(rank == dstRank && file == dstFile)) {
                        Move newMove = new Move(file, rank, dstFile, dstRank);
                        if (newMove.capturedPiece(b).getColor() != color) {
                            moves.add(newMove);
                        }
                    }
                }
            }
        }
        return moves;
    }

    public static List<Move> GetQueenMoves(Board b, int rank, int file, PieceColor color) {
        Piece p = b.getPiece(rank, file);
        List<Move> moves = new ArrayList<>();
        if (p.getColor() == color) {
            moves = GetBishopMoves(b, rank, file, color);
            moves.addAll(GetRookMoves(b, rank, file, color));
        }
        return moves;
    }

    public static List<Move> GetBishopMoves(Board b, int rank, int file, PieceColor color) {
        Piece p = b.getPiece(rank, file);
        List<Move> moves = new ArrayList<>();
        if (p.getColor() == color) {
            // get NW moves
            int dstRank = rank - 1;
            int dstFile = rank - 1;
            while (dstRank >= 0 && dstFile >= 0) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstRank--;
                dstFile--;
            }
            // get NE moves
            dstRank = rank - 1;
            dstFile = file + 1;
            while (dstRank >= 0 && dstFile < 8) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstRank--;
                dstFile++;
            }
            // get SE moves
            dstRank = rank + 1;
            dstFile = file + 1;
            while (dstRank < 8 && dstFile < 8) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstRank++;
                dstFile++;
            }
            // get SW moves
            dstRank = rank + 1;
            dstFile = file - 1;
            while (dstRank < 8 && dstFile >= 0) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstRank++;
                dstFile--;
            }
        }
        return moves;
    }

    public static List<Move> GetKnightMoves(Board b, int rank, int file, PieceColor color) {
        Piece p = b.getPiece(rank, file);
        List<Move> moves = new ArrayList<>();
        if (p.getColor() == color) {
            // Check all possible knight moves
            int[] rankOffsets = {-2, -2, -1, -1, 1, 1, 2, 2};
            int[] fileOffsets = {-1, 1, -2, 2, -2, 2, -1, 1};
            // 8 possible moves at most
            for (int i = 0; i < 8; i++) {
                int dstRank = rank + rankOffsets[i];
                int dstFile = file + fileOffsets[i];
                if (dstRank >= 0 && dstRank < 8 && dstFile >= 0 && dstFile < 8) {
                    Move newMove = new Move(file, rank, dstFile, dstRank);
                    if (newMove.capturedPiece(b).getColor() != color) {
                        moves.add(newMove);
                    }
                }
            }
        }
        return moves;
    }

    public static List<Move> GetRookMoves(Board b, int rank, int file, PieceColor color) {
        Piece p = b.getPiece(rank, file);
        List<Move> moves = new ArrayList<>();
        if (p.getColor() == color) {
            // get N moves
            int dstRank = rank-1;
            int dstFile= file;
            while (dstRank >= 0) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstRank--;
            }
            // get W moves
            dstRank = rank;
            dstFile = file-1;
            while (dstFile >= 0) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstFile--;
            }
            // get S moves
            dstRank = rank+1;
            dstFile = file;
            while (dstRank < 8) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstRank++;
            }
            // get E moves
            dstRank = rank;
            dstFile = file+1;
            while (dstFile < 8) {
                Move newMove = new Move(file, rank, dstFile, dstRank);
                if (newMove.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(newMove);
                } else if (newMove.capturedPiece(b).getColor() != color) {
                    moves.add(newMove);
                    break;
                } else {
                    break;
                }
                dstFile++;
            }
        }
        return moves;
    }

    public static List<Move> GetPawnMoves(Board b, int rank, int file, PieceColor color) {
        Piece p = b.getPiece(rank, file);
        List<Move> moves = new ArrayList<>();
        if (p.getColor() == color) {
            if (color == PieceColor.Black) {
                Move m1 = new Move(file, rank, file, rank+1);
                if (m1.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(m1);
                    if (rank == 1) {
                        Move m2 = new Move(file, rank, file, rank+2);
                        if (m2.capturedPiece(b).equals(Pieces.NoPiece())) {
                            moves.add(m2);
                        }
                    }
                }
                if (file > 0) {
                    Move attack1 = new Move(file, rank, file-1, rank+1);
                    if (attack1.capturedPiece(b).getColor() == PieceColor.White) {
                        moves.add(attack1);
                    }
                }
                if (file < 7) {
                    Move attack2 = new Move(file, rank, file+1, rank+1);
                    if (attack2.capturedPiece(b).getColor() == PieceColor.White) {
                        moves.add(attack2);
                    }
                }
            } else { // Color is assumed to be white
                Move m1 = new Move(file, rank, file, rank-1);
                if (m1.capturedPiece(b).equals(Pieces.NoPiece())) {
                    moves.add(m1);
                    if (rank == 6) {
                        Move m2 = new Move(file, rank, file, rank-2);
                        if (m2.capturedPiece(b).equals(Pieces.NoPiece())) {
                            moves.add(m2);
                        }
                    }
                }
                if (file > 0) {
                    Move attack1 = new Move(file, rank, file-1, rank-1);
                    if (attack1.capturedPiece(b).getColor() == PieceColor.Black) {
                        moves.add(attack1);
                    }
                }
                if (file < 7) {
                    Move attack2 = new Move(file, rank, file+1, rank-1);
                    if (attack2.capturedPiece(b).getColor() == PieceColor.Black) {
                        moves.add(attack2);
                    }
                }
            }
        }
        return moves;
    }
}
