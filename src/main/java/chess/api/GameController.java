package chess.api;

import chess.game.GameInterface;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chess")
public class GameController {

    @PostMapping("/new")
    public ResponseEntity<String> newGame() {
        GameInterface newGame = GamePool.NewGame();
        return ResponseEntity.ok("Created new GameInterface with id=" + newGame.getID());
    }

    @GetMapping("/fen/{gameID}")
    public ResponseEntity<String> getFEN(@PathVariable Integer gameID) {
        return ResponseEntity.ok(GamePool.GetGameState(gameID));
    }

    @PostMapping(value = "/move/{gameID}", consumes = {"text/plain"})
    public ResponseEntity<String> postMove(@PathVariable Integer gameID, @RequestBody String uciString) {
        System.out.println(uciString);
        return ResponseEntity.ok(GamePool.PostMoveToGame(gameID, uciString));
    }
}
