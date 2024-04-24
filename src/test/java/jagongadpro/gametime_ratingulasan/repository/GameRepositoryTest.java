package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameRepositoryTest {

    @InjectMocks
    private GameRepository repository;
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setIdPenjual("seller123");
        game.setNama("CyberQuest");
        game.setDeskripsi("Futuristic RPG");
        game.setHarga(60);
        game.setKategori("RPG");
        game.setStok(100);
    }

    @Test
    void testCreateGame() {
        Game savedGame = repository.create(game);
        assertNotNull(savedGame.getId());
        assertEquals(game, savedGame);
    }

    @Test
    void testFindById() {
        Game savedGame = repository.create(game);
        Game foundGame = repository.findById(savedGame.getId());
        assertEquals(savedGame, foundGame);
    }

    @Test
    void testUpdateGame() {
        Game savedGame = repository.create(game);
        savedGame.setDeskripsi("Updated futuristic RPG");
        Game updatedGame = repository.update(savedGame);
        assertEquals("Updated futuristic RPG", updatedGame.getDeskripsi());
    }

    @Test
    void testDeleteGame() {
        Game savedGame = repository.create(game);
        repository.delete(savedGame.getId());
        assertNull(repository.findById(savedGame.getId()));
    }

    @Test
    void testFindAll() {
        repository.create(game);
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    void testFindByCategoryId() {
        repository.create(game);
        assertFalse(repository.findByCategoryId("RPG").isEmpty());
    }

    @Test
    void testFindByUserId() {
        repository.create(game);
        Game anotherGame = new Game();
        anotherGame.setIdPenjual("seller123");
        anotherGame.setNama("Another Game");
        anotherGame.setDeskripsi("Another description");
        anotherGame.setHarga(50);
        anotherGame.setKategori("Action");
        anotherGame.setStok(150);
        repository.create(anotherGame);

        List<Game> foundGames = repository.findByUserId("seller123");
        assertEquals(2, foundGames.size(), "Should find games by user ID");
        assertTrue(foundGames.contains(game), "The list should contain the original game");
        assertTrue(foundGames.contains(anotherGame), "The list should contain the second game");
    }
}
