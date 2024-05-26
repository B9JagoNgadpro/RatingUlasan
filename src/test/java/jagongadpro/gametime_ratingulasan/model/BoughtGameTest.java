package jagongadpro.gametime_ratingulasan.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoughtGameTest {

    @Test
    public void testBoughtGameConstructorAndGetters() {
        BoughtGame boughtGame = new BoughtGame("game1", "user1");
        assertEquals("game1", boughtGame.getIdGame());
        assertEquals("user1", boughtGame.getIdUser());
        assertFalse(boughtGame.isReviewed());
    }

    @Test
    public void testSetters() {
        BoughtGame boughtGame = new BoughtGame();
        boughtGame.setIdGame("game1");
        boughtGame.setIdUser("user1");
        boughtGame.setReviewed(true);

        assertEquals("game1", boughtGame.getIdGame());
        assertEquals("user1", boughtGame.getIdUser());
        assertTrue(boughtGame.isReviewed());
    }

    @Test
    public void testDefaultConstructor() {
        BoughtGame boughtGame = new BoughtGame();
        assertNull(boughtGame.getIdGame());
        assertNull(boughtGame.getIdUser());
        assertFalse(boughtGame.isReviewed());
    }
}
