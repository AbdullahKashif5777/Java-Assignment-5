import javax.swing.*;
import java.awt.*;

class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Color darkGray = new Color(30, 30, 30);
        Color mediumDarkGray = new Color(70, 70, 70);
        Color lightDarkGray = new Color(110, 110, 110);

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradientPaint = new GradientPaint(0, 0, darkGray, width / 2, height / 2, mediumDarkGray, true);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);

        gradientPaint = new GradientPaint(width / 2, height / 2, mediumDarkGray, width, height, lightDarkGray, true);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
    }
}