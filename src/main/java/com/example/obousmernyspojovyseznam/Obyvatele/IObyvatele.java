package com.example.obousmernyspojovyseznam.Obyvatele;

import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import com.example.obousmernyspojovyseznam.ENUMS.enumPozice;
import com.example.obousmernyspojovyseznam.Obec;

public interface IObyvatele {
    int importData(String soubor) throws ObyvateleException; // – provede import dat z datového souboru
    // kraje.csv, kde číslo kraje odpovídá indexu pole-1. Návratová hodnota přestavuje počet
    // úspěšně načtených záznamů.
    void vlozObec(Obec obec, enumPozice pozice, enumKraj kraj) throws ObyvateleException; // -
    // vloží novou obec do seznamu obcí na příslušnou pozici (první, poslední, předchůdce,
    // následník), v odpovídajícím kraji
    Obec zpristupniObec(enumPozice pozice, enumKraj Kraj) throws ObyvateleException; //-
    //    zpřístupní obec z požadované pozice (první, poslední, předchůdce, následník, aktuální),
    //    v odpovídajícím kraji

    Obec odeberObec(enumPozice pozice, enumKraj Kraj) throws ObyvateleException; // - odebere
    // obec z požadované pozice (první, poslední, předchůdce, následník, aktuální),
    // v odpovídajícím kraji
    float zjistiPrumer(enumKraj Kraj) throws ObyvateleException; //– zjistí průměrný počet obyvatel
    // v kraji, pokud je hodnota kraje rovna nule, pak je průměr spočítán pro všechny kraje.
    void zobrazObce(enumKraj Kraj) throws ObyvateleException;  //– pomocí iterátoru provede výpis obcí
    // v daném kraji, pokud je hodnota kraje rovna nule, pak jsou vypsány všechny kraje.
    void zobrazObceNadPrumer(enumKraj Kraj) throws ObyvateleException; // – pomocí iterátoru provede
    // výpis obcí, které mají v daném kraji nadprůměrný počet obyvatel. Pokud je hodnota kraje
    // rovna nule, pak je průměr spočítán pro všechny kraje.
    void zrus(enumKraj Kraj) throws ObyvateleException; // – zruší všechny obce v kraji. Pokud je hodnota
    // kraje rovna nule, pak zruší všechny obce.

}
