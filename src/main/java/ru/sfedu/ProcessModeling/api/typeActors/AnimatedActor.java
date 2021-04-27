package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimatedActor extends RectangleActor {
    BufferedImage[] images;

    public AnimatedActor(Simulation processing, float x, float y, int width, int height, String path) throws IOException {
        super(processing, x, y, width, height);
    }

    private BufferedImage[] readAllImages(String path) throws IOException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        images = new BufferedImage[listOfFiles.length];
        int i = 0;
        for (File file: listOfFiles)
            if(file.isFile()) {
                images[i] = ImageIO.read(file);
                i++;
            }
        return images;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.translate(centerX + x, centerY + y);
        graphics2D.rotate(rotation);
        rotate(0.01f);
        graphics2D.drawImage(images[0], (int)-(centerX), (int)-(centerY), null);
        graphics2D.rotate(-rotation);
        graphics2D.setColor(Color.magenta);
        for (int i = 0; i<points[0].length; i++){
            graphics2D.fillOval((int)points[0][i], (int)points[1][i], 4,4);
        }
        graphics2D.translate(-(centerX+x), -(centerY + y));
    }
}
