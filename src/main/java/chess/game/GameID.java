package chess.game;

public abstract class GameID {
    private static int currID = 0;
    public static int GetNewID() {
        currID += 1;
        return currID;
    }
}
