package com.example.obousmernyspojovyseznam.AbstrDoubleList;

import java.util.Iterator;

public interface IAbstrDoubleList<T> {
    void zrus();//zrušení celého seznamu,
    boolean jePrazdny();//test naplněnosti seznamu,
    void vlozPrvni(T data);//vložení prvku do seznamu na první místo
    void vlozPosledni(T data);//vložení prvku do seznamu na poslední místo,
    void vlozNaslednika(T data);//vložení prvku do seznamu jakožto následníka aktuálního prvku

    void vlozPredchudce(T data);//vložení prvku do seznamu jakožto předchůdce aktuálního prvku

    T zpristupniAktualni();//zpřístupnění aktuálního prvku seznamu,
    T zpristupniPrvni();//zpřístupnění prvního prvku seznamu,
    T zpristupniPosledni();//zpřístupnění posledního prvku seznamu,
    T zpristupniNaslednika();//zpřístupnění následníka aktuálního prvku,
    T zpristupniPredchudce();//zpřístupnění předchůdce aktuálního prvku, Pozn. Operace typu zpřístupni, přenastavují pozici aktuálního prvku

    T odeberAktualni();//odebrání (vyjmutí) aktuálního prvku ze seznamu poté je aktuální prvek nastaven na první prvek

    T odeberPrvni();//odebrání prvního prvku ze seznamu,
    T odeberPosledni();//odebrání posledního prvku ze seznamu,
    T odeberNaslednika();//odebrání následníka aktuálního prvku ze seznamu,
    T odeberPredchudce();//odebrání předchůdce aktuálního prvku ze seznamu,
    Iterator<T> iterator(); //vytvoří iterátor (dle rozhraní Iterable)

}
