package org.caih.sectum;

import junit.framework.TestCase;

/**
 * Tests the Material class.
 *
 * @author cesar
 */
public class MaterialTest extends TestCase {

    public MaterialTest(String testName) {
        super(testName);
    }

    /**
     * Test of rotate method, of class Material.
     */
    public void testRotate() {
        assertEquals(new Material(2, 1), new Material(1, 2).rotate());
        assertEquals(new Material(1, 2), new Material(2, 1).rotate());
    }

    /**
     * Test of makeHorizontal method, of class Material.
     */
    public void testMakeHorizontal() {
        assertEquals(new Material(2, 1), new Material(1, 2).horizontal());
        assertEquals(new Material(2, 1), new Material(2, 1).horizontal());
    }

    /**
     * Test of makeVertical method, of class Material.
     */
    public void testMakeVertical() {
        assertEquals(new Material(1, 2), new Material(1, 2).vertical());
        assertEquals(new Material(1, 2), new Material(2, 1).vertical());
    }

    /**
     * Test of getArea method, of class Material.
     */
    public void testGetArea() {
        assertEquals(2.0, new Material(1, 2).getArea());
    }

    /**
     * Test of fitsInto method, of class Material.
     */
    public void testFitsInto() {
        assertEquals(true, new Material(1, 2).fitsInto(new Material(2, 1)));
        assertEquals(true, new Material(1, 2).fitsInto(new Material(1, 2)));
        assertEquals(false, new Material(2, 3).fitsInto(new Material(2, 1)));
        assertEquals(false, new Material(2, 3).fitsInto(new Material(1, 2)));
    }

    /**
     * Test of compareTo method, of class Material.
     */
    public void testCompareTo() {
        assertTrue(new Material(1, 2).compareTo(new Material(1, 2)) == 0);
        assertTrue(new Material(1, 2).compareTo(new Material(2, 2)) < 0);
        assertTrue(new Material(2, 2).compareTo(new Material(1, 2)) > 0);
    }

    /**
     * Test of clone method, of class Material.
     */
    public void testClone() {
        assertEquals(new Material(1, 2), new Material(1, 2).clone());
    }
}
