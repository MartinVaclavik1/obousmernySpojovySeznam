package com.example.obousmernyspojovyseznam.Obyvatele;

import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import org.junit.jupiter.api.Test;


class ObyvateleTest {

    private final String cestaKCSVSouboru = "C:\\Users\\admin\\OneDrive - Univerzita Pardubice\\Plocha\\Škola\\2\\Datové Struktury\\Sem.A\\kraje.csv";

    @Test
    public void testImportData01(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
    }

    @Test
    public void testZjistiPrumer01(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
        float prumer = obyvatele.zjistiPrumer(enumKraj.NULA);
        System.out.println(prumer);
    }

    @Test
    public void testZjistiPrumer02(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
        float prumer = obyvatele.zjistiPrumer(enumKraj.HLAVNYMESTOPRAHA);
        System.out.println(prumer);
    }

    @Test
    public void testZobrazObce01(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
        obyvatele.zobrazObce(enumKraj.NULA);
    }

    @Test
    public void testZobrazObceNadPrumer01(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
        System.out.println(obyvatele.zjistiPrumer(enumKraj.NULA));
        obyvatele.zobrazObceNadPrumer(enumKraj.NULA);
    }

    @Test
    public void testZobrazObceNadPrumer02(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
        System.out.println(obyvatele.zjistiPrumer(enumKraj.KARLOVARSKY));
        obyvatele.zobrazObceNadPrumer(enumKraj.KARLOVARSKY);
    }

    @Test
    public void testZobrazObceNadPrumer03(){
        Obyvatele obyvatele = new Obyvatele();
        obyvatele.importData(cestaKCSVSouboru);
        System.out.println(obyvatele.zjistiPrumer(enumKraj.MORAVSKOSLEZSKY));
        obyvatele.zobrazObceNadPrumer(enumKraj.MORAVSKOSLEZSKY);
    }

}