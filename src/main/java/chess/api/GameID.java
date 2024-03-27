package chess.api;

public class GameID {
    private static int gameIdCounter = 0;
    public static int GetNewID() {
        gameIdCounter += 1;
        return gameIdCounter;
    }
}
