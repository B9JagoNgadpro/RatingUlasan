package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class GameRepository {
    private final List<Game> games = new ArrayList<>();

    public Game create(Game game) {
        if (game.getId() == null) {
            game.setId(UUID.randomUUID().toString());
        }
        games.add(game);
        return game;
    }

    public Game findById(String id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Game> findAll() {
        return new ArrayList<>(games);
    }

    public List<Game> findByCategoryId(String categoryId) {
        return games.stream()
                .filter(game -> game.getKategori().equals(categoryId))
                .collect(Collectors.toList());
    }

    public List<Game> findByUserId(String userId) {
        return games.stream()
                .filter(game -> game.getIdPenjual().equals(userId))
                .collect(Collectors.toList());
    }

    public Game update(Game game) {
        Game existingGame = findById(game.getId());
        if (existingGame != null) {
            games.set(games.indexOf(existingGame), game);
        }
        return game;
    }

    public void delete(String id) {
        games.removeIf(game -> game.getId().equals(id));
    }
}
