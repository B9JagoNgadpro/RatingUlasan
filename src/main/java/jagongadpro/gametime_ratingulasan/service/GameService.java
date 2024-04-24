package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Game;
import java.util.List;

public interface GameService {
    Game createGame(Game game);
    Game updateGame(Game game);
    void deleteGame(String id);
    Game findGameById(String id);
    List<Game> findAllGames();
    List<Game> findGamesByCategoryId(String categoryId);
    List<Game> findGamesByUserId(String userId);
}
