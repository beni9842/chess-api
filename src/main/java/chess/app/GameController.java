package chess.app;

import chess.game.Game;
import chess.game.GameInterface;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Objects;

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
        return ResponseEntity.ok(GamePool.GetFEN(gameID));
    }

    @PostMapping(value = "/move/{gameID}", consumes = {"text/plain"})
    public ResponseEntity<String> postMove(@PathVariable Integer gameID, @PathParam("notation") String notation, @RequestBody String moveString) {
        System.out.println(notation);
        System.out.println(moveString);
        if (Objects.equals(notation, "uci")) {
            return ResponseEntity.ok(GamePool.PostMoveUCI(gameID, moveString));
        } else if (Objects.equals(notation, "san")) {
            return ResponseEntity.ok(GamePool.PostMoveSAN(gameID, moveString));
        } else {
            return ResponseEntity.ok("Unknown notation type");
        }
    }
}
