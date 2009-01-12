package org.caih.sectum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * A simple algorithm to cut glass:
 *
 * <ol>
 *   <li>Get the largest piece of material from the order.</li>
 *   <li>Get the smallest piece of material from the warehouse that can contain the ordered piece.</li>
 *   <li>Cut vertically.</li>
 *   <li>Repeat the process.</li>
 * </ol>
 *
 * @author César Izurieta
 */
public class SimpleGlassCutter implements Cutter {

    public void cut(Warehouse warehouse, Order order) throws NoMaterialException {
        order = (Order) order.clone();
        int i = 0;

        while (!order.getMaterials().isEmpty()) {
            Material required = order.getLargest();
            Material available = warehouse.getSmallest(required);

            available = available.horizontal();
            required = required.vertical();

            if (required.height > available.height ||
                    required.width > available.width) {
                required = required.horizontal();
            }

            MaterialCollection.CutResult cutResult = warehouse.cutVertically(available, required, ++i);
            RenderedImage im = this.draw(cutResult, 640, warehouse.unit);

            try {
                File output = new File("result-" + cutResult.num + ".png");

                ImageIO.write(im, "png", output);
            } catch (IOException ex) {
                Logger.getLogger(SimpleGlassCutter.class.getName()).log(Level.SEVERE, null, ex);
            }


            order.removeMaterial(required);
        }
    }

    public RenderedImage draw(MaterialCollection.CutResult cutResult, int w, String unit) {
        DecimalFormat df = new DecimalFormat("0.00 " + unit);
        double scale = w / cutResult.original.width;
        int h = (int) (cutResult.original.height * scale);
        int cx = (int) (cutResult.result.width * scale);
        int cy = (int) (cutResult.result.height * scale);

        BufferedImage result = new BufferedImage(w + 1 + 60, 100 + h + 1 + 60, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = (Graphics2D) result.getGraphics();
        g.fillRect(0, 0, result.getWidth(), result.getHeight());
        g.setColor(Color.BLACK);

        g.setFont(new Font("Sans serif", Font.BOLD, 25));
        g.drawString("[#" + cutResult.num + "] Piece " + cutResult.result.name, 10, 30);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.drawString(cutResult.result.toString(), 10, 50);

        g.translate(300, 10);
        g.drawRect(0, 0, 80, 80);
        g.drawRect(0, 0, 40, 40);
        g.drawRect(0, 0, 40, 80);
        g.drawLine(0, 0, 40, 40);
        g.drawLine(0, 40, 40, 0);
        g.drawString("A", 17, 65);
        g.drawString("B", 57, 45);
        g.translate(100, 0);
        g.drawString("Mat: " + cutResult.original.toString(), 0, 10);
        g.drawString("A:   " + cutResult.left1.toString(), 0, 25);
        g.drawString("B:   " + cutResult.left2.toString(), 0, 40);
        g.translate(-400, -10);

        g.translate(0, 100);

        g.drawLine(25, 15, cx + 35, 15);
        g.drawLine(30, 10, 30, 20);
        g.drawLine(cx + 30, 10, cx + 30, 20);
        drawStringCenter(g, df.format(cutResult.result.width), (25 + cx + 35) / 2, 10);

        g.drawLine(15, 25, 15, cy + 35);
        g.drawLine(10, 30, 20, 30);
        g.drawLine(10, cy + 30, 20, cy + 30);
        drawStringVerticalCenter(g, df.format(cutResult.result.height), 10, (25 + cy + 35) / 2);

        g.drawLine(25, h + 50, w + 35, h + 50);
        g.drawLine(30, h + 45, 30, h + 55);
        g.drawLine(w + 30, h + 45, w + 30, h + 55);
        drawStringCenter(g, df.format(cutResult.original.width), (25 + w + 35) / 2, h + 45);

        g.drawLine(w + 50, 25, w + 50, h + 35);
        g.drawLine(w + 45, 30, w + 55, 30);
        g.drawLine(w + 45, h + 30, w + 55, h + 30);
        drawStringVerticalCenter(g, df.format(cutResult.original.height), w + 45, (25 + h + 35) / 2);

        g.translate(30, 30);

        g.drawRect(0, 0, w, h);
        g.drawRect(0, 0, cx, cy);
        g.drawRect(0, 0, cx, h);
        g.drawLine(0, 0, cx, cy);
        g.drawLine(0, cy, cx, 0);

        return result;
    }

    public void drawStringCenter(Graphics2D g, String string, int x, int y) {
        TextLayout tl = new TextLayout(string, g.getFont(), g.getFontRenderContext());
        tl.draw(g, (float) (x - tl.getBounds().getWidth() / 2), y);
    }

    public void drawStringVerticalCenter(Graphics2D g, String string, int x, int y) {
        g.rotate(3 * Math.PI / 2);
        drawStringCenter(g, string, -y, x);
        g.rotate(Math.PI / 2);
    }
}
