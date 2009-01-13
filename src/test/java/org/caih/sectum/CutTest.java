package org.caih.sectum;

import junit.framework.TestCase;

/**
, Simple test class.
 *
, @author César Izurieta
 */
public class CutTest extends TestCase {

    public void testCut() throws NoMaterialException {
        Warehouse warehouse = new Warehouse("cm");
        Order order = new Order("cm");

        warehouse.addMaterial(new Material(90.0, 100.0, "", "M1"));
        warehouse.addMaterial(new Material(90.0, 100.0, "", "M2"));
        warehouse.addMaterial(new Material(90.0, 100.0, "", "M3"));
        warehouse.addMaterial(new Material(90.0, 100.0, "", "M4"));

        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));
        order.addMaterial(new Material(10.0, 90.0));

        /*
        warehouse.addMaterial(new Material(214.0, 330.0, "", "X1"));
        warehouse.addMaterial(new Material(214.0, 330.0, "", "X2"));
        warehouse.addMaterial(new Material(214.0, 330.0, "", "X3"));
        warehouse.addMaterial(new Material(214.0, 330.0, "", "X4"));
        warehouse.addMaterial(new Material(214.0, 330.0, "", "X5"));

        order.addMaterial(new Material(55.5, 65.4));
        order.addMaterial(new Material(55.4, 65.3));
        order.addMaterial(new Material(33.5, 55.4));
        order.addMaterial(new Material(33.5, 55.4));
        order.addMaterial(new Material(33.5, 55.4));
        order.addMaterial(new Material(63.6, 41.3));
        order.addMaterial(new Material(53.5, 63.4));
        order.addMaterial(new Material(63.3, 41.7));
        order.addMaterial(new Material(71.7, 39.5));
        order.addMaterial(new Material(35, 41.5));
        order.addMaterial(new Material(41.7, 62.3));
        order.addMaterial(new Material(41.7, 62.3));
        order.addMaterial(new Material(39.7, 59.7));
        order.addMaterial(new Material(41.7, 35));
        order.addMaterial(new Material(35, 41.7));
        order.addMaterial(new Material(35, 41.5));
        order.addMaterial(new Material(35, 41.5));
        order.addMaterial(new Material(35, 41.5));
        */

        Cutter cutter = new SimpleGlassCutter();

        cutter.cut(warehouse, order);

        warehouse.sort(true);
        System.out.println("Left:");
        for (Material material : warehouse.getMaterials()) {
            System.out.println(material);
        }
    }
}
