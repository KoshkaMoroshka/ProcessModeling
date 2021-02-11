package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;
import javax.swing.*;
import java.awt.*;

public class ProcessField extends JPanel {
    private final Simulation processing;

    public ProcessField(Simulation processing){
        this.processing = processing;
    }
    int i = 0;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        processing.width = (float) g.getClipBounds().getWidth();
        processing.height = (float) g.getClipBounds().getHeight();

        if(processing.rectangleActor2.collision(processing.rectangleActor3) || processing.rectangleActor3.collision(processing.rectangleActor2)){
            //processing.actors.clear();
            processing.rectangleActor2.color = Color.RED;
            i += 1;
            System.out.println("poo");
            //processing.rectangleActor2.setSpeedX(processing.rectangleActor2.getSpeedX()*-1);
            //processing.rectangleActor2.setSpeedY(processing.rectangleActor2.getSpeedY()*-1);
           // return;
        }
        for(Actor actor : processing.actors)
            actor.draw(g);
    }
}
