package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UlasanServiceImplTest {

    @Mock
    private UlasanRepository ulasanRepository;

    @InjectMocks
    private UlasanServiceImpl ulasanService;

    private Ulasan ulasan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ulasan = new Ulasan.Builder()
                .id("ulasan1")
                .idUser("user1")
                .game("game1")
                .rating(5)
                .deskripsi("Excellent!")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void testCreateUlasan() {
        when(ulasanRepository.save(ulasan)).thenReturn(ulasan);
        CompletableFuture<Ulasan> createdFuture = ulasanService.createUlasan(ulasan);
        Ulasan created = createdFuture.join();
        assertNotNull(created);
        assertEquals(ulasan.getId(), created.getId());
    }

    @Test
    void testFindAllUlasans() {
        when(ulasanRepository.findAll()).thenReturn(Arrays.asList(ulasan));
        CompletableFuture<List<Ulasan>> ulasanListFuture = ulasanService.findAllUlasans();
        List<Ulasan> ulasanList = ulasanListFuture.join();
        assertFalse(ulasanList.isEmpty());
        assertEquals(1, ulasanList.size());
        assertEquals(ulasan.getId(), ulasanList.get(0).getId());
    }

    @Test
    void testFindUlasansByUserId() {
        when(ulasanRepository.findAllByIdUser("user1")).thenReturn(Arrays.asList(ulasan));
        CompletableFuture<List<Ulasan>> resultFuture = ulasanService.findUlasansByUserId("user1");
        List<Ulasan> result = resultFuture.join();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ulasan.getIdUser(), result.get(0).getIdUser());
    }

    @Test
    void testFindUlasansByGameId() {
        when(ulasanRepository.findAllByGame("game1")).thenReturn(Arrays.asList(ulasan));
        CompletableFuture<List<Ulasan>> resultFuture = ulasanService.findUlasansByGameId("game1");
        List<Ulasan> result = resultFuture.join();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ulasan.getGame(), result.get(0).getGame());
    }

    @Test
    void testFindUlasanById() {
        when(ulasanRepository.findById("ulasan1")).thenReturn(Optional.of(ulasan));
        CompletableFuture<Optional<Ulasan>> foundFuture = ulasanService.findUlasanById("ulasan1");
        Optional<Ulasan> found = foundFuture.join();
        assertTrue(found.isPresent());
        assertEquals(ulasan.getId(), found.get().getId());
    }

    @Test
    void testUpdateUlasan() {
        when(ulasanRepository.save(ulasan)).thenReturn(ulasan);
        CompletableFuture<Ulasan> updatedFuture = ulasanService.updateUlasan(ulasan);
        Ulasan updated = updatedFuture.join();
        assertNotNull(updated);
        assertEquals(ulasan.getId(), updated.getId());
    }

    @Test
    void testDeleteUlasan() {
        doNothing().when(ulasanRepository).deleteById("ulasan1");
        assertDoesNotThrow(() -> ulasanService.deleteUlasan("ulasan1"));
        verify(ulasanRepository).deleteById("ulasan1");
    }

    @Test
    void testFindByIdThrowsException() {
        when(ulasanRepository.findById("nonexistent")).thenReturn(Optional.empty());
        CompletableFuture<Optional<Ulasan>> resultFuture = ulasanService.findUlasanById("nonexistent");
        Optional<Ulasan> result = resultFuture.join();
        assertFalse(result.isPresent());
    }
}
