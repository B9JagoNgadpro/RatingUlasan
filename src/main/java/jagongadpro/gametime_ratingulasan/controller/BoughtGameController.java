package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;
import jagongadpro.gametime_ratingulasan.service.BoughtGameService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bought-games")
@CrossOrigin(origins = {"http://localhost:3000", "https://gametime-frontend.vercel.app/"})
public class BoughtGameController {

    private final BoughtGameService service;
    private final RestTemplate restTemplate;

    public BoughtGameController(BoughtGameService service, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.service = service;
    }

    public BoughtGame inputBoughtGame(Map<String, Object> data) {
        BoughtGame boughtGame = new BoughtGame(
                data.get("idGame").toString(),
                data.get("idUser").toString()
        );
        return service.inputBoughtGame(boughtGame);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<BoughtGame>> getBoughtGameUser(@PathVariable String idUser) {
        List<BoughtGame> boughtGames = service.getBoughtGamesByUserId(idUser);
        return ResponseEntity.ok(boughtGames);
    }

    @PatchMapping("/isReviewed/user/{idUser}")
    public BoughtGame boughtGameReviewed(@PathVariable String idUser, @RequestBody Map<String, Object> data) {
        return service.boughtGameReviewed(data.get("idGame").toString(), idUser);
    }

    @PatchMapping("/unReviewed/user/{idUser}")
    public BoughtGame boughtGameUnReviewed(@PathVariable String idUser, @RequestBody Map<String, Object> data) {
        return service.boughtGameUnReviewed(data.get("idGame").toString(), idUser);
    }

    @PostMapping("/input/{userId}")
    public void fetchBoughtGame(@PathVariable String userId) {
        String url = "https://gametimeriwayat-production.up.railway.app/get/" + userId;

        // Fetch data from the URL
        List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

        // Process each game's data
        for (Map<String, Object> data : response) {
            List<Map<String, Object>> games = (List<Map<String, Object>>) data.get("games");
            String pembeliId = data.get("pembeli_id").toString();
            for (Map<String, Object> gameData : games) {
                // Create a data map to pass to inputBoughtGame
                Map<String, Object> boughtGameData = Map.of(
                        "idGame", gameData.get("id"),
                        "idUser", pembeliId
                );
                inputBoughtGame(boughtGameData);
            }
        }
    }
}
