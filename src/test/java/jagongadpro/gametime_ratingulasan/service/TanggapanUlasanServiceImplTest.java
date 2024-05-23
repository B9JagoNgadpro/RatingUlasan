package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.repository.TanggapanUlasanRepository;
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

class TanggapanUlasanServiceImplTest {

    @Mock
    private TanggapanUlasanRepository tanggapanUlasanRepository;

    @InjectMocks
    private TanggapanUlasanServiceImpl tanggapanUlasanService;

    private TanggapanUlasan tanggapanUlasan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tanggapanUlasan = new TanggapanUlasan.Builder()
                .id("1")
                .penjualId("penjual1")
                .tanggapan("Makasih udah membeli!")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void createTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.save(tanggapanUlasan)).thenReturn(tanggapanUlasan);
        CompletableFuture<TanggapanUlasan> createdFuture = tanggapanUlasanService.createTanggapanUlasan(tanggapanUlasan);
        TanggapanUlasan created = createdFuture.join();
        assertNotNull(created);
        assertEquals("Makasih udah membeli!", created.getTanggapan());
    }

    @Test
    void updateTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.save(tanggapanUlasan)).thenReturn(tanggapanUlasan);
        CompletableFuture<TanggapanUlasan> updatedFuture = tanggapanUlasanService.updateTanggapanUlasan(tanggapanUlasan);
        TanggapanUlasan updated = updatedFuture.join();
        assertNotNull(updated);
    }

    @Test
    void deleteTanggapanUlasanTest() {
        doNothing().when(tanggapanUlasanRepository).deleteById("1");
        CompletableFuture<Void> deleteFuture = tanggapanUlasanService.deleteTanggapanUlasan("1");
        deleteFuture.join();
        verify(tanggapanUlasanRepository).deleteById("1");
    }

    @Test
    void findTanggapanUlasanByIdTest() {
        when(tanggapanUlasanRepository.findById("1")).thenReturn(Optional.of(tanggapanUlasan));
        CompletableFuture<Optional<TanggapanUlasan>> foundFuture = tanggapanUlasanService.findTanggapanUlasanById("1");
        Optional<TanggapanUlasan> found = foundFuture.join();
        assertTrue(found.isPresent());
        assertEquals("1", found.get().getId());
    }

    @Test
    void findTanggapanUlasanByUlasanIdTest() {
        when(tanggapanUlasanRepository.findByUlasanId("ulasanId")).thenReturn(tanggapanUlasan);
        CompletableFuture<Optional<TanggapanUlasan>> foundFuture = tanggapanUlasanService.findTanggapanUlasanByUlasanId("ulasanId");
        Optional<TanggapanUlasan> found = foundFuture.join();
        assertTrue(found.isPresent());
    }

    @Test
    void findAllTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.findAll()).thenReturn(Arrays.asList(tanggapanUlasan));
        CompletableFuture<List<TanggapanUlasan>> allFuture = tanggapanUlasanService.findAllTanggapanUlasan();
        List<TanggapanUlasan> all = allFuture.join();
        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
    }

    @Test
    void findAllTanggapanUlasanByPenjualIdTest() {
        when(tanggapanUlasanRepository.findAllByPenjualId("penjual1")).thenReturn(Arrays.asList(tanggapanUlasan));
        CompletableFuture<List<TanggapanUlasan>> byPenjualFuture = tanggapanUlasanService.findAllTanggapanUlasanByPenjualId("penjual1");
        List<TanggapanUlasan> byPenjual = byPenjualFuture.join();
        assertFalse(byPenjual.isEmpty());
        assertEquals(1, byPenjual.size());
    }
}
