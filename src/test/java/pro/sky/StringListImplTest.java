package pro.sky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private final StringList out = new StringListImpl();

    @Test
    public void addTest() {
        out.add("1");
        out.add("2");
        out.add("3");

        assertEquals(3, out.size());
        assertEquals("1", out.get(0));

        out.add("4");
        out.add("5");
        out.add("6");
        out.add("7");

        assertEquals(7, out.size());
        assertArrayEquals(new String[]{"1", "2", "3", "4", "5", "6", "7"}, out.toArray());
    }

    @Test
    void addIndexTest() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("4");
        out.add("5");
        out.add("6");
        out.add(3, "7");

        assertEquals(7, out.size());
        assertEquals("7", out.get(3));
        assertArrayEquals(new String[]{"1", "2", "3", "7", "4", "5", "6"}, out.toArray());
    }

    @Test
    void setTest() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("4");
        out.add("5");
        out.add("6");
        out.set(3, "7");

        assertEquals(6, out.size());
        assertEquals("7", out.get(3));
        assertArrayEquals(new String[]{"1", "2", "3", "7", "5", "6"}, out.toArray());
    }

    @Test
    void removeByItemTest() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("4");
        out.add("5");
        out.add("6");
        out.add("7");

        assertEquals(7, out.size());

        out.remove("4");

        assertEquals(6, out.size());
        assertArrayEquals(new String[]{"1", "2", "3", "5", "6", "7"}, out.toArray());

        out.remove("5");
        out.remove("1");
        out.remove("7");

        assertEquals(3, out.size());
        assertArrayEquals(new String[]{"2", "3", "6"}, out.toArray());

        out.remove("2");
        out.remove("3");
        out.remove("6");

        assertTrue(out::isEmpty);
    }

    @Test
    void removeByIndexTest() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("4");
        out.add("5");
        out.add("6");
        out.add("7");

        assertEquals(7, out.size());

        out.remove(3);

        assertEquals(6, out.size());
        assertArrayEquals(new String[]{"1", "2", "3", "5", "6", "7"}, out.toArray());

        out.remove(4);
        out.remove(0);
        out.remove(3);

        assertEquals(3, out.size());
        assertArrayEquals(new String[]{"2", "3", "5"}, out.toArray());

        out.remove(2);
        out.remove(1);
        out.remove(0);

        assertTrue(out::isEmpty);
    }

    @Test
    void containsTest() {
        out.add("1");
        out.add("2");
        out.add("3");

        assertEquals(3, out.size());
        assertTrue(() -> out.contains("2"));
        assertTrue(() -> out.contains("1"));
        assertTrue(() -> out.contains("3"));
        assertFalse(() -> out.contains("7"));
    }

    @Test
    void indexOfTest() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("4");
        out.add("3");
        out.add("2");
        out.add("7");

        assertEquals(7, out.size());
        assertEquals(2, out.indexOf("3"));
        assertEquals(1, out.indexOf("2"));
    }

    @Test
    void lastIndexOfTest() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("4");
        out.add("3");
        out.add("2");
        out.add("7");

        assertEquals(7, out.size());
        assertEquals(4, out.lastIndexOf("3"));
        assertEquals(5, out.lastIndexOf("2"));
    }

    @Test
    void getTest() {
        out.add("ok");
        out.add("hi");
        out.add("bye");

        assertEquals("ok", out.get(0));
        assertEquals("hi", out.get(1));
        assertEquals("bye", out.get(2));
    }

    @Test
    void equalsTest() {
        StringList stringList = new StringListImpl();
        stringList.add("ok");
        stringList.add("hi");
        stringList.add("bye");

        out.add("ok");
        out.add("hi");
        out.add("bye");

        assertTrue(() -> out.equals(stringList));

        out.remove(0);

        assertFalse(() -> out.equals(stringList));
    }

    @Test
    void sizeTest() {
        out.add("ok");
        out.add("hi");
        out.add("bye");

        assertEquals(3, out.size());
        assertNotEquals(2, out.size());

        out.remove(2);

        assertEquals(2, out.size());
        assertNotEquals(3, out.size());
    }

    @Test
    void isEmptyTest() {
        assertTrue(out.isEmpty());

        out.add("ok");

        assertFalse(out.isEmpty());
    }

    @Test
    void clearTest() {
        out.add("ok");
        out.add("hi");
        out.add("bye");

        out.clear();

        assertTrue(out.isEmpty());
    }

    @Test
    void toArrayTest() {
        out.add("ok");
        out.add("hi");
        out.add("bye");

        assertArrayEquals(new String[]{"ok", "hi", "bye"}, out.toArray());
    }

    @Test
    void toStringTest() {
        out.add("ok");
        out.add("hi");
        out.add("bye");

        assertEquals("StringListImpl={ok, hi, bye}", out.toString());
    }

    @Test
    void whenThrowExceptionTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.add(3, "3"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.set(3, "3"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.remove(3));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.get(3));
        assertThrows(IllegalArgumentException.class, () -> out.remove("3"));
        assertThrows(IllegalArgumentException.class, () -> out.equals(null));
    }
}