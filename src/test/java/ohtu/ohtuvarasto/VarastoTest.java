package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto tilavuudeton_varasto;
    Varasto varasto_tilavuus_alkusaldo;
    Varasto varasto_saldolla_ilman_tilavuutta;
    Varasto varasto_saldo_tilavuutta_isompi;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        tilavuudeton_varasto = new Varasto(-1);
        varasto_tilavuus_alkusaldo = new Varasto(10, 9);
        varasto_saldolla_ilman_tilavuutta = new Varasto(-1, -1);
        varasto_saldo_tilavuutta_isompi = new Varasto(10, 12);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaTyhjaTilavuus() {
        assertEquals(0, tilavuudeton_varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldollaOikeaTilavuus() {
        assertEquals(10, varasto_tilavuus_alkusaldo.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        assertEquals(9, varasto_tilavuus_alkusaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiLuoNegatiivistaTilavuuttaSaldolla() {
        assertEquals(0, varasto_saldolla_ilman_tilavuutta.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNollaaNegatiivisenSaldon() {
        assertEquals(0, varasto_saldolla_ilman_tilavuutta.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAntaaSaldoaMaxTilavuudenVerran() {
        assertEquals(10, varasto_saldo_tilavuutta_isompi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiLisaaNegatiivista() {
        varasto_tilavuus_alkusaldo.lisaaVarastoon(-8);
        
        assertEquals(9, varasto_tilavuus_alkusaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaMaxTilavuuden() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottamistaEiTehdaJosNegatiivinen() {
        varasto.lisaaVarastoon(8);
        
        varasto.otaVarastosta(-2);
        
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void annetaanKaikkiMitaVoidaan() {
        varasto.lisaaVarastoon(8);
        
        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }
    
    @Test
    public void annetaanKaikkiJaSaldoOnNolla() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(10);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTulostusToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}