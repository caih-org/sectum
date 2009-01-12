package org.caih.sectum;

import java.text.DecimalFormat;

/**
 * This class represents a piece of material.
 *
 * @author César Izurieta
 */
public class Material implements Cloneable, Comparable<Material> {

    public double width = 0;
    public double height = 0;
    public String type = "";
    public String name = "";

    public Material(double width, double height) {
        this(width, height, "", "");
    }

    public Material(double width, double height, String tipo, String name) {
        this.width = width;
        this.height = height;
        this.type = tipo;
        this.name = name;
    }

    public Material rotate() {
        return new Material(this.height, this.width, this.type, this.name);
    }

    public Material horizontal() {
        return this.isVertical() ? this.rotate() : this;
    }

    public Material vertical() {
        return this.isHorizontal() ? this.rotate() : this;
    }

    public boolean isHorizontal() {
        return this.width > this.height;
    }

    public boolean isVertical() {
        return this.width < this.height;
    }

    public Double getArea() {
        return this.width * this.height;
    }

    public boolean fitsInto(Material material) {
        return (this.height <= material.height && this.width <= material.width) || (this.height <= material.width && this.width <= material.height);
    }

    void setSuffix(int suffix) {
        if (this.name.matches(".*-\\d+")) {
            this.name = this.name.replaceAll("-\\d+$", "");
        }

        this.name = this.name + "-" + suffix;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String w = df.format(this.width);
        String h = df.format(this.height);

        while (w.length() < 7) {
            w = " " + w;
        }

        while (h.length() < 7) {
            h = " " + h;
        }

        return w + " x " + h + " [" + this.name + "]";
    }

    public int compareTo(Material material) {
        return this.getArea().compareTo(material.getArea());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Material) {
            Material material = (Material) obj;
            return (material.height == this.height && material.width == this.width) || (material.height == this.width && material.width == this.height) && material.name.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 17 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);

        return hash;
    }

    @Override
    protected Material clone() {
        return new Material(this.width, this.height, this.type, this.name);
    }
}
