package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

public class NotifPenjualTest {
    private NotifPenjual notifPenjual;
    private ArrayList<String> notifikasi;

    @BeforeEach
    void setUp() {
        this.notifikasi = new ArrayList<>();
        notifPenjual = new NotifPenjual();

        String notif1 = "User123 telah mengulas produk game123 kamu!";
        String notif2 = "User456 telah mengulas produk game123 kamu!";
        String notif3 = "User789 telah mengulas produk game456 kamu!";
        notifikasi.add(notif1);
        notifikasi.add(notif2);
        notifikasi.add(notif3);
    }

    @Test
    public void testConstructor() {
        NotifPenjual newNotifPenjual = new NotifPenjual();
        assertNotNull(newNotifPenjual);
    }

    @Test
    public void testSetterGetter() {
        notifPenjual.setIdPenjual("penjual123");
        assertEquals("penjual123", notifPenjual.getIdPenjual());
    }

    @Test
    public void testEmptyNotifications() {
        notifikasi.clear();
        notifPenjual.setNotifs(notifikasi);
        assertFalse(!notifPenjual.getNotifs().isEmpty());
    }

    @Test
    public void testListNotifications() {
        notifPenjual.setNotifs(notifikasi);
        assertArrayEquals(notifikasi.toArray(), notifPenjual.getNotifs().toArray());
    }

    @Test
    public void testNewNotifs(){
        notifPenjual.newNotif("User1 telah mengulas produk game1 kamu!");
        assertFalse(notifPenjual.getNotifs().isEmpty());
    }
}
