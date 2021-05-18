package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EllipseGraphicActor extends CircleActor{

    BufferedImage image;

    public EllipseGraphicActor(Simulation processing, float x, float y, BufferedImage image) {
        super(processing, x, y, image.getWidth(), image.getHeight());
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.translate(centerX + x, centerY + y);
        graphics2D.rotate(rotation);
        //rotate(0.01f);
        graphics2D.drawImage(image, (int)-(centerX), (int)-(centerY), null);
        graphics2D.rotate(-rotation);
        graphics2D.setColor(Color.magenta);
        for (int i = 0; i<points[0].length; i++){
            //graphics2D.fillOval((int)points[0][i], (int)points[1][i], 4,4);
        }
        graphics2D.translate(-(centerX+x), -(centerY + y));
    }
}
