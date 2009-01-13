package org.caih.sectum;

/**
 * Defines a cutter. A cutter should cut all pieces from the order but may throw
 * a NoMaterialException if any of the pieces cannot be cutted.
 *
 * @author César Izurieta
 */
public interface Cutter {

    public void cut(Warehouse warehouse, Order order) throws NoMaterialException;

}
