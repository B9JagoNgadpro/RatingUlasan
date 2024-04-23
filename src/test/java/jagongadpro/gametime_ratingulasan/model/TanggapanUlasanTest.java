package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class TanggapanUlasanTest {
    private TanggapanUlasan tanggapanUlasan;

    @BeforeEach
    void setUp() {
        Game game = new Game();
        Ulasan ulasan = new Ulasan();
        ulasan.setId("ulasan1");
        ulasan.setIdUser("user123");
        ulasan.setGame(game);
        ulasan.setRating(4);
        ulasan.setDeskripsi("Game seru untuk action.");
        ulasan.setDate(LocalDate.now());

        tanggapanUlasan = new TanggapanUlasan();
        tanggapanUlasan.setId("tanggapanUlasan1");
        tanggapanUlasan.setPenjualId("penjual123");
        tanggapanUlasan.setUlasan(ulasan);
        tanggapanUlasan.setTanggapan("Terima kasih reviewnya!");
        tanggapanUlasan.setDate(LocalDate.now());
    }

    @Test
    public void testConstructor() {
        TanggapanUlasan tanggapanUlasan = new TanggapanUlasan();
        assertNotNull(tanggapanUlasan);
    }

    @Test
    public void testGetter() {
        assertEquals("tanggapanUlasan1", tanggapanUlasan.getId());
        assertEquals("penjual123", tanggapanUlasan.getPenjualId());
        assertNotNull(tanggapanUlasan.getUlasan());
        assertEquals("Terima kasih reviewnya!", tanggapanUlasan.getTanggapan());
        assertEquals(LocalDate.now(), tanggapanUlasan.getDate());
    }

    @Test
    public void testValidationTanggapan() {
        assertThrows(IllegalArgumentException.class, () -> {
            tanggapanUlasan.setTanggapan(null);
        });
    }

    @Test
    public void testValidationUlasan() {
        assertThrows(IllegalArgumentException.class, () -> {
            tanggapanUlasan.setUlasan(null);
        });
    }


}
