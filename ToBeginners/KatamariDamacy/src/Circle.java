import java.awt.Color;
import java.awt.Graphics;

/**
 * Java. Katamari Damacy Game
 *  Class Circle: base class
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Oct 16, 2018
 */

public class Circle {
    protected int x, y;
    protected int size;
    protected int direction;
    protected Color color;

    public Circle(int x, int y, int size, int direction, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = direction;
        this.color = color;
    }

    public int getX() {
        return x + size / 2;
    }

    public int getY() {
        return y + size / 2;
    }

    public int getSize() {
        return size;
    }

    public int getRadius() {
        return size / 2;
    }

    public boolean isCrossing(Circle circle) {
        return getRadius() + circle.getRadius() >= Math.sqrt(
                Math.pow(getX() - circle.getX(), 2) +
                Math.pow(getY() - circle.getY(), 2));
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.drawOval(x, y, size, size);
    }
}
