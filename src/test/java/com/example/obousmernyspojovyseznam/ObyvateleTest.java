package com.example.obousmernyspojovyseznam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObyvateleTest {

    @Test
    public void testImportData(){
        Obyvatele obyvatele = new Obyvatele();
        //TODO potom přesunout někam do projektu
        obyvatele.importData("C:\\Users\\admin\\OneDrive - Univerzita Pardubice\\Plocha\\Škola\\2\\Datové Struktury\\Sem.A\\kraje.csv");
    }
}