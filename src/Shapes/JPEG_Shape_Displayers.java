package Shapes;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;

public class JPEG_Shape_Displayers {
    
    public static void main(String[] args) throws Exception {
        Color[] colors = {Color.BLACK, Color.GRAY, Color.GREEN, Color.RED, Color.BLUE}; 
        
        List<Shape> list = new ArrayList<>();
        
        // Add circles
        for (int x = 1; x <= 3; x++) {
            Circle c = new Circle(20 * x); // Standard sizes for circles
            c.setColor(colors[x - 1]);
            list.add(c);
        }
        
        // Add triangles
        for (int x = 1; x <= 3; x++) {
            Triangle t = new Triangle(20 * x, 30 * x); // Standard sizes for triangles
            t.setColor(colors[x - 1]);
            list.add(t);
        }
        
        // Add squares
        for (int x = 1; x <= 4; x++) {
            Square sq = new Square(20 * x); // Standard sizes for squares
            sq.setColor(colors[x - 1]);
            list.add(sq);
        }
        
        // Add pentagons with the same size logic
        for (int x = 1; x <= 3; x++) {
            JGSHAPE hexagon = new JGSHAPE(20 * x); // Same base size as other shapes
            hexagon.setColor(Color.RED);
            list.add(hexagon);
        }

        ShowImages(list); // Show the images
    }
    public static void ShowImages(List<Shape> list) throws Exception {
        JFrame frame = new JFrame("Display Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 5));

        // Randomly select 10 shapes
        Random rand = new Random();
        List<Integer> selectedIndices = new ArrayList<>();
        
        while (selectedIndices.size() < 10 && selectedIndices.size() < list.size()) {
            int randomIndex = rand.nextInt(list.size());
            if (!selectedIndices.contains(randomIndex)) {
                selectedIndices.add(randomIndex);
            }
        }

        // Load and add images for the selected shapes
        for (int index : selectedIndices) {
            File tempFile = new File("shapeTemp_" + index + ".jpg");
            Shape s = list.get(index);
            s.renderAsJpeg(tempFile);
            BufferedImage img = ImageIO.read(tempFile);

            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            panel.add(label);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}
