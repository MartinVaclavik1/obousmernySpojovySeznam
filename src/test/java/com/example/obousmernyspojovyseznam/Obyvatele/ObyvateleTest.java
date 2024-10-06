package com.example.obousmernyspojovyseznam.Obyvatele;

import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ObyvateleTest {

    private final String cestaKCSVSouboru = "C:\\Users\\admin\\OneDrive - Univerzita Pardubice\\Plocha\\Škola\\2\\Datové Struktury\\Sem.A\\kraje.csv";

    @Test
    public void testImportData01() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
        } catch (ObyvateleException x) {
            fail();
        }


    }

    @Test
    public void testZjistiPrumer01() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
            float prumer = obyvatele.zjistiPrumer(enumKraj.JIHOMORAVSKY);
            System.out.println(prumer);
        } catch (ObyvateleException x) {
            fail();
        }
    }

    @Test
    public void testZjistiPrumer02() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
            float prumer = obyvatele.zjistiPrumer(enumKraj.HLAVNIMESTOPRAHA);
            System.out.println(prumer);
        } catch (ObyvateleException x) {
            fail();
        }
    }

    @Test
    public void testZobrazObce01() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
            obyvatele.zobrazObce(enumKraj.KRALOVEHRADECKY);
        } catch (ObyvateleException x) {
            fail();
        }
    }

    @Test
    public void testZobrazObceNadPrumer01() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
            System.out.println(obyvatele.zjistiPrumer(enumKraj.LIBERECKY));
            obyvatele.zobrazObceNadPrumer(enumKraj.LIBERECKY);
        } catch (ObyvateleException x) {
            fail();
        }
    }

    @Test
    public void testZobrazObceNadPrumer02() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
            System.out.println(obyvatele.zjistiPrumer(enumKraj.KARLOVARSKY));
            obyvatele.zobrazObceNadPrumer(enumKraj.KARLOVARSKY);
        } catch (ObyvateleException x) {
            fail();
        }
    }

    @Test
    public void testZobrazObceNadPrumer03() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.importData(cestaKCSVSouboru);
            System.out.println(obyvatele.zjistiPrumer(enumKraj.MORAVSKOSLEZSKY));
            obyvatele.zobrazObceNadPrumer(enumKraj.MORAVSKOSLEZSKY);
        } catch (ObyvateleException x) {
            fail();
        }
    }

    @Test
    public void testnacti01() {
        try {
            Obyvatele obyvatele = new Obyvatele();
            obyvatele.nacti("zaloha.bin");
        } catch (ObyvateleException x) {
            fail();
        }
    }

}