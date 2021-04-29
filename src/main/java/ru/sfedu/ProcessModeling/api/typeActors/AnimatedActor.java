package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimatedActor extends RectangleActor {
    BufferedImage[] images;
    int numberFrame = 0;

    public AnimatedActor(){
        super();
    };

    public void createAnimatedActor(Simulation simulation, float x, float y, float weight, String filePath) throws IOException {
        this.processing = simulation;
        this.x = x;
        this.y = y;
        this.weight = weight;
        readAllImages(filePath);
        this.width = images[0].getWidth();
        this.height = images[0].getHeight();
        this.points = getPoints(width, height);
        resolveRect();
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
        graphics2D.drawImage(images[numberFrame], (int)-(centerX), (int)-(centerY), null);
        numberFrame++;
        if(numberFrame == images.length)
            numberFrame = 0;
        graphics2D.rotate(-rotation);
        graphics2D.setColor(Color.magenta);
        for (int i = 0; i<points[0].length; i++){
            graphics2D.fillOval((int)points[0][i], (int)points[1][i], 4,4);
        }
        graphics2D.translate(-(centerX+x), -(centerY + y));
    }
}
