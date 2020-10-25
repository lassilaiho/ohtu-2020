package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemioux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchExistingPlayer() {
        var kurri = stats.search("Kurri");
        assertNotNull(kurri);
        assertEquals("Kurri", kurri.getName());
    }

    @Test
    public void searchNonExistentPlayer() {
        assertNull(stats.search("nobody"));
    }

    @Test
    public void getExistingTeam() {
        var edm = stats.team("EDM");
        assertNotNull(edm);
        assertEquals(3, edm.size());
        for (var player : edm) {
            assertEquals("EDM", player.getTeam());
        }
    }

    @Test
    public void getNonExistentTeam() {
        var empty = stats.team("none");
        assertNotNull(empty);
        assertEquals(0, empty.size());
    }

    @Test
    public void getNoTopScorers() {
        var scorers = stats.topScorers(0);
        assertNotNull(scorers);
        assertEquals(0, scorers.size());
    }

    @Test
    public void getSomeTopScorers() {
        var scorers = stats.topScorers(3);
        assertNotNull(scorers);
        assertEquals(3, scorers.size());
        assertEquals("Gretzky", scorers.get(0).getName());
        assertEquals("Lemioux", scorers.get(1).getName());
        assertEquals("Yzerman", scorers.get(2).getName());
    }

    @Test
    public void getTooManyScorers() {
        var scorers = stats.topScorers(6);
        assertNotNull(scorers);
        assertEquals(5, scorers.size());
        assertEquals("Gretzky", scorers.get(0).getName());
        assertEquals("Lemioux", scorers.get(1).getName());
        assertEquals("Yzerman", scorers.get(2).getName());
        assertEquals("Kurri", scorers.get(3).getName());
        assertEquals("Semenko", scorers.get(4).getName());
    }
}
