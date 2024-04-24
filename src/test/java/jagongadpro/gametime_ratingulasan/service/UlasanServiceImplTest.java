package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Game;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UlasanServiceImplTest {

    @Mock
    private UlasanRepository ulasanRepository;

    @InjectMocks
    private UlasanServiceImpl ulasanService;

    private Ulasan ulasan;
    private Game game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        game = new Game("game1", "seller1", "Game One", "First game description", 50, "Adventure", 10);
        ulasan = new Ulasan("ulasan1", "user1", game, 5, "Excellent!", LocalDate.now());
    }

    @Test
    void testCreateUlasan() {
        when(ulasanRepository.create(ulasan)).thenReturn(ulasan);
        Ulasan created = ulasanService.createUlasan(ulasan);
        assertNotNull(created);
        assertEquals(ulasan.getId(), created.getId());
    }

    @Test
    void testFindAllUlasans() {
        when(ulasanRepository.findAll()).thenReturn(Arrays.asList(ulasan).iterator());
        List<Ulasan> ulasanList = ulasanService.findAllUlasans();
        assertFalse(ulasanList.isEmpty());
        assertEquals(1, ulasanList.size());
        assertEquals(ulasan.getId(), ulasanList.get(0).getId());
    }

    @Test
    void testFindUlasansByUserId() {
        when(ulasanRepository.findAllByUserId("user1")).thenReturn(Arrays.asList(ulasan));
        List<Ulasan> result = ulasanService.findUlasansByUserId("user1");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ulasan.getIdUser(), result.get(0).getIdUser());
    }

    @Test
    void testFindUlasansByGameId() {
        when(ulasanRepository.findAllByGameId("game1")).thenReturn(Arrays.asList(ulasan));
        List<Ulasan> result = ulasanService.findUlasansByGameId("game1");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(game.getId(), result.get(0).getGame().getId());
    }

    @Test
    void testFindUlasanById() {
        when(ulasanRepository.findById("ulasan1")).thenReturn(ulasan);
        Ulasan found = ulasanService.findUlasanById("ulasan1");
        assertNotNull(found);
        assertEquals(ulasan.getId(), found.getId());
    }

    @Test
    void testUpdateUlasan() {
        when(ulasanRepository.edit(ulasan)).thenReturn(ulasan);
        Ulasan updated = ulasanService.updateUlasan(ulasan);
        assertNotNull(updated);
        assertEquals(ulasan.getId(), updated.getId());
    }

    @Test
    void testDeleteUlasan() {
        when(ulasanRepository.delete("ulasan1")).thenReturn(ulasan);
        Ulasan deleted = ulasanService.deleteUlasan("ulasan1");
        assertNotNull(deleted);
        assertEquals(ulasan.getId(), deleted.getId());
    }

    @Test
    void testFindByIdThrowsException() {
        when(ulasanRepository.findById("nonexistent")).thenThrow(new IllegalArgumentException("Invalid ulasan Id: nonexistent"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ulasanService.findUlasanById("nonexistent");
        });
        assertEquals("Invalid ulasan Id: nonexistent", exception.getMessage());
    }
}
