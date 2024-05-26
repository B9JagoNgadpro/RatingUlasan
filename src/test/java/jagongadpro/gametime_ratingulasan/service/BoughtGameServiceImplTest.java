package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;
import jagongadpro.gametime_ratingulasan.repository.BoughtGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BoughtGameServiceImplTest {

    @Mock
    private BoughtGameRepository boughtGameRepository;

    @InjectMocks
    private BoughtGameServiceImpl boughtGameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInputBoughtGame() {
        BoughtGame boughtGame = new BoughtGame("game1", "user1");
        when(boughtGameRepository.save(any(BoughtGame.class))).thenReturn(boughtGame);

        BoughtGame result = boughtGameService.inputBoughtGame(boughtGame);

        assertEquals("game1", result.getIdGame());
        assertEquals("user1", result.getIdUser());
        verify(boughtGameRepository, times(1)).save(boughtGame);
    }

    @Test
    public void testGetBoughtGamesByUserId() {
        List<BoughtGame> boughtGames = Arrays.asList(
                new BoughtGame("game1", "user1"),
                new BoughtGame("game2", "user1")
        );
        when(boughtGameRepository.findAllByIdUser(anyString())).thenReturn(boughtGames);

        List<BoughtGame> result = boughtGameService.getBoughtGamesByUserId("user1");

        assertEquals(2, result.size());
        assertEquals("game1", result.get(0).getIdGame());
        assertEquals("game2", result.get(1).getIdGame());
        verify(boughtGameRepository, times(1)).findAllByIdUser("user1");
    }

    @Test
    public void testBoughtGameReviewed() {
        BoughtGame boughtGame = new BoughtGame("game1", "user1");
        when(boughtGameRepository.findByIdGameAndIdUser(anyString(), anyString())).thenReturn(boughtGame);
        when(boughtGameRepository.save(any(BoughtGame.class))).thenReturn(boughtGame);

        BoughtGame result = boughtGameService.boughtGameReviewed("game1", "user1");

        assertTrue(result.isReviewed());
        verify(boughtGameRepository, times(1)).findByIdGameAndIdUser("game1", "user1");
        verify(boughtGameRepository, times(1)).save(boughtGame);
    }

    @Test
    public void testBoughtGameUnReviewed() {
        BoughtGame boughtGame = new BoughtGame("game1", "user1");
        boughtGame.setReviewed(true);
        when(boughtGameRepository.findByIdGameAndIdUser(anyString(), anyString())).thenReturn(boughtGame);
        when(boughtGameRepository.save(any(BoughtGame.class))).thenReturn(boughtGame);

        BoughtGame result = boughtGameService.boughtGameUnReviewed("game1", "user1");

        assertFalse(result.isReviewed());
        verify(boughtGameRepository, times(1)).findByIdGameAndIdUser("game1", "user1");
        verify(boughtGameRepository, times(1)).save(boughtGame);
    }
}
