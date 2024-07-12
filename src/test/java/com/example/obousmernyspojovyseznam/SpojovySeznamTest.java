package com.example.obousmernyspojovyseznam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpojovySeznamTest {
    private static class TestClass {

        int a;

        public TestClass(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "T" + a;
        }

    }

    /**
     * *
     * Sada instancí testovací třídy pro ověření implementace třídy
     * SpojovySeznam
     */
    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);
    private final TestClass T5 = new TestClass(5);
    private final TestClass T6 = new TestClass(6);
    private final TestClass T7 = new TestClass(7);
    private final TestClass T8 = new TestClass(8);
    private final TestClass T9 = new TestClass(9);

    @Test
    public void testVlozPrvni01(){
        SpojovySeznam<TestClass> seznam = new SpojovySeznam<>();
        seznam.vlozPrvni(T1);
        TestClass[] expected = new TestClass[]{T1};
        TestClass[] result = new TestClass[]{seznam.dejPrvni()};//
        assertArrayEquals(expected, result);
    }

    @Test
    public void testVlozPrvni02(){
        SpojovySeznam<TestClass> seznam = new SpojovySeznam<>();
        seznam.vlozPrvni(T1);
        seznam.vlozPrvni(T2);
        TestClass[] expected = new TestClass[]{T2, T1};
        TestClass[] result = new TestClass[]{seznam.dejPrvni(), seznam.dejPosledni()};//
        assertArrayEquals(expected, result);
    }

    @Test
    public void testVlozDalsi01(){
        SpojovySeznam<TestClass> seznam = new SpojovySeznam<>();
        seznam.vlozPrvni(T1);
        seznam.nastavPrvni();
        seznam.vlozDalsi(T2);
        TestClass[] expected = new TestClass[]{T1, T2};
        TestClass[] result = new TestClass[]{seznam.dejPrvni(), seznam.dejPosledni()};//
        assertArrayEquals(expected, result);
    }

    @Test
    public void testVlozDalsi02(){
        SpojovySeznam<TestClass> seznam = new SpojovySeznam<>();
        seznam.vlozPrvni(T1);
        seznam.vlozPrvni(T3);
        seznam.nastavPrvni();
        seznam.vlozDalsi(T2);
        TestClass[] expected = new TestClass[]{T3, T2, T1};
        seznam.nastavDalsi();
        TestClass prostredni = seznam.dejAktualni();
        TestClass[] result = new TestClass[]{seznam.dejPrvni(), prostredni,seznam.dejPosledni()};//
        assertArrayEquals(expected, result);
    }

    @Test
    public void testVlozPosledni01(){
        SpojovySeznam<TestClass> seznam = new SpojovySeznam<>();
        seznam.vlozPosledni(T1);
        TestClass[] expected = new TestClass[]{T1};
        TestClass[] result = new TestClass[]{seznam.dejPosledni()};//
        assertArrayEquals(expected, result);
    }

    @Test
    public void testVlozPosledni02(){
        SpojovySeznam<TestClass> seznam = new SpojovySeznam<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        TestClass[] expected = new TestClass[]{T1, T2};
        TestClass[] result = new TestClass[]{seznam.dejPrvni(), seznam.dejPosledni()};//
        assertArrayEquals(expected, result);
    }
}