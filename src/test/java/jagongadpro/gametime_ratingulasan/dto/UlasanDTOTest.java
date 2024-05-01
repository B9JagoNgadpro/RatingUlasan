package jagongadpro.gametime_ratingulasan.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UlasanDTOTest {

    @Test
    public void testUlasanDTO() {
        UlasanDTO ulasan = new UlasanDTO();
        ulasan.setId("1");
        ulasan.setIdUser("user001");
        ulasan.setGame("Skyrim");
        ulasan.setRating(5);
        ulasan.setDeskripsi("Amazing gameplay and graphics!");
        ulasan.setDate(LocalDate.of(2024, 4, 30));

        assertEquals("1", ulasan.getId());
        assertEquals("user001", ulasan.getIdUser());
        assertEquals("Skyrim", ulasan.getGame());
        assertEquals(5, ulasan.getRating());
        assertEquals("Amazing gameplay and graphics!", ulasan.getDeskripsi());
        assertEquals(LocalDate.of(2024, 4, 30), ulasan.getDate());
    }
}
