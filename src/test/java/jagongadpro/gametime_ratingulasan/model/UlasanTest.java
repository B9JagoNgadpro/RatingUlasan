package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class UlasanTest {
    private Ulasan ulasan;

    @BeforeEach
    void setUp(){
        ulasan = new Ulasan.Builder()
                .id("ulasan1")
                .idUser("user123")
                .game("game1")
                .rating(4)
                .deskripsi("Game seru untuk action.")
                .date(LocalDate.now())
                .build();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ulasan);
    }

    @Test
    public void testGetter() {
        assertEquals("ulasan1", ulasan.getId());
        assertEquals("user123", ulasan.getIdUser());
        assertEquals(4, ulasan.getRating().intValue());
        assertEquals("Game seru untuk action.", ulasan.getDeskripsi());
        assertEquals("game1", ulasan.getGame());
        assertNotNull(ulasan.getDate());
        assertEquals(LocalDate.now(), ulasan.getDate());
    }

    @Test
    public void testValidationRating() {
        Ulasan testRatingUlasan = new Ulasan.Builder()
                                .rating(0) // Invalid rating
                                .build();

        assertEquals(1, testRatingUlasan.getRating().intValue());
    }

    @Test
    public void testValidationDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ulasan.Builder()
                    .deskripsi("") // Empty description
                    .build();
        });
        assertTrue(exception.getMessage().contains("Deskripsi tidak boleh kosong"));
    }
}
