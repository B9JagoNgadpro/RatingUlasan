package jagongadpro.gametime_ratingulasan.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TanggapanUlasanDTOTest {

    @Test
    public void testTanggapanUlasanDTO() {
        UlasanDTO ulasan = new UlasanDTO();
        ulasan.setId("1");
        ulasan.setIdUser("user001");
        ulasan.setGame("Skyrim");
        ulasan.setRating(5);
        ulasan.setDeskripsi("Excellent game!");
        ulasan.setDate(LocalDate.of(2024, 4, 30));

        TanggapanUlasanDTO tanggapan = new TanggapanUlasanDTO();
        tanggapan.setId("t1");
        tanggapan.setPenjualId("seller123");
        tanggapan.setUlasan(ulasan);
        tanggapan.setTanggapan("Thank you for your review!");
        tanggapan.setDate(LocalDate.of(2024, 5, 1));

        assertEquals("t1", tanggapan.getId());
        assertEquals("seller123", tanggapan.getPenjualId());
        assertEquals(ulasan, tanggapan.getUlasan());
        assertEquals("Thank you for your review!", tanggapan.getTanggapan());
        assertEquals(LocalDate.of(2024, 5, 1), tanggapan.getDate());
    }
}
