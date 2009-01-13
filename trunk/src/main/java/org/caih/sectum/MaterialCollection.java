package org.caih.sectum;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains a collection of materials with some utility methods.
 *
 * @author César Izurieta
 */
public abstract class MaterialCollection implements Cloneable {

    private List<Material> materials = new LinkedList<Material>();

    private int sufix = 1;

    public String unit;

    public MaterialCollection(String unit) {
        this.unit = unit;
    }

    /**
     * Get the value of availableMaterial
     *
     * @return the value of availableMaterial
     */
    public Collection<Material> getMaterials() {
        return this.materials;
    }

    public void addMaterial(Material material) {
        if (material.getArea() == 0) {
            return;
        } else if (material.getArea() < 0) {
            throw new Error("Invalid material " + material);
        }

        if (material.name.equals("")) {
            material.name = String.valueOf(this.materials.size() + 1);
        }

        this.materials.add(material);
    }

    public void removeMaterial(Material material) {
        materials.remove(material);
    }

    public CutResult cutVertically(Material material, Material required, int num) {
        Material left1 = new Material(required.width, material.height - required.height, material.type, material.name);
        Material left2 = new Material(material.width - required.width, material.height, material.type, material.name);

        left1.setSuffix(sufix++);
        left2.setSuffix(sufix++);

        this.removeMaterial(material);
        this.addMaterial(left1);
        this.addMaterial(left2);

        return new CutResult(num, material, required, left1, left2);
    }

    public Material getFirst(Material required) throws NoMaterialException {
        for (Material material : this.materials) {
            if (required == null || required.fitsInto(material)) {
                return material;
            }
        }

        throw new NoMaterialException();
    }

    public Material getLargest() throws NoMaterialException {
        return getLargest(null);
    }

    public Material getLargest(Material required) throws NoMaterialException {
        this.sort(true);

        return getFirst(required);
    }

    public Material getSmallest() throws NoMaterialException {
        return getSmallest(null);
    }

    public Material getSmallest(Material required) throws NoMaterialException {
        this.sort(false);

        return getFirst(required);
    }

    @Override
    protected Object clone() {
        try {
            MaterialCollection clone = (MaterialCollection) super.clone();

            clone.materials = new LinkedList<Material>();
            for (Material material : this.materials) {
                clone.addMaterial((Material) material.clone());
            }

            return clone;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MaterialCollection.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public void sort(boolean reverse) {
        Collections.sort(this.materials);
        if (reverse) {
            Collections.reverse(this.materials);
        }
    }

    public class CutResult {

        public int num = 0;
        public Material original;
        public Material result;
        public Material left1;
        public Material left2;

        private CutResult(int num, Material original, Material result, Material left1, Material left2) {
            this.num = num;
            this.original = original;
            this.result = result;
            this.left1 = left1;
            this.left2 = left2;
        }
        
    }
}
