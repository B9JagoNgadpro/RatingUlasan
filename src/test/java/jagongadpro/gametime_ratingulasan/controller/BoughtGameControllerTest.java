package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;
import jagongadpro.gametime_ratingulasan.service.BoughtGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BoughtGameControllerTest {

    @Mock
    private BoughtGameService boughtGameService;

    @InjectMocks
    private BoughtGameController boughtGameController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInputBoughtGame() {
        Map<String, Object> data = new HashMap<>();
        data.put("idGame", "game1");
        data.put("idUser", "user1");

        BoughtGame boughtGame = new BoughtGame("game1", "user1");
        when(boughtGameService.inputBoughtGame(any(BoughtGame.class))).thenReturn(boughtGame);

        BoughtGame result = boughtGameController.inputBoughtGame(data);

        assertEquals("game1", result.getIdGame());
        assertEquals("user1", result.getIdUser());
        verify(boughtGameService, times(1)).inputBoughtGame(any(BoughtGame.class));
    }

    @Test
    public void testGetBoughtGameUser() {
        List<BoughtGame> boughtGames = Arrays.asList(
                new BoughtGame("game1", "user1"),
                new BoughtGame("game2", "user1")
        );
        when(boughtGameService.getBoughtGamesByUserId(anyString())).thenReturn(boughtGames);

        ResponseEntity<List<BoughtGame>> response = boughtGameController.getBoughtGameUser("user1");

        assertEquals(2, response.getBody().size());
        assertEquals("game1", response.getBody().get(0).getIdGame());
        assertEquals("game2", response.getBody().get(1).getIdGame());
        verify(boughtGameService, times(1)).getBoughtGamesByUserId("user1");
    }

    @Test
    public void testBoughtGameReviewed() {
        Map<String, Object> data = new HashMap<>();
        data.put("idGame", "game1");

        BoughtGame boughtGame = new BoughtGame("game1", "user1");
        boughtGame.setReviewed(true);
        when(boughtGameService.boughtGameReviewed(anyString(), anyString())).thenReturn(boughtGame);

        BoughtGame result = boughtGameController.BoughtGameReviewed("user1", data);

        assertTrue(result.isReviewed());
        verify(boughtGameService, times(1)).boughtGameReviewed("game1", "user1");
    }

    @Test
    public void testFetchBoughtGame() {
        List<Map<String, Object>> gamesData = new ArrayList<>();
        Map<String, Object> gameData1 = new HashMap<>();
        gameData1.put("idGame", "game1");
        gameData1.put("idUser", "user1");
        gamesData.add(gameData1);

        Map<String, Object> gameData2 = new HashMap<>();
        gameData2.put("idGame", "game2");
        gameData2.put("idUser", "user1");
        gamesData.add(gameData2);



        boughtGameController.fetchBoughtGame(gamesData);

        verify(boughtGameService, times(2)).inputBoughtGame(any(BoughtGame.class));
    }
}
