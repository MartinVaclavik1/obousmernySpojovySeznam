package com.example.obousmernyspojovyseznam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Obyvatele implements IObyvatele{
    //TODO
    @Override
    public int importData(String soubor) {
        int korektneNactene = 0;
        try(BufferedReader nactenySoubor = new BufferedReader(new FileReader(new File(soubor)))) {
            String radek = nactenySoubor.readLine();
            while (radek != null){
                String[] rozdelenyRadek = radek.split(";");
                System.out.println(radek);

                radek = nactenySoubor.readLine();
            }

            //TODO chytat konkrétní chyby
        }catch (Exception x){
            //TODO vyhazovat chybu
            System.err.println("chyba při načítání souboru");
        }
        return korektneNactene;
    }

    @Override
    public void vlozObec(Obec obec, enumPozice pozice, enumKraj kraj) {

    }

    @Override
    public Obec zpristupniObec(enumPozice pozice, enumKraj Kraj) {
        return null;
    }

    @Override
    public Obec odeberObec(enumPozice pozice, enumKraj Kraj) {
        return null;
    }

    @Override
    public float zjistiPrumer(enumKraj Kraj) {
        return 0;
    }

    @Override
    public void zobrazObce(enumKraj Kraj) {

    }

    @Override
    public void zobrazObceNadPrumer(enumKraj Kraj) {

    }

    @Override
    public void zrus(enumKraj Kraj) {

    }
}
