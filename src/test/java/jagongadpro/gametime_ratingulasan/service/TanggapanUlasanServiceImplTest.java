package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.repository.TanggapanUlasanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
        Ulasan ulasanBaru = new Ulasan.Builder().build();
        ulasanBaru.setIdUser("user1");

        tanggapanUlasan = new TanggapanUlasan.Builder()
                .id("1").penjualId("penjual1")
                .ulasan(ulasanBaru)
                .tanggapan("Makasih udah membeli!")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void createTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.create(tanggapanUlasan)).thenReturn(tanggapanUlasan);
        TanggapanUlasan created = tanggapanUlasanService.createTanggapanUlasan(tanggapanUlasan);
        assertNotNull(created);
        assertEquals("Makasih udah membeli!", created.getTanggapan());
    }

    @Test
    void updateTanggapanUlasanTest() {
        when(tanggapanUlasanRepository.update(tanggapanUlasan)).thenReturn(tanggapanUlasan);
        TanggapanUlasan updated = tanggapanUlasanService.updateTanggapanUlasan(tanggapanUlasan);
        assertNotNull(updated);
    }

    @Test
    void deleteTanggapanUlasanTest() {
        doNothing().when(tanggapanUlasanRepository).delete("1");
        tanggapanUlasanService.deleteTanggapanUlasan("1");
        verify(tanggapanUlasanRepository).delete("1");
    }

    @Test
    void findTanggapanUlasanByIdTest() {
        when(tanggapanUlasanRepository.findById("1")).thenReturn(tanggapanUlasan);
        TanggapanUlasan found = tanggapanUlasanService.findTanggapanUlasanById("1");
        assertEquals("1", found.getId());
    }

    @Test
    void findTanggapanUlasanByUlasanIdTest() {
        when(tanggapanUlasanRepository.findByUlasanId("user1")).thenReturn(tanggapanUlasan);
        TanggapanUlasan found = tanggapanUlasanService.findTanggapanUlasanByUlasanId("user1");
        assertNotNull(found);
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
