package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UlasanRepositoryTest {

    @InjectMocks
    private UlasanRepository repository;
    private Ulasan ulasan1;
    private Ulasan ulasan2;

    @BeforeEach
    void setUp() {
        ulasan1 = new Ulasan();
        ulasan1.setIdUser("user123");
        ulasan1.setDate(LocalDate.now());
        Game game1 = new Game();
        game1.setId("game123");
        ulasan1.setGame(game1);
        ulasan1.setRating(3);
        ulasan1.setDeskripsi("Great game!");

        ulasan2 = new Ulasan();
        ulasan2.setIdUser("user124");
        ulasan2.setDate(LocalDate.now().minusDays(1));
        Game game2 = new Game();
        game2.setId("game124");
        ulasan2.setGame(game2);
        ulasan2.setRating(5);
        ulasan2.setDeskripsi("Excellent game!");
    }

    @Test
    void testCreateUlasan() {
        Ulasan savedUlasan = repository.create(ulasan1);
        assertNotNull(savedUlasan.getId());
        assertEquals(ulasan1, savedUlasan);

        Ulasan savedUlasan2 = repository.create(ulasan2);
        assertNotNull(savedUlasan2.getId());
        assertEquals(ulasan2, savedUlasan2);
    }

    @Test
    void testCreateUlasanUUID() {
        Ulasan newUlasan = new Ulasan();
        Ulasan savedUlasan = repository.create(newUlasan);
        assertNotNull(savedUlasan.getId());
        assertEquals(newUlasan, savedUlasan);
    }

    @Test
    void testFindById() {
        Ulasan savedUlasan = repository.create(ulasan1);
        Ulasan foundUlasan = repository.findById(savedUlasan.getId());
        assertEquals(savedUlasan, foundUlasan);
    }

    @Test
    void testDeleteUlasan() {
        Ulasan savedUlasan = repository.create(ulasan1);
        Ulasan deletedUlasan = repository.delete(savedUlasan.getId());
        assertThrows(IllegalArgumentException.class, () -> repository.findById(savedUlasan.getId()));
    }

    @Test
    void testEditUlasan() {
        Ulasan savedUlasan = repository.create(ulasan1);
        savedUlasan.setDeskripsi("Updated description");
        Ulasan updatedUlasan = repository.edit(savedUlasan);
        assertEquals("Updated description", updatedUlasan.getDeskripsi());
    }

    @Test
    void testFindAll() {
        repository.create(ulasan1);
        repository.create(ulasan2);
        Iterator<Ulasan> allUlasan = repository.findAll();
        assertTrue(allUlasan.hasNext());
        allUlasan.next(); // Consume the first
        assertTrue(allUlasan.hasNext());
    }

    @Test
    void testFindAllByUserId() {
        repository.create(ulasan1);
        assertFalse(repository.findAllByUserId("user123").isEmpty());
    }

    @Test
    void testFindAllByGameId() {
        repository.create(ulasan1);
        assertTrue(!repository.findAllByGameId("game123").isEmpty());
    }
}
