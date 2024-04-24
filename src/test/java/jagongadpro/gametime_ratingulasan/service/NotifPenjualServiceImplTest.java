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

    private NotifPenjual notifPenjual;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        notifPenjual = new NotifPenjual("penjual1", new ArrayList<>());
    }

    @Test
    void testSaveNotifPenjual() {
        when(notifPenjualRepository.save(notifPenjual)).thenReturn(notifPenjual);
        NotifPenjual savedNotif = notifPenjualService.saveNotifPenjual(notifPenjual);
        assertNotNull(savedNotif);
        assertEquals(notifPenjual, savedNotif);
    }

    @Test
    void testFindNotifPenjualById() {
        when(notifPenjualRepository.findById("penjual1")).thenReturn(notifPenjual);
        NotifPenjual foundNotif = notifPenjualService.findNotifPenjualById("penjual1");
        assertNotNull(foundNotif);
        assertEquals(notifPenjual, foundNotif);
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
}
