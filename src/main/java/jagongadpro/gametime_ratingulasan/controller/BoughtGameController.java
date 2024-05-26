package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;
import jagongadpro.gametime_ratingulasan.service.BoughtGameService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bought-games")
@CrossOrigin(origins = {"http://localhost:3000", "https://gametime-frontend.vercel.app/"})
public class BoughtGameController {

    private final BoughtGameService service;

    private BoughtGameController(BoughtGameService service) {
        this.service = service;
    }

    public BoughtGame inputBoughtGame(@RequestBody Map<String, Object> data) {
        BoughtGame boughtGame = new BoughtGame(
                data.get("idGame").toString(),
                data.get("idUser").toString()
        );
        return service.inputBoughtGame(boughtGame);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<BoughtGame>> getBoughtGameUser(@PathVariable String idUser){
        List<BoughtGame> boughtGames = service.getBoughtGamesByUserId(idUser);
        return ResponseEntity.ok(boughtGames);
    }

    @PatchMapping("/isReviewed/user/{idUser}")
    public BoughtGame boughtGameReviewed(@PathVariable String idUser, @RequestBody Map<String, Object> data) {
        BoughtGame boughtGameReviewed = service.boughtGameReviewed(data.get("idGame").toString(), idUser);
        return boughtGameReviewed;
    }

    @PatchMapping("/unReviewed/user/{idUser}")
    public BoughtGame boughtGameUnReviewed(@PathVariable String idUser, @RequestBody Map<String, Object> data) {
        BoughtGame boughtGameReviewed = service.boughtGameUnReviewed(data.get("idGame").toString(), idUser);
        return boughtGameReviewed;
    }

    @PostMapping("/input")
    public void fetchBoughtGame(@RequestBody List<Map<String, Object>> gamesData) {
        for (Map<String, Object> gameData : gamesData) {
            inputBoughtGame(gameData);
        }
    }
}
