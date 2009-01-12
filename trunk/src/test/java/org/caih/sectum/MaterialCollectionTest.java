package org.caih.sectum;

import junit.framework.TestCase;

/**
 * Tests the MaterialCollection class.
 *
 * @author César Izurieta
 */
public class MaterialCollectionTest extends TestCase {

    private MaterialCollection materials;

    public MaterialCollectionTest(String testName) {
        super(testName);

    }

    @Override
    protected void setUp() throws Exception {
        materials = new MaterialCollection("m") {
        };

        materials.addMaterial(new Material(2, 2));
        materials.addMaterial(new Material(1, 2));
        materials.addMaterial(new Material(2, 3));
        materials.addMaterial(new Material(3, 4));
        materials.addMaterial(new Material(4, 5));
    }

    /**
     * Test of cutHorizontal method, of class MaterialCollection.
     */
    public void testCutHorizontal() {
    }

    /**
     * Test of getFirst method, of class MaterialCollection.
     */
    public void testGetFirst() throws Exception {
        assertEquals(materials.getFirst(null), new Material(2, 2));
        assertEquals(materials.getFirst(new Material(2, 2.5)), new Material(2, 3));
        assertEquals(materials.getFirst(new Material(2, 3.5)), new Material(3, 4));
    }

    /**
     * Test of getLargest method, of class MaterialCollection.
     */
    public void testGetLargest() throws Exception {
        assertEquals(materials.getLargest(null), new Material(4, 5));
        assertEquals(materials.getLargest(new Material(2, 1.5)), new Material(4, 5));
    }

    /**
     * Test of getSmallest method, of class MaterialCollection.
     */
    public void testGetSmallest() throws Exception {
        assertEquals(materials.getSmallest(null), new Material(1, 2));
        assertEquals(materials.getSmallest(new Material(2, 1.5)), new Material(2, 2));
        assertEquals(materials.getSmallest(new Material(2, 2)), new Material(2, 2));
        assertEquals(materials.getSmallest(new Material(2, 3)), new Material(2, 3));
    }
}
