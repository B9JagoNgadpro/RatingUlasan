package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UlasanRepositoryTest {

    @InjectMocks
    private UlasanRepository repository;
    private Ulasan ulasan1;
    private Ulasan ulasan2;

    @BeforeEach
    void setUp() {
        ulasan1 = new Ulasan.Builder()
                .id("ulasan1")
                .idUser("user123")
                .game("game1")
                .rating(3)
                .deskripsi("Great game!")
                .date(LocalDate.now())
                .build();

        ulasan2 = new Ulasan.Builder()
                .id("ulasan2")
                .idUser("user124")
                .game("game2")
                .rating(5)
                .deskripsi("Excellent game!")
                .date(LocalDate.now().minusDays(1))
                .build();
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
        Ulasan newUlasan = new Ulasan.Builder().build();
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
        Ulasan resultUlasan = repository.edit(savedUlasan);
        assertEquals("Updated description", resultUlasan.getDeskripsi());
    }

    @Test
    void testFindAll() {
        repository.create(ulasan1);
        repository.create(ulasan2);
        List<Ulasan> allUlasan = repository.findAll();
        assertFalse(allUlasan.isEmpty());
        assertEquals(2, allUlasan.size());
    }

    @Test
    void testFindAllByUserId() {
        repository.create(ulasan1);
        assertFalse(repository.findAllByUserId("user123").isEmpty());
    }

    @Test
    void testFindAllByGameId() {
        repository.create(ulasan1);
        assertFalse(repository.findAllByGameId("game1").isEmpty());
    }
}
