package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.NotifPenjual;
import jagongadpro.gametime_ratingulasan.repository.NotifPenjualRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotifPenjualServiceImplTest {

    @Mock
    private NotifPenjualRepository notifPenjualRepository;

    @InjectMocks
    private NotifPenjualServiceImpl notifPenjualService;

    private NotifPenjual notifPenjualBaru;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        notifPenjualBaru = new NotifPenjual("penjual1", new ArrayList<>());
    }

    @Test
    void testSaveNotifPenjual() {
        when(notifPenjualRepository.save(notifPenjualBaru)).thenReturn(notifPenjualBaru);
        NotifPenjual savedNotif = notifPenjualService.saveNotifPenjual(notifPenjualBaru);
        assertNotNull(savedNotif);
        assertEquals("penjual1", savedNotif.getIdPenjual());
    }

    @Test
    void testFindNotifPenjualById() {
        when(notifPenjualRepository.findById("penjual1")).thenReturn(notifPenjualBaru);
        NotifPenjual foundNotif = notifPenjualService.findNotifPenjualById("penjual1");
        assertNotNull(foundNotif);
        assertEquals(notifPenjualBaru, foundNotif);
    }

    @Test
    void testDeleteNotifPenjualById() {
        doNothing().when(notifPenjualRepository).deleteById("penjual1");
        notifPenjualService.deleteNotifPenjualById("penjual1");
        verify(notifPenjualRepository).deleteById("penjual1");
    }

    @Test
    void testCheckIfNotifPenjualExists() {
        when(notifPenjualRepository.existsById("penjual1")).thenReturn(true);
        assertTrue(notifPenjualService.checkIfNotifPenjualExists("penjual1"));
    }

    @Test
    void testObserverNotifications() {
        when(notifPenjualRepository.save(notifPenjualBaru)).thenReturn(notifPenjualBaru);
        NotifPenjual savedNotif = notifPenjualService.saveNotifPenjual(notifPenjualBaru);

        when(notifPenjualRepository.findById(savedNotif.getIdPenjual())).thenReturn(savedNotif);
        notifPenjualService.update("User456 telah mengulas produk game123 kamu!", savedNotif.getIdPenjual());

        when(notifPenjualRepository.findById(savedNotif.getIdPenjual())).thenReturn(savedNotif);
        NotifPenjual cariUlangPenjual = notifPenjualRepository.findById(savedNotif.getIdPenjual());
        assertFalse(cariUlangPenjual.getNotifs().isEmpty());
    }
}
