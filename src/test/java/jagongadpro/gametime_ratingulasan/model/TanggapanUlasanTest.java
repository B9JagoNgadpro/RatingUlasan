package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TanggapanUlasanTest {
    private TanggapanUlasan tanggapanUlasan;

    @BeforeEach
    void setUp() {
        Ulasan ulasan = new Ulasan.Builder()
                .id("ulasan1")
                .idUser("user123")
                .game("game1")
                .rating(4)
                .deskripsi("Game seru untuk action.")
                .date(LocalDate.now())
                .build();

        tanggapanUlasan = new TanggapanUlasan.Builder()
                .id("tanggapanUlasan1")
                .penjualId("penjual123")
                .ulasan(ulasan)
                .tanggapan("Terima kasih reviewnya!")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void testConstructor() {
        assertNotNull(tanggapanUlasan);
    }

    @Test
     void testGetter() {
        assertEquals("tanggapanUlasan1", tanggapanUlasan.getId());
        assertEquals("penjual123", tanggapanUlasan.getPenjualId());
        assertNotNull(tanggapanUlasan.getUlasan());
        assertEquals("Terima kasih reviewnya!", tanggapanUlasan.getTanggapan());
        assertEquals(LocalDate.now(), tanggapanUlasan.getDate());
    }

    @Test
     void testValidationTanggapan() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TanggapanUlasan.Builder()
                    .tanggapan("") // Empty tanggapan
                    .build();
        });
        assertTrue(exception.getMessage().contains("Tanggapan tidak boleh kosong"));
    }

    @Test
     void testValidationUlasan() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TanggapanUlasan.Builder()
                    .ulasan(null) // Null ulasan
                    .build();
        });
        assertTrue(exception.getMessage().contains("Ulasan tidak boleh kosong"));
    }
}
