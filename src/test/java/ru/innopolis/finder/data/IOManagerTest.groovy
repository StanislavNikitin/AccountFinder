package ru.innopolis.finder.data
/**
 * Created by user on 21.07.16.
 */
class IOManagerTest extends GroovyTestCase {

    void testCheckData() {
        IOManager im = new IOManager();

        boolean expected = false;
        boolean actual = im.checkData("", "");
        assertEquals(expected, actual);

        expected = false;
        actual = im.checkData("ewasdsade", "");
        assertEquals(expected, actual);

        expected = false;
        actual = im.checkData("", "asdsasdad");
        assertEquals(expected, actual);

        expected = true;
        actual = im.checkData("asdasd", "asdsad");
        assertEquals(expected, actual);

    }
}