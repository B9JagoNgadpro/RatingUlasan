package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoughtGameIdTest {

    @Test
    public void testBoughtGameIdConstructorAndGetters() {
        BoughtGameId boughtGameId = new BoughtGameId("game1", "user1");
        assertEquals("game1", boughtGameId.getIdGame());
        assertEquals("user1", boughtGameId.getIdUser());
    }

    @Test
    public void testSetters() {
        BoughtGameId boughtGameId = new BoughtGameId();
        boughtGameId.setIdGame("game1");
        boughtGameId.setIdUser("user1");

        assertEquals("game1", boughtGameId.getIdGame());
        assertEquals("user1", boughtGameId.getIdUser());
    }

    @Test
    public void testEqualsAndHashCode() {
        BoughtGameId boughtGameId1 = new BoughtGameId("game1", "user1");
        BoughtGameId boughtGameId2 = new BoughtGameId("game1", "user1");
        BoughtGameId boughtGameId3 = new BoughtGameId("game2", "user2");

        assertEquals(boughtGameId1, boughtGameId2);
        assertNotEquals(boughtGameId1, boughtGameId3);
        assertEquals(boughtGameId1.hashCode(), boughtGameId2.hashCode());
        assertNotEquals(boughtGameId1.hashCode(), boughtGameId3.hashCode());
    }

    @Test
    public void testDefaultConstructor() {
        BoughtGameId boughtGameId = new BoughtGameId();
        assertNull(boughtGameId.getIdGame());
        assertNull(boughtGameId.getIdUser());
    }
}
