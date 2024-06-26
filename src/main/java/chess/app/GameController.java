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
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    @PostMapping("/new")
    public ResponseEntity<String> newGame() {
        GameInterface newGame = GamePool.NewGame();
        String response = "Created new GameInterface with id=" + newGame.getID();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fen/{gameID}")
    public ResponseEntity<String> getFEN(@PathVariable Integer gameID) {
        System.out.println("Getting fen for game " + gameID);
        String response = GamePool.GetFEN(gameID);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/move/{gameID}", consumes = {"text/plain"})
    public ResponseEntity<String> postMove(@PathVariable Integer gameID, @PathParam("notation") String notation, @RequestBody String moveString) {
        System.out.println("Received move: " + moveString);
        String response = "";
        if (Objects.equals(notation, "uci")) {
            response = GamePool.PostMoveUCI(gameID, moveString);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } else if (Objects.equals(notation, "san")) {
            response = GamePool.PostMoveSAN(gameID, moveString);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } else {
            response = "Unknown notation type";
            System.out.println(response);
            return ResponseEntity.ok(response);
        }
    }
}
