package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UlasanTest {
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
     void testDefaultConstructor(){
        Ulasan newUlasan = new Ulasan();
        assertNotNull(newUlasan);
    }

    @Test
     void testConstructor() {
        assertNotNull(ulasan);
    }

    @Test
     void testGetter() {
        assertEquals("ulasan1", ulasan.getId());
        assertEquals("user123", ulasan.getIdUser());
        assertEquals(4, ulasan.getRating().intValue());
        assertEquals("Game seru untuk action.", ulasan.getDeskripsi());
        assertEquals("game1", ulasan.getGame());
        assertNotNull(ulasan.getDate());
        assertEquals(LocalDate.now(), ulasan.getDate());
    }

    @Test
     void testValidationRatingLow() {
        Ulasan testRatingUlasan = new Ulasan.Builder()
                                .rating(0) // Invalid rating
                                .build();

        assertEquals(1, testRatingUlasan.getRating().intValue());
    }

    @Test
     void testValidationRatingHigh() {
        Ulasan testRatingUlasan = new Ulasan.Builder()
                .rating(10) // Invalid rating
                .build();

        assertEquals(5, testRatingUlasan.getRating().intValue());
    }

    @Test
     void testValidationRatingNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ulasan.Builder()
                    .rating(null) // Empty description
                    .build();
        });
        assertTrue(exception.getMessage().contains("Rating tidak boleh kosong"));
    }

    @Test
     void testNullDescription() {
        Ulasan testDescriptionUlasan = new Ulasan.Builder()
                    .deskripsi(null) // Empty description
                    .build();
        assertEquals("", testDescriptionUlasan.getDeskripsi());
    }

    @Test
     void testFilledDescription() {
        Ulasan testDescriptionUlasan = new Ulasan.Builder()
                .deskripsi("Wow keren!") // Empty description
                .build();
        assertEquals("Wow keren!", testDescriptionUlasan.getDeskripsi());
    }

    @Test
     void testChangeDescription() {
        Ulasan testDescriptionUlasan = new Ulasan.Builder()
                .deskripsi("Wow keren!") // Empty description
                .build();

        testDescriptionUlasan.setDeskripsi("Biasa aja deng");
        assertEquals("Biasa aja deng", testDescriptionUlasan.getDeskripsi());
    }
}
