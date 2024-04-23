package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class UlasanTest {
    private Ulasan ulasan;

    @BeforeEach
    void setUp(){
        Game game = new Game();
        game.setId("game1");
        game.setNama("God of War");
        game.setDeskripsi("Game tentang bapak dan anak");
        game.setHarga(250000);
        game.setKategori("Adventure");
        game.setStok(5);

        ulasan = new Ulasan();
        ulasan.setId("ulasan1");
        ulasan.setIdUser("user123");
        ulasan.setGame(game);
        ulasan.setRating(4);
        ulasan.setDeskripsi("Game seru untuk action.");
        ulasan.setDate(LocalDate.now());
    }

    @Test
    public void testConstructor() {
        Ulasan newUlasan = new Ulasan();
        assertNotNull(newUlasan);
    }

    @Test
    public void testGetter() {
        assertEquals("ulasan1", ulasan.getId());
        assertEquals("user123", ulasan.getIdUser());
        assertEquals(4, ulasan.getRating().intValue());
        assertEquals("Game seru untuk action.", ulasan.getDeskripsi());
        assertNotNull(ulasan.getDate());
        assertEquals(LocalDate.now(), ulasan.getDate());
    }

    @Test
    public void testGetterGame(){
        assertNotNull(ulasan.getGame());
        assertEquals("game1", ulasan.getGame().getId());
        assertEquals("God of War", ulasan.getGame().getNama());
        assertEquals("Game tentang bapak dan anak", ulasan.getGame().getDeskripsi());
        assertEquals(250000, ulasan.getGame().getHarga());
        assertEquals("Adventure", ulasan.getGame().getKategori());
        assertEquals(5, ulasan.getGame().getStok());
    }

    @Test
    public void testValidationRating() {
        // Memastikan rating berada dalam rentang 1-5
        ulasan.setRating(10);
        assertNotEquals(10, ulasan.getRating().intValue());

        ulasan.setRating(-1);
        assertNotEquals(-1, ulasan.getRating().intValue());
    }

    @Test
    public void testValidationDescription() {
        // Memastikan deskripsi tidak boleh kosong
        assertThrows(IllegalArgumentException.class, () -> {
            ulasan.setDeskripsi(null);
        });
    }
}
