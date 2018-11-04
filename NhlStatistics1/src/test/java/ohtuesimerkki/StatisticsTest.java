/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author annapiir
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void etsintaLoytaaPelaajan() {
        assertEquals("Semenko", stats.search("Sem").getName());
    }
    
    @Test
    public void etsintaEiLoydaPelaajaa() {
        assertEquals(null, stats.search("Mikkonen"));
    }
    
    @Test
    public void joukkuelistausPalauttaaPelaajat() {
        ArrayList team = (ArrayList) stats.team("PIT");
        Player player = (Player) team.get(0);
        
        assertEquals("Lemieux", player.getName());
    }
    
    @Test
    public void topScorersPalauttaaOikeanEkana() {
        ArrayList<Player> players = (ArrayList<Player>) stats.topScorers(1);
        
        assertEquals("Gretzky", players.get(0).getName());
    }
}
