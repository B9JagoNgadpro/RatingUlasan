package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.NotifPenjual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotifPenjualRepositoryTest {

    @InjectMocks
    private NotifPenjualRepository repository;

    @BeforeEach
    void setUp() {
        // Menambahkan data dummy untuk pengujian
        NotifPenjual notifPenjual1 = new NotifPenjual();
        notifPenjual1.setIdPenjual("penjual1");
        repository.save(notifPenjual1);

        NotifPenjual notifPenjual2 = new NotifPenjual();
        notifPenjual2.setIdPenjual("penjual2");
        repository.save(notifPenjual2);
    }

    @Test
    void testSave() {
        NotifPenjual notifPenjual = new NotifPenjual();
        notifPenjual.setIdPenjual("penjual3");
        repository.save(notifPenjual);

        assertTrue(repository.existsById("penjual3"));
    }

    @Test
    void testFindById() {
        NotifPenjual foundNotifPenjual = repository.findById("penjual1");
        assertNotNull(foundNotifPenjual);
        assertEquals("penjual1", foundNotifPenjual.getIdPenjual());
    }

    @Test
    void testDeleteById() {
        repository.deleteById("penjual1");
        assertFalse(repository.existsById("penjual1"));
    }

    @Test
    void testExistsById() {
        assertTrue(repository.existsById("penjual1"));
        assertFalse(repository.existsById("penjual3"));
    }
}
