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
        TanggapanUlasan created = tanggapanUlasanService.createTanggapanUlasan(tanggapanUlasan);
        assertNotNull(created);
        assertEquals("Makasih udah membeli!", created.getTanggapan());
    }

    @Test
    void updateTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.save(tanggapanUlasan)).thenReturn(tanggapanUlasan);
        TanggapanUlasan updated = tanggapanUlasanService.updateTanggapanUlasan(tanggapanUlasan);
        assertNotNull(updated);
    }

    @Test
    void deleteTanggapanUlasanTest() {
        doNothing().when(tanggapanUlasanRepository).deleteById("1");
        tanggapanUlasanService.deleteTanggapanUlasan("1");
        verify(tanggapanUlasanRepository).deleteById("1");
    }

    @Test
    void findTanggapanUlasanByIdTest() {
        when(tanggapanUlasanRepository.findById("1")).thenReturn(Optional.of(tanggapanUlasan));
        Optional<TanggapanUlasan> found = tanggapanUlasanService.findTanggapanUlasanById("1");
        assertTrue(found.isPresent());
        assertEquals("1", found.get().getId());
    }

    @Test
    void findTanggapanUlasanByUlasanIdTest() {
        when(tanggapanUlasanRepository.findByUlasanId("ulasanId")).thenReturn(tanggapanUlasan);
        Optional<TanggapanUlasan> found = tanggapanUlasanService.findTanggapanUlasanByUlasanId("ulasanId");
        assertTrue(found.isPresent());
    }

    @Test
    void findAllTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.findAll()).thenReturn(Arrays.asList(tanggapanUlasan));
        List<TanggapanUlasan> all = tanggapanUlasanService.findAllTanggapanUlasan();
        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
    }

    @Test
    void findAllTanggapanUlasanByPenjualIdTest() {
        when(tanggapanUlasanRepository.findAllByPenjualId("penjual1")).thenReturn(Arrays.asList(tanggapanUlasan));
        List<TanggapanUlasan> byPenjual = tanggapanUlasanService.findAllTanggapanUlasanByPenjualId("penjual1");
        assertFalse(byPenjual.isEmpty());
        assertEquals(1, byPenjual.size());
    }
}
