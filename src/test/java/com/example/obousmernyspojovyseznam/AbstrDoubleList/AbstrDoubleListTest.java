package com.example.obousmernyspojovyseznam.AbstrDoubleList;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AbstrDoubleListTest {

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
    public void testJePrazdny01() {
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        boolean expected = true;
        boolean result = seznam.jePrazdny();
        assertEquals(expected, result);
    }

    @Test
    public void testJePrazdny02() throws AbstrDoubleListException {
            IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
            seznam.vlozPrvni(T1);
            boolean expected = false;
            boolean result = seznam.jePrazdny();
            assertEquals(expected, result);
    }

    @Test
    public void testVlozPrvni01() throws AbstrDoubleListException {
            IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
            seznam.vlozPrvni(T1);
            TestClass[] expected = new TestClass[]{T1};
            TestClass[] result = new TestClass[]{seznam.zpristupniPrvni()};//
            assertArrayEquals(expected, result);
    }

    @Test
    public void testVlozPrvni02() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPrvni(T1);
        seznam.vlozPrvni(T2);
        TestClass[] expected = new TestClass[]{T2, T1};
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), seznam.zpristupniPosledni()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testVlozNaslednika01() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPrvni(T1);
        seznam.zpristupniPrvni();
        seznam.vlozNaslednika(T2);
        TestClass[] expected = new TestClass[]{T1, T2};
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), seznam.zpristupniPosledni()};//
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testVlozNaslednika02() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPrvni(T1);
        seznam.vlozPrvni(T3);
        seznam.zpristupniPrvni();
        seznam.vlozNaslednika(T2);
        TestClass[] expected = new TestClass[]{T3, T2, T1};
        seznam.zpristupniNaslednika();
        TestClass prostredni = seznam.zpristupniAktualni();
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), prostredni, seznam.zpristupniPosledni()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testVlozPosledni01() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        TestClass[] expected = new TestClass[]{T1};
        TestClass[] result = new TestClass[]{seznam.zpristupniPosledni()};//
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testVlozPosledni02() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        TestClass[] expected = new TestClass[]{T1, T2};
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), seznam.zpristupniPosledni()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testOdeberAktualni01() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        seznam.zpristupniPrvni();
        seznam.zpristupniNaslednika();
        seznam.odeberAktualni();
        TestClass[] expected = new TestClass[]{T1, T3};
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), seznam.zpristupniPosledni()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testOdeberAktualni02() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        seznam.zpristupniPrvni();
        seznam.zpristupniNaslednika();

        TestClass expected = T2;
        TestClass result = seznam.odeberAktualni();
        assertEquals(expected, result);
        
    }

    @Test
    public void testOdeberAktualni03() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        seznam.zpristupniPrvni();
        seznam.odeberAktualni();
        TestClass[] expected = new TestClass[]{T2, T3};
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), seznam.zpristupniPosledni()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testOdeberAktualni04() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        seznam.zpristupniPosledni();
        seznam.odeberAktualni();
        TestClass[] expected = new TestClass[]{T1, T2};
        TestClass[] result = new TestClass[]{seznam.zpristupniPrvni(), seznam.zpristupniPosledni()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testIteratorNext01() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        Iterator<TestClass> iterator = seznam.iterator();
        TestClass[] expected = new TestClass[]{T1, T2, T3};
        TestClass[] result = new TestClass[]{iterator.next(), iterator.next(), iterator.next()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testIteratorNext02() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        seznam.vlozPosledni(T4);
        seznam.vlozPosledni(T5);
        seznam.vlozPosledni(T6);
        seznam.vlozPosledni(T7);
        seznam.vlozPosledni(T8);
        seznam.vlozPosledni(T9);
        Iterator<TestClass> iterator = seznam.iterator();
        TestClass[] expected = new TestClass[]{T1, T2, T3, T4, T5, T6, T7, T8, T9};
        TestClass[] result = new TestClass[]{iterator.next(), iterator.next(), iterator.next(),
                iterator.next(), iterator.next(), iterator.next(),
                iterator.next(), iterator.next(), iterator.next()};
        assertArrayEquals(expected, result);
        
    }

    @Test
    public void testIteratorNext03() throws AbstrDoubleListException {
        
        IAbstrDoubleList<TestClass> seznam = new AbstrDoubleList<>();
        seznam.vlozPosledni(T1);
        seznam.vlozPosledni(T2);
        seznam.vlozPosledni(T3);
        seznam.vlozPosledni(T4);
        Iterator<TestClass> iterator = seznam.iterator();
        TestClass[] expected = new TestClass[]{T1, T2, T3, T4, null};
        TestClass[] result = new TestClass[]{iterator.next(), iterator.next(),
                iterator.next(), iterator.next(), iterator.next()};
        assertArrayEquals(expected, result);
        
    }
}