package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Game;
import jagongadpro.gametime_ratingulasan.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createGameTest() {
        Game game = new Game("1", "seller1", "Game Name", "Description", 20, "Action", 100);
        when(gameRepository.create(any(Game.class))).thenReturn(game);
        Game created = gameService.createGame(game);
        assertEquals(game, created);
    }

    @Test
    void updateGameTest() {
        Game game = new Game("1", "seller1", "Game Name", "Description", 20, "Action", 100);
        when(gameRepository.update(game)).thenReturn(game);
        Game updated = gameService.updateGame(game);
        assertEquals(game, updated);
    }

    @Test
    void deleteGameTest() {
        doNothing().when(gameRepository).delete("1");
        gameService.deleteGame("1");
        verify(gameRepository, times(1)).delete("1");
    }

    @Test
    void findGameByIdTest() {
        Game game = new Game("1", "seller1", "Game Name", "Description", 20, "Action", 100);
        when(gameRepository.findById("1")).thenReturn(game);
        Game found = gameService.findGameById("1");
        assertEquals(game, found);
    }

    @Test
    void findAllGamesTest() {
        Game game = new Game("1", "seller1", "Game Name", "Description", 20, "Action", 100);
        when(gameRepository.findAll()).thenReturn(Collections.singletonList(game));
        List<Game> games = gameService.findAllGames();
        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        assertEquals(game, games.get(0));
    }

    @Test
    void findGamesByCategoryIdTest() {
        Game game = new Game("1", "seller1", "Game Name", "Description", 20, "Action", 100);
        when(gameRepository.findByCategoryId("Action")).thenReturn(Collections.singletonList(game));
        List<Game> games = gameService.findGamesByCategoryId("Action");
        assertFalse(games.isEmpty());
        assertEquals(game, games.get(0));
    }

    @Test
    void findGamesByUserIdTest() {
        Game game = new Game("1", "seller1", "Game Name", "Description", 20, "Action", 100);
        when(gameRepository.findByUserId("seller1")).thenReturn(Collections.singletonList(game));
        List<Game> games = gameService.findGamesByUserId("seller1");
        assertFalse(games.isEmpty());
        assertEquals(game, games.get(0));
    }
}
