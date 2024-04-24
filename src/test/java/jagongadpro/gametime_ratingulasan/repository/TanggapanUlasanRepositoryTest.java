package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TanggapanUlasanRepositoryTest {

    private TanggapanUlasanRepository repository;
    private TanggapanUlasan tanggapanUlasan;
    private Ulasan ulasan;

    @BeforeEach
    void setUp() {
        repository = new TanggapanUlasanRepository();
        tanggapanUlasan = new TanggapanUlasan();
        ulasan = new Ulasan();
        ulasan.setId("ulasan123");

        tanggapanUlasan.setPenjualId("penjual123");
        tanggapanUlasan.setUlasan(ulasan);
        tanggapanUlasan.setTanggapan("Terima kasih atas ulasannya!");
        tanggapanUlasan.setDate(LocalDate.now());
    }

    @Test
    void testCreateTanggapanUlasan() {
        TanggapanUlasan savedTanggapanUlasan = repository.create(tanggapanUlasan);
        assertNotNull(savedTanggapanUlasan.getId(), "TanggapanUlasan ID should not be null after creation");
        assertEquals(tanggapanUlasan, savedTanggapanUlasan, "The created tanggapanUlasan should match the input tanggapanUlasan");
    }

    @Test
    void testFindById() {
        TanggapanUlasan savedTanggapanUlasan = repository.create(tanggapanUlasan);
        TanggapanUlasan foundTanggapanUlasan = repository.findById(savedTanggapanUlasan.getId());
        assertEquals(savedTanggapanUlasan, foundTanggapanUlasan, "The found tanggapanUlasan should match the created tanggapanUlasan");
    }

    @Test
    void testFindByUlasanId() {
        repository.create(tanggapanUlasan);
        TanggapanUlasan foundTanggapanUlasan = repository.findByUlasanId("ulasan123");
        assertNotNull(foundTanggapanUlasan, "Should find tanggapanUlasan by ulasan ID");
    }

    @Test
    void testFindAllByPenjualId() {
        repository.create(tanggapanUlasan);
        List<TanggapanUlasan> foundTanggapanUlasanList = repository.findAllByPenjualId("penjual123");
        assertFalse(foundTanggapanUlasanList.isEmpty(), "Should find tanggapanUlasan by penjual ID");
        assertTrue(foundTanggapanUlasanList.contains(tanggapanUlasan), "The list should contain the created tanggapanUlasan");
    }

    @Test
    void testUpdateTanggapanUlasan() {
        TanggapanUlasan savedTanggapanUlasan = repository.create(tanggapanUlasan);
        savedTanggapanUlasan.setTanggapan("Updated tanggapan");
        TanggapanUlasan updatedTanggapanUlasan = repository.update(savedTanggapanUlasan);
        assertEquals("Updated tanggapan", updatedTanggapanUlasan.getTanggapan(), "The tanggapan should be updated in the edited tanggapanUlasan");
    }

    @Test
    void testDeleteTanggapanUlasan() {
        TanggapanUlasan savedTanggapanUlasan = repository.create(tanggapanUlasan);
        repository.delete(savedTanggapanUlasan.getId());
        assertNull(repository.findById(savedTanggapanUlasan.getId()), "TanggapanUlasan should be null after deletion");
    }

    @Test
    void testCreateTanggapanUlasanNull() {
        assertThrows(IllegalArgumentException.class, () -> repository.create(null), "Creating with null tanggapanUlasan should throw IllegalArgumentException");
    }

    @Test
    void testFindByIdNonExistent() {
        assertNull(repository.findById("nonExistentId"), "Finding non-existent tanggapanUlasan should return null");
    }

    @Test
    void testFindByUlasanIdNonExistent() {
        assertNull(repository.findByUlasanId("nonExistentId"), "Finding tanggapanUlasan by non-existent ulasan ID should return an empty list");
    }

    @Test
    void testFindAllByPenjualIdNonExistent() {
        assertTrue(repository.findAllByPenjualId("nonExistentId").isEmpty(), "Finding tanggapanUlasan by non-existent penjual ID should return an empty list");
    }

    @Test
    void testUpdateNonExistent() {
        TanggapanUlasan nonExistentTanggapanUlasan = new TanggapanUlasan();
        nonExistentTanggapanUlasan.setId("nonExistentId");
        assertNull(repository.update(nonExistentTanggapanUlasan), "Updating non-existent tanggapanUlasan should return null");
    }

    @Test
    void testDeleteNonExistent() {
        assertDoesNotThrow(() -> repository.delete("nonExistentId"), "Deleting non-existent tanggapanUlasan should not throw an exception");
    }
}
