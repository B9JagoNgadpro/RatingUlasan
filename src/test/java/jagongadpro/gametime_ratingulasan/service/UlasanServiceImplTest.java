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
        Ulasan created = ulasanService.createUlasan(ulasan);
        assertNotNull(created);
        assertEquals(ulasan.getId(), created.getId());
    }

    @Test
    void testFindAllUlasans() {
        when(ulasanRepository.findAll()).thenReturn(Arrays.asList(ulasan));
        List<Ulasan> ulasanList = ulasanService.findAllUlasans();
        assertFalse(ulasanList.isEmpty());
        assertEquals(1, ulasanList.size());
        assertEquals(ulasan.getId(), ulasanList.get(0).getId());
    }

    @Test
    void testFindUlasansByUserId() {
        when(ulasanRepository.findAllByIdUser("user1")).thenReturn(Arrays.asList(ulasan));
        List<Ulasan> result = ulasanService.findUlasansByUserId("user1");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ulasan.getIdUser(), result.get(0).getIdUser());
    }

    @Test
    void testFindUlasansByGameId() {
        when(ulasanRepository.findAllByGame("game1")).thenReturn(Arrays.asList(ulasan));
        List<Ulasan> result = ulasanService.findUlasansByGameId("game1");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(ulasan.getGame(), result.get(0).getGame());
    }

    @Test
    void testFindUlasanById() {
        when(ulasanRepository.findById("ulasan1")).thenReturn(Optional.of(ulasan));
        Optional<Ulasan> found = ulasanService.findUlasanById("ulasan1");
        assertTrue(found.isPresent());
        assertEquals(ulasan.getId(), found.get().getId());
    }

    @Test
    void testUpdateUlasan() {
        when(ulasanRepository.save(ulasan)).thenReturn(ulasan);
        Ulasan updated = ulasanService.updateUlasan(ulasan);
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
        Optional<Ulasan> result = ulasanService.findUlasanById("nonexistent");
        assertFalse(result.isPresent());
    }
}
