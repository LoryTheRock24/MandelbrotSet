package me.lory24;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MandelbrotSet extends JComponent {

    public static final int WIDTH = 30000;
    public static final int HEIGHT = 30000;
    public static final int ITERATIONS = 1050;
    private final BufferedImage buffer;

    public MandelbrotSet() throws IOException {
        System.out.println("Generating...");
        buffer = new BufferedImage(WIDTH,
                HEIGHT, BufferedImage.TYPE_INT_RGB);
        render();

        System.out.println("Generated! Saving in file...");
        File file = new File("D:\\FilesVari\\mandelbrot.jpg");
        ImageIO.write(buffer, "jpg", file);
        System.out.println("Saved!");

//        JFrame frame = new JFrame("Mandelbrot Set");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(true);
//        frame.getContentPane().add(this);
//        frame.pack();
//        frame.setVisible(true);
    }

    @Override
    public void addNotify() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }

    public void render() {
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++) { int color = calcColor(x, y); buffer.setRGB(x, y, color); }
        }
    }

    public int calcColor(int x, int y){
        ComplexNumber number = ComplexNumber.convertToComplex(x, y, WIDTH, HEIGHT), z = number;
        int i = 0;
        for (; i < ITERATIONS; i++){
            z = z.powComplex(z).addition(number);
            if (z.abs() > 2.0) break;
        }
        if (i == ITERATIONS) return 0;
        return Color.HSBtoRGB(((float) i / ITERATIONS + 0f)%1, 0.5f, 1);
        //return i | (i << 18);
    }

    public static void main(String[] args) throws IOException {
        new MandelbrotSet();
    }
}
